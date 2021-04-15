package com.online.aceBook.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.online.aceBook.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}