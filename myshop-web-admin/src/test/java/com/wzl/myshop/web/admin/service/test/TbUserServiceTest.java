package com.wzl.myshop.web.admin.service.test;

import com.wzl.myshop.domain.TbUser;
import com.wzl.myshop.web.admin.service.TbUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by thinkpad on 2018/9/8.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml"
        ,"classpath:spring-context-mybatis.xml"})
public class TbUserServiceTest {

    @Autowired
    private TbUserService tbUserService;

    @Test
    public void testSelectAll() {
        List<TbUser> tbUsers = tbUserService.selectAll();
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser.getUsername());
        }
    }

    @Test
    public void testSelectByName() {
        List<TbUser> tbUsers = tbUserService.selectByUsername("e");
        for (TbUser tbUser : tbUsers) {
            System.out.println(tbUser);
        }
    }

    @Test
    public void testGetById() {
        TbUser tbUser = tbUserService.getById(7L);
        System.out.println(tbUser);
    }

    @Test
    public void testInsert() {
        TbUser tbUser = new TbUser();
        tbUser.setUsername("new111");
        tbUser.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        tbUser.setPhone("123");
        tbUser.setEmail("123@123.com");
        tbUser.setCreated(new Date());
        tbUser.setUpdated(new Date());

        tbUserService.save(tbUser);
    }

    @Test
    public void testMD5() {
        System.out.println(DigestUtils.md5DigestAsHex("123456".getBytes()));
    }

    @Test
    public void testDelete() {
        tbUserService.delete(9L);
    }

    @Test
    public void testUpdate() {
        TbUser tbUser = tbUserService.getById(7L);
        tbUser.setUsername("update111");
        tbUserService.update(tbUser);
    }
}











