package com.atguigu.auth;


import com.atguigu.auth.mapper.SysRoleMapper;
import com.wwj.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMpDemo1 {

    @Autowired
    private SysRoleMapper mapper;

    @Test
    public void getAll() {
        List<SysRole> list = mapper.selectList(null);
        System.out.println(list);
    }

    @Test
    public void add() {
        SysRole role = new SysRole();
        SysRole sysRole = new SysRole();
        sysRole.setRoleName("角色管理员1");
        sysRole.setRoleCode("role");
        sysRole.setDescription("角色管理员1");

        int result = mapper.insert(sysRole);
        System.out.println(result); //影响的行数
        System.out.println(sysRole); //id自动回填
    }

    @Test
    public void update() {
        SysRole sysRole = new SysRole();
        sysRole.setId(9L);
        sysRole.setRoleName("角色管理员1");

        int result = mapper.updateById(sysRole);
        System.out.println(result);
    }

    @Test
    public void deleteId() {
        int rows = mapper.deleteById(10);
    }

}
