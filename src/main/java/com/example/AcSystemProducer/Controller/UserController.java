package com.example.AcSystemProducer.Controller;

import com.example.AcSystemProducer.DTO.UserDTO;
import com.example.AcSystemProducer.DTO.UserResponse;
import com.example.AcSystemProducer.Entity.User;
import com.example.AcSystemProducer.Mapper.UserMapper;
import com.example.AcSystemProducer.Service.CompanyService;
import com.example.AcSystemProducer.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CompanyService companyService;
    private final UserMapper userMapper;

    @GetMapping("/api/users/{login}")
    public ResponseEntity<UserResponse> userInfo(@PathVariable String login){
        User user = userService.getUser(login);
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/api/createUser")
    public ResponseEntity<UserResponse> userCreate(@Valid @RequestBody UserDTO userDTO){
        User saved = userService.createUser(userDTO.getLogin(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getNameCompany(), userDTO.isCeo());
        UserResponse userResponse = userMapper.toUserResponse(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/api/users/workless")
    public ResponseEntity<List<UserResponse>> userLess(){
        if(userService.getAllUsers() != null) {
            return ResponseEntity.ok(userService.getAllUsers().stream().filter(x -> x.getCompany() == null).map(userMapper::toUserResponse).toList());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("api/users/random")
    public ResponseEntity<UserResponse> userRandom(){
        User random = userService.getRandom();
        if(random != null) {
            return ResponseEntity.ok(userMapper.toUserResponse(userService.getRandom()));

        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
