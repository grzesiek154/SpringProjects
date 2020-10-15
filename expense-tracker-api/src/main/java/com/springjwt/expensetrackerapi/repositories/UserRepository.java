package com.springjwt.expensetrackerapi.repositories;

import com.springjwt.expensetrackerapi.domain.User;
import com.springjwt.expensetrackerapi.exceptions.EtAuthException;

public interface UserRepository {

    Integer create(String firstName, String lastName, String email, String password) throws EtAuthException;

    User findByEyEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    User findById(Integer userId);
}
