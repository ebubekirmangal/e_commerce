package com.tobeto.ecommerce.repositories;

import com.tobeto.ecommerce.entities.User;
import com.tobeto.ecommerce.services.dtos.responses.user.GetAllUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("SELECT u.id FROM User u")
    List<GetAllUserId> findAllUserIds();
}
