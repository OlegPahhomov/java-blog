package com.olegpage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.olegpage.entity.Blog;
import com.olegpage.entity.Item;
import com.olegpage.entity.User;
import com.olegpage.exception.RssException;
import com.olegpage.repository.BlogRepository;
import com.olegpage.repository.ItemRepository;
import com.olegpage.repository.UserRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RssService rssService;
	@Autowired
	private ItemRepository itemRepository;
	
	//1 hour * 60 min * 60 sec * 1000 ms
	@Scheduled(fixedDelay=3600000)
	public void reloadBlogs(){
		List<Blog> allBlogs = blogRepository.findAll();
		for (Blog blog : allBlogs) {
			saveItems(blog);
		}
	}
	
	public void saveItems(Blog blog){
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
				if (savedItem == null){
					item.setBlog(blog);
					itemRepository.save(item);
				}
			}
		} catch (RssException e) {
			// TODO
			e.printStackTrace();
		}
	}
	
	public void save(Blog blog, String userName) {
		User user = userRepository.findByName(userName);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
		
	}

}
