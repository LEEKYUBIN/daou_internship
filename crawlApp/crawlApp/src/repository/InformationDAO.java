package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.SqlSessionUtil;
import vo.Information;

public class InformationDAO {

	private static InformationDAO uniqueInstance = new InformationDAO();

	private InformationDAO() {
	}

	public static InformationDAO getInstance() {
		return uniqueInstance;
	}

	private SqlSessionUtil sqlSessionUtil = util.SqlSessionUtil.getInstance();

	private void saveData(Information information) {
		SqlSession session = null;
		try {
			session = sqlSessionUtil.getSession();
			session.insert("information.insert", information);
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void save(Information information, List<List<String>> attribute) {

		for (int i = 0; i < attribute.size(); i++) {
			if ("".equals(attribute.get(i).get(0)) || attribute.get(i).get(0) == null)
				return;
			else if ("".equals(attribute.get(i).get(1)) || attribute.get(i).get(1) == null)
				return;
			else {
				information.setAttributeText(attribute.get(i).get(0));
				information.setAttribute(attribute.get(i).get(1));
				saveData(information);
			}
		}
	}
}
