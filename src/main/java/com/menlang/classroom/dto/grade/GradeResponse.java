package com.menlang.classroom.dto.grade;

import com.menglang.common.library.page.paginate.BasePageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class GradeResponse extends BasePageResponse {
    private Long id;
    private String grade;
    private String description;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}
