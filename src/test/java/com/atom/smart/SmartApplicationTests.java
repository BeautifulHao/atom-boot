package com.atom.smart;

import com.atom.smart.sys.entity.*;
import com.atom.smart.sys.service.*;
import com.atom.smart.sys.service.impl.SysOrganizationServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertFalse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(false)
public class SmartApplicationTests {

    @Autowired
    private SysOrganizationService sysOrganizationService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysFunctionService sysFunctionService;

    @Autowired
    private SysModuleService sysModuleService;

    @Autowired
    private RoleFunctionService roleFunctionService;

    @Autowired
    private UserFunctionService userFunctionService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAddOrg(){

        QueryWrapper<SysOrganization> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code","0001");

        if(sysOrganizationService.count(queryWrapper)>0){
            return;
        }

        SysOrganization group = new SysOrganization(UUID.randomUUID().toString().replace("-",""),
                "0001",
                "淘宝集团",
                Integer.valueOf(0));

        group.setParentCode("");
        group.setAddress("浙江杭州");
        group.setEmail("taobao@gmail.com");
        group.setSortNo(0);
        group.setStatus(true);
        group.setManager("马云");
        group.setTel("0571-8288888");

        sysOrganizationService.save(group);

        SysOrganization company = new SysOrganization(UUID.randomUUID().toString().replace("-",""),
                "00010001",
                "总公司",
                Integer.valueOf(1));

        company.setParentCode("0001");
        company.setAddress("浙江杭州");
        company.setEmail("taobao@gmail.com");
        company.setSortNo(1);
        company.setStatus(true);
        company.setManager("郭靖");
        company.setTel("0571-8288889");

        sysOrganizationService.save(company);


        SysOrganization department = new SysOrganization(UUID.randomUUID().toString().replace("-",""),
                "000100010001",
                "研发中心",
                Integer.valueOf(2));

        department.setParentCode("000100010001");
        department.setAddress("浙江杭州");
        department.setEmail("taobao@gmail.com");
        department.setSortNo(2);
        department.setStatus(true);
        department.setManager("张无忌");
        department.setTel("0571-8288887");

        sysOrganizationService.save(department);

    }

    @Test
    public void testAddUser(){

        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code","2018");

        if(sysUserService.count(queryWrapper)>0){
            return;
        }

        SysUser sysUser = new SysUser(getUUID(),"2018","admin",passwordEncoder.encode("123456"),"000100010001");
        sysUser.setCreateItem(LocalDateTime.now());
        sysUser.setCreateUser("2018");
        sysUser.setEnable(true);
        sysUser.setPwdExpire(System.currentTimeMillis()+1000*60*60*24*365);
        sysUser.setUserExpire(System.currentTimeMillis()+1000*60*60*24*365);
        sysUser.setRemark("管理员");
        sysUser.setSortNo(0);
        sysUser.setLock(false);
        sysUser.setTelNo("138888888");

        sysUserService.save(sysUser);

        sysUser = new SysUser(getUUID(),"2019","dev",passwordEncoder.encode("123456"),"000100010001");
        sysUser.setCreateItem(LocalDateTime.now());
        sysUser.setCreateUser("2018");
        sysUser.setEnable(true);
        sysUser.setPwdExpire(System.currentTimeMillis()+1000*60*60*24*365);
        sysUser.setUserExpire(System.currentTimeMillis()+1000*60*60*24*365);
        sysUser.setRemark("开发员");
        sysUser.setSortNo(1);
        sysUser.setLock(false);
        sysUser.setTelNo("138888889");

        sysUserService.save(sysUser);
    }

    @Test
    public void testAddRole(){

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code","role_admin");

        if(sysRoleService.count(queryWrapper)>0){
            return;
        }

        SysRole sysAdminRole = new SysRole(getUUID(),"role_admin","管理员",0,0,true,"系统内置管理员");
        SysRole sysDevRole = new SysRole(getUUID(),"role_dev","开发员",1,1,true,"普通用户");

        sysRoleService.save(sysAdminRole);
        sysRoleService.save(sysDevRole);
    }

