package com.atom.smart.sys.service.impl;

import com.atom.smart.sys.entity.SysUser;
import com.atom.smart.sys.mapper.SysUserMapper;
import com.atom.smart.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人员 服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Override
    public SysUser selectUserWithPermissionByCode(String code) {
        return baseMapper.selectUserWithPermissionByCode(code);
    }

    //@CacheEvict(value = "userDetail", key = "#p0")
    //更新和删除操作需要删除 认证缓存数据
    @CacheEvict(value = "userDetail", key = "#userCode")
    public void removeUser(String userCode){
        Wrapper<SysUser> wrapper = new QueryWrapper<>();
        ((QueryWrapper<SysUser>) wrapper).eq("code",userCode);
        super.removeById(wrapper);
    }

    //更新和删除操作需要删除 认证缓存数据
    @CacheEvict(value = "userDetail", key = "#sysUser.userCode")
    public boolean updateUser(SysUser sysUser){
        return super.saveOrUpdate(sysUser);
    }
}
