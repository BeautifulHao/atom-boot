package com.atom.smart.support;

import com.atom.smart.sys.entity.SysFunction;
import com.atom.smart.sys.entity.SysRole;
import com.atom.smart.sys.entity.SysUser;
import com.atom.smart.sys.service.SysUserService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author BeautifulHao
 * @description
 * @create 2018-11-07 09:17
 **/
@Service
@Primary
public class SysUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Override
    @Cacheable(value = "userDetail", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysUser sysUser = sysUserService.selectUserWithPermissionByCode(username);

        if(null== sysUser){
            throw new UsernameNotFoundException(username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<SysFunction> sysFunctions = sysUser.getSysAllFunctions();

        //纯权限分配
        for (SysFunction permission:sysFunctions){

            String permissions =permission !=null?permission.getPermissions():null;

            if(StringUtils.isNotEmpty(permissions))
            {
                for (String item : permission.getPermissions().split(",")){
                    if(StringUtils.isNotEmpty(item)){
                        authorities.add(new SimpleGrantedAuthority(item));
                    }
                }
            }
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        }

        //角色编号也作为权限分配对象，spring security authority和role 区分度不理想
        for(SysRole sysRole :sysUser.getSysAllRoles()){
            authorities.add(new SimpleGrantedAuthority(sysRole.getCode()));
        }


        boolean isNonUserExpired =sysUser.getUserExpire() ==null?true: sysUser.getUserExpire() - System.currentTimeMillis() > 0;
        boolean isNonPwdExpired =sysUser.getPwdExpire() ==null?true: sysUser.getPwdExpire() - System.currentTimeMillis() > 0;

        User authUser =new User(sysUser.getCode(),
                sysUser.getPassword(),sysUser.getEnable(),isNonUserExpired,isNonPwdExpired,!sysUser.getLock(),
                authorities);

        return authUser;
    }
}