    @Test
    public void testAddModule(){

        QueryWrapper<SysModule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("module_code","0001");
        if(sysModuleService.count(queryWrapper)>0){
            return;
        }

        SysModule sysModule = new SysModule(getUUID(),"0001","系统管理",true,"icon-system",0);
        sysModule.setCreateDate(LocalDate.now());
        sysModule.setSeriesCode(UUID.randomUUID().toString());

        SysModule sysbusModule = new SysModule(getUUID(),"0002","客户关系管理",false,"icon-custom",1);
        sysbusModule.setCreateDate(LocalDate.now());
        sysbusModule.setSeriesCode(UUID.randomUUID().toString());

        sysModuleService.save(sysModule);
        sysModuleService.save(sysbusModule);

    }

    @Test
    public void testAddOrgFunction(){

        QueryWrapper<SysFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code","org-List");
        if(sysFunctionService.count(queryWrapper)>0){
            return;
        }

        SysFunction sysMenuFunction = new SysFunction(getUUID(),
                "org-List","",
                "组织机构管理",
                1,
                "api/sys-organization","sys-org-list,sys-org-view",true,0,"0001","icon-org","组织机构管理");

        SysFunction sysbutAdd = new SysFunction(getUUID(),
                "org-List-Add","org-List",
                "新增",
                2,
                "","sys-org-sava",true,1,"0001","icon-org-add","组织机构管理新增");

        SysFunction sysbutEdit = new SysFunction(getUUID(),
                "org-List-Edit","org-List",
                "修改",
                2,
                "","sys-org-update",true,2,"0001","icon-org-edit","组织机构管理修改");


        SysFunction sysbutRemove = new SysFunction(getUUID(),
                "org-List-Remove","org-List",
                "删除",
                2,
                "","sys-org-remove",true,3,"0001","icon-org-remove","组织机构管理删除");

        SysFunction sysbutView = new SysFunction(getUUID(),
                "org-List-View","org-List",
                "查看",
                2,
                "","sys-org-view",true,4,"0001","icon-org-view","组织机构管理查看");

        sysFunctionService.save(sysMenuFunction);
        sysFunctionService.save(sysbutAdd);
        sysFunctionService.save(sysbutEdit);
        sysFunctionService.save(sysbutRemove);
        sysFunctionService.save(sysbutView);

    }

    @Test
    public void testAddDruidFunction(){

        QueryWrapper<SysFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code","druid");
        if(sysFunctionService.count(queryWrapper)>0){
            return;
        }

        SysFunction sysMenuFunction = new SysFunction(getUUID(),
                "druid","",
                "数据源监控",
                1,
                "/druid","sys-datasource-admin",true,0,"0001","icon-druid","数据源监控");

        sysFunctionService.save(sysMenuFunction);


    }

    @Test
    public void testAddUserFunction(){

        QueryWrapper<SysFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code","user-List");
        if(sysFunctionService.count(queryWrapper)>0){
            return;
        }

        SysFunction sysMenuFunction = new SysFunction(getUUID(),
                "user-List","",
                "人员管理",
                1,
                "api/sys-user","sys-user-list,sys-user-view",true,0,"0001","icon-user","人员管理");

        SysFunction sysbutAdd = new SysFunction(getUUID(),
                "user-List-Add","user-List",
                "新增",
                2,
                "","sys-user-sava",true,1,"0001","icon-user-add","人员新增");

        SysFunction sysbutEdit = new SysFunction(getUUID(),
                "user-List-Edit","user-List",
                "修改",
                2,
                "","sys-org-update",true,2,"0001","icon-user-edit","人员修改");


        SysFunction sysbutRemove = new SysFunction(getUUID(),
                "user-List-Remove","user-List",
                "删除",
                2,
                "","sys-org-remove",true,3,"0001","icon-user-remove","人员删除");

        SysFunction sysbutView = new SysFunction(getUUID(),
                "user-List-View","user-List",
                "查看",
                2,
                "","sys-user-view",true,4,"0001","icon-user-view","人员查看");

        sysFunctionService.save(sysMenuFunction);
        sysFunctionService.save(sysbutAdd);
        sysFunctionService.save(sysbutEdit);
        sysFunctionService.save(sysbutRemove);
        sysFunctionService.save(sysbutView);

    }

