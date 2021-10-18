package com.example.guestservices.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.guestservices.Entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {

}
