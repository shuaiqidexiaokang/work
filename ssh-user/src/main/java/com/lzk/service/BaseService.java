package com.lzk.service;

import com.lzk.pojo.PageInfo;
import com.lzk.pojo.ProcResult;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xiaokang
 * @date 2018-06-01 15:24
 * @desc
 */
public interface BaseService {
    /**
     * 保存对象
     *
     * @param obj 所要保存的对象
     * @return 唯一主键
     */
    <T> Serializable save(T obj);

    /**
     * 删除对象
     *
     * @param obj 所要删除的对象
     */
    <T> void delete(T obj);

    /**
     * 修改对象
     *
     * @param obj 所要修改的对象
     */
    <T> void update(T obj);

    /**
     * 保存或修改
     * @param obj 所要修改的对象
     * @return
     */
    Object saveOrUpdate(Object obj);

    /**
     * 批量保存
     * @param entityList
     * @param <T>
     */
    <T> void batchSave(List<T> entityList);

    /**
     * 批量更新
     * @param entityList
     * @param <T>
     */
    <T> void batchUpdate(List<T> entityList);

    /**
     * 批量删除
     * @param entityList
     * @param <T>
     */
    <T> void batchDelete(List<T> entityList);

    /**
     * 批量保存或修改
     * @param entityList
     * @param <T>
     */
    <T> void batchSaveOrUpdate(List<T> entityList);

    /**
     * 查询此对象所有数据
     * @param clazz
     * @param <T>
     * @return List
     */
    <T> List<T> list(Class<T> clazz);

    /**
     * 根据主键获取对象
     *
     * @param clazz 所要获取对象的类
     * @param id    主键
     * @return T
     */
    <T> T get(Class<T> clazz, Serializable id);

    /**
     * hql语句查询单个实体对象
     *
     * @param hql 查询语句
     * @return 实体对象
     */
    <T> T get(String hql);

    /**
     * hql语句带条件查询单个实体对象
     *
     * @param hql    查询语句
     * @param params 条件参数
     * @return 实体对象
     */
    <T> T get(String hql, Map<String, Object> params);

    /**
     * hql语句查询实体集合
     *
     * @param hql 查询
     * @return List
     */
    <T> List<T> find(String hql);

    /**
     * hql语句带条件查询实体集合
     *
     * @param hql    查询语句
     * @param params 条件参数
     * @return List
     */
    <T> List<T> find(String hql, Map<String, Object> params);

    /**
     * hql语句分页查询实体集合
     *
     * @param hql  查询语句
     * @param page 当前页号
     * @param rows 行数
     * @return List
     */
    <T> List<T> find(String hql, int page, int rows);

    /**
     * hql语句带条件分页查询实体集合
     *
     * @param hql    查询语句
     * @param params 条件参数
     * @param page   当前页号
     * @param rows   行数
     * @return List
     */
    <T> List<T> find(String hql, Map<String, Object> params, int page, int rows);

    /**
     * hql语句查询记录数
     *
     * @param hql 查询语句
     * @return long
     */
    long count(String hql);

    /**
     * hql语句带条件查询记录数
     *
     * @param hql    查询语句
     * @param params 条件参数
     * @return Long
     */
    Long count(String hql, Map<String, Object> params);

    /**
     * 执行hql语句（可带事务）
     *
     * @param hql 查询语句
     * @return int
     */
    int executeHql(String hql);

    /**
     * sql查询获取实体对象
     *
     * @param sql 查询语句
     * @return T
     */
    <T> T getBySql(String sql);

    /**
     * sql带条件查询获取实体对象
     *
     * @param sql    查询语句
     * @param params 条件参数
     * @return T
     */
    <T> T getBySql(String sql, Map<String, Object> params,Class<T> clzz);

    /**
     * sql查询实体结果集
     *
     * @param sql   查询语句
     * @param clazz 类对象
     * @return List
     */
    <T> List<T> findBySql(String sql, Class<T> clazz);

    /**
     * sql带条件查询实体结果集
     *
     * @param sql    查询语句
     * @param params 条件参数
     * @return List
     */
    <T> List<T> findBySql(String sql, Map<String, Object> params);

    /**
     * sql分页查询结果集
     *
     * @param sql   查询语句
     * @param page  当前页
     * @param rows  行数
     * @param clazz 类对象
     * @return List
     */
    <T> List<T> findBySql(String sql, int page, int rows, Class<T> clazz);

