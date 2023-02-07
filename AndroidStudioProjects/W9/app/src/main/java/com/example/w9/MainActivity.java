package com.example.w9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MySimpleContract.ContractForView{
    private MySimplePresenter presenter;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);

        presenter = new MySimplePresenter(this, new MySimpleModel(0));
        presenter.onChanged();
        btn.setOnClickListener(view-> {presenter.onAddButtonTouched();});


    }

    @Override
    public void displayValue(int value) {
        btn.setText("Value: " + value);
    }
}