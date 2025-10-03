package com.fec.yana.authentication.repository;

import com.fec.yana.authentication.model.SecurityUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SecurityUserRepository extends CrudRepository<SecurityUser, UUID> {

    boolean existsSecurityUserByUsername(String username);

    boolean existsSecurityUserByUsernameAndPassword(String username, String password);

    Optional<SecurityUser> findSecurityUserByUsername(String username);
}
