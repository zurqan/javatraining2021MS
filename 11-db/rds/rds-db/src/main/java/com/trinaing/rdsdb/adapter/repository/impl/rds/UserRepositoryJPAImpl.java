package com.trinaing.rdsdb.adapter.repository.impl.rds;

import com.trinaing.rdsdb.adapter.repository.UserRepository;
import com.trinaing.rdsdb.model.Email;
import com.trinaing.rdsdb.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UserRepositoryJPAImpl implements UserRepository {

    private final UserRepositorySpringImpl userRepositoryJPASpring;

    public UserRepositoryJPAImpl(UserRepositorySpringImpl userRepositoryJPASpring) {
        this.userRepositoryJPASpring = userRepositoryJPASpring;
    }

    @Override
    public long save(User user) {
        UserEntity userEntity=toEntity(user);
        UserEntity saved = userRepositoryJPASpring.save(userEntity);
        return saved.getId();
    }

    @Override
    public Optional<User> byId(long id) {
        return userRepositoryJPASpring.findById(id).map(toModel());
    }

    @Override
    public List<User> findAll() {
        return userRepositoryJPASpring.findAll().stream().map(toModel()).collect(Collectors.toList());
    }

    @Override
    public Page<User> loadUsers(Pageable pageable) {
        return userRepositoryJPASpring.findAll(pageable).map(toModel());
    }

    @Override
    public Page<User> searchUserByName(String name, Pageable pageable) {

        return userRepositoryJPASpring
                .findByName(name,pageable)
                .map(toModel());
    }

    @Override
    public void removeUser(Long id) {
        userRepositoryJPASpring.deleteById(id);
    }

    private UserEntity toEntity(User user) {
        return UserEntity
                .builder()
                .active(user.isActive())
                .age(user.getAge())
                .city(user.getCity())
                .email(user.getEmail().getData())
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    private Function<UserEntity, User> toModel() {
        return userEntity -> User
                .builder()
                .id(userEntity.getId())
                .active(userEntity.isActive())
                .age(userEntity.getAge())
                .city(userEntity.getCity())
                .email(new Email(userEntity.getEmail()))
                .name(userEntity.getName())
                .build();
    }
}
