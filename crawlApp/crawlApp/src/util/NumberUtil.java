package util;

public class NumberUtil {

	public static String floatCompare(String numStr, float f) {

		float num = Float.parseFloat(numStr);
		if (Float.compare(num, f) == -1) {
			return "S";
		} else {
			return "L";
		}

	}

}
