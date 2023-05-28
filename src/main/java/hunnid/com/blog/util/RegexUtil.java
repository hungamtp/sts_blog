package hunnid.com.blog.util;

import java.util.Arrays;
import java.util.List;

public class RegexUtil {
    public static final String START_DIV_TAG_REGEX = "<div\\b[^>]*>";
    public static final String START_SPAN_TAG_REGEX = "<span\\b[^>]*>";
    public static final String END_SPAN_TAG_REGEX = "<\\/span\\b[^>]*>";
    public static final String END_DIV_TAG_REGEX = "<\\/div\\b[^>]*>";
    public static final String START_STRONG_TAG_REGEX = "<strong\\b[^>]*>";
    public static final String END_STRONG_TAG_REGEX = "</strong\\b[^>]*>";
    public static final String IMG_TAG_REGEX = "<img\\b[^>]*/>";
    
    public static final List<String> HTML_TAG_REGEX = Arrays.asList(
            START_DIV_TAG_REGEX,
            START_SPAN_TAG_REGEX,
            END_SPAN_TAG_REGEX,
            END_DIV_TAG_REGEX,
            START_STRONG_TAG_REGEX,
            END_STRONG_TAG_REGEX,
            IMG_TAG_REGEX
    );
}
