package com.atguigu.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wwj.model.system.SysUser;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2023-03-04
 */
public interface SysUserService extends IService<SysUser> {

    void updateStatus(Long id, Integer status);

    SysUser getUserByUserName(String username);
}
