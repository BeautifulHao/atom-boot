package com.atom.smart.sys.mapper;

import com.atom.smart.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 人员 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserWithPermissionByCode(String code);
}
