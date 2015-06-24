package org.jderive.util;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Durga on 6/24/2015.
 */
public final class NumberUtil {

    private static final Pattern PATTERN = Pattern.compile("^((-)?\\d+)$");

    private NumberUtil() {
        //constructor.
    }

    public static boolean isNumeric(String input) {
        //-[digits]
        //String regex = "^((-)?[0-9]+)$";
        if (StringUtils.isEmpty(input)) {
            return false;
        }

        Matcher matcher = PATTERN.matcher(input);
        return matcher.find();
    }

    /*public static void main(String[] args) {
        System.out.println(isNumeric("-66"));
    }*/

    public static Long parseLong(String input) {
        return Long.parseLong(input);
    }
}
