package com.srm.services.rest.dao;

import java.util.List;

import com.srm.services.entity.Users;

public interface TransactionDao {
    public List<Users> findAllUsers();
}
