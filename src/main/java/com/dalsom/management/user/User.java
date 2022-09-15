package com.dalsom.management.user;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.character.Characters;
import com.dalsom.management.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_character_id")
    private Characters mainCharacter;

    private String madisonName;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    private LocalDateTime joinDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "join_approve_id")
    private Admin joinApproveAdmin;

    private LocalDateTime leaveDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leave_approve_id")
    private Admin leaveApproveAdmin;

    @Lob
    private String leaveReason;

    @OneToMany(mappedBy = "user")
    private List<Characters> characters = new ArrayList<>();

    protected User() {

    }

    public static User createNewUser(LocalDateTime joinDate, Admin joinApproveAdmin) {
        User user = new User();
        user.setJoinDate(joinDate == null ? LocalDateTime.now() : joinDate);
        user.setJoinApproveAdmin(joinApproveAdmin);
        user.setUserStatus(UserStatus.JOIN);

        return user;
    }

    public void addCharacters(Characters... characters) {
        this.characters.addAll(Arrays.asList(characters));
    }

    public void changeMainCharacter(Characters characters) {
        this.mainCharacter = characters;
    }

    public void changeMadisonName(String madisonName) {
        this.madisonName = madisonName;
    }

    public void changeJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public void leave(LocalDateTime leaveDate, String leaveReason, UserStatus userStatus, Admin leaveApproveAdmin) {
        changeLeaveDate(leaveDate);
        changeLeaveReason(leaveReason);
        changeUserState(userStatus);
        this.leaveApproveAdmin = leaveApproveAdmin;
    }

    private void changeUserState(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public void changeLeaveDate(LocalDateTime leaveDate) {
        this.leaveDate = leaveDate;
    }

    private void changeLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
}


