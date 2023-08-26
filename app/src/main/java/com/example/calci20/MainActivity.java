package com.example.calci20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MainActivity1 {
    public static final String NAME="com.example.calci20.extra.NAME";
    TextView ans, solution;
    Button button_more,button_ac, button_c, button_ob, button_cb, button_dot, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_plus, button_minus, button_star, button_div, button_equal;
    String solution_text;
    String ans_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("err", "hurrah: ");

        //initialization(button_more, R.id.button_more);
        initialization(button_0, R.id.button_0);
        initialization(button_1, R.id.button_1);
        initialization(button_2, R.id.button_2);
        initialization(button_3, R.id.button_3);
        initialization(button_4, R.id.button_4);
        initialization(button_5, R.id.button_5);
        initialization(button_6, R.id.button_6);
        initialization(button_7, R.id.button_7);
        initialization(button_8, R.id.button_8);
        initialization(button_9, R.id.button_9);
        initialization(button_0, R.id.button_0);
        initialization(button_ac, R.id.button_ac);
        initialization(button_c, R.id.button_c);
        initialization(button_ob, R.id.button_ob);
        initialization(button_cb, R.id.button_cb);
        initialization(button_dot, R.id.button_dot);
        initialization(button_plus, R.id.button_plus);
        initialization(button_minus, R.id.button_minus);
        initialization(button_star, R.id.button_star);
        initialization(button_div, R.id.button_div);
        initialization(button_equal, R.id.button_equal);
        ans = findViewById(R.id.ans);
        solution = findViewById(R.id.solution);
        //button_0=findViewById(R.id.button_0);
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        button_more=findViewById(R.id.button_more);
         solution_text=solution.getText().toString();
        //String solution_text="Hello Everyone..";
         ans_text=ans.getText().toString();
        button_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,More_activity.class);
                intent.putExtra("solution_text",solution_text);
               intent.putExtra("ans_text",ans_text);
                startActivity(intent);
            }
        });

    }

 public void initialization(Button button, int id) {
        Log.d("error", "initialization: ");
        button = findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();
                String solutionText = solution.getText().toString();
                String data = null;
                switch (buttonText) {
                    case "AC":
                        solution.setText("");
                        ans.setText("0");
                        // return;

                        break;
                    case "C":
                        if(solutionText.length()>1){
                        data = solutionText.substring(0, solutionText.length() - 1);
                        }
                        else if(solutionText.length()==1){
                            ans.setText("0");
                        }
                        // solution.setText(newText);
                        break;
                    case "=":
                        if(!solutionText.isEmpty()){
                        solution.setText(ans.getText().toString());

                        }
                        return;

                    default:
                        data = solutionText + buttonText;

                        break;
                }
                solution.setText(data);
                 solution_text=data;

                String result = getResult(data);

                if (!Objects.equals(result, "error")) {
                    if(result.endsWith(".0")){
                        result.replace(".0","");
                    }
                    ans_text=result;
                    ans.setText(result);
                }
                //solution.setText(buttonText);
            }

        });

    }

//    public void onClick(View view) {
//        Button button = (Button) view;
//        String buttonText = button.getText().toString();
//        String solutionText = solution.getText().toString();
//        String data = "";
//        if (buttonText == "AC") {
//            String newText = "";
//            solution.setText(newText);
//            ans.setText("0");
//
//        } else if (buttonText == "C") {
//            String newText = solutionText.substring(0, solutionText.length() - 1);
//            solution.setText(newText);
//        } else if (buttonText == "=") {
//            solution.setText(ans.getText().toString());
//            return;
//
//        } else {
//            data = buttonText + solutionText;
//
//        }
//        solution.setText(data);
//        String result = getResult(data);
//        if (result != "error") {
//            ans.setText(result);
//        }
//        solution.setText(buttonText);
//    }

    public String getResult(String str) {
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String finalresult = "";
        try {

            Scriptable scriptable = rhino.initStandardObjects();
            finalresult = rhino.evaluateString(scriptable, str, "javascript", 1, null).toString();
        } catch (Exception e) {
            finalresult = "error";
        }
        if(finalresult.endsWith(".0")){
            finalresult= finalresult.replace(".0","");
        }
        return finalresult;
    }

}

