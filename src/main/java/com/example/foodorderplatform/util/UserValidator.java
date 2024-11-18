package com.example.foodorderplatform.util;

import static com.example.foodorderplatform.enumclass.UserRoleEnum.*;

import com.example.foodorderplatform.entity.Store;
import com.example.foodorderplatform.entity.User;
import com.example.foodorderplatform.enumclass.UserRoleEnum;
import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    private static final List<UserRoleEnum> userUpperRoles = new ArrayList<>(List.of(USER,OWNER,MANAGER, ADMIN));
    private static final List<UserRoleEnum>  ownerUpperRoles = new ArrayList<>(List.of(OWNER, MANAGER, ADMIN));
    private static final List<UserRoleEnum>  managerUpperRoles = new ArrayList<>(List.of(MANAGER, ADMIN));
    private static final List<UserRoleEnum>  adminUpperRoles = new ArrayList<>(List.of(ADMIN));

    // 가게 주인 검증
    public static boolean validateIsStoreOwner(Store store, User user) {
        return store.getUser().equals(user);
    }

    // role이 owner 이상인지 검증
    public static boolean validateRoleUpperOwner(User user){
        return ownerUpperRoles.contains(user.getRole());
    }

    // role이 manager 이상인지 검증
    public static boolean validateRoleUpperManager(User user){
        return managerUpperRoles.contains(user.getRole());
    }

    // role이 admin 이상인지 검증
    public static boolean validateRoleUpperAdmin(User user){
        return adminUpperRoles.contains(user.getRole());
    }
}
