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

    public void setPagination(Integer page, Integer maxPage) {
        this.currentPage = page;
        this.maxPage = maxPage;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0,page - i);
            }
            if (page + i <= this.maxPage) {
                pages.add(page + i);
            }
        }

        hasLeftBracket = page != 1;
        hasRightBracket = !page.equals(this.maxPage);
        hasDoubleLeftBracket = !pages.contains(1);
        hasDoubleRightBracket = !pages.contains(this.maxPage);
    }

}
