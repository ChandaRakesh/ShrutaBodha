package com.shrutabodha.backend.controller;

import com.shrutabodha.backend.dto.CreateUserRequest;
import com.shrutabodha.backend.dto.UserResponse;
import com.shrutabodha.backend.entity.User;
import com.shrutabodha.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
//@Controller vs @RestController>@Controller returns HTML views so it is used for server side rendered web applications
//@RestController is used for REST APIs data is converted to json response.
//spring converts the object to json using jackson.
//internally @RestController(so its a shortcut annotation) == @Controller and @ResponseBody combo

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request){
        return userService.createUser(request);
    }
    @GetMapping
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }


}
