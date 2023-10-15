package br.com.pedrobarbosa.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
   UserModel findByuserName(String username);
}
