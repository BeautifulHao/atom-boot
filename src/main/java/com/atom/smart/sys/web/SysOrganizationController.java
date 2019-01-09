package com.atom.smart.sys.web;


import com.atom.smart.sys.entity.SysOrganization;
import com.atom.smart.sys.service.SysOrganizationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * <p>
 * 组织机构 前端控制器
 * </p>
 *
 * @author admin
 * @since 2018-11-06
 */
@Api(tags = "Organization",description="组织机构")
@RestController
@RequestMapping("/api/sys-organization")
public class SysOrganizationController {

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @ApiOperation(value="获取组织机构列表"
            ,notes="获取组织机构全部信息."
            ,tags = "Organization"
            ,authorizations = @Authorization(value = "sys-org-list")
            ,produces="application/json")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('sys-org-list')")
    public List<SysOrganization> getAllOrg(){
        return sysOrganizationService.list(null);
    }

    @ApiOperation(value="获取组织机构信息",
            notes="根据组织机构编码获取该组织信息"
            ,tags = "Organization"
            ,produces="application/json")
    @ApiImplicitParam(name = "code", value = "组织机构编码", required = true, dataType = "String",paramType ="path" )
    @GetMapping("/item/{code}")
    @PreAuthorize("hasAnyAuthority('sys-org-view')")
    public SysOrganization getOrganization(@PathVariable String code){
        QueryWrapper<SysOrganization> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",code);
        return sysOrganizationService.getOne(queryWrapper);
    }
}
