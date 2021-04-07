package service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Element;

import common.Constant;
import repository.InformationDAO;
import util.NumberUtil;
import vo.Information;

public class InformationService {

	private static InformationService uniqueInstance = new InformationService();

	private InformationService() {
	}

	public static InformationService getInstance() {
		return uniqueInstance;
	}

	private InformationDAO informationRepository = InformationDAO.getInstance();

	public void informationSave(Element shop, int itemNo, String itemName) {
		Information information = new Information();
		List<List<String>> informationList = new ArrayList<>();
		information.setItemNo(itemNo);
		informationList.add(getInformationCrawl(shop, 0, "인치", "화면크기"));
		informationList.add(getInformationCrawl(shop, 1, "kg", "무게"));
		informationList.add(getInformationCrawl(shop, 3, Constant.osMap, "운영체제", itemName));
		informationList.add(getInformationCrawl(shop, 5, Constant.chipMakerMap, "칩셋 제조사", itemName));
		informationList.add(getInformationCrawl(shop, 6, Constant.coreMap, "코어종류", itemName));
		informationList.add(getInformationCrawl(shop, 2, Constant.delim, "종류", itemName));
		save(information, informationList);
	}

	private Element getCrawl(Element shop, int i, String selectStr) {
		try {
			return shop.select(selectStr).get(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<String> getInformationCrawl(Element shop, int i, String divide, String key) {
		Element element = getCrawl(shop, i, Constant.informationString);

		if (element == null) {
			return Constant.StringArrayException;
		}
		String[] splitInformation = new String[2];
		String[] split = element.text().split(" : ");

		if (split[0] != null && split[0].equals(key)) {
			splitInformation[0] = split[1];
			int idx = split[1].indexOf(divide);

			try {
				splitInformation[1] = split[1].substring(0, idx);
				if ("무게".equals(key)) {
					splitInformation[1] = NumberUtil.floatCompare(splitInformation[1], 1.0f);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return Constant.StringArrayException;
			}
		} else {
			return Constant.StringArrayException;
		}
		return Arrays.asList(splitInformation);
	}

	private List<String> getInformationCrawl(Element shop, int i, Map<String, String> map, String key, String name) {

		try {
			if (name.contains("Apple")) {
				if (i == 3) {
					i = 2;
					String[] splitInformation = new String[2];
					splitInformation[0] = "macOS";
					splitInformation[1] = "mac";
					return Arrays.asList(splitInformation);
				} else if (i == 5) {
					i = 4;
				} else if (i == 6) {
					i = 5;
				} else {
					return Constant.StringArrayException;
				}
			}

			Element element = getCrawl(shop, i, Constant.informationString);
			if (element == null) {
				return Constant.StringArrayException;
			}
			String[] splitInformation = new String[2];
			String[] split = element.text().split(" : ");

			if (split[0] != null && split[0].equals(key)) {
				splitInformation[0] = split[1];

				for (String mapKey : map.keySet()) {
					int idx = split[1].indexOf(mapKey);

					if (idx >= 0) {
						splitInformation[1] = map.get(mapKey);
						break;
					}
				}
			} else {
				return Constant.StringArrayException;
			}
			return Arrays.asList(splitInformation);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.StringArrayException;
		}

	}

	private List<String> getInformationCrawl(Element shop, int i, List<String> list, String key, String name) {

		try {
			if (name.contains("Apple")) {
				i = 3;
				Element element = getCrawl(shop, i, Constant.informationString);
				String[] splitInformation = new String[2];
				String[] split = element.text().split(" : ");
				splitInformation[0] = split[1];
				splitInformation[1] = split[1];
				return Arrays.asList(splitInformation);
			}

			Element element = getCrawl(shop, i, Constant.informationString);

			if (element == null) {
				return Constant.StringArrayException;
			}
			String[] splitInformation = new String[2];
			String[] split = element.text().split(" : ");

			if (split[0] != null && split[0].equals(key)) {
				splitInformation[0] = split[1];
				for (String delim : list) {
					int idx = split[1].indexOf(delim);
					if ("셀러론".equals(split[1])) {
						splitInformation[1] = "셀러론";
						break;
					} else if ("코어M".equals(split[1])) {
						splitInformation[1] = "코어M";
						break;
					} else if (idx >= 0) {
						splitInformation[1] = split[1].substring(0, idx);
						break;
					}
				}
			} else {
				return Constant.StringArrayException;
			}
			return Arrays.asList(splitInformation);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.StringArrayException;
		}

	}

	private void save(Information information, List<List<String>> informationList) {
		boolean checkInfo = false;
		for (int i = 0; i < informationList.size(); i++) {
			if (Constant.StringArrayException.equals(informationList.get(i))) {
				checkInfo = false;
			} else {
				checkInfo = true;
			}
		}
		if (checkInfo) {
			informationRepository.save(information, informationList);
		}

	}

}
