package com.kelppickles.knutcollab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 해당 클래스가 DB 테이블임을 Hibernate에 알림
@Table(name = "users")  // 테이블 이름 지정
@Getter // Getter 메서드 생성
@Setter // Setter 메서드 생성
@NoArgsConstructor  // 매개변수 없는 기본 생성자 생성
// (Hibernate는 데이터를 읽어올 때, 객체 생성 후 각 값을 setter로 지정하는 방식 사용
// 따라서 기본 생성자가 필요함.)
// 실제 사용시(권장) : @NoArgsConstructor(access = AccessLebel.PROTECTED)로 개발자가 생성자 호출하는 것 방지함.
public class User {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTOINCREMENT
    private Long id;

    @Column(nullable = false, unique = true)    // NOT NULL, UNIQUE
    private String email;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;
}
