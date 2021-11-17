package life.yang.community.studycommunity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {
    private List<QuestionDto> questions;
    private boolean hasLeftBracket;
    private boolean hasDoubleLeftBracket;
    private boolean hasRightBracket;
    private boolean hasDoubleRightBracket;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();
    private Integer maxPage;

    public void setPagination(Integer page, Integer size, Integer totalCount) {
        this.currentPage = page;
        if (totalCount % size == 0) {
            this.maxPage = totalCount / size;
        } else {
            this.maxPage = totalCount / size + 1;
        }

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= maxPage) {
                pages.add(page + i);
            }
        }

        hasLeftBracket = page != 1;
        hasRightBracket = !page.equals(maxPage);
        hasDoubleLeftBracket = !pages.contains(1);
        hasDoubleRightBracket = !pages.contains(maxPage);
    }
}
