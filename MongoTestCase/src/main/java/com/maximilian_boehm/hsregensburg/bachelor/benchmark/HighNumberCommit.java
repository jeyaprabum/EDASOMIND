package com.maximilian_boehm.hsregensburg.bachelor.benchmark;

import com.google.code.morphia.annotations.AlsoLoad;
import com.google.code.morphia.annotations.Entity;

@Entity
public class HighNumberCommit {

    private String s0;
    private String s1;
    private String s2;
    private String s3;

    private Double d0;
    private Double d1;
    private Double d2;
    private Double d3;


    private Float f0;
    @AlsoLoad("f6")
    private Float f1;
    @AlsoLoad("f5")
    private Float f2;
    @AlsoLoad("f4")
    private Float f3;
}
