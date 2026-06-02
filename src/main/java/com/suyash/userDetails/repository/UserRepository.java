package com.suyash.userDetails.repository;

import com.suyash.userDetails.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo , String> {

    Optional<UserInfo> findByUserId(String userId);


}
