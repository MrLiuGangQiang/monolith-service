package org.monolith.web.system.service;

import java.util.List;
import java.util.Map;

import org.monolith.web.system.entity.User;
import org.monolith.web.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserMapper mapper;

	public List<Map<String, Object>> getList(User user) {
		return mapper.getList(user);
	}
}
