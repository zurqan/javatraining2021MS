package com.trinaing.rdsdb.adapter.repository;

import com.trinaing.rdsdb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    public long save(User user);

    public Optional<User> byId(long id);

    public List<User> findAll();

    public Page<User> loadUsers(Pageable pageable);

    public Page<User> searchUserByName(String name, Pageable pageable);

    public void removeUser(Long id);

}
