package JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	// �����ļ���Ĭ������!Ҫ��������c3p0-config.xml
	 private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	 private static final ThreadLocal<Connection> THREADlOCAL =new ThreadLocal<Connection>();
	 /**
	      * ʹ�����ӳط���һ�����ӳض���
	  * @return
	  * @throws SQLException
	  */
	 public static Connection getConnection () throws SQLException{
		 Connection con = THREADlOCAL.get();
		 // ��con������null��˵���Ѿ�������beginTransaction()����ʾ�Ѿ�����������
		 if(con != null) {
			 return con;
		 }
		 return dataSource.getConnection();
	 }
	 
	 /**
	      * �������ӳض���
	  * @return
	  */
	 public static DataSource getDataSource() {
		 return dataSource;
	 }
	 
	 /**
	     *    ��������
	  * 1.��ȡһ��Connection������������setAutoCommit(false)
	  * 2.��Ҫ��֤dao��ʹ�õ������Ǹոմ�����
	  * --------------
	  * 1.����һ��Connection������Ϊ�ֶ��ύ
	  * 2.�����Connection��dao��
	  * 3.����commitTransaction()��rollbackTransaction()���Ի�ȡ��
	  */
	 public static void beginTransaction() throws SQLException {
		 Connection con = THREADlOCAL.get(); //��ȡ��ǰ�̵߳�ר������
		 if(con != null) {
			 throw new SQLException("�����Ѿ��������������ظ�������");
		 }
		 /**
		  * 1.��con��ֵ
		  * 2.con����Ϊ�ֶ��ύ
		  */
		 con = getConnection(); //��con��ֵ����ʾ�����Ѿ�����
		 con.setAutoCommit(false);
		 
		 THREADlOCAL.set(con);//�ѵ�ǰ�̱߳�������
	 }
	 
	 /**
	      *  �ύ����
	  *  ��ȡbeginTransaction�ṩ��Connection������commit()
	  * @throws SQLException 
	  * 
	  */
	 public static void commitTransaction() throws SQLException {
		 Connection con = THREADlOCAL.get(); //��ȡ��ǰ�̵߳�ר������
		 if(con == null) {
			 throw new SQLException("��û�������񣬲����ύ");
		 }
		 con.commit();
		 con.close();
		 // ��������Ϊnull,��ʾ�����Ѿ�����,�´���ȥ����getConection()���صľͲ���con��
		 THREADlOCAL.remove();// ��THREADLOCAL���Ƴ�
	 }
	 
	 /**
	      *  �ύ����
	      *  ��ȡbeginTransaction�ṩ��Connection������rollback()
	  * @throws SQLException
	  */
	 public static void rollbackTransaction() throws SQLException {
		 Connection con = THREADlOCAL.get();
		 if(con == null) {
			 throw new SQLException("��û�������񣬲��ܻع�");
		 }
		 con.rollback();
		 con.close();
		 THREADlOCAL.remove();
	 }
	 
	/**
	 *  �ͷ�����
	 * @param connetion
	 * @throws SQLException
	 */
	 public static void releaseConnection(Connection connection) throws SQLException {
		 Connection con = THREADlOCAL.get();
		 /**
	      *     �ж����ǲ�������ר�ã�����ǣ��Ͳ��ر�
	      *     �����������͹ر�
	      */
		 // ���con == null��˵��������û��������ôConnectionһ����������ר�е� 
		 if(con == null) {
			 connection.close();
		 }
		 // ���con != null ,˵��������������ô��Ҫ�жϲ��������Ƿ���con���,�����ȣ�˵���������Ӳ�������ר�����ӣ���Ӧ�ùر�
		 if(con != connection) {
			 connection.close();
		 }
	 }
}
