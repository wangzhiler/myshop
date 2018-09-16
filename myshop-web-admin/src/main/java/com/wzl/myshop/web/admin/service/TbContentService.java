package com.wzl.myshop.web.admin.service;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.commons.dto.PageInfo;
import com.wzl.myshop.commons.persistence.BaseService;
import com.wzl.myshop.domain.TbContent;
import com.wzl.myshop.domain.TbUser;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2018/9/13.
 */
public interface TbContentService extends BaseService<TbContent>{


    public List<TbContent> selectByUsername(String username);

}
