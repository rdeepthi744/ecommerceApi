package com.ecommerce.service;

import com.ecommerce.entity.User;

public interface UserService {
    User getUserByUsername(String username);
}
