package com.example.w9;

public class MySimplePresenter implements MySimpleContract.ContractForPresenter{
    private MySimpleContract.ContractForView view;
    private  MySimpleContract.ContractForModel model;

    public MySimplePresenter(MySimpleContract.ContractForView view, MySimpleContract.ContractForModel model){
        this.view = view;
        this.model = model;
    }

    @Override
    public void onAddButtonTouched() {
        model.addOne(this::onChanged);
    }

    @Override
    public void onChanged(){
        if(view!=null) view.displayValue(model.getValue());
    }
}
