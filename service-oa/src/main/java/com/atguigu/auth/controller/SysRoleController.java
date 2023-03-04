package com.atguigu.auth.controller;

import com.atguigu.auth.mapper.SysRoleMapper;
import com.atguigu.auth.service.SysRoleService;
import com.atguigu.common.config.exception.GuiguException;
import com.atguigu.common.result.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.xdevapi.Warning;
import com.wwj.model.system.SysRole;
import com.wwj.vo.system.SysRoleQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    // http://localhost:8800/admin/system/sysRole/findAll
    @Autowired
    private SysRoleService sysRoleService;

/*    @GetMapping("/findAll")
    public List<SysRole> findAll() {
        List<SysRole> list = sysRoleService.list();
        return list;
    }*/

    //统一返回结果
    @ApiOperation("查询所有角色")
    @GetMapping("/findAll")
    public Result findAll() {
        List<SysRole> list = sysRoleService.list();
//        try {
//            int i = 10/0;
//        } catch (Exception e) {
//            throw new GuiguException(20001,"执行了自定义异常处理..");
//        }
        return Result.ok(list);
    }

    //条件分页查询
    //page当前页 limit 每页显示记录数
    //SysRoleQueryVo 条件查询
    @ApiOperation("条件分页查询")
    @GetMapping("{page}/{limit}")
    public Result pageQueryRole(@PathVariable Long page,
                                @PathVariable Long limit,
                                SysRoleQueryVo sysRoleQueryVo) {
        Page<SysRole> pageParam = new Page<>(page,limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if(!StringUtils.isEmpty(roleName)) {
            wrapper.like(SysRole::getRoleName,roleName);
        }
        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }

    @ApiOperation("添加角色")
    @PostMapping("save")
    public Result save(SysRole role) {
        boolean is_success = sysRoleService.save(role);
        if(is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation("根据id查询")
    @GetMapping("get/{id}")
    public Result updateById(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.getById(id);
        return Result.ok(sysRole);
    }

    @ApiOperation("修改角色")
    @PutMapping("update")
    public Result update(SysRole role) {
        boolean is_success = sysRoleService.updateById(role);
        if(is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        boolean is_success = sysRoleService.removeById(id);
        if(is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if(is_success) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

}
