package com.kelppickles.knutcollab.service;

import com.kelppickles.knutcollab.entity.Application;
import com.kelppickles.knutcollab.entity.Project;
import com.kelppickles.knutcollab.entity.User;
import com.kelppickles.knutcollab.repository.ApplicationRepository;
import com.kelppickles.knutcollab.repository.ProjectRepository;
import com.kelppickles.knutcollab.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    // 프로젝트 신청 로직
    public void apply(String email, Long projectId) {

        // 유저 정보 가져옴
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없음."));

        // 프로젝트 아이디 가져옴
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트를 찾을 수 없음."));

        // Applcation 엔티티 생성
        Application application = new Application(user, project);

        // DB 기록
        applicationRepository.save(application);
    }

}
