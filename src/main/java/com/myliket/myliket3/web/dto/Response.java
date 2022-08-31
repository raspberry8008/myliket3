package com.myliket.myliket3.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Response {

    private Object data;
    private List<?> resultList;

}
