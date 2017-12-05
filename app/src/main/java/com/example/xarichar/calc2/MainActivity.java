package com.example.xarichar.calc2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText operand_one, operand_two;
    private TextView result_text;
    private TextView Special_text1;
    private Button add, subtract, multiply, divide, sqrt, power, clear;
    //private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        //context = this.getApplicationContext();

        operand_one = (EditText) findViewById(R.id.operand1_text);
        operand_two = (EditText) findViewById(R.id.operand2_text);
        result_text = (TextView) findViewById(R.id.resulttext);

        // Capture our button from layout
        add = (Button) findViewById(R.id.addbutton);
        subtract = (Button) findViewById(R.id.subtractbutton);
        multiply = (Button) findViewById(R.id.multiplybutton);
        divide = (Button) findViewById(R.id.dividebutton);
        sqrt = (Button) findViewById(R.id.sqrtbutton);
        power = (Button) findViewById(R.id.powerbutton);
        clear = (Button) findViewById(R.id.clearbutton);
        /* Register the onClick listener with the implementation below */
        add.setOnClickListener(this);
        subtract.setOnClickListener( this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        power.setOnClickListener(this);
        clear.setOnClickListener(this);
    }





        // Create an anonymous implementation of OnClickListener
        //View.OnClickListener mButtonListener = new View.OnContextClickListener() {
    @Override
        public void onClick(View v) {
        //

        //String num1 = operand_one.getText().toString();

        //String num2 = operand_two.getText().toString();

        //

        int num1 = input_to_int(parenthesis_remover(operand_one.getText().toString()));
        int num2 = input_to_int(parenthesis_remover(operand_two.getText().toString()));

            //Toast toast = new Toast(getApplicationContext());
            //toast.setDuration(Gravity.TOP | Gravity.Left, 0, 0);
            //toast.makeText(MainActivity.this, operand_one.getText(), toast.LENGTH_SHORT).show();
            //toast.makeText(MainActivity.this, operand_two.getText(), toast.LENGTH_SHORT).show();
            //toast.makeText(MainActivity.this, result_text.getText(), toast.LENGTH_SHORT).show();


            // do something when the button is clicked
            // Yes we will handle click here but which button clicked??? We don't know
            // So we will make

                switch (v.getId()) {

                    case R.id.addbutton:
                        //int addition = Integer.parseInt(num1) + Integer.parseInt(num2);
                        int addition = num1 + num2;
                        result_text.setText(String.valueOf(addition));
                        //result_text=Integer.parseInt(mEdit.getText().toString());
                        //result_text.setText(" " + ())
                        break;


                    case R.id.subtractbutton:
                        //int subtraction = Integer.parseInt(num1) - Integer.parseInt(num2);
                        int subtraction = num1 - num2;
                        result_text.setText(String.valueOf(subtraction));
                        break;

                    case R.id.multiplybutton:
                        //int multiply = Integer.parseInt(num1) * Integer.parseInt(num2);
                        int multiply = num1 * num2;
                        result_text.setText(String.valueOf(multiply));
                        break;

                    case R.id.dividebutton:
                        try{
                            //int division = Integer.parseInt(num1) / Integer.parseInt(num2);
                            int division = num1 / num2;
                            result_text.setText(String.valueOf(division));
                        }catch(Exception e){
                            result_text.setText("Cannot DIVIDE! by 0");
                        }
                        break;

                    case R.id.powerbutton:
                        //double power =  Math.pow(Integer.parseInt(num1),Integer.parseInt(num2));
                        double power = Math.pow(num1,num2);
                        result_text.setText(String.valueOf(power));
                        break;

                    case R.id.sqrtbutton:
                        try {
                            //double sqrt = Math.sqrt(Integer.parseInt(num1));
                            double sqrt = Math.sqrt(num1);
                            result_text.setText(String.valueOf(sqrt));
                        }
                        catch(Exception e) {
                            result_text.setText("Cannot take sqrt of negatives");
                        }

                        break;

                    case R.id.clearbutton:
                        result_text.setText("");
                        operand_one.setText("");
                        operand_two.setText("");
                        break;

                    default:
                        break;
                }

            }

    private int input_to_int(String x){
        int num = 0;
        int sub_num = 0;
        int int_cnt = 0;
        int char_cnt = 0;
        boolean special_char = false;
        String input_str = x ;
        Pattern p = Pattern.compile("(?<=[-+*/()])|(?=[-+*/()])");
        Matcher m = p.matcher(input_str);
        // boolean b = m.matches();

        boolean b = m.find();
        //Array of numbers
        int[] split_array ;

        String regex = "(?<=[-+*/()])|(?=[-+*/()])";
        //System.out.println(input_str.split(regex));
        String char_num = "";

        if(b == false){
            for(int i = 0; i < x.length(); i++)
            {
                char_num += x.charAt(i);
            }
        }

        else{
            input_str.split(regex);
            char_num = operand_solver(input_str);
        }

        num = Integer.parseInt(char_num);

        return num;
    }

    private String operand_solver(String x) {
        int int_op1 = 0;
        int int_op2 = 0;
        String str_op1 = "";
        String str_op2 = "";
        int lencheck;
        char operation = '\0';
        boolean multi_operations = false;
        int final_value = 0;


        //Operator position
        //number builder
        for (int i = 0; i < x.length(); i++) {
            if (!multi_operations) {

                if (!isSpecial(x.charAt(i))) {
                    str_op1 += x.charAt(i);
                } else {
                    operation = x.charAt(i);
                    i++;

                    while (!isSpecial(x.charAt(i))) {

                        str_op2 += x.charAt(i);

                        lencheck = i + 1;
                        i++;

                        if (lencheck >= x.length()) {
                            break;
                        }
                    }

                    int_op1 = input_to_int(str_op1);
                    int_op2 = input_to_int(str_op2);

                    if (operation == '*') {
                        final_value = int_op1 * int_op2;
                    }
                    if (operation == '-') {
                        final_value = int_op1 - int_op2;
                    }
                    if (operation == '+') {
                        final_value = int_op1 + int_op2;
                    }
                    if (operation == '/') {
                        final_value = int_op1 / int_op2;
                    }

                    multi_operations = true;


                }
            }
            else {
                operation = x.charAt(i - 1);
                str_op2 = "";

                while (!isSpecial(x.charAt(i))) {

                    str_op2 += x.charAt(i);

                    i++;

                    if (i == x.length()) {
                        break;
                    }
                }

                int_op1 = final_value;
                int_op2 = input_to_int(str_op2);

                if (operation == '*') {
                    final_value = int_op1 * int_op2;
                }
                if (operation == '-') {
                    final_value = int_op1 - int_op2;
                }
                if (operation == '+') {
                    final_value = int_op1 + int_op2;
                }
                if (operation == '/') {
                    final_value = int_op1 / int_op2;
                }

            }

        }
        result_text.setText(Integer.toString(final_value));
        return Integer.toString(final_value);
    }

    //Function that deals with the parenthesis in the function by solving whats inside the parenthesis with
    //with meaningful string for the operand solver
    public String parenthesis_remover(String x){

        //Full input with parenthesis

        StringBuilder full_input = new StringBuilder(x);
        int start_pos = 0;
        int end_pos = 0;
        String sub_str = "";
        String part_input = "";

        while(hasParenthesis(full_input)){
            for(int i = 0 ; i <full_input.length(); i++){
                if(full_input.charAt(i) == '('){
                    start_pos = i;

                }
                if(full_input.charAt(i) == ')'){
                    end_pos = i;
                }
            }
            for(int i = 0 ; i <full_input.length(); i++){
                if(i == start_pos){
                    while(i != (end_pos + 1)){
                        sub_str += full_input.charAt(i);
                        i++;
                    }

                }
            }
            for(int i = 0 ; i <full_input.length(); i++){

                if(i == start_pos + 1 ){
                    while(i != (end_pos )){
                        part_input += full_input.charAt(i);
                        i++;
                    }

                }
            }


            sub_str = operand_solver(part_input);
            for(int i = 0 ; i <full_input.length(); i++){
                int j = 0;
                if(i == start_pos){
                    full_input.setCharAt(i,sub_str.charAt(j));
                    j++;
                    i++;
                    while(i <= (end_pos )){
                        int start_end_diff = (end_pos + 1) - start_pos;
                        int str_diff = start_end_diff - sub_str.length();
                        if(j < sub_str.length()) {
                            full_input.setCharAt(i, sub_str.charAt(j));
                            j++;
                            i++;
                        }
                        else{
                            i = i + str_diff;
                            while(i < full_input.length() ) {
                                full_input.setCharAt(i - str_diff, full_input.charAt(i ) );
                                j++;
                                i++;
                            }
                            full_input.setLength(full_input.length() - str_diff);
                        }

                    }

                }

            }


        }
        return full_input.toString();
    }

    //Function to check if character is a special character
    public boolean isSpecial(char x){
        boolean unique = false;

        if((x == '-' )|| (x == '+') || (x ==  '*') || (x == '/')){
            unique = true;
        }
        return unique;


    }
    public boolean hasParenthesis(StringBuilder x){
        int i = 0;
        Boolean hasParen = false;

        while(i < x.length()){
            if((x.charAt(i) == '(' )|| (x.charAt(i) == ')') ){
                hasParen = true;
            }
            i++;
        }
        return hasParen;
    }

}
















