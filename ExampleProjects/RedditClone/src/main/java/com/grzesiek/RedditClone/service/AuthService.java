package com.grzesiek.RedditClone.service;

import com.grzesiek.RedditClone.dto.AuthenticationResponse;
import com.grzesiek.RedditClone.dto.LoginRequest;
import com.grzesiek.RedditClone.dto.RegisterRequest;
import com.grzesiek.RedditClone.exceptions.SpringRedditException;
import com.grzesiek.RedditClone.model.NotificationEmail;
import com.grzesiek.RedditClone.model.User;
import com.grzesiek.RedditClone.model.VerificationToken;
import com.grzesiek.RedditClone.repository.UserRepo;
import com.grzesiek.RedditClone.repository.VerificationTokenRepo;
import com.grzesiek.RedditClone.security.JwtProvider;
import com.grzesiek.RedditClone.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthService {

//    Inside the AuthService class, we are mapping the RegisterRequest object to the User object and when setting the password, we are calling the encodePassword() method. This method is using the BCryptPasswordEncoder to encode our password. After that, we save the user into the database. Note that we are setting the enabled flag as false, as we want to disable the user after registration, and we only enable the user after verifying the user’s email address.

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final VerificationTokenRepo verificationTokenRepo;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);
        userRepo.save(user);

        String token = generateVerificationToken(user);
        log.info("User Registered Successfully, Sending Authentication Email");
//        We added the generateVerificationToken() method and calling that method right after we saved the user into UserRepository. Note that, we are creating a random UUID as our token, creating an object for VerificationToken, fill in the data for that object and save it into the VerificationTokenRepository. As we have the token, now its time to send an email that contains this verification token.
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                Constants.ACTIVATION_EMAIL + token));
    }

    @Transactional(readOnly = true)
    User getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return userRepo.findByUsername(principal.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("User name not found - " + principal.getUsername()));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepo.save(verificationToken);
        return token;
    }



    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getUsername());
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepo.findByToken(token);
        verificationToken.orElseThrow(() -> new SpringRedditException("Invalid token"));
        fetchUserAndEnable(verificationToken.get());
    }


    void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepo.findByUsername(username).orElseThrow(() -> new SpringRedditException("User not found with name :" + username));
        user.setEnabled(true);
        userRepo.save(user);
    }


}
