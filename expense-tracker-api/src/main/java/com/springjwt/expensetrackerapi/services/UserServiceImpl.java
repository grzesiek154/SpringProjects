package com.springjwt.expensetrackerapi.services;

import com.springjwt.expensetrackerapi.domain.User;
import com.springjwt.expensetrackerapi.exceptions.EtAuthException;
import com.springjwt.expensetrackerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
@Transactional // means that all the methods from the class can create transaction wtih database
// if runtime exception will be thrown transaction value will be null
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        return null;
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null) email = email.toLowerCase();
        if(!pattern.matcher(email).matches())
            throw new EtAuthException("Invalid email format");
        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new EtAuthException("Email already in use");
        Integer userId = userRepository.create(firstName,lastName,email,password);

        return userRepository.findById(userId);
    }
}
