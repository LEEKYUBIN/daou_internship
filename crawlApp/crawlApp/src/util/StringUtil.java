package util;

import java.sql.Date;
import java.util.StringTokenizer;

import common.Constant;

public class StringUtil {

	public static int numberParsing(String str) {
		if (str == null)
			return Constant.intException;
		String priceTmp = "";
		for (char c : str.toCharArray()) {
			if (c >= '0' && c <= '9') {
				priceTmp += c;
			}
		}
		try {
			return Integer.parseInt(priceTmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Constant.intException;
	}

	public static String getImageUrl(String url) {
		url = url.substring(url.indexOf("\"imageUrl\":\"") + 12);
		return url.substring(0, url.indexOf("\",\""));
	}

	public static StringTokenizer splitRegdate(String splitRegdateBefore) {
		StringTokenizer regdateHasGarbage = new StringTokenizer(splitRegdateBefore);
		String pureRegdate = null;
		if ("등록일".equals(regdateHasGarbage.nextToken())) {
			pureRegdate = regdateHasGarbage.nextToken();
		}
		for (String delim : Constant.delim) {
			if (pureRegdate.contains(delim)) {
				StringTokenizer splitRegdateAfter = new StringTokenizer(pureRegdate, delim);
				return splitRegdateAfter;
			}
		}
		return null;
	}

	public static Date sumRegdate(String yearstr, String monthstr) {
		Date regDate = Date.valueOf(yearstr + "-" + monthstr + "-01");
		return regDate;
	}
}