    /**
     * sql分页查询结果集
     *
     * @param sql    查询语句
     * @param params 条件参数
     * @param page   当前页
     * @param rows   行数
     * @param clazz  类对象
     * @return List
     */
    <T> List<T> findBySql(String sql, Map<String, Object> params, int page, int rows, Class<T> clazz);

    /**
     * sql查询Map结果集
     *
     * @param sql 查询语句
     * @return List
     */
    List<Map<String, Object>> findMapBySql(String sql);

    /**
     * sql带条件查询Map结果集
     *
     * @param sql    查询语句
     * @param params 条件参数
     * @return List
     */
    List<Map<String, Object>> findMapBySql(String sql, Map<String, Object> params);

    /**
     * sql分页查询Map结果集
     *
     * @param sql  查询语句
     * @param page 当前页号
     * @param rows 行数
     * @return List
     */
    List<Map<String, Object>> findMapBySql(String sql, int page, int rows);

    /**
     * sql带条件分页查询Map结果集
     *
     * @param sql    查询语句
     * @param params 条件参数
     * @param page   当前页
     * @param rows   行数
     * @return List
     */
    List<Map<String, Object>> findMapBySql(String sql, Map<String, Object> params, int page, int rows);

    /**
     * sql查询记录数
     *
     * @param sql 查询语句
     * @return Long
     */
    Long countBySql(String sql);

    /**
     * sql带条件查询记录数
     *
     * @param sql    查询语句
     * @param params 条件参数
     * @return Long
     */
    Long countBySql(String sql, Map<String, Object> params);

    /**
     * 执行sql语句（带事务）
     *
     * @param sql 执行语句
     * @return int
     */
    int executeSql(String sql);

    /**
     *
     * @param criteria
     * @param page
     * @param <T>
     * @return List
     */
    <T> List<T> getListByCriteria(DetachedCriteria criteria, PageInfo page);

    /**
     *
     * @param criteria
     * @param startPage
     * @param pageSize
     * @return List
     */
    List<?> getListByCriteria(DetachedCriteria criteria, Integer startPage, Integer pageSize);

    /**
     *
     * @param criteria
     * @return int
     */
    int getCountByCriteria(DetachedCriteria criteria);

    /**
     *
     * @param example
     * @return List
     */
    List findByExample(Object example);

    /**
     *
     * @param hql
     * @param param
     * @return boolean
     */
    boolean isExist(String hql, Map<String, Object> param);

    /**
     *
     * @param criteria
     * @param <T>
     * @return List
     */
    <T> List<T> findByCriteria(DetachedCriteria criteria);

    /**
     *
     * @param sql
     * @param params
     * @param types
     * @return int
     */
    int countBySql(String sql, Object[] params, Type[] types);

    /**
     *
     * @param sql
     * @param params
     * @param types
     * @param clazz
     * @return List
     */
    List findMapBySql(String sql, Object[] params, Type[] types, Class clazz);

    /**
     *
     * @param sql
     * @param countStr
     * @param pageInfo
     * @param params
     * @param types
     * @param clazz
     * @return List
     */
    List findMapBySql(String sql, String countStr, PageInfo pageInfo, Object[] params, Type[] types, Class clazz);

    /**
     *
     * @param example
     * @param condition
     * @param enableLike
     * @return List
     */
    List findByExample(Object example, String condition, boolean enableLike);

    /**
     * 通用存储过程查询（单结果集）
     *
     * @param procSql 查询SQL
     * @param params  参数
     * @return ProcResult 返回结果集
     */
    ProcResult find_procedure(final String procSql, final Object... params);
    /**
     * 通用存储过程查询（多结果集）
     *
     * @param procSql 查询SQL
     * @param params  参数
     * @return ProcResult 返回结果集
     */
    ProcResult find_procedure_multi(final String procSql, final Object... params);

    /**
     * 通用存储过程查询（单结果集）
     *
     * @param procSql    查询SQL
     * @param listParams 参数
     * @param outCount   输出参数个数
     * @return ProcResult
     */
    ProcResult find_procedure_list(final String procSql, final List<Object> listParams, final int outCount) throws Exception;

    /**
     * 通用存储过程（更新、删除用）
     *
     * @param procSql  存储过程 SQL 如 {call p_test_upd(?,?,?)}
     * @param params   参数
     * @param outCount 输出参数个数
     * @return ProcResult
     */
    ProcResult upd_procedure(final String procSql, final List<Object> params, final int outCount) throws Exception;

    /**
     * APi 异常
     *
     * @param result 存储返回结果
     * @throws Exception
     */
    void callProducerExceptionBack(ProcResult result) throws Exception;
}
