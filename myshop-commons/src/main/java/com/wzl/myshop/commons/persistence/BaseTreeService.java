package com.wzl.myshop.commons.persistence;

import com.wzl.myshop.commons.dto.BaseResult;

import java.util.List;

/**
 * Created by thinkpad on 2018/9/16.
 */
public interface BaseTreeService<T extends BaseEntity> {



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
    BaseResult save(T entity);

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
