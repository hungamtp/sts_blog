package hunnid.com.blog.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringUtils {
    public static String matchingKeyWord(String content, String keywords, Boolean isTitle) {
        // 15 word for title
        String result = "";
        Map<String, Boolean> mapKeyword = new HashMap<>();

        if (keywords.contains(" ")) {
            String[] words = keywords.split(" ");
            for (var word : words) {
                if (!mapKeyword.containsKey(word)) {
                    mapKeyword.put(word.toLowerCase(), true);
                }
            }
        } else {
            mapKeyword.put(keywords, true);
        }

        if (content.contains(" ")) {
            String[] titleWord = content.split(" ");
            for (var word : titleWord) {
                if (mapKeyword.containsKey(word.toLowerCase())) {
                    content = content.replace(word, addTag(content, "b"));
                }
            }

        } else {
            if (mapKeyword.containsKey(content))
                return addTag(content, "b");
        }


        return content;
    }

    public static String addTag(String title, String tag) {
        return new StringBuilder().append("<").append(tag).append(">").append(title).append("</").append(tag).toString();
    }
    
}
