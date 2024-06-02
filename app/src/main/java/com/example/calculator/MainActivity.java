package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewOnReceiveContentListener;

import android.os.Bundle;
import android.view.View;
//import android.widget.Switch;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    Boolean haveClickedNum = false;
    Boolean haveCalculated = false;


    TextView textAtasElement;
    TextView operatorBufferElement;
    TextView operand1Element;
    TextView operand2Element;
    TextView equalBufferElement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textAtasElement = findViewById(R.id.textAtas);
        operatorBufferElement = findViewById(R.id.operatorBuffer);
        operand1Element = findViewById(R.id.operand1Buffer);
        operand2Element = findViewById(R.id.operand2Buffer);
        equalBufferElement= findViewById(R.id.equalBuffer);
    }


    public void clickAngka(View v){
        if(!haveClickedNum){
            textAtasElement.setText("");
        }
        TextView angkaElement = (TextView)v ;
        String clickedNumber = angkaElement.getText().toString();
        if(haveCalculated){
            textAtasElement.setText("");
            operand2Element.setText("");
            equalBufferElement.setText("");
        }
        String newText = textAtasElement.getText().toString().concat(clickedNumber);
        textAtasElement.setText(newText);
        this.haveClickedNum = true;
        this.haveCalculated=false;
    }

    public void clickOperator(View v){
        TextView operatorElement = (TextView) v;
        String lastOperator = operatorBufferElement.getText().toString();
        String clickedOperator = operatorElement.getText().toString();
        equalBufferElement.setText("");

        operatorBufferElement.setText(clickedOperator);
        if (this.haveClickedNum == true && haveOperand1() && !this.haveCalculated){
            calculate(lastOperator);
            operand1Element.setText(textAtasElement.getText().toString());
        }
        else if(haveClickedNum && !haveCalculated){
            operand1Element.setText(textAtasElement.getText().toString());
            textAtasElement.setText("");
//            this.haveOperand1 = true;

        }
        else if (haveCalculated) {
            operand1Element.setText(textAtasElement.getText().toString());
            operand2Element.setText("");
            textAtasElement.setText("");
        }
        equalBufferElement.setText("");
        this.haveClickedNum = false;



    }

    public void clickEqual(View v){
        equalBufferElement.setText("=");
        String textAtas=textAtasElement.getText().toString();


        if(haveOperator()){
            operand2Element.setText(textAtas);
            calculate(operatorBufferElement.getText().toString());
        }
        else{
            operand1Element.setText(textAtas);
        }
        this.haveClickedNum=false;
    }

    public void clickClear(View v){
        textAtasElement.setText("");
        equalBufferElement.setText("");
        operand1Element.setText("");
        operatorBufferElement.setText("");
        operand2Element.setText("");
        this.haveCalculated=false;
        this.haveClickedNum=false;
//        this.haveClickEqual=false;
//        this.haveOperand1=false;
    }

    public void calculate(String operator){
        double result = 0;
        switch (operator) {
            case "+":
                result = add();
                break;
            case "-":
                result = substract();
                break;
            case "X":
                result = multiply();

                break;
            case "/":
                result = divide();
                break;
            default:
                return;
        }
        haveCalculated = true;
        textAtasElement.setText(String.valueOf(result));

    }

    public double add(){
        double operand1 = Double.parseDouble(operand1Element.getText().toString());
        double textAtas = Double.parseDouble(textAtasElement.getText().toString());
        return  operand1+textAtas;
    }

    public double multiply(){
        double operand1 = Double.parseDouble(operand1Element.getText().toString());
        double textAtas = Double.parseDouble(textAtasElement.getText().toString());
        return  operand1*textAtas;
    }

    public double substract(){
        double operand1 = Double.parseDouble(operand1Element.getText().toString());
        double textAtas = Double.parseDouble(textAtasElement.getText().toString());
        return  operand1-textAtas;
    }

    public double divide(){
        double operand1 = Double.parseDouble(operand1Element.getText().toString());
        double textAtas = Double.parseDouble(textAtasElement.getText().toString());
        return  operand1/textAtas;
    }

    public Boolean haveOperand1(){
        return !(operand1Element.getText() == "");
    }

    public Boolean haveOperand2(){
        return !(operand2Element.getText() == "");
    }

    public Boolean haveOperator(){
        return !(operatorBufferElement.getText() == "");
    }



}