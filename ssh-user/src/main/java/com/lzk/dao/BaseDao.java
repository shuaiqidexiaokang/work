package com.lzk.dao;

import com.lzk.pojo.PageInfo;
import com.lzk.pojo.ProcResult;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseDao {

    /**
     * 保存对象
     *
     * @param obj 所要保存的对象
     * @param <T>
     * @return 唯一主键
     */
    <T> Serializable save(T obj);

    /**
     * 删除对象
     *
     * @param obj 所要删除的对象
     * @param <T>
     */
    <T> void delete(T obj);

    /**
     * 修改对象
     *
     * @param obj 所要修改的对象
     * @param <T>
     */
    <T> void update(T obj);

    /**
     * 保存或修改
     *
     * @param obj 要修改的对象
     * @return Object
     */
    Object saveOrUpdate(Object obj);

    /**
     * 批量保存
     *
     * @param entityList
     * @param <T>
     */
    <T> void batchSave(List<T> entityList);

    /**
     * 批量更新
     *
     * @param entityList
     * @param <T>
     */
    <T> void batchUpdate(List<T> entityList);

    /**
     * 批量删除
     *
     * @param entityList
     * @param <T>
     */
    <T> void batchDelete(List<T> entityList);

    /**
     * 批量保存或修改
     *
     * @param entityList
     * @param <T>
     */
    <T> void batchSaveOrUpdate(List<T> entityList);

    /**
     * 查询此对象所有数据
     *
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
     * @param <T>
     * @return T
     */
    <T> T get(Class<T> clazz, Serializable id);

    /**
     * hql语句查询单个实体对象
     *
     * @param hql 查询语句
     * @param <T>
     * @return 实体对象
     */
    <T> T get(String hql);

    /**
     * hql语句带条件查询单个实体对象
     *
     * @param hql    查询语句
     * @param params 条件参数
     * @param <T>
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
     * @param hql
     * @return List
     */
    List<Map> findMap(String hql);

    /**
     * hql语句带条件查询实体集合
     *
     * @param hql    查询语句
     * @param params 条件参数
     * @return
     */
    <T> List<T> find(String hql, Map<String, Object> params);

    /**
     * hql语句分页查询实体集合
     *
     * @param hql  查询语句
     * @param page 当前页号
     * @param rows 行数
     * @return
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
     * @return Long
     */
    Long count(String hql);

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
    <T> T getBySql(String sql, Map<String, Object> params, Class<T> clazz);

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
     * @param criteria
     * @param page
     * @param <T>
     * @return List
     */
    <T> List<T> getListByCriteria(DetachedCriteria criteria, PageInfo page);

    /**
     * @param criteria
     * @param startPage
     * @param pageSize
     * @return List
     */
    List<?> getListByCriteria(DetachedCriteria criteria, Integer startPage, Integer pageSize);

    /**
     * @param criteria
     * @return int
     */
    int getCountByCriteria(DetachedCriteria criteria);

    /**
     * @param example
     * @return List
     */
    List findByExample(Object example);

    /**
     * @param criteria
     * @param <T>
     * @return List
     */
    <T> List<T> findByCriteria(DetachedCriteria criteria);

    /**
     * @param sql
     * @param firstResult
     * @param maxResult
     * @param params
     * @param types
     * @param clazz
     * @return List
     */
    List findMapBySql(String sql, int firstResult, int maxResult, Object[] params, Type[] types, Class clazz);

    /**
     * @param sql
     * @param params
     * @param types
     * @return int
     */
    int countBySql(String sql, Object[] params, Type[] types);

    /**
     * @param sql
     * @param params
     * @param types
     * @param clazz
     * @return List
     */
    List findMapBySql(String sql, Object[] params, Type[] types, Class clazz);

    /**
     * @param example
     * @param condition
     * @param enableLike
     * @return List
     */
    List findByExample(Object example, String condition, boolean enableLike);

    /**
     * @param procSql
     * @param params
     * @return ProcResult
     */
    ProcResult find_procedure(String procSql, Object... params);

    /**
     * @param procSql
     * @param params
     * @return List
     */
    ProcResult find_procedure_multi(String procSql, Object... params);

    /**
     * @param procSql    sql 脚本
     * @param listParams 参数
     * @param outCount   输出参数个数
     * @return ProcResult
     */
    ProcResult find_procedure_list(final String procSql, final List<Object> listParams, final int outCount);

    /**
     * @param procSql  sql 脚本
     * @param params   参数
     * @param outCount 输出参数个数
     * @return ProcResult
     */
    ProcResult upd_procedure(String procSql, final List<Object> params, final int outCount);

}
