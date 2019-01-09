package com.atom.smart.sys.service;

import com.atom.smart.sys.entity.SysFunction;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
public interface SysFunctionService extends IService<SysFunction> {
    List<SysFunction> selectFunctionsByUserCode(String userCode);
}
