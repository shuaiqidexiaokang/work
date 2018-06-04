package com.lzk.dao.impl;

import com.lzk.dao.BaseDao;
import com.lzk.pojo.PageInfo;
import com.lzk.pojo.ProcResult;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.jdbc.Work;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.*;
import java.util.*;

/**
 * @author lzk
 * @date 2018-06-01 15:16
 * @desc
 */
@Repository
public class BaseDaoImpl implements BaseDao {
    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);
    @Resource
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {

        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public <T> Serializable save(T obj) {
        return this.getCurrentSession().save(obj);
    }

    @Override
    public <T> void delete(T obj) {

        this.getCurrentSession().delete(obj);
    }

    @Override
    public <T> void update(T obj) {

        this.getCurrentSession().update(obj);
    }

    @Override
    public Object saveOrUpdate(Object obj) {

        this.getCurrentSession().saveOrUpdate(obj);
        return obj;
    }

    @Override
    public <T> void batchSave(List<T> entityList) {

        Session session = getCurrentSession();
        for (int i = 0; i < entityList.size(); i++) {
            T entity = entityList.get(i);
            session.save(entity);
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public <T> void batchUpdate(List<T> entityList) {

        Session session = getCurrentSession();
        for (int i = 0; i < entityList.size(); i++) {
            T entity = entityList.get(i);
            session.update(entity);
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public <T> void batchDelete(List<T> entityList) {

        Session session = getCurrentSession();
        for (int i = 0; i < entityList.size(); i++) {
            T entity = entityList.get(i);
            session.delete(entity);
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public <T> void batchSaveOrUpdate(List<T> entityList) {

        Session session = getCurrentSession();
        for (int i = 0; i < entityList.size(); i++) {
            T entity = entityList.get(i);
            session.saveOrUpdate(entity);
            if (i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> list(Class<T> clazz) {

        Criteria ct = this.getCurrentSession().createCriteria(clazz);
        return ct.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> clazz, Serializable id) {

        return (T) this.getCurrentSession().get(clazz, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String hql) {

        Query query = this.getCurrentSession().createQuery(hql);
        List<T> ls = query.list();
        if (ls != null && ls.size() > 0) {
            return ls.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String hql, Map<String, Object> params) {

        Query query = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        List<T> ls = query.list();
        if (ls != null && ls.size() > 0) {
            return ls.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> find(String hql) {

        Query query = this.getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> find(String hql, Map<String, Object> params) {

        Query query = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                Object obj = params.get(key);
                if (obj instanceof Collection<?>) {
                    query.setParameterList(key, (Collection<?>) obj);
                } else if (obj instanceof Object[]) {
                    query.setParameterList(key, (Object[]) obj);
                } else {
                    query.setParameter(key, obj);
                }
            }
        }
        return query.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> find(String hql, int page, int rows) {

        Query query = this.getCurrentSession().createQuery(hql);
        return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> find(String hql, Map<String, Object> params, int page, int rows) {

        Query query = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public Long count(String hql) {

        Query query = this.getCurrentSession().createQuery(hql);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long count(String hql, Map<String, Object> params) {

        Query query = this.getCurrentSession().createQuery(hql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
        }
        return (Long) query.uniqueResult();
    }

    @Override
    public int executeHql(String hql) {

        Query query = this.getCurrentSession().createQuery(hql);
        return query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBySql(String sql) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        List<T> ls = sqlQuery.list();
        if (ls != null && ls.size() > 0) {
            return ls.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBySql(String sql, Map<String, Object> params, Class<T> clazz) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.addEntity(clazz);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sqlQuery.setParameter(key, params.get(key));
            }
        }
        List<T> ls = sqlQuery.list();
        if (ls != null && ls.size() > 0) {
            return ls.get(0);
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findBySql(String sql, Class<T> clazz) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.addEntity(clazz);
        return sqlQuery.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findBySql(String sql, Map<String, Object> params) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sqlQuery.setParameter(key, params.get(key));
            }
        }
        return sqlQuery.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findBySql(String sql, int page, int rows, Class<T> clazz) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        sqlQuery.addEntity(clazz);
        return sqlQuery.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> findBySql(String sql, Map<String, Object> params, int page, int rows, Class<T> clazz) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sqlQuery.setParameter(key, params.get(key));
            }
        }
        sqlQuery.addEntity(clazz);
        return sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
    }

    @Override
    public Long countBySql(String sql) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        return ((BigInteger) sqlQuery.uniqueResult()).longValue();
    }

    @Override
    public Long countBySql(String sql, Map<String, Object> params) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sqlQuery.setParameter(key, params.get(key));
            }
        }
        return (Long) sqlQuery.uniqueResult();
    }

    /**
     * 返回存储过程结果集
     *
     * @param procSql 存储过程调用名称
     * @param params  参数
     * @return ProcResult 返回结果集
     */
    @SuppressWarnings("unckecked")
    @Override
    public ProcResult find_procedure(final String procSql, final Object... params) {
        final List<Map<String, Object>> resultData = new ArrayList<Map<String, Object>>();
        final ProcResult result = new ProcResult();
        // 获取最后一个参数，得到输出参数个数
        int paramsLength = params.length;
        final int outCount = (Integer) params[paramsLength - 1];
        Session session = this.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection conn) throws SQLException {
                CallableStatement cs = null;
                ResultSet rs = null;
                try {
                    cs = conn.prepareCall(procSql);
                    int paramsLength = params.length - 1;
                    for (int i = 0; i < paramsLength; i++) {
                        //设置参数
                        cs.setObject(i + 1, params[i]);
                    }
                    // out 参数 两个输出参数
                    if (outCount == 2) {
                        cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 2, Types.VARCHAR);
                    } else if (outCount == 3) {
                        cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 2, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 3, Types.VARCHAR);
                    }
                    rs = cs.executeQuery();

                    ResultSetMetaData metaData = rs.getMetaData();
                    int colCount = metaData.getColumnCount();
                    while (rs.next()) {
                        Map<String, Object> map = new HashMap<String, Object>(16);
                        for (int i = 1; i <= colCount; i++) {
                            String colName = metaData.getColumnName(i);
                            map.put(colName, rs.getObject(colName));
                        }
                        resultData.add(map);
                    }
                    // 获取输出值
                    if (outCount == 2) {
                        result.setOutCode(cs.getInt(paramsLength + 1));
                        result.setOutmsg(cs.getString(paramsLength + 2));
                    } else if (outCount == 3) {
                        result.setCountNum(cs.getInt(paramsLength + 1));
                        result.setOutCode(cs.getInt(paramsLength + 2));
                        result.setOutmsg(cs.getString(paramsLength + 3));
                    }
                } catch (SQLException e) {
                    logger.error("执行存储过程异常：" + e.getStackTrace().toString());
                    throw new SQLException(e);
                } finally {
                    close(cs, rs);
                }

            }
        });

        result.setData(resultData);

        return result;
    }

    /**
     * 返回多结果集
     *
     * @param procSql
     * @param params
     * @return List
     */
    @Override
    public ProcResult find_procedure_multi(String procSql, Object... params) {
        final Map<String, Object> resutMap = new HashMap<>(3);
        final ProcResult result = new ProcResult();
        // 获取最后一个参数，得到输出参数个数
        int paramsLength = params.length;
        final int outCount = (Integer) params[paramsLength - 1];
        Session session = this.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection conn) throws SQLException {
                CallableStatement cs = null;
                ResultSet rs = null;
                try {
                    cs = conn.prepareCall(procSql);
                    int paramsLength = params.length - 1;
                    for (int i = 0; i < paramsLength; i++) {
                        //设置参数
                        cs.setObject(i + 1, params[i]);
                    }
                    // out 参数 两个输出参数
                    if (outCount == 2) {
                        cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 2, Types.VARCHAR);
                    } else if (outCount == 3) {
                        cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 2, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 3, Types.VARCHAR);
                    }
                    // 执行存储
                    boolean hasResult = cs.execute();
                    int retDataCount = 0;
                    while (true) {
                        if (hasResult) {
                            // 获取结果集
                            List<Map<String, Object>> resultData = new ArrayList<Map<String, Object>>();
                            rs = cs.getResultSet();
                            ResultSetMetaData metaData = rs.getMetaData();
                            int colCount = metaData.getColumnCount();
                            while (rs.next()) {
                                Map<String, Object> map = new HashMap<String, Object>(16);
                                for (int i = 1; i <= colCount; i++) {
                                    String colName = metaData.getColumnName(i);
                                    map.put(colName, rs.getObject(colName));
                                }
                                resultData.add(map);
                            }
                            // 放入Map
                            resutMap.put("result_" + retDataCount, resultData);
                        } else {
                            logger.debug("无数据集");
                            int updateCount = cs.getUpdateCount();
                            if (updateCount == -1) {
                                // 无数据集
                                break;
                            }
                        }

                        /**
                         每次判断下一个是否为了数据集
                         stmt.getMoreResults() 为 true表示下一次循环为数据集，false为空
                         */
                        retDataCount++;
                        hasResult = cs.getMoreResults();
                    }


                    // 获取输出值
                    if (outCount == 2) {
                        result.setOutCode(cs.getInt(paramsLength + 1));
                        result.setOutmsg(cs.getString(paramsLength + 2));
                    } else if (outCount == 3) {
                        result.setCountNum(cs.getInt(paramsLength + 1));
                        result.setOutCode(cs.getInt(paramsLength + 2));
                        result.setOutmsg(cs.getString(paramsLength + 3));
                    }
                } catch (SQLException e) {
                    logger.error("执行存储过程异常：" + e.getStackTrace().toString());
                    throw new SQLException(e);
                } finally {
                    close(cs, rs);
                }

            }
        });

        result.setData(resutMap);

        return result;
    }

    @Override
    public ProcResult find_procedure_list(final String procSql, final List<Object> listParams, final int outCount) {
        final List<Map<String, Object>> resultData = new ArrayList<Map<String, Object>>();
        final ProcResult result = new ProcResult();

        Session session = this.getCurrentSession();
        session.doWork(new Work() {
            @Override
            public void execute(Connection conn) {
                CallableStatement cs = null;
                ResultSet rs = null;
                try {
                    cs = conn.prepareCall(procSql);
                    int paramsLength = listParams.size();
                    for (int i = 0; i < paramsLength; i++) {
                        //设置参数
                        cs.setObject(i + 1, listParams.get(i));
                    }
                    // out 参数 两个输出参数
                    if (outCount == 2) {
                        cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 2, Types.VARCHAR);
                    } else if (outCount == 3) {
                        cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 2, Types.INTEGER);
                        cs.registerOutParameter(paramsLength + 3, Types.VARCHAR);
                    }
                    rs = cs.executeQuery();

                    ResultSetMetaData metaData = rs.getMetaData();
                    int colCount = metaData.getColumnCount();
                    while (rs.next()) {
                        Map<String, Object> map = new HashMap<String, Object>(16);
                        for (int i = 1; i <= colCount; i++) {
                            String colName = metaData.getColumnName(i);
                            map.put(colName, rs.getObject(colName));
                        }
                        resultData.add(map);
                    }
                    // 获取输出值
                    if (outCount == 2) {
                        result.setOutCode(cs.getInt(paramsLength + 1));
                        result.setOutmsg(cs.getString(paramsLength + 2));
                    } else if (outCount == 3) {
                        result.setCountNum(cs.getInt(paramsLength + 1));
                        result.setOutCode(cs.getInt(paramsLength + 2));
                        result.setOutmsg(cs.getString(paramsLength + 3));
                    }
                } catch (SQLException e) {
                    logger.error("执行存储过程异常：" + e.getStackTrace().toString());
                } finally {
                    close(cs, rs);
                }

            }
        });

        result.setData(resultData);

        return result;
    }

    /**
     * 执行存储过程
     *
     * @param procSql  存储过程名字
     * @param params   参数
     * @param outCount 输出数字
     * @return
     */
    @SuppressWarnings("unckecked")
    @Override
    public ProcResult upd_procedure(final String procSql, final List<Object> params, final int outCount) {
        Session session = this.getCurrentSession();
        final ProcResult result = new ProcResult();
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                CallableStatement cs = null;
                boolean rs = false;
                cs = connection.prepareCall(procSql);
                int paramsLength = params.size();
                for (int i = 1; i <= paramsLength; i++) {
                    //设置参数
                    cs.setObject(i, params.get(i - 1));
                }
                // out 参数 两个输出参数
                if (outCount == 2) {
                    cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                    cs.registerOutParameter(paramsLength + 2, Types.VARCHAR);
                } else if (outCount == 3) {
                    cs.registerOutParameter(paramsLength + 1, Types.INTEGER);
                    cs.registerOutParameter(paramsLength + 2, Types.INTEGER);
                    cs.registerOutParameter(paramsLength + 3, Types.VARCHAR);
                }
                // 执行更新操作，返回 false
                rs = cs.execute();
                // 获取输出值
                if (outCount == 2) {
                    result.setOutCode(cs.getInt(paramsLength + 1));
                    result.setOutmsg(cs.getString(paramsLength + 2));
                } else if (outCount == 3) {
                    result.setCountNum(cs.getInt(paramsLength + 1));
                    result.setOutCode(cs.getInt(paramsLength + 2));
                    result.setOutmsg(cs.getString(paramsLength + 3));
                }
                cs.close();
            }
        });
        return result;
    }

    @Override
    public int executeSql(String sql) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        return sqlQuery.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findMapBySql(String sql) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findMapBySql(String sql, Map<String, Object> params) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sqlQuery.setParameter(key, params.get(key));
            }
        }
        return sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findMapBySql(String sql, int page, int rows) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        return sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findMapBySql(String sql, Map<String, Object> params, int page, int rows) {

        SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sqlQuery.setParameter(key, params.get(key));
            }
        }
        return sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }


    @Override
    public List findMapBySql(String sql, Object[] params, Type[] types, Class clazz) {

        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        if (clazz != null) {
            query.setResultTransformer(Transformers.aliasToBean(clazz));
        } else {
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        query.setParameters(params, types);
        return query.list();
    }

    @Override
    public List findMapBySql(String sql, int firstResult, int maxResult, Object[] params, Type[] types, Class clazz) {

        if (clazz != null) {
            {
                return this.getCurrentSession().createSQLQuery(sql).setParameters(params, types).setFirstResult(firstResult)
                        .setMaxResults(maxResult).setResultTransformer(Transformers.aliasToBean(clazz)).list();
            }
        } else {
            {
                return this.getCurrentSession().createSQLQuery(sql).setParameters(params, types).setFirstResult(firstResult)
                        .setMaxResults(maxResult).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            }
        }
    }


    @Override
    public int countBySql(String sql, Object[] params, Type[] types) {

        SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
        query.setParameters(params, types);
        return Integer.parseInt(query.uniqueResult().toString());
    }

    @Override
    public List<Map> findMap(String hql) {

        Query query = this.getCurrentSession().createQuery(hql);
        query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    @Override
    public <T> List<T> getListByCriteria(DetachedCriteria criteria, PageInfo page) {

        if (page == null) {
            return criteria.getExecutableCriteria(getCurrentSession()).setProjection(null)
                    .setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
        } else {
            return criteria.getExecutableCriteria(getCurrentSession()).setProjection(null)
                    .setResultTransformer(CriteriaSpecification.ROOT_ENTITY).setFirstResult((page.getPageNum() - 1) * page.getPageSize())
                    .setMaxResults(page.getPageSize()).list();
        }
    }

    @Override
    public List<?> getListByCriteria(DetachedCriteria criteria, Integer startPage, Integer pageSize) {

        if (startPage != null && pageSize != null) {
            return criteria.getExecutableCriteria(getCurrentSession()).setProjection(null)
                    .setResultTransformer(CriteriaSpecification.ROOT_ENTITY).setFirstResult(startPage).setMaxResults(pageSize).list();
        } else {
            return criteria.getExecutableCriteria(getCurrentSession()).setProjection(null)
                    .setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
        }
    }

    @Override
    public <T> List<T> findByCriteria(DetachedCriteria criteria) {

        return criteria.getExecutableCriteria(getCurrentSession()).setProjection(null)
                .setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
    }

    @Override
    public int getCountByCriteria(DetachedCriteria criteria) {

        return ((Long) criteria.getExecutableCriteria(getCurrentSession()).setProjection(Projections.rowCount()).uniqueResult()).intValue();
    }

    @Override
    public List findByExample(Object example) {

        return this.getCurrentSession().createCriteria(example.getClass()).add(Example.create(example)).list();
    }

    @Override
    public List findByExample(Object example, String condition, boolean enableLike) {

        Criteria ec = this.getCurrentSession().createCriteria(example.getClass());
        if (enableLike) {
            ec.add(Example.create(example).enableLike());
        } else {
            ec.add(Example.create(example));
        }
        if (condition != null && !"".equals(condition)) {
            String newCondition = condition.replaceAll("`", "'");
            ec.add(Restrictions.sqlRestriction(newCondition));
        }
        return ec.list();
    }

    /**
     * 关闭连接
     *
     * @param cs CallableStatement
     * @param rs ResultSet
     */
    private void close(CallableStatement cs, ResultSet rs) {
        try {
            if (cs != null) {
                cs.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
