package com.example.SecurityAmigosCode.testing;

import com.example.SecurityAmigosCode.security.AppUserPermission;
import com.example.SecurityAmigosCode.security.AppUserRole;

public class TestMain {


    public static void main(String[] args) {
        System.out.println("works");
        System.out.println(AppUserPermission.STUDENT_READ.getPermission());
        System.out.println(AppUserRole.ADMINTRAINEE.getPermissions());
        System.out.println(AppUserRole.ADMIN.getGrantedAuthorities());
    }
}
