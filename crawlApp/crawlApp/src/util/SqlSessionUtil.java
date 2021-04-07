package util;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtil {

	private static SqlSessionUtil uniqueInstance = new SqlSessionUtil();

	private SqlSessionUtil() {
	}

	public static SqlSessionUtil getInstance() {
		return uniqueInstance;
	}

	public SqlSession getSession() {
		try {
			Reader reader = Resources.getResourceAsReader("config/mybatis-config.xml");
			SqlSessionFactoryBuilder sb = new SqlSessionFactoryBuilder();
			SqlSessionFactory factory = sb.build(reader);
			return factory.openSession(true);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
