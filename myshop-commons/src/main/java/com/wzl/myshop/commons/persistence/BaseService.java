package com.wzl.myshop.commons.persistence;

import com.wzl.myshop.commons.dto.BaseResult;
import com.wzl.myshop.commons.dto.PageInfo;

import java.util.List;

/**
 * Created by thinkpad on 2018/9/16.
 */
public interface BaseService<T extends BaseEntity> {


    public List<T> selectAll();

    public BaseResult save(T tbUser);

    public T getById(Long id);

    public void delete(Long id);

    public void update(T tbUser);

    void deleteMulti(String[] ids);

    PageInfo<T> page(int start, int length, int draw, T entity);

    /**
     * 查询
     * @return
     */
    int count(T entity);
}
