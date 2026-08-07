package com.kelppickles.knutcollab.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectCreateRequest {
    private String title;
    private String description;
    private Integer maxMember;
    private String status;
}
