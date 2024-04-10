package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.entities.User;
import com.tobeto.ecommerce.repositories.UserRepository;
import com.tobeto.ecommerce.services.abstracts.UserService;
import com.tobeto.ecommerce.services.dtos.requests.user.AddUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.DeleteUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.GetByIdUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.UpdateUserRequest;
import com.tobeto.ecommerce.services.dtos.responses.user.*;
import com.tobeto.ecommerce.services.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AddUserResponse add(AddUserRequest request) {

        User user = UserMapper.INSTANCE.userFromAddUserRequest(request);
        User saved = userRepository.save(user);

        AddUserResponse response = UserMapper.INSTANCE.AddResponseToUser(saved);

        return response;
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {

        return null;
    }

    @Override
    public DeleteUserResponse delete(DeleteUserRequest request) {
        return null;
    }

    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> findUser = userRepository.findAll();
        List<GetAllUserResponse> response = UserMapper.INSTANCE.usersFromListUserResponse(findUser);

        return response;
    }

    @Override
    public GetByIdUserResponse getById(GetByIdUserRequest request) {
        return null;
    }
}
