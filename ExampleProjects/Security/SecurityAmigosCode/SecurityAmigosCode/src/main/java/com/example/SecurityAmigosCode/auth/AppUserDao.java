package com.example.SecurityAmigosCode.auth;

import java.util.Optional;

public interface AppUserDao {

    Optional<AppUser> selectAppUserByUserName(String username);
}
