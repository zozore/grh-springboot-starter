package com.grhtest.service;

import com.grhtest.pojo.SysUser;

import java.util.List;

/**
 * @author grh
 */
public interface UserService {

    /** 插入数据 **/
    public int saveUser(SysUser user) throws Exception;

    public int updateUser(SysUser user);

    public int deletUser(SysUser user);

    public SysUser queryUserById(String userId);

    public List<SysUser> queryUsers(SysUser user);

    /** 下面开始分页查询 */
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize);

    public SysUser queryUserByIdCustom(String userId);

    public int saveUserTransactional(SysUser user);


}
