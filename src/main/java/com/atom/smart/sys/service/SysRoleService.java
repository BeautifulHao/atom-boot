package com.atom.smart.sys.service;

import com.atom.smart.sys.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> selectRolesByUserCode(String userCode);
}