    @Test
    public void testAddRoleFunction(){

        QueryWrapper<RoleFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code","role_admin");

        if(roleFunctionService.count(queryWrapper)>0){
            return;
        }

        List<RoleFunction> roleFunctions =new ArrayList<>();
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","org-List",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","org-List-Add",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","org-List-Edit",LocalDateTime.now()));
        //roleFunctions.add(new RoleFunction(getUUID(),"role_admin","org-List-Remove",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","org-List-View",LocalDateTime.now()));
        roleFunctionService.saveBatch(roleFunctions);

        roleFunctions =new ArrayList<>();
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","user-List",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","user-List-Add",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","user-List-Edit",LocalDateTime.now()));
        //roleFunctions.add(new RoleFunction(getUUID(),"role_admin","user-List-Remove",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_admin","user-List-View",LocalDateTime.now()));
        roleFunctionService.saveBatch(roleFunctions);

        roleFunctions =new ArrayList<>();
        roleFunctions.add(new RoleFunction(getUUID(),"role_dev","user-List",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_dev","user-List-Add",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_dev","user-List-Edit",LocalDateTime.now()));
        //roleFunctions.add(new RoleFunction(getUUID(),"role_admin","user-List-Remove",LocalDateTime.now()));
        roleFunctions.add(new RoleFunction(getUUID(),"role_dev","user-List-View",LocalDateTime.now()));
        roleFunctionService.saveBatch(roleFunctions);

        roleFunctionService.save(new RoleFunction(getUUID(),"role_admin","druid",LocalDateTime.now()));
    }

    @Test
    public void testAddUserFunctions(){
        QueryWrapper<UserFunction> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code","2018");

        if(userFunctionService.count(queryWrapper)>0){
            return;
        }

        List<UserFunction> userFunctions =new ArrayList<>();
        userFunctions.add(new UserFunction(getUUID(),"2018","org-List-Remove",LocalDateTime.now()));
        userFunctions.add(new UserFunction(getUUID(),"2018","user-List-Remove",LocalDateTime.now()));
        userFunctionService.saveBatch(userFunctions);

        userFunctions =new ArrayList<>();
        userFunctions.add(new UserFunction(getUUID(),"2019","user-List-Remove",LocalDateTime.now()));
        userFunctionService.saveBatch(userFunctions);

    }

    @Test
    public void testAddUserRoles(){
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_code","2018");

        if(userRoleService.count(queryWrapper)>0){
            return;
        }

        ArrayList<UserRole> userRoles = new ArrayList<>();
        userRoles.add(new UserRole(getUUID(),"2018","role_admin",LocalDateTime.now()));
        userRoles.add(new UserRole(getUUID(),"2018","role_dev",LocalDateTime.now()));
        userRoles.add(new UserRole(getUUID(),"2019","role_dev",LocalDateTime.now()));


        userRoleService.saveBatch(userRoles);
    }

    @Test
    public void testSysFunctionAndSysRoleGetByUser(){
        List<SysFunction> sysFunctions = sysFunctionService.selectFunctionsByUserCode("2018");
        System.out.println(sysFunctions.size());
        Assert.assertTrue(sysFunctions.size()>0);

        List<SysRole> sysRoles = sysRoleService.selectRolesByUserCode("2018");
        System.out.println(sysRoles.size());
        Assert.assertTrue(sysRoles.size()>0);
    }

    @Test
    public void testGetUserWithPermissionByCode(){
        SysUser sysUser = sysUserService.selectUserWithPermissionByCode("2018");

        Assert.assertTrue(sysUser.getSysAllFunctions().size()>0);
        Assert.assertTrue(sysUser.getSysAllRoles().size()>0);
    }

    private String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
