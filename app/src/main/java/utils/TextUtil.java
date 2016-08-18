package utils;

/**
 * Created by zhaoyang on 16/8/16.
 */
public class TextUtil {
    public static boolean isNull(CharSequence... str) {

        for (CharSequence cha : str) {
            if (cha == null || cha.length() == 0 || cha.equals("null") || "".equals("")) {
                return true;
            } else {
                return false;
            }
        }

        return true;
    }
}
