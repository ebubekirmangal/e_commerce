package com.tobeto.ecommerce.services.mapper;

import com.tobeto.ecommerce.entities.User;
import com.tobeto.ecommerce.services.dtos.requests.user.AddUserRequest;
import com.tobeto.ecommerce.services.dtos.responses.user.AddUserResponse;
import com.tobeto.ecommerce.services.dtos.responses.user.ListUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userFromAddUserRequest(AddUserRequest request);

    AddUserResponse AddResponseToUser(User user);

    List<ListUserResponse> usersFromListUserResponse(List<User> users);
}
