package com.olegpage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olegpage.entity.Blog;
import com.olegpage.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{

	public List<Blog> findByUser(User user);
}
