/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mrt.example.security;

import com.mrt.example.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Component
public class SpringDataJpaUserDetailsService implements UserDetailsService {

	private final UserRepository repository;

	@Autowired
	public SpringDataJpaUserDetailsService(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.mrt.example.dao.User user = this.repository.findByUsername(username);
		System.out.println("************************************************");
		System.out.println(user + "///////////" + user.getPassword());
		System.out.println("************************************************");
		return new User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(new String[]{user.getRole()}));
	}

}
// end::code[]
