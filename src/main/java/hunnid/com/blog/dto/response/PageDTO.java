package hunnid.com.blog.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class PageDTO<T> {
    private List<T> data;
    private int page;
    private int size;
    private int actualResult;
    private int totalResults;
    private int totalPages;
}
