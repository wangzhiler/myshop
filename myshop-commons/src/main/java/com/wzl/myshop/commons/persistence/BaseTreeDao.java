package com.wzl.myshop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * 通用的树型结构接口
 */
public interface BaseTreeDao<T extends BaseEntity> {


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
     * 根据父结点查询其所有子结点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);

}
