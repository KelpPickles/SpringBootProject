package com.kelppickles.knutcollab.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  // 유저는 여러 개의 지원 가능. 즉, Many Application -> One User
    // FetchType.LAZY : Applcation만 불러오고, User를 실제 불러올 때 User 테이블을 조회하겠음.
    @JoinColumn(name = "user_id", nullable = false) // 외래키 컬럼 명시
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    private LocalDateTime createdAt;

    public Application(User user, Project project) {
        this.user = user;
        this.project = project;
        this.createdAt = LocalDateTime.now();
    }
}
