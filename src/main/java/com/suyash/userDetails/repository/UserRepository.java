package com.suyash.userDetails.repository;

import com.suyash.userDetails.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo , String> {

    UserInfo findByUserId(String userId);
}
