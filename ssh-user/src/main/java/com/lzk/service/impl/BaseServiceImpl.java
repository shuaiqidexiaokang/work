package com.lzk.service.impl;

import com.lzk.dao.BaseDao;
import com.lzk.pojo.PageInfo;
import com.lzk.pojo.ProcResult;
import com.lzk.service.BaseService;
import com.lzk.utils.StrUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 *
 */
@Service
public class BaseServiceImpl implements BaseService {
    private static final Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    @Resource
    private BaseDao baseDao;

    @Override
    public <T> Serializable save(T obj) {

        return baseDao.save(obj);
    }

    @Override
    public <T> void delete(T obj) {

        baseDao.delete(obj);
    }

    @Override
    public <T> void update(T obj) {

        baseDao.update(obj);
    }

    @Override
    public Object saveOrUpdate(Object obj) {

        return baseDao.saveOrUpdate(obj);
    }

    @Override
    public <T> void batchSave(List<T> entityList) {

        baseDao.batchSave(entityList);
    }

    @Override
    public <T> void batchUpdate(List<T> entityList) {

        baseDao.batchUpdate(entityList);
    }

    @Override
    public <T> void batchDelete(List<T> entityList) {

        baseDao.batchDelete(entityList);
    }

    @Override
    public <T> void batchSaveOrUpdate(List<T> entityList) {

        baseDao.batchSaveOrUpdate(entityList);
    }

    @Override
    public <T> List<T> list(Class<T> clazz) {

        return baseDao.list(clazz);
    }

    @Override
    public <T> T get(Class<T> clazz, Serializable id) {

        return baseDao.get(clazz, id);
    }

    @Override
    public <T> T get(String hql) {

        return baseDao.get(hql);
    }

    @Override
    public <T> T get(String hql, Map<String, Object> params) {

        return baseDao.get(hql, params);
    }

    @Override
    public <T> List<T> find(String hql) {

        return baseDao.find(hql);
    }

    @Override
    public <T> List<T> find(String hql, Map<String, Object> params) {

        return baseDao.find(hql, params);
    }

    @Override
    public <T> List<T> find(String hql, int page, int rows) {

        return baseDao.find(hql, page, rows);
    }

    @Override
    public <T> List<T> find(String hql, Map<String, Object> params, int page, int rows) {

        return baseDao.find(hql, params, page, rows);
    }

    @Override
    public long count(String hql) {

        Long count = baseDao.count(hql);
        if (count == null) {
            return 0;
        }
        return 0;

    }

    @Override
    public Long count(String hql, Map<String, Object> params) {

        return baseDao.count(hql, params);
    }

    @Override
    public int executeHql(String hql) {

        return baseDao.executeHql(hql);
    }

    @Override
    public <T> T getBySql(String sql) {

        return baseDao.getBySql(sql);
    }

    @Override
    public <T> T getBySql(String sql, Map<String, Object> params,Class<T> clazz) {

        return baseDao.getBySql(sql, params,clazz);
    }

    @Override
    public <T> List<T> findBySql(String sql, Class<T> clazz) {

        return baseDao.findBySql(sql, clazz);
    }

    @Override
    public <T> List<T> findBySql(String sql, Map<String, Object> params) {

        return baseDao.findBySql(sql, params);
    }

    @Override
    public <T> List<T> findBySql(String sql, int page, int rows, Class<T> clazz) {

        return baseDao.findBySql(sql, page, rows, clazz);
    }

    @Override
    public <T> List<T> findBySql(String sql, Map<String, Object> params, int page, int rows, Class<T> clazz) {

        return baseDao.findBySql(sql, params, page, rows, clazz);
    }

    @Override
    public List<Map<String, Object>> findMapBySql(String sql) {

        return baseDao.findMapBySql(sql);
    }

    @Override
    public List<Map<String, Object>> findMapBySql(String sql, Map<String, Object> params) {

        return baseDao.findMapBySql(sql, params);
    }

    @Override
    public List<Map<String, Object>> findMapBySql(String sql, int page, int rows) {

        return baseDao.findMapBySql(sql, page, rows);
    }

    @Override
    public List<Map<String, Object>> findMapBySql(String sql, Map<String, Object> params, int page, int rows) {

        return baseDao.findMapBySql(sql, params, page, rows);
    }

    @Override
    public List findMapBySql(String sql, Object[] params, Type[] types, Class clazz) {

        return baseDao.findMapBySql(sql, params, types, clazz);
    }

    @Override
    public List findMapBySql(String sql, String countStr, PageInfo pageInfo, Object[] params, Type[] types, Class clazz) {

        if (StrUtil.isEmpty(countStr)) {
            countStr = "count(*)";
        }
        String countSql = "select " + countStr + " from (" + sql + ") as table_alias";
        int count = this.countBySql(countSql, params, types);
        pageInfo.setCount(count);
        return baseDao
                .findMapBySql(sql, pageInfo.getPageSize() * (pageInfo.getPageNum() - 1), pageInfo.getPageSize(), params, types, clazz);
    }

