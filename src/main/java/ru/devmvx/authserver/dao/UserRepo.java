package ru.devmvx.authserver.dao;

import ru.devmvx.authserver.model.User;

public interface UserRepo {
    public User getUserByUsername(String username);
}
