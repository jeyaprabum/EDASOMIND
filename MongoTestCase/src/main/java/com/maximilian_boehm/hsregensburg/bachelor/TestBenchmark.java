package com.maximilian_boehm.hsregensburg.bachelor;

import com.google.code.morphia.annotations.AlsoLoad;
import com.google.code.morphia.annotations.NotSaved;
import com.google.code.morphia.annotations.Transient;

public class TestBenchmark {

    @Transient
    private String ABC;

    @NotSaved
    private long DEF;

    @AlsoLoad("ABC")
    private String GHI;


    private String s1;
    private String s2;
    private String s3;
    private String s5;
    private String s6;
    private int n1;
    private int n3;
    private int n4;
    private int n5;
    private int n6;
    private long l1;
    private long l2;
    private long l3;
    private long l4;
    private long l5;
    private long l8;
    private long l9;
    private char a2;
    private char a3;
    private char a4;
    private char a5;
    private char a6;
    private char a7;

}
