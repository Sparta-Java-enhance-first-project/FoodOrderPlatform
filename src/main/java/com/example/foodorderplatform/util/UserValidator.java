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

    public static boolean validateIsOwner(User user){
      return ownerUpperRoles.contains(user.getRole());
    }

    public static boolean validateIsStoreOwner(Store store, User user) {
        boolean first = store.getUser().equals(user);
        boolean second = ownerUpperRoles.contains(user.getRole());
        return store.getUser().equals(user) && ownerUpperRoles.contains(user.getRole());
    }
}
