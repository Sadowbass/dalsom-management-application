package com.dalsom.management.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchCondition {

    private String keyword;
    private String category;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchCondition{");
        sb.append("keyword='").append(keyword).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
