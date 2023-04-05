package database.dao;

import utility.JDBCUtility;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 我亦无他,唯手熟尔
 *
 * @Author: ttpfx
 * @Date: 2021/10/01/19:53
 * @Description: 为人所不为, 能人所不能
 */
public class BasicDAO<T> {
    private QueryRunner queryRunner = new QueryRunner();
    public int update(String sql, Object... parameters){
        Connection connection = JDBCUtility.getConnection();
        try {
            int update = queryRunner.update(connection, sql, parameters);
            return update;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtility.close(connection);
        }
    }

    /**
     *
     * @param sql sql 语句，可以有 ?
     * @param clazz 传入一个类的Class对象 比如 Actor.class
     * @param parameters 传入 ? 的具体的值，可以是多个
     * @return 根据Actor.class 返回对应的 ArrayList 集合
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters) {

        Connection connection = null;
        try {
            connection = JDBCUtility.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<T>(clazz), parameters);

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        } finally {
            JDBCUtility.close(connection);
        }

    }

    //查询单行结果 的通用方法
    public T querySingle(String sql, Class<T> clazz, Object... parameters) {

        Connection connection = null;
        try {
            connection = JDBCUtility.getConnection();
            return  queryRunner.query(connection, sql, new BeanHandler<T>(clazz), parameters);

        } catch (SQLException e) {
            //将编译异常->运行异常 ,抛出
            throw  new RuntimeException(e);
        } finally {
            JDBCUtility.close(connection);
        }
    }

    //查询单行单列的方法,即返回单值的方法

    public Object queryScalar(String sql, Object... parameters) {

        Connection connection = null;
        try {
            connection = JDBCUtility.getConnection();
            return  queryRunner.query(connection, sql, new ScalarHandler(), parameters);

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        } finally {
            JDBCUtility.close(connection);
        }
    }

}
