package com.shashankshivakumar.bmi;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BMIActivity extends AppCompatActivity {

    protected int unit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        final EditText yourWeight = (EditText) findViewById(R.id.weight);
        final EditText yourHeight = (EditText) findViewById(R.id.height);
        final TextView yourResult = (TextView) findViewById(R.id.result);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.toggle);

        final EditText yourLbWeight = (EditText) findViewById(R.id.lbweight);
        final EditText yourFtHeight = (EditText) findViewById(R.id.feet);
        final EditText yourInHeight = (EditText) findViewById(R.id.inch);

        final LinearLayout metricLayout = (LinearLayout) findViewById(R.id.metric);
        final LinearLayout stdLayout = (LinearLayout) findViewById(R.id.standard);

        Button btn_compute = (Button) findViewById(R.id.btn_compute);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yourResult.setText("");
                if((toggleButton.getText()).equals("Metric") || (toggleButton.getText()).equals("METRIC")){
                    metricLayout.setVisibility(LinearLayout.INVISIBLE);
                    stdLayout.setVisibility(LinearLayout.VISIBLE);
                    unit = 0;
                }else{
                    metricLayout.setVisibility(LinearLayout.VISIBLE);
                    stdLayout.setVisibility(LinearLayout.INVISIBLE);
                    unit = 1;
                }
            }
        });

        btn_compute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(unit == 0){
                    String weight = yourLbWeight.getText().toString();
                    String feet = yourFtHeight.getText().toString();
                    String in = yourInHeight.getText().toString();

                    yourResult.setText("Standard unit");
                    if(feet.length()>0 && weight.length()>0){
                        float inkgms = (float) (Float.parseFloat(weight) / 2.2046);
                        float incms = (float) (((Float.parseFloat(feet) * 12) + Float.parseFloat(in)) / 0.39370);
                        String[] result = calculate_bmi(inkgms, incms);
                        yourResult.setText(result[0]);
                        yourResult.setTextColor(Color.parseColor(result[1]));
                    }else{
                        yourResult.setText("Enter valid values");
                    }
                }else{
                    String weight = yourWeight.getText().toString();
                    String height = yourHeight.getText().toString();
                    if(height.length()>0 && weight.length()>0){
                        String[] result = calculate_bmi(Float.parseFloat(weight), Float.parseFloat(height));
                        yourResult.setText(result[0]);
                        yourResult.setTextColor(Color.parseColor(result[1]));
                    }else{
                        yourResult.setText("Enter valid values");
                    }
                }
            }
        });
    }

    private String[] calculate_bmi(float weight, float height){

        height = height / 100;
        float BMI = weight / (height * height);
        String[] result = new String[2];
        result[1] = "#000";

        if(BMI < 16){
            result[0] = "Your BMI: " + String.format("%.2f", BMI) + " ( Severely underweight )";
            result[1] = "#ff1c1c";
        }else if(BMI < 18.5){
            result[0] = "Your BMI: " + String.format("%.2f", BMI) + " ( Underweight )";
            result[1] = "#ffb523";
        }else if(BMI < 25){
            result[0] = "Your BMI: " + String.format("%.2f", BMI) + " ( Normal )";
            result[1] = "#37c12a";
        }else if(BMI > 30){
            result[0] = "Your BMI: " + String.format("%.2f", BMI) + " ( Overweight )";
            result[1] = "#ff1c1c";
        }else{
            result[0] = "Your BMI: " + String.format("%.2f", BMI) + " ( Obese )";
            result[1] = "#ff1c1c";
        }

        return result;
    }
}