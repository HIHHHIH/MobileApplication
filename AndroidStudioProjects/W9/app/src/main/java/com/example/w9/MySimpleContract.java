package com.example.w9;


public interface MySimpleContract {
    interface ContractForView{
        void displayValue(int value);
    }

    interface ContractForModel{
        int getValue();
        void addOne(OnValueChangedListener listener);
        interface OnValueChangedListener{
            void onChanged();
        }
    }
    interface ContractForPresenter{
        void onAddButtonTouched();

        void onChanged();
    }
}
