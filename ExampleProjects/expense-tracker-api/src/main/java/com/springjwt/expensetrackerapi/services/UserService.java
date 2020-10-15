package com.springjwt.expensetrackerapi.services;

import com.springjwt.expensetrackerapi.domain.User;
import com.springjwt.expensetrackerapi.exceptions.EtAuthException;

public interface UserService {

    User validateUser(String email, String password) throws EtAuthException;
    User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;
}
