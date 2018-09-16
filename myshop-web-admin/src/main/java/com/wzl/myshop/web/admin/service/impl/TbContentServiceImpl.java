package com.wzl.myshop.web.admin.service.impl;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.commons.dto.PageInfo;
import com.wzl.myshop.commons.utils.RegexpUtils;
import com.wzl.myshop.commons.validator.BeanValidator;
import com.wzl.myshop.domain.TbContent;
import com.wzl.myshop.domain.TbUser;
import com.wzl.myshop.web.admin.dao.TbContentDao;
import com.wzl.myshop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2018/9/13.
 */

@Service
public class TbContentServiceImpl implements TbContentService {


    @Autowired
    private TbContentDao tbContentDao;


    @Override
    public List<TbContent> selectAll() {
        return tbContentDao.selectAll();
    }

    @Override
    public TbContent getById(Long id) {
        return tbContentDao.getById(id);
    }

    @Override
    public List<TbContent> selectByUsername(String username) {
        return null;
    }

    @Override
    public BaseResult save(TbContent tbContent) {
        String validator = BeanValidator.validator(tbContent);

        if (validator != null) {
            System.out.println("fiqejfq");
            return BaseResult.fail(validator);
        }
        else{
            tbContent.setUpdated(new Date());

            //新增用户
            if (tbContent.getId() == null) {
                tbContent.setCreated(new Date());
                tbContentDao.insert(tbContent);
            }
            //编辑用户
            else {
                tbContentDao.update(tbContent);
            }
            return BaseResult.success("保存内容信息成功");
        }
    }

    @Override
    public void delete(Long id) {
        tbContentDao.delete(id);
    }

    @Override
    public void update(TbContent tbContent) {
        tbContentDao.update(tbContent);
    }


    @Override
    public void deleteMulti(String[] ids) {
        tbContentDao.deleteMulti(ids);
    }

    @Override
    public PageInfo<TbContent> page(int start, int length, int draw, TbContent tbContent) {
        int count = tbContentDao.count(tbContent);

        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        params.put("tbContent", tbContent);

        PageInfo<TbContent> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(tbContentDao.page(params));

        return pageInfo;
    }

    @Override
    public int count(TbContent tbContent) {
        return tbContentDao.count(tbContent);
    }



}
