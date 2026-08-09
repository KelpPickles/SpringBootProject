package com.kelppickles.knutcollab.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project {

    public Project(
            String title,
            String description,
            Integer maxMember,
            ProjectStatus status
    ) {
        this.title = title;
        this.description = description;
        this.maxMember = maxMember;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer maxMember;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    public void update(String title,
                       String description,
                       Integer maxMember,
                       ProjectStatus status) {
        this.title = title;
        this.description = description;
        this.maxMember = maxMember;
        this.status = status;
    }
}
