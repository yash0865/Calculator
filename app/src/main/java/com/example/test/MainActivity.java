package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    TextView problemTV,resultTV;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonDivide, buttonMultiply, buttonAdd, buttonMinus, buttonEquals;
    MaterialButton buttonAC, buttonDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        problemTV =findViewById(R.id.problem_tv);
        resultTV = findViewById(R.id.result_tv);

        buttonC = findViewById(R.id.button_c);
        buttonBrackOpen = findViewById(R.id.button_open_bracket);
        buttonBrackClose = findViewById(R.id.button_close_bracket);

        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);

        buttonDivide = findViewById(R.id.button_divide);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonAdd = findViewById(R.id.button_add);
        buttonMinus = findViewById(R.id.button_minus);
        buttonEquals = findViewById(R.id.button_equalTo);

        buttonAC = findViewById(R.id.button_AC);
        buttonDot = findViewById(R.id.button_dot);

    }
    public void onClick(View view){
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = problemTV.getText().toString();

        if(buttonText.equals("AC")){
            problemTV.setText("");
            resultTV.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            problemTV.setText(resultTV.getText());
            return;
        }
        if(buttonText.equals("C")){
            if(resultTV.getText().toString().equals("org.mozilla.javascript.Undefined@0")){
                resultTV.setText("0");
            }
            if(dataToCalculate.equals("")){
                return;
            }
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate = dataToCalculate+buttonText;
        }

        problemTV.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if (finalResult != "err"){
            resultTV.setText(finalResult);
        }

    }
    String getResult(String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();

            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "err";
        }
    }
}