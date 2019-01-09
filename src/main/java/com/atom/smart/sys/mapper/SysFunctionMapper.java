package com.atom.smart.sys.mapper;

import com.atom.smart.sys.entity.SysFunction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
public interface SysFunctionMapper extends BaseMapper<SysFunction> {
    List<SysFunction> selectFunctionsByUserCode(String userCode);
}
