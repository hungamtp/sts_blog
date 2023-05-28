package hunnid.com.blog.constraint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlSpecialChar {
    public static final Map<String ,String> ENCODED_CHAR = new HashMap<String, String>() {{
        put("&amp;quot;", "\"");
        put("&gt;", ">");
        put("&lt;", "<");
        put("&amp;#x27;", "\'");
    }};
}
