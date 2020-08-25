package JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * 这个类的方法，自己来处理连接问题
 * 无需外界传递
 *
 * 通过JdbcUtils.getConnection()得到连接，有可能是事务连接 ，有可能是普通连接
 * JdbcUtils.releaseConnection()完成对连接的释放!如果是普通连接，则关闭!
 * @author Canoe
 *
 */

public class TxQueryRunner extends QueryRunner{

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		/**
		 * 1.得到连接
		 * 2.执行父类中的方法,传递连接对象
		 * 3.释放连接
		 * 4.返回值
		 */
		Connection con = JdbcUtils.getConnection();
		int [] result = super.batch(con, sql, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}
	@Override
	public int execute(String sql, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.execute(con, sql, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> execute(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		@SuppressWarnings("unchecked")
		T result = (T) super.execute(con, sql, rsh, params);
		JdbcUtils.releaseConnection(con);
		return (List<T>) result;
	}
	@Override
	public <T> T insert(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result = super.insert(con, sql, rsh, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T insertBatch(String sql, ResultSetHandler<T> rsh, Object[][] params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		T result = super.insertBatch(con, sql, rsh, params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, Object param, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		@SuppressWarnings("deprecation")
		T result = super.query(con, sql, param, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, Object[] params, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		@SuppressWarnings("deprecation")
		T result = super.query(con, sql, params , rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		@SuppressWarnings("deprecation")
		T result = super.query(con, sql, rsh, params );
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		@SuppressWarnings("deprecation")
		T result = super.query(con, sql, rsh);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con,sql,params);
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql, Object param) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con, sql,param );
		JdbcUtils.releaseConnection(con);
		return result;
	}

	@Override
	public int update(String sql) throws SQLException {
		Connection con = JdbcUtils.getConnection();
		int result = super.update(con, sql);
		JdbcUtils.releaseConnection(con);
		return result;
	}
	
}
