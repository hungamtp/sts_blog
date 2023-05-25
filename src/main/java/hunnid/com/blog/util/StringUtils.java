package hunnid.com.blog.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    public static String matchingKeyWord(String content, String keywords, Boolean isTitle) {
        // 15 word for title
        content = content.trim();
        Map<String, Boolean> mapKeyword = new HashMap<>();

        if (keywords.contains(" ")) {
            String[] words = keywords.split(" ");
            for (var word : words) {
                if (!mapKeyword.containsKey(word)) {
                    mapKeyword.put(word.toLowerCase(), true);
                }
            }
        } else {
            mapKeyword.put(keywords.toLowerCase(), true);
        }

        if (content.contains(" ")) {
            String[] titleWord = content.split(" ");
            StringBuilder resultBuilder = new StringBuilder();
            for (int i = 0; i < titleWord.length; i++) {

                if (mapKeyword.containsKey(titleWord[i])) {
                    resultBuilder.append(addTag(content, "b")).append(" ");
                } else {
                    resultBuilder.append(titleWord[i]);
                }
            }
            return resultBuilder.toString().trim();
        } else {
            if (mapKeyword.containsKey(content.toLowerCase().trim()))
                return addTag(content, "b");
        }
        return content;
    }

    public static String addTag(String title, String tag) {
        return new StringBuilder().append("<").append(tag).append(">").append(title).append("</").append(tag).append(">").toString();
    }
}
