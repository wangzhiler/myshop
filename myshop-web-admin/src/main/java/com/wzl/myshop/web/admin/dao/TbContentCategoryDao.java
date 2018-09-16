package com.wzl.myshop.web.admin.dao;

import com.wzl.myshop.commons.persistence.BaseDao;
import com.wzl.myshop.commons.persistence.BaseTreeDao;
import com.wzl.myshop.domain.TbContentCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by thinkpad on 2018/9/13.
 */

@Repository
public interface TbContentCategoryDao extends BaseTreeDao<TbContentCategory> {

}
