package br.com.pedrobarbosa.todolist.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel){
       var user = this.userRepository.findByuserName(userModel.getUserName());

       if(user != null){
    
        //mensagem de erro
        //status code
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USUARIOS JÁ EXISTE");
       }
       //criptgrafando a senha passada pelo usuario
       var passHashred = BCrypt.withDefaults()
       .hashToString(12, userModel.getPassword().toCharArray());

       userModel.setPassword(passHashred);


       //salvando no banco
      var userCreated = this.userRepository.save(userModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    } 
    
}
