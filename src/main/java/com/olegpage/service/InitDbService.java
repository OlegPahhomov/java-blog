package com.olegpage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.olegpage.entity.Blog;
import com.olegpage.entity.Role;
import com.olegpage.entity.User;
import com.olegpage.repository.BlogRepository;
import com.olegpage.repository.ItemRepository;
import com.olegpage.repository.RoleRepository;
import com.olegpage.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init (){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin= new User();
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleUser);
			roles.add(roleAdmin);
		userAdmin.setRoles(roles);
		userAdmin.setEnabled(true);
		userRepository.save(userAdmin);
		
		Blog blogJava = new Blog();
		blogJava.setName("Java blog");
		blogJava.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		blogJava.setUser(userAdmin);
		blogRepository.save(blogJava);
		
		/*Item item1 = new Item();
		item1.setBlog(blogJava);
		item1.setTitle("Java blog");
		item1.setLink("http://www.awesome.com/");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2 = new Item();
		item2.setBlog(blogJava);
		item2.setTitle("second blog");
		item2.setLink("blablablah");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);*/
	}
}
