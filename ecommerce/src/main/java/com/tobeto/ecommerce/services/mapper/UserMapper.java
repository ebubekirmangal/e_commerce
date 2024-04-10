package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.User;
import com.tobeto.ecommerce.services.dtos.requests.user.AddUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.UpdateUserRequest;
import com.tobeto.ecommerce.services.dtos.responses.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userFromAddUserRequest(AddUserRequest request);

    AddUserResponse AddResponseToUser(User user);

    List<ListUserResponse> usersFromListUserResponse(List<User> users);

    GetByIdUserResponse GetByIdResponseToUser(User user);

    DeleteUserResponse DeleteResponseToUser(User user);

    User userFromUpdateUserRequest(UpdateUserRequest request);

    UpdateUserResponse UptadeResponseToUser(User user);

}
