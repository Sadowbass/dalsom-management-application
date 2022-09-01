package com.dalsom.management.user;

import com.dalsom.management.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserComment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "user_comment_id")
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private CommentType commentType;

    @Lob
    private String comment;
}
