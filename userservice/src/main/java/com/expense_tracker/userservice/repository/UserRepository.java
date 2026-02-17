package com.expense_tracker.userservice.repository;

import com.expense_tracker.userservice.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserId(String userId);
}
