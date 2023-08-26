package com.example.calci20;

import static com.example.calci20.MainActivity.NAME;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;
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

public class More_activity extends AppCompatActivity {
    TextView ans_more, solution_more;
    Button button_sin, button_cos, button_tan, button_fact, button_root, button_xpow,button_ln, button_log,button_ac, button_c, button_ob, button_cb, button_dot, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_0, button_plus, button_minus, button_star, button_div, button_equal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        solution_more=findViewById(R.id.solution_more);
        ans_more=findViewById(R.id.ans_more);
        Intent intent=getIntent();
        ans_more.setText(intent.getStringExtra("ans_text"));
        solution_more.setText(intent.getStringExtra("solution_text"));
        initialization(button_sin, R.id.button_sin);
        initialization(button_cos, R.id.button_cos);
        initialization(button_tan, R.id.button_tan);
        initialization(button_fact, R.id.button_fact);
        initialization(button_root, R.id.button_root);
        initialization(button_xpow, R.id.button_xpow);
        initialization(button_ln, R.id.button_ln);
        initialization(button_log, R.id.button_log);
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
      Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();


    }

    public void initialization(Button button, int id) {

        Log.d("error", "initialization: ");
        button = findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String result;
                Button button = (Button) view;
                String buttonText = button.getText().toString();
                String solutionText = solution_more.getText().toString();
                String data = null;
                switch (buttonText) {
                    case "AC":
                        solution_more.setText("");
                        ans_more.setText("0");
                        // return;

                        break;
                    case "C":
                        if(solutionText.length()>1){
                            data = solutionText.substring(0, solutionText.length() - 1);
                        }
                        else if(solutionText.length()==1){
                            ans_more.setText("0");
                        }
                        // solution.setText(newText);
                        break;
                    case "=":
                        if(!solutionText.isEmpty()){
                            solution_more.setText(ans_more.getText().toString());

                        }
                        return;
                    case "x!":
                        data=solutionText+"!";
                       int f=Integer.parseInt(solutionText);
                     String fS=Integer.toString(findFact(f));
                        solution_more.setText(data);
                     ans_more.setText(fS);
                     return;
                    case "x^2":
                        data=solutionText+"^2";
                        int c=Integer.parseInt(solutionText);
                        int c1=c*c;
                        String cS=Integer.toString(c1);
                        solution_more.setText(data);
                        ans_more.setText(cS);
                        return;
                    case "sin":
                        solutionText=solutionText+"sin";
                        solution_more.setText(solutionText);

                    default:
                        data = solutionText + buttonText;
                           char n='n';
                        String sinStore = null;
                       if(data.contains("sin")){
                           for(int i=(data.indexOf(n)+1);i<data.length();i++){
                               char a=data.charAt(i);
                               sinStore=sinStore+a;
                           }

                           double s=Double.parseDouble(sinStore);
                           String SS=Double.toString(Math.sin(Math.toRadians(s)));
                           solution_more.setText(data);
                           ans_more.setText(SS);
                       }


                        break;
                }

                    if(buttonText!="sin"||buttonText!="cos"||buttonText!="tan"||buttonText!="x!"||buttonText!="ln"||buttonText!="log"||buttonText!="√x"||buttonText!="x^2"){
                        solution_more.setText(data);
                        result = getResult(data);

                        if (!Objects.equals(result, "error")) {
                            ans_more.setText(result);
                        }
                }

                //solution.setText(buttonText);
            }

        });

    }
    public int findFact(int n){
        if(n==0||n==1){
            return 1;
        }
        return n*findFact(n-1);
    }
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
