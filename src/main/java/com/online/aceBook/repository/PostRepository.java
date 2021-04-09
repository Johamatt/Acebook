package com.online.aceBook.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.online.aceBook.model.Post;
import com.online.aceBook.model.Profile;

public interface PostRepository extends JpaRepository<Post, Long> {
}