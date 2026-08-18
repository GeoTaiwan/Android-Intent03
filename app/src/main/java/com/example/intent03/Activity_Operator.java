package com.example.intent03;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Operator extends AppCompatActivity {
    RadioGroup rg01;
    RadioButton rb_add, rb_substract, rb_multiply, rb_divide;
    CheckBox cb_divide;
    Button btn_calc;
    TextView tv_msg;
    Double op_01,op_02,result;
    boolean operate_status = false;

    Intent i;
    Bundle b = new Bundle();
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_operator);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        rg01 = findViewById(R.id.rg_operators);
        rb_add = findViewById(R.id.rb_add);
        rb_substract = findViewById(R.id.rb_substract);
        rb_multiply = findViewById(R.id.rb_multiply);
        rb_divide = findViewById(R.id.rb_divide);

        cb_divide = findViewById(R.id.cb_divide);
        btn_calc = findViewById(R.id.btn_calculate);
        tv_msg = findViewById(R.id.tv_msg);


        btn_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = Activity_Operator.this.getIntent();
                b = i.getExtras();
                if (b!=null){
                    op_01 = Double.parseDouble(b.getString("input01"));
                    op_02 = Double.parseDouble(b.getString("input02"));

                    if(rb_add.isChecked()){
                        result = op_01 + op_02;
                        operate_status = true;
                        tv_msg.setText("");
                    } else if (rb_substract.isChecked()){
                        result = op_01 - op_02;
                        operate_status = true;
                        tv_msg.setText("");
                    }else if (rb_multiply.isChecked()){
                        result = op_01 * op_02;
                        operate_status = true;
                        tv_msg.setText("");
                    } else if (rb_divide.isChecked()){
                        if (cb_divide.isChecked()){
                            result = Math.floor( op_01 / op_02);
                        }else{
                            result = op_01 / op_02;
                        }
                        operate_status = true;
                        tv_msg.setText("");
                    } else {
                        tv_msg.setText("請選擇運算子！");
                        tv_msg.setTextColor(Color.RED);
                    }

                    if (operate_status){
                        i = new Intent();
                        b.putDouble("CALCULATE_RESULT", result);
                        i.putExtras(b);
                        setResult(RESULT_OK,i);
                        finish();
                    }
                } else {
                    tv_msg.setText("發生錯誤：bundle為null！");
                    tv_msg.setTextColor(Color.RED);
                }
            }
        });




    }



}