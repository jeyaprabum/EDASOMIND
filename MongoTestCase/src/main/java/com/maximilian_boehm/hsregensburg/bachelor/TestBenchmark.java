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
    @NotSaved
    private String s5;
    @AlsoLoad("n2")
    private int n1;
    private int n4;
    @NotSaved
    private int n6;
    private long l1;
    private long l4;
    @AlsoLoad("XXX")
    private long l9;
    private char a2;
    private char a4;
    @AlsoLoad("AASD")
    private char a5;

}
