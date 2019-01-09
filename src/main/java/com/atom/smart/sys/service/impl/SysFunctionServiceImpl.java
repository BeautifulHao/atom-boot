package com.atom.smart.sys.service.impl;

import com.atom.smart.sys.entity.SysFunction;
import com.atom.smart.sys.mapper.SysFunctionMapper;
import com.atom.smart.sys.service.SysFunctionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@Service
public class SysFunctionServiceImpl extends ServiceImpl<SysFunctionMapper, SysFunction> implements SysFunctionService {

    @Override
    public List<SysFunction> selectFunctionsByUserCode(String userCode) {
        return baseMapper.selectFunctionsByUserCode(userCode);
    }
}
