package com.fulinlin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Hello {


    private String id;

    private String name;

    private List<Remark> remarks;


    @Data
    @AllArgsConstructor
    public static class Remark {

        private String name;

        private String value;

    }


}
