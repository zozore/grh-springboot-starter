package com.grhtest.mapper;

import com.grhtest.pojo.SysUser;

import java.util.List;

/**
 * @author grh
 */
public interface SysUserMapperCustomer {

    public List<SysUser> queryUserById(String userId);
}
