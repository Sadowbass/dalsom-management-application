package com.dalsom.management.guild;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuildListDto {

    private Long id;
    private String guildName;
    private int maxCapacity;
    private int numberOfMembers;
    private String guildMaster;

    @QueryProjection
    public GuildListDto(Long id, String guildName, int maxCapacity, int numberOfMembers, String guildMaster) {
        this.id = id;
        this.guildName = guildName;
        this.maxCapacity = maxCapacity;
        this.numberOfMembers = numberOfMembers;
        this.guildMaster = guildMaster;
    }
}
