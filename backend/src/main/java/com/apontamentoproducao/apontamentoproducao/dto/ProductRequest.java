package com.apontamentoproducao.apontamentoproducao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {

    private String code;
    private String name;
    private Integer unitsPerBox;
}