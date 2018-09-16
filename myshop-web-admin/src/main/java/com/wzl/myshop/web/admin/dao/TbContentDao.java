package com.wzl.myshop.web.admin.dao;

import com.wzl.myshop.commons.persistence.BaseDao;
import com.wzl.myshop.domain.TbContent;
import com.wzl.myshop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2018/9/13.
 */

@Repository
public interface TbContentDao extends BaseDao<TbContent>{

    /**
     * 搜索
     * @param tbContent
     * @return
     */
    List<TbContent> search(TbContent tbContent);



}
