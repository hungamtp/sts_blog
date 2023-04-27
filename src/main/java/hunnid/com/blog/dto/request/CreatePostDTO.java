package hunnid.com.blog.dto.request;


import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class CreatePostDTO {
    private String coverImage;
    private Set<UUID> tagIds;
    private Map<UUID, List<PostContent>> contentMapByLanguageId;
    private List<PostContent> contents;
    
    public void mapContentByLanguageId() {
        Map<UUID , List<PostContent>> result = new HashMap<>();
        contents.forEach((value) ->{
            if(result.containsKey(value.getLanguageId())){
                result.get(value.getLanguageId()).add(value);
            }else{
                var postContent = new ArrayList<PostContent>();
                postContent.add(value);
                result.put(value.getLanguageId() , postContent);
            }
        });
        this.contentMapByLanguageId = result;
    }
}
