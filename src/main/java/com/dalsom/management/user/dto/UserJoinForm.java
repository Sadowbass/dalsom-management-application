package com.dalsom.management.user.dto;

import com.dalsom.management.character.Jobs;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinForm {

    @NotEmpty(message = "캐릭명은 필수입니다.")
    private String characterName;

    //    @NotNull(message = "가입일은 필수입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    @NotEmpty(message = "직업은 필수입니다.")
    private String job;

    @NotNull(message = "레벨은 필수입니다.")
    private Integer level;

    @NotNull(message = "아이템 레벨은 필수입니다.")
    private Integer itemLevel;

    public Jobs getJob() {
        return Jobs.findJob(this.job);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserJoinForm{");
        sb.append("characterName='").append(characterName).append('\'');
        sb.append(", joinDate=").append(joinDate);
        sb.append(", job='").append(job).append('\'');
        sb.append(", level=").append(level);
        sb.append(", itemLevel=").append(itemLevel);
        sb.append('}');
        return sb.toString();
    }
}
