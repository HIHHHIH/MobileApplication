package com.example.w9_assignment;

public class MySimpleModel {
    private int value;

    public MySimpleModel(int initialValue){
        this.value = initialValue;
    }

    public int getValue(){
        return this.value;
    }

    public void addOne(){
        this.value +=1;
    }
}
