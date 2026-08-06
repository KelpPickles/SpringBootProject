package com.kelppickles.knutcollab.service;

import com.kelppickles.knutcollab.dto.UserCreateRequest;
import com.kelppickles.knutcollab.entity.User;
import com.kelppickles.knutcollab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service    // Spring이 해당 클래스를 Bean으로 등록, Service 계층으로 사용하도록 관리
@RequiredArgsConstructor
// final로 선언된 UserRepository에 대한 생성자 주입 코드 생성
// final로 선언한 Repository가 여러 개 -> 생성자 겁나 긺 -> 생성자 주입으로 가독성 증대.
public class UserService {
    private final UserRepository userRepository;

    public void createUser(UserCreateRequest req) {
        // User 객체 생성
        User user = new User(); // User Entity 리팩토링 후 매개변수 생성자로 수정

        // req 값을 user에 복사
        user.setEmail(req.getEmail());
        user.setNickname(req.getNickname());
        user.setPassword(req.getPassword());    // encode 필요

        // 데이터 저장
        userRepository.save(user);
    }
}
