package com.mrt.example.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//// tag::code[]
//@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);

    List<User> findByRole(String role);

}
// end::code[]
