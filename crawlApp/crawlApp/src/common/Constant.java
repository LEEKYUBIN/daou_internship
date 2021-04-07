package common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Constant {

	public static final int maxPage = 10;
	public static final int cnt = 0;
	public static final int laptop = 1;
	public static final int subStringReviewIndex = 6;

	public static final String docFront = "https://search.shopping.naver.com/search/category?catId=50000151&exagency=true&exrental=true&exused=true&frm=NVSHCAT&origQuery&pagingIndex=";
	public static final String docBack = "&pagingSize=5&productSet=total&query&sort=rel&timestamp=&viewType=list";

	public static final String imageFront = "https://search.shopping.naver.com/search/category?brand=116504%20108602%20147285&catId=50000151&"
			+ "exagency=true&exrental=true&exused=true&frm=NVSHPRC&maxPrice=1400000&minPrice=700000&origQuery&pagingIndex=";
	public static final String imageBack = "&pagingSize=1&productSet=total&query&sort=rel&timestamp=&viewType=list";

	public static final String shopListString = ".basicList_item__2XT81";
	public static final String nameString = ".basicList_link__1MaTN";
	public static final String imgString = ".thumbnail_thumb__3Agq6";
	public static final String priceString = ".price_num__2WUXn";
	public static final String regdateString = ".basicList_etc__2uAYO";
	public static final String reviewNumString = ".basicList_num__1yXM9";
	public static final String reviewString = ".basicList_star__3NkBn";

	public static final String reviewTagAttribute = "style";

	public static final String informationString = ".basicList_detail_box__3ta3h .basicList_detail__27Krk";

	public static final int intException = 0;
	public static final int noRegdateException = -1;
	public static final int checkCorrect = 1;
	public static final int reviewException = 404;
	public static final String stringException = "0";
	public static final java.sql.Date defaultRegdate = java.sql.Date.valueOf("2021-03-17");

	public static final Map<String, String> osMap;
	public static final Map<String, String> chipMakerMap;
	public static final Map<String, String> coreMap;
	public static final List<String> delim;
	public static final List<String> StringArrayException;

	static {
		osMap = new ConcurrentHashMap<String, String>();
		osMap.put("미포함", "N");
		osMap.put("윈도우", "W");
		osMap.put("리눅스", "Li");
		osMap.put("mac", "mac");

		chipMakerMap = new ConcurrentHashMap<String, String>();
		chipMakerMap.put("인텔", "I");
		chipMakerMap.put("AMD", "A");
		chipMakerMap.put("애플", "M");

		coreMap = new ConcurrentHashMap<String, String>();
		coreMap.put("듀얼코어", "dual");
		coreMap.put("쿼드코어", "quad");
		coreMap.put("헥사코어", "hexa");
		coreMap.put("옥타코어", "octa");

		delim = new ArrayList();
		delim.add(" ");
		delim.add("-");
		delim.add("/");
		delim.add(".");

		StringArrayException = new ArrayList();
		StringArrayException.add("");
	}
}