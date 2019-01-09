## 表结构

sp_sys_organization

id\code\name\parent_code\config_type(集团、公司、部门、虚拟)\status\sort_no\address\tel\email\manager

sp_sys_module

id\module_code\module_name\is_system\icon\sort_no\create_date\series_code

sp_sys_function

id\code\parent_code\name\config_type(菜单、按钮、分组)\url\permissions\status\sort_no\remark\module_code\icon

sp_sys_role

id\code\name\config_type(系统、业务)\remark\sort_no\status

sp_sys_user

id\code\name\password\is_lock\is_enable\user_expire\pwd_expire\remark\sort_no\tel_no\create_user\create_time

sp_user_role

id\user_code\role_code\attach_datetime

sp_user_function

id\user_code\function_code\attach_datetime

sp_role_function

id\role_code\function_code\attach_datetime

sp_sys_log

id\user\operation\method\params\create_time\ip_address\timestamp

## Some想法

token认证 授权上下文可以在jwt内只记录权限键，值可以缓存redis，避免大量数据库访问

api/** 进行token认证（参加ipauth示例http://www.spring4all.com/article/448）,其他走正常认证，但是认证失败不进行登录页面跳转，直接返回无权限或者没有登录提示页面，401、403处理参见（https://www.jianshu.com/p/4468a2fff879）

登录过程同时返回token和记录session 便于其他依赖session的页面能够访问，比如druid/swagger



## 代办

1、JwtAuthenticationProvider  基于JwtUsernamePasswordAuthenticationToken 重新分装 ，直接继承AuthenticationProvider接口--ok

2、JwtAuthenticationSuccessHandler 接口实现--ok

3、SecurityConfig 多余bean清理--ok

4、AuthResultUtils方法处理--ok

5、JwtLogoutFilter处理 --ok

6、认证获取数据这块，可以采用方法缓存，用户更新则失效，缓解数据库访问压力--ok

7、JwtUsernamePasswordAuthenticationToken  扩展支持记录token串，登录成功处理生成token时，重新SecurityContextHolder.getContext().setAuthentication(authResult);过滤验证拦截器，每次也重新设置。以上过程实现后，可以在注销时从context获取token。--ok

8、redis存取方法封装--ok

9、获取人员方法支持缓存，并且人员更新时设置失效。--ok

10、token刷新过滤器实现--ok
