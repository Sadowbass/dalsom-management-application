package com.dalsom.management.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PageObject<T> {

    private Page<T> originalPage;
    private List<T> content;
    private int currentPageNumber;
    private int startPageNumber;
    private int endPageNumber;
    private int numberOfPagesShown = 5;
    private List<Integer> shownPages;

    public PageObject(Page<T> originalPage) {
        this.originalPage = originalPage;
        this.content = originalPage.getContent();
        this.currentPageNumber = originalPage.getNumber() + 1;
        this.startPageNumber = originalPage.getNumber() / numberOfPagesShown * numberOfPagesShown + 1;
        this.endPageNumber = Math.min(startPageNumber + numberOfPagesShown, originalPage.getTotalPages() + 1);
        this.shownPages = createPageNumberList();
    }

    private List<Integer> createPageNumberList() {
        List<Integer> list = new ArrayList<>();
        for (int i = startPageNumber; i < endPageNumber; i++) {
            list.add(i);
        }

        return list;
    }

    public boolean isNeedPrevButton() {
        return getCurrentPageBlock() != 0;
    }

    public int getPrevButtonPage() {
        return (getCurrentPageBlock() - 1) * 5 + 1;
    }

    public boolean isNeedNextButton() {
        return this.endPageNumber < originalPage.getTotalPages();
    }

    public int getNextButtonPage() {
        return (getCurrentPageBlock() + 1) * 5 + 1;
    }

    public int getCurrentPageBlock() {
        return originalPage.getNumber() / numberOfPagesShown;
    }
}
