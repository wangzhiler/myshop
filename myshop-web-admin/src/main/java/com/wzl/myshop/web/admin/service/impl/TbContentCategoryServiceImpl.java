package com.wzl.myshop.web.admin.service.impl;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.commons.dto.PageInfo;
import com.wzl.myshop.commons.validator.BeanValidator;
import com.wzl.myshop.domain.TbContent;
import com.wzl.myshop.domain.TbContentCategory;
import com.wzl.myshop.web.admin.dao.TbContentCategoryDao;
import com.wzl.myshop.web.admin.service.TbContentCategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by thinkpad on 2018/9/13.
 */

@Service("TbContentCategory")
public class TbContentCategoryServiceImpl implements TbContentCategoryService {
    @Autowired
    private TbContentCategoryDao tbContentCategoryDao;

    @Override
    public List<TbContentCategory> selectAll() {
        return tbContentCategoryDao.selectAll();
    }

    @Override
    public BaseResult save(TbContentCategory tbContentCategory) {

        if (tbContentCategory==null) {

        }

        return null;
//        String validator = BeanValidator.validator(tbContentCategory);
//        if (validator != null) {
//            return BaseResult.fail(validator);
//        }
//        else{
//            if (tbContentCategory == null) {
//
//            }
//            tbContentCategory.getParentId();
//            TbContentCategory parent = tbContentCategory.getParent();
//            //如果没有选择父级结点or默认设置为根目录
//            if (parent == null || parent.getId() == null) {
////                parent.setId(0L);
//                tbContentCategory.setParentId(0L);
////                parent.setIsParent(true);
//            }
//
//            tbContentCategory.setUpdated(new Date());
//
//            //新增
//            if (tbContentCategory.getId() == null) {
//                tbContentCategory.setCreated(new Date());
//                tbContentCategory.setIsParent(false);
//                //判断新增的结点有无父级结点
//                if (tbContentCategory.getParentId() != 0L) {
//                    TbContentCategory currentCategoryParent = getById(tbContentCategory.getParentId());
//                    if (currentCategoryParent != null) {
//                        //为父级结点设置isParent为true
//                        currentCategoryParent.setIsParent(true);
//                        update(currentCategoryParent);
//                    }
//                }
//                //父级结点为0，表示为根目录
//                else{
//                    tbContentCategory.setParentId(0L);
//                    tbContentCategory.setIsParent(true);
//                }
//                tbContentCategoryDao.insert(tbContentCategory);
//            }
//            //修改
//            else{
//                tbContentCategoryDao.update(tbContentCategory);
//            }
//            return BaseResult.success("保存信息成功");
//        }
    }

    @Override
    public TbContentCategory getById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        tbContentCategoryDao.delete(id);
    }

    @Override
    public void update(TbContentCategory tbContentCategory) {
        tbContentCategoryDao.update(tbContentCategory);
    }



    @Override
    public List<TbContentCategory> selectByPid(Long pid) {
        return tbContentCategoryDao.selectByPid(pid);
    }


}
