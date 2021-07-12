package com.trinaing.rdsdb.adapter.repository.impl.inmemory;

import com.trinaing.rdsdb.adapter.repository.UserRepository;
import com.trinaing.rdsdb.model.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Profile("in-memory")
public class InMemoryUserRepo implements UserRepository {

    Map<Long,User> users =new ConcurrentHashMap<>();
    AtomicLong idSeq=new AtomicLong();

    @Override
    public long save(User user) {
        User newUser = user.toBuilder().id(idSeq.incrementAndGet()).build();
        users.put(user.getId(),user);
        return newUser.getId();
    }

    @Override
    public Optional<User> byId(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Page<User> loadUsers(Pageable pageable) {
        return null;
    }

    @Override
    public Page<User> searchUserByName(String name, Pageable pageable) {
        return null;
    }

    @Override
    public void removeUser(Long id) {
        users.remove(id);
    }
}
