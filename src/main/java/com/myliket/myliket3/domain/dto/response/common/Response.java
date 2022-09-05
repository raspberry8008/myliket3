package com.myliket.myliket3.domain.dto.response.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class Response {

    private Object data;
    private List<?> resultList;
    @Builder
    public Response(Object data, List<?> resultList) {
        this.data = data;
        this.resultList = resultList;
    }
}
