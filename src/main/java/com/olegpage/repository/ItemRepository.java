package com.olegpage.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.olegpage.entity.Blog;
import com.olegpage.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	
	public List<Item> findByBlog(Blog blog, Pageable pageable);

	public Item findByBlogAndLink(Blog blog, String link);
}
