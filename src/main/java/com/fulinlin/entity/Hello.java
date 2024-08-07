package com.fulinlin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hello {


    private String id;

    private String name;

    private List<Remark> remarks;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Remark {

        private String name;

        private String value;

    }


}
