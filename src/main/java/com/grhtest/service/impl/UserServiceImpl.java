package com.grhtest.service.impl;

import com.github.pagehelper.PageHelper;
import com.grhtest.mapper.SysUserMapper;
import com.grhtest.mapper.SysUserMapperCustomer;
import com.grhtest.pojo.SysUser;
import com.grhtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author grh
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysUserMapperCustomer userMapperCustomer;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int saveUser(SysUser user) throws Exception {
        /**
         * insert 插入时将所有的字段都添加一遍，相当于全字段插入；
         * insertSelective 插入时，只将赋值的语句进行插入，相当于选择插入；
         * 两个插入语句，在后台数据展示时，没有区别，只是插入的SQL语句不同。
         */
        //return userMapper.insertSelective(user);

        return userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateUser(SysUser user) {
        /**
         * updateByPrimaryKey 执行更新的时候，使用全字段更新，没有赋值的字段默认为null；
         * updateByPrimaryKeySelective 执行更新的时候，只更新赋值的字段，没有赋值的字段保持原样；
         * 更新和插入时，都是带selective的方法为选择更新/插入
         */
        //return  userMapper.updateByPrimaryKey(user);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deletUser(SysUser user) {
        /**
         * delete 删除数据时，如果传入的参数为null，则会删除整张表的数据
         * deleteByPrimaryKey 根据主键来进行删除数据，当传入的参数为空时，会执行报错
         * 所以，生产上面使用deleteByPrimaryKey
         */
        //return userMapper.delete(user);
        return userMapper.deleteByPrimaryKey(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUsers(SysUser user) {
        //List<SysUser> userList = userMapper.selectAll();
        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        List<SysUser> userList = userMapper.selectByExample(example);
        return userList;
    }

    /**
     * 分页查询
     * @param user
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<SysUser> queryUserListPaged(SysUser user, Integer page, Integer pageSize) {
        // 开始分页
        PageHelper.startPage(page, pageSize);

        Example example = new Example(SysUser.class);
        Example.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmptyOrWhitespace(user.getUserName())) {
            criteria.andLike("userName", "%" + user.getUserName() + "%");
        }
        example.orderBy("userId").desc();
        List<SysUser> userList = userMapper.selectByExample(example);

        return userList;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public SysUser queryUserByIdCustom(String userId) {
        List<SysUser> userList = userMapperCustomer.queryUserById(userId);
        if (null != userList && !userList.isEmpty()) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int saveUserTransactional(SysUser user) {
        userMapper.insert(user);
        int i = 1 / 0;
        user.setWeight(100);
        return userMapper.updateByPrimaryKeySelective(user);
    }

}
