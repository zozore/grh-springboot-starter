package com.grhtest.controller;

import com.grhtest.pojo.GrhJsonResult;
import com.grhtest.pojo.SysUser;
import com.grhtest.service.UserService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author grh
 */
@RestController
@RequestMapping("/mybatis")
public class MyBatisCRUDController {

    @Autowired
    private UserService userService;

    @Autowired
    private Sid sid;

    @RequestMapping("saveUser")
    public GrhJsonResult saveUser() throws Exception {
        SysUser user = new SysUser();
        user.setUserName("小红");
        user.setAge(56);
        user.setBirthday(new Date());
        user.setHeight(123);
        user.setWeight(88);
        String id = sid.nextShort();
        user.setUserId(id);
        return  userService.saveUser(user) > 0 ? GrhJsonResult.ok("保存成功！") : GrhJsonResult.ok("保存失败！");
    }

    @RequestMapping("updateUser")
    public GrhJsonResult updateUser() {
        SysUser user = new SysUser();
        user.setUserId("210203FSSSWRXF3C");
        user.setUserName("小明");
        user.setAge(66);
        user.setBirthday(new Date());
//        user.setHeight(321);
        user.setWeight(99);
        return  userService.updateUser(user) > 0 ? GrhJsonResult.ok("更新成功！") : GrhJsonResult.ok("更新失败！");
    }

    @RequestMapping("deleteUser")
    public GrhJsonResult deleteUser() {
        SysUser user = new SysUser();
        //user.setUserId("210203FTC83TTR40");
        return  userService.deletUser(user) > 0 ? GrhJsonResult.ok("删除成功！") : GrhJsonResult.ok("删除失败！");
    }

    @RequestMapping("queryByUserId")
    public GrhJsonResult queryUserById(String userId) {
        return GrhJsonResult.ok(userService.queryUserById(userId));
    }

    @RequestMapping("queryUsers")
    public GrhJsonResult queryUsers(SysUser user) {
        return GrhJsonResult.ok(userService.queryUsers(user));
    }

    @RequestMapping("queryUserListPage")
    public GrhJsonResult queryUserListPage(Integer page, SysUser user) {
        if (page == null) {
            page = 1;
        }
        int pageSize = 5;

        List<SysUser> userList = userService.queryUserListPaged(user, page, pageSize);

        return GrhJsonResult.ok(userList);
    }
}
