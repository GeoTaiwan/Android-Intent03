package com.example.intent03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView tv_output;
    EditText et_input_opd1,et_input_opd2;
    Button btn_choose_op;
    Intent i;
    Bundle b=new Bundle();
    String strTemp;
//    private  static final int REQUEST_CODE_OP = 1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        tv_output = findViewById(R.id.tv_output);
        et_input_opd1 = findViewById(R.id.et_input_opd1);
        et_input_opd2 = findViewById(R.id.et_input_opd2);
        btn_choose_op = findViewById(R.id.btn_choose_op);

        strTemp = tv_output.getText().toString();

        btn_choose_op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                double input01,input02;
//                input01 = Double.parseDouble(et_input_opd1.getText().toString().isEmpty()?"0":et_input_opd1.getText().toString());
//                input02 = Double.parseDouble(et_input_opd2.getText().toString().isEmpty()?"0":et_input_opd2.getText().toString());

                i = new Intent(MainActivity.this,Activity_Operator.class);

//                b.putDouble("input01",input01);
//                b.putDouble("input02",input02);
                b.putString("input01",et_input_opd1.getText().toString().isEmpty()?"0":et_input_opd1.getText().toString());
                b.putString("input02",et_input_opd2.getText().toString().isEmpty()?"0":et_input_opd2.getText().toString());
                i.putExtras(b);
                launcher_CallActivityOperator.launch(i);
//                startActivityForResult(i,REQUEST_CODE_OP);
            }
        });

    }

    ActivityResultLauncher launcher_CallActivityOperator = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult o) {
                    int result_code = o.getResultCode();
                    if (result_code == RESULT_OK){
                        Intent i_resultReturn = o.getData();
                        Bundle b_resultReturn = i_resultReturn.getExtras();

                        if (b_resultReturn != null){
                            Double answer = b_resultReturn.getDouble("CALCULATE_RESULT");
                            tv_output.setText(strTemp + "\n" +answer.toString());
                        }

                    }
                }
            });

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intentReturn_data) {
//        super.onActivityResult(requestCode, resultCode, intentReturn_data);
//        if(requestCode==REQUEST_CODE_OP){
//            if(resultCode==RESULT_OK){
//                Bundle bundle_resultReturn = intentReturn_data.getExtras();
//                if (bundle_resultReturn!=null){
//                    Double answer = bundle_resultReturn.getDouble("CALCULATE_RESULT");
//                    tv_output.setText(strTemp + "\n" +answer.toString());
//                }
//
//            }
//        }
//    }




}