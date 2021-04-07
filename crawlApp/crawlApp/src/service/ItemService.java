package service;

import java.sql.Date;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import common.Constant;
import repository.ItemsDAO;
import util.StringUtil;
import vo.Item;

public class ItemService {

	private static ItemService uniqueInstance = new ItemService();

	private ItemService() {
	}

	public static ItemService getInstance() {
		return uniqueInstance;
	}

	private ItemsDAO itemRepository = ItemsDAO.getInstance();

	public Item itemSave(String name, Element shop, int i) {
		Item item = new Item();
		item.setCategoryNo(Constant.laptop);
		item.setName(name);
		item.setImg(getCrawlImg(shop));
		item.setPrice(StringUtil.numberParsing(getCrawl(shop, 0, Constant.priceString).text()));
		item.setRegdate(getCrawlRegdate(shop, 1, getCrawlCheck(shop, 1)));
		item.setReview(StringUtil.numberParsing(getCrawl(shop, 0, Constant.reviewNumString).text()));
		item.setReviewNum(getCrawlReview(shop, i, getCrawlCheck(shop, 1),
				StringUtil.numberParsing(getCrawl(shop, 0, Constant.reviewNumString).text())));
		save(item);
		return item;
	}

	public Element getCrawl(Element shop, int i, String selectStr) {
		try {
			return shop.select(selectStr).get(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getCrawlImg(Element shop) {
		try {
			String imageUrl = shop.select(Constant.imgString).attr("href");
			Document docu = Jsoup.connect(imageUrl).get();
			Element imageShop = docu.select("img").first();
			String imageSrc = imageShop.attr("src");
			return imageSrc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.stringException;
	}

	private int getCrawlCheck(Element shop, int i) {
		Element CheckElement = getCrawl(shop, i, Constant.regdateString);
		try {
			if (CheckElement.text().contains("찜")) {
				return Constant.intException;
			} else if (CheckElement.text().contains("구매")) {
				return Constant.noRegdateException;
			} else {
				return Constant.checkCorrect;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.noRegdateException;
	}

	private Date getCrawlRegdate(Element shop, int i, int check) {
		Element regElement = getCrawl(shop, 1, Constant.regdateString);
		try {
			if (check == Constant.intException) {
				regElement = getCrawl(shop, 0, Constant.regdateString);
			} else if (check == Constant.noRegdateException) {
				return Constant.defaultRegdate;
			}
			StringTokenizer splitRegdate = StringUtil.splitRegdate(regElement.text());
			return StringUtil.sumRegdate(splitRegdate.nextToken(), splitRegdate.nextToken());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private int getCrawlReview(Element shop, int i, int check, int reviewNum) {
		if (check == Constant.checkCorrect) {
			try {
				String widthStr = getCrawl(shop, 0, Constant.reviewString).attr(Constant.reviewTagAttribute)
						.substring(Constant.subStringReviewIndex);
				widthStr = widthStr.substring(0, widthStr.length() - 1);
				return reviewCheck(Integer.parseInt(widthStr), reviewNum);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Constant.intException;
		}

		else {
			return reviewCheck(Constant.intException, reviewNum);
		}
	}

	private int reviewCheck(int review, int reviewNum) {
		try {
			if (review == Constant.intException && reviewNum != Constant.intException) {
				return Constant.reviewException;
			}
			return review;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.reviewException;
		}

	}

	private void save(Item item) {
		if ("".equals(item.getImg()) || item.getImg() == null) {
			return;
		} else if ("".equals(item.getName()) || item.getName() == null) {
			return;
		} else if ("".equals(item.getRegdate()) || item.getRegdate() == null) {
			return;
		} else {

			itemRepository.save(item);
		}

	}

}
