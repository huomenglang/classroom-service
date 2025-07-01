package com.menlang.classroom.dto.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SubjectRequest(
        @NotBlank(message = "Name must not be blank!")
        @NotNull(message = "Name is Require")
        @Size(min = 3, max = 50, message = "Subject Min is 3 Char and Max is 50 Char")
        String name,
        String description
) implements Serializable {
}
