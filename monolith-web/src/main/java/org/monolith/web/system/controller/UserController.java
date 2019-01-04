package org.monolith.web.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.monolith.core.api.ApiCode;
import org.monolith.core.api.JsonApi;
import org.monolith.core.api.MultiLine;
import org.monolith.core.entity.BaseEntity.SelectAll;
import org.monolith.web.system.entity.User;
import org.monolith.web.system.service.UserService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@RestController
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping(value = { "user/users" }, method = RequestMethod.GET)
	public JsonApi getUserList(@Validated({ SelectAll.class }) User user, BindingResult result) {
		Page<?> page = PageHelper.startPage(user.getPage(), user.getRows());
		List<Map<String, Object>> userList = userService.getList(user);
		if (userList != null && userList.size() > 0) {
			return new JsonApi(ApiCode.OK, new MultiLine(page.getTotal(), userList));
		}
		return new JsonApi(ApiCode.NOT_FOUND);
	}
}
