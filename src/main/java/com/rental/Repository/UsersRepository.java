package com.rental.Repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rental.Models.Users.UsersModels;

@Repository
public interface UsersRepository extends JpaRepository<UsersModels, BigInteger> {

}
