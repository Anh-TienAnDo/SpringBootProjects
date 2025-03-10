package com.ptit.graduation.dto.response.base;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class BaseListResponse<T> {
    private List<T> data;
    private long total;
}
