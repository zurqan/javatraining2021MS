package com.trinaing.rdsdb.adapter.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoryJPASpringImpl extends JpaRepository<UserEntity,Long> {

    Page<UserEntity>  findByName(String name, Pageable pageable);
}
