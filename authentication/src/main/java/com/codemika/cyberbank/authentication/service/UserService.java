package com.codemika.cyberbank.authentication.service;

import com.codemika.cyberbank.authentication.dto.RqCreateUser;
import com.codemika.cyberbank.authentication.entity.UserEntity;
import com.codemika.cyberbank.authentication.repository.UserRepository;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
public class UserService {
    private final UserRepository userRepository;
    public ResponseEntity<?> registration (String name, String surname,String patronymic,String phone, String password) {
        UserEntity newUser = new UserEntity()
                .setName(name)
                .setSurname(surname)
                .setPatronymic(patronymic)
                .setPhone(phone)
                .setPassword(password);
        UserEntity user = userRepository.save(newUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(user);

    }
}
