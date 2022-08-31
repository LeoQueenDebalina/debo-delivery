package com.ecommerce.app.debodelivery.repository;


import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("select case when count(u)>0 then true else false end from User u where u.mobileNumber = :n")
    public boolean ifNumberIsExist(@Param("n") String number);

    @Query("select u.userId from User u where u.mobileNumber = :n")
    public String getUserIdByNumber(@Param("n") String number);

    @Query("select u.isDeleted from User u where u.mobileNumber = :n")
    public Boolean getUserIsDeletedByNumber(@Param("n") String number);
//    @Query("select u from User u where u.mobileNumber = :n")
//    public User findByUserName(@Param("n") String number);

    @Transactional
    @Modifying
    @Query("update User u set u.isDeleted = true, u.mobileNumber = concat(u.mobileNumber,'-deleted') where u.mobileNumber = :n")
    public Integer deleteAccount(@Param("n") String number);
}
