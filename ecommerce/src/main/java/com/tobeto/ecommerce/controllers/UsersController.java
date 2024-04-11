package com.tobeto.ecommerce.controllers;

import com.tobeto.ecommerce.services.abstracts.UserService;
import com.tobeto.ecommerce.services.dtos.requests.user.AddUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.DeleteUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.GetByIdUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.UpdateUserRequest;
import com.tobeto.ecommerce.services.dtos.responses.user.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddUserResponse add(@RequestBody @Valid AddUserRequest request) {

        return  userService.add(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserResponse update(@RequestBody UpdateUserRequest request){
        return userService.update(request);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public DeleteUserResponse delete(DeleteUserRequest request){
        return userService.delete(request);
    }
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ListUserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getById")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdUserResponse getById(GetByIdUserRequest request){

        return userService.getById(request);
    }
}
