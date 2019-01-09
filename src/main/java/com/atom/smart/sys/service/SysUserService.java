package com.atom.smart.sys.service;

import com.atom.smart.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 人员 服务类
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
public interface SysUserService extends IService<SysUser> {
    SysUser selectUserWithPermissionByCode(String code);
}
