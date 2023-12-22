/*
 * Copyright 2013-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.enterprise.manage.web;
import com.enterprise.biz.system.repository.entity.SysUser;
import com.enterprise.biz.system.service.UserService;
import com.enterprise.common.model.R;

import com.enterprise.framwork.jwt.JwtUtil;
import com.enterprise.framwork.web.AuthScope;
import com.enterprise.manage.dto.LoginForm;
import com.enterprise.manage.dto.UserLoginDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public R login(@RequestBody LoginForm loginForm) throws Exception{
        SysUser currentUser = userService.authenticate(loginForm.getUserName(),
            loginForm.getPassword());
        String token = jwtUtil.encode(loginForm.getUserName());
        return R.success(new UserLoginDto(token, currentUser.getUserId().toString(), currentUser.getUserName()));
    }

    @GetMapping("/getUser")
    public R getUser() {
        return R.success(AuthScope.getLoginUser());
    }


    @GetMapping("/test")
    public Object test() {
        return "test";
    }
}
