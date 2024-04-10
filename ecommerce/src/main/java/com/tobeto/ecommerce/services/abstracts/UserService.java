package com.tobeto.ecommerce.services.abstracts;

import com.tobeto.ecommerce.services.dtos.requests.user.AddUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.DeleteUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.GetByIdUserRequest;
import com.tobeto.ecommerce.services.dtos.requests.user.UpdateUserRequest;
import com.tobeto.ecommerce.services.dtos.responses.user.*;

import java.util.List;

public interface UserService {

    AddUserResponse add(AddUserRequest request);

    UpdateUserResponse update(UpdateUserRequest request);

    DeleteUserResponse delete(DeleteUserRequest request);

    List<ListUserResponse> getAll();

    GetByIdUserResponse getById(GetByIdUserRequest request);
}
