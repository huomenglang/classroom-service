package com.menlang.classroom.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record SubjectRequest(
        @NotNull(message = "Name is Require!")
        @NotBlank(message = "Name must not be blank!")
        @Size(min = 3, max = 50, message = "Subject Min is 3 Char and Max is 50 Char")
        String name,
        String description
) implements Serializable {
}
