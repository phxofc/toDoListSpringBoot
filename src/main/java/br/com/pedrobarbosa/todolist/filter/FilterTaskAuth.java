package br.com.pedrobarbosa.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.pedrobarbosa.todolist.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")) {

            // pegar a auth (user and pass)
            var authorizarion = request.getHeader("Authorization");

            var authEncoded = authorizarion.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecode);

            String[] credentials = authString.split(":");

            String username = credentials[0];
            String password = credentials[1];

            // depois validar user
            var user = this.userRepository.findByuserName(username);
            if (user == null) {
                response.sendError(401);
            } else {

                // depois validar pass
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {

                //pegando id do user
                request.setAttribute("idUser", user.getID());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
                // segue viagem...

            }

        }else{

           filterChain.doFilter(request, response);

        }

    }

}
