package com.atom.smart.sys.mapper;

import com.atom.smart.sys.entity.SysRole;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> selectRolesByUserCode(String userCode);
}
