package hunnid.com.blog.util;

import hunnid.com.blog.constraint.HtmlSpecialChar;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    public static String matchingKeyWordTitle(String content, String keywords) {
        // 15 word for title
        // highlighting matching keywords
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

                if (mapKeyword.containsKey(titleWord[i].toLowerCase())) {
                    resultBuilder.append(addTag(addTag(addTag(titleWord[i], "b"), "i"), "u")).append(" ");
                } else {
                    resultBuilder.append(titleWord[i]).append(" ");
                }
            }
            return resultBuilder.toString().trim();
        } else {
            if (mapKeyword.containsKey(content.toLowerCase().trim()))
                return addTag(addTag(addTag(content, "b"), "i"), "u");
        }
        return content;
    }
    
    public static String matchingKeywordContent(String content , String keywords){
        // pre-handle the unuseful keywords (ex : html tag and special characters)
        // find most appearance keywords in 20-words string;

        content = removeHtmlTags(RegexUtil.HTML_TAG_REGEX, decodeSpecialChar(content.trim()));
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
        
        List<Integer> indexMatchingKeyword = new ArrayList<>();
        
        String[] words = content.split(" ");

        for(var word : words) {
            if(mapKeyword.containsKey(word.toLowerCase()))
                indexMatchingKeyword.add(1);
            else indexMatchingKeyword.add(0);
        }
        
        int start = 0;
        int maxMatchingStart = 0;
        int highestMatching = 0;
        int currentHighestMatching = 0;
        int limit = 0;
        int maxLimit = 20;
        boolean isStart = true;
        for(int i = 0 ; i < indexMatchingKeyword.size() ; i++) {
            if (isStart) {
                if (indexMatchingKeyword.get(i) == 0) {
                    start++;
                } else {
                    start = i;
                    isStart = false;
                    currentHighestMatching++;
                }
            } else {
                if (indexMatchingKeyword.get(i) == 1) {
                    currentHighestMatching++;
                }
                limit++;
                if(limit == maxLimit){
                    if(currentHighestMatching > highestMatching){
                        maxMatchingStart = start;
                        highestMatching = currentHighestMatching;
                    }
                    currentHighestMatching = 0;
                    i  = start + 1;
                    limit = 0;
                    isStart = true;
                }
            }
        }
        
        StringBuilder result = new StringBuilder();
        // in case content too short 
        if(maxLimit > words.length - maxMatchingStart){
            maxLimit = words.length - maxMatchingStart;
        }
        
        for(int i = maxMatchingStart ; i < maxLimit ; i++){
            if(mapKeyword.containsKey(words[i].toLowerCase()))
                result.append(addTag(addTag(addTag(words[i], "b"), "i"), "u")).append(" ");
            else
                result.append(words[i]).append(" ");
        }
        
        return result.toString().trim();
    }

    public static String addTag(String title, String tag) {
        return new StringBuilder().append("<").append(tag).append(">").append(title).append("</").append(tag).append(">").toString();
    }

    public static String encode(String string) {
        try {
            return java.net.URLDecoder.decode(string, StandardCharsets.UTF_8.name());
        } catch (Exception exception) {
            return string;
        }
    }

    public static String removeHtmlTags(List<String> regexes, String string) {
        for(var regex : regexes){
            Pattern pattern = Pattern.compile(regex);
            string = pattern.matcher(string).replaceAll(" ");
        }
        return string;
    }
    
    public static String decodeSpecialChar(String string){
        for(var specialChar : HtmlSpecialChar.ENCODED_CHAR.keySet()){
            string = string.replaceAll(specialChar , HtmlSpecialChar.ENCODED_CHAR.get(specialChar));
        }
       return string;
    }
    
    
}
