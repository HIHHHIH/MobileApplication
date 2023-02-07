package com.example.w9;

public class MySimpleModel implements MySimpleContract.ContractForModel{
    private int value;

    public MySimpleModel(int initialValue){
        this.value = initialValue;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public void addOne(OnValueChangedListener listener) {
        this.value = +1;
        listener.onChanged();
    }
}
