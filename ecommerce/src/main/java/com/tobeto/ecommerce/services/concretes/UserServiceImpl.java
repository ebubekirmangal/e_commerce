package com.tobeto.ecommerce.services.concretes;

import com.tobeto.ecommerce.core.utils.exceptions.types.BusinessException;
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
import java.util.Optional;

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

        User user = UserMapper.INSTANCE.userFromUpdateUserRequest(request);
        User updated = userRepository.save(user);

        UpdateUserResponse response = UserMapper.INSTANCE.UptadeResponseToUser(updated);

        return response;
    }

    @Override
    public DeleteUserResponse delete(DeleteUserRequest request) {

        User userBeDelete = userRepository.findById(request.getId()).orElseThrow(()-> new BusinessException("Bu id'de tanımlı kullanıcı bulunamadı."));

        userRepository.delete(userBeDelete);

        DeleteUserResponse response = UserMapper.INSTANCE.DeleteResponseToUser(userBeDelete);
        return response;
    }

    @Override
    public List<ListUserResponse> getAll() {
        List<User> findUser = userRepository.findAll();
        List<ListUserResponse> response = UserMapper.INSTANCE.usersFromListUserResponse(findUser);

        return response;
    }

    @Override
    public GetByIdUserResponse getById(GetByIdUserRequest request) {
        User findUser = userRepository.findById(request.getId()).orElseThrow(() -> new BusinessException("id'e tanımlı kullanıcı bulunamadı."));

        GetByIdUserResponse response = UserMapper.INSTANCE.GetByIdResponseToUser(findUser);


        return response;
    }
}