    @Override
    public int countBySql(String sql, Object[] params, Type[] types) {

        return baseDao.countBySql(sql, params, types);
    }

    @Override
    public Long countBySql(String sql) {

        return baseDao.countBySql(sql);
    }

    @Override
    public Long countBySql(String sql, Map<String, Object> params) {

        return baseDao.countBySql(sql, params);
    }

    @Override
    public int executeSql(String sql) {

        return baseDao.executeSql(sql);
    }

    @Override
    public <T> List<T> getListByCriteria(DetachedCriteria criteria, PageInfo page) {

        return baseDao.getListByCriteria(criteria, page);
    }

    @Override
    public List<?> getListByCriteria(DetachedCriteria criteria, Integer startPage, Integer pageSize) {

        return baseDao.getListByCriteria(criteria, startPage, pageSize);
    }

    @Override
    public int getCountByCriteria(DetachedCriteria criteria) {

        return baseDao.getCountByCriteria(criteria);
    }

    @Override
    public List findByExample(Object example) {

        return baseDao.findByExample(example);
    }

    @Override
    public List findByExample(Object example, String condition, boolean enableLike) {

        return baseDao.findByExample(example, condition, enableLike);
    }

    /**
     * 通用存储过程查询（单结果集）
     *
     * @param procSql 查询SQL
     * @param params  参数,
     *                最后一个参数为存储的输出个数，目前只能为 2 或 3
     * @return ProcResult
     */
    @Override
    public ProcResult find_procedure(String procSql, Object... params) {
        int length = params.length;
        try {
            int outCount = length >= 1 ? (Integer) params[length - 1] : -1;
            if (outCount == 2 || outCount == 3) {
                return baseDao.find_procedure(procSql, params);
            }
        } catch (Exception e) {
            logger.error("方法：find_procedure 执行异常:" + e.getMessage());
            return new ProcResult(-999, e.getMessage());
        }
        return new ProcResult(0, "");
    }

    /**
     * 通用存储过程查询（多结果集）
     *
     * @param procSql 查询SQL
     * @param params  参数
     * @return ProcResult 返回结果集
     */
    @Override
    public ProcResult find_procedure_multi(String procSql, Object... params) {
        int length = params.length;
        try {
            int outCount = length >= 1 ? (Integer) params[length - 1] : -1;
            if (outCount == 2 || outCount == 3) {
                return baseDao.find_procedure_multi(procSql, params);
            }
        } catch (Exception e) {
            logger.error("方法：find_procedure 执行异常:" + e.getMessage());
            return new ProcResult(-999, e.getMessage());
        }
        return new ProcResult(0, "");
    }

    /**
     * 通用存储过程查询（单结果集）
     *
     * @param procSql    查询SQL
     * @param listParams 参数
     * @param outCount   输出参数个数
     * @return
     */
    @Override
    public ProcResult find_procedure_list(final String procSql, final List<Object> listParams, final int outCount) throws Exception {
        try {
            if (outCount == 2 || outCount == 3) {
                return baseDao.find_procedure_list(procSql, listParams, outCount);
            }
        } catch (Exception e) {
            logger.error("方法：find_procedure 执行异常:" + e.getMessage());
            throw new Exception(e);
        }
        return new ProcResult(-1, "输出参数个数只能为2或3");
    }

    /**
     * 通用存储过程（更新、删除用）
     *
     * @param procSql  存储过程 SQL 如 {call p_test_upd(?,?,?)}
     * @param params   参数
     * @param outCount 输出参数个数
     * @return
     */
    @Override
    public ProcResult upd_procedure(final String procSql, final List<Object> params, final int outCount) throws Exception {
        try {
            if (outCount == 2 || outCount == 3) {
                return baseDao.upd_procedure(procSql, params, outCount);
            }
        } catch (Exception e) {
            logger.error("方法：upd_procedure 执行异常:" + e.getMessage());
            throw new Exception(e);
        }
        return new ProcResult(-1, "输出参数个数只能为2或3");
    }

    /**
     * APi 异常
     *
     * @param result 存储返回结果
     * @throws Exception
     */
    @Override
    public void callProducerExceptionBack(ProcResult result) throws Exception {
        if (result != null && result.getOutCode() != 0) {
            final String code = String.valueOf(result.getOutCode() == null ? 0 : result.getOutCode());
            final String msg = result.getOutmsg();
            logger.error("错误代码:" + code + " 错误消息：" + msg);
            throw new Exception("错误代码:" + code + " 错误消息：" + msg);
        }
    }

    @Override
    public boolean isExist(String hql, Map<String, Object> param) {

        return count(hql, param) > 0;
    }

    @Override
    public <T> List<T> findByCriteria(DetachedCriteria criteria) {

        return baseDao.findByCriteria(criteria);
    }
}
