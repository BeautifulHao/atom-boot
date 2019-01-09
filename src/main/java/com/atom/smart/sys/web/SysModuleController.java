package com.atom.smart.sys.web;


import com.atom.smart.sys.entity.SysModule;
import com.atom.smart.sys.service.SysModuleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2018-11-05
 */
@Api(tags = "Module",description="系统模块")
@RestController
@RequestMapping("/api/module")
public class SysModuleController {
    @Autowired
    private SysModuleService sysModuleService;

    @ApiOperation(value="获取系统模块集合", notes="获取系统模块集合全部信息.",tags = "Module")
    @GetMapping("/items")
    public IPage<SysModule> getItems(){
        QueryWrapper<SysModule> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("module_name","测试");
        return sysModuleService.page(new Page<>(2, 10), queryWrapper);
    }
}
