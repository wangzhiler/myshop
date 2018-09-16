package com.wzl.myshop.web.admin.dao;

import com.wzl.myshop.commons.persistence.BaseDao;
import com.wzl.myshop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2018/9/8.
 */

@Repository
public interface TbUserDao extends BaseDao<TbUser> {

    /**
     * 通过用户名模糊查询
     * @param username
     * @return
     */
    public List<TbUser> selectByUsername(String username);

    /**
     * 根据email查询
     * @param email
     * @return
     */
    public TbUser getByEmail(String email);

    /**
     * 搜索
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);



}
