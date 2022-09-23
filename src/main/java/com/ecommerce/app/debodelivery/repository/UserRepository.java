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
    public boolean existsUserByMobileNumber(String mobileNumber);

    public User findByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    @Query("update User u set u.isDeleted = true, u.mobileNumber = concat(u.mobileNumber,'-deleted') where u.mobileNumber = :n")
    public Integer deleteAccount(@Param("n") String mobileNumber);
    @Transactional
    @Modifying
    @Query("update User u set u.isEmailVerified = true where u.mobileNumber = :n")
    public Integer activateAccount(@Param("n") String mobileNumber);
}
