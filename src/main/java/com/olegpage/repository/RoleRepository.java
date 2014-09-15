package com.olegpage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olegpage.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	public Role findByName(String name);

}
