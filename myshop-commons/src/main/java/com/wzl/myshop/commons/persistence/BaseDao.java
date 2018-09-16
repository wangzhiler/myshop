package com.wzl.myshop.commons.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2018/9/16.
 */
public interface BaseDao<T> {
    /**
     * 查询全部信息
     *
     * @return
     */
    public List<T> selectAll();


    /**
     * 新增
     *
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 查询单个对象
     *
     * @param id
     * @return
     */
    T getById(Long id);


    /**
     * 更新TbUser
     *
     * @param entity
     */
    void update(T entity);


    /**
     * 批量数组
     *
     * @param ids
     */
    void deleteMulti(String[] ids);


    /**
     * 分页查询
     *
     * @param params 需要两个参数，start记录开始位置，length记录每页记录数
     * @return
     */
    List<T> page(Map<String, Object> params);


    /**
     * 查询总记录数
     *
     * @return
     */
    int count(T entity);

}
