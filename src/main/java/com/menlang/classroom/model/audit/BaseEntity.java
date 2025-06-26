package com.menlang.classroom.model.audit;


import com.menglang.common.library.page.paginate.BasePageResponse;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.io.Serializable;

@Getter
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> extends BasePageResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;

}