package com.atom.smart.sys.service.impl;

import com.atom.smart.sys.entity.SysRole;
import com.atom.smart.sys.mapper.SysRoleMapper;
import com.atom.smart.sys.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> selectRolesByUserCode(String userCode) {
        return baseMapper.selectRolesByUserCode(userCode);
    }
}
