package com.wzl.myshop.web.admin.service;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.commons.dto.PageInfo;
import com.wzl.myshop.commons.persistence.BaseService;
import com.wzl.myshop.domain.TbUser;

import java.util.List;

/**
 * Created by thinkpad on 2018/9/8.
 */
public interface TbUserService extends BaseService<TbUser>{


    public List<TbUser> selectByUsername(String username);

    public TbUser login(String email, String password);

    List<TbUser> search(TbUser tbUser);

}
