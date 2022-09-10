package com.dalsom.management.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @CreatedDate
    private LocalDateTime createdDate;
    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    @LastModifiedBy
    private String lastModifiedBy;

    @PrePersist
    protected void prePersist() {
        this.createdDate = LocalDateTime.now();
        this.createdBy = "system";
        this.lastModifiedDate = LocalDateTime.now();
        this.lastModifiedBy = "system";
    }

    @PreUpdate
    protected void preUpdate() {
        this.lastModifiedDate = LocalDateTime.now();
        this.lastModifiedBy = "system";
    }
}
