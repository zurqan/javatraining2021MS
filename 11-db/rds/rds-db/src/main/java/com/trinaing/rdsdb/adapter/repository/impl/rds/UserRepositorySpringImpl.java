package com.trinaing.rdsdb.adapter.repository.impl.rds;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface UserRepositorySpringImpl extends JpaRepository<UserEntity,Long> {

    Page<UserEntity>  findByName(String name, Pageable pageable);

    List<UserEntity> findByNameOrderByNameAsc(String name);

    List<UserEntity> findByNameIgnoreCaseOrderByNameAsc(String name);


    List<UserEntity> findByNameAndAgeOrderByNameAsc(String name,int age);

    List<UserEntity> findByNameContaining(String subStr);

    List<UserEntity> findByNameStartsWith(String prefix);
    List<UserEntity> findByNameEndsWith(String suffix);

    @Transactional
    Stream<UserEntity> findByAgeGreaterThan(int age);

    List<UserEntity> findByNameOrAgeOrderByNameDesc(String name, int age);

    List<UserEntity> findFirst2ByName(String name);

    List<UserEntity> findByAgeIn(Collection<Integer> ageCollection);
    List<UserEntity> findByAgeBetween(int first,int second);

    List<UserEntity> findByActiveTrue();
    List<UserEntity> findByActiveFalse();

    @Query("select u from UserEntity u where u.email=?1") //<-- JPA query
    //select * from user_entity where user_entity.email = ?1  <--- native
    Optional<UserEntity>  searchByEmail(String email);

    @Query("select u from UserEntity u where u.name=:name and u.email=:email") //<-- JPA query
    List<UserEntity> searchBy(@Param("name")String name,@Param("email") String email);

    @Query("select u from #{#entityName} u where u.name=?1 and u.email=?2") //<-- JPA query
    <T> List<T> searchBySPEL(String name,String email);


    List<UserName> findByAgeLessThan(int age);//closed projection

    List<UserNameAndAge> findByEmailContaining(String sub);//closed projection

    List<UserNameAndAgeV2> findByEmailEndsWith(String suffix);//open projection

    @Query(value = "select * from user_entity u where u.city='Irbid'",nativeQuery = true)
    List<UserEntity> doNative();

    @Query(value = "select * from user_entity u where u.city=?1",nativeQuery = true)
    List<UserEntity> doNative(String city);




    @Query("select new com.trinaing.rdsdb.adapter.repository.impl.rds.SubUser(u.name,u.age) from UserEntity u where u.name=:name and u.email=:email") //<-- JPA query
    List<SubUser> searchByV3(@Param("name")String name,@Param("email") String email);


    @Query("select new com.trinaing.rdsdb.adapter.repository.impl.rds.ActiveStatistic(u.active,count(u)) from " +
            "UserEntity u group by u.active") //<-- JPA query
    List<ActiveStatistic> groupActive();






}
