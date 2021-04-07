package repository;

import org.apache.ibatis.session.SqlSession;

import util.SqlSessionUtil;
import vo.Item;

public class ItemsDAO {

	private static ItemsDAO uniqueInstance = new ItemsDAO();

	private ItemsDAO() {
	}

	public static ItemsDAO getInstance() {
		return uniqueInstance;
	}

	private SqlSessionUtil sqlSessionUtil = util.SqlSessionUtil.getInstance();

	public void save(Item item) {

		SqlSession session = null;
		try {
			session = sqlSessionUtil.getSession();
			session.insert("items.insert", item);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}