package com.enterprise.manage;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.enterprise.biz.system.repository.entity.SysUser;
import com.enterprise.biz.system.repository.mapper.UserMapper;
import com.enterprise.biz.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class SyManageApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Test
    void testMapper() {
//        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysUser::getUserName, "chenyi");
//        userMapper.selectOne(queryWrapper);


        SysUser user = userService.queryUserByName("chenyi");
    }

}
