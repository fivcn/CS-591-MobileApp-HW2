package com.example.sse.lect2_activitylifecycle_logging_savingstate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "LECT2_FLAG";  //this will be our trail of breadcrumbs for logging events.
    private static int eventCount = 0;

    private Button btnConvert;
    private EditText Output;
    private EditText Input;
    private RadioButton btnCtoF;
    private RadioButton btnFtoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onCreate State Transition");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConvert = (Button) findViewById(R.id.btnConvert);
        Output = (EditText) findViewById(R.id.txtOutput);
        Input = (EditText) findViewById(R.id.txtInput);
        btnCtoF = (RadioButton) findViewById(R.id.btnCtoF);
        btnFtoC = (RadioButton) findViewById(R.id.btnFtoC);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String C, F;
                Double DegC, DegF;
                Boolean FtoC = false;
                Boolean CtoF = false;

                // checking the status
                if (btnCtoF.isChecked())
                {
                    FtoC = false;
                    CtoF = true;
                }
                else if (btnFtoC.isChecked())
                {
                    FtoC = true;
                    CtoF = false;
                }
                else
                {
                    Log.i("Debugger", "radio button not selected");
                }

                if (FtoC == true)
                {
                    F = Input.getText().toString();
                    try{
                        DegF = Double.parseDouble(F);
                        DegC = (DegF - 32) * 5.0/9.0;
                        DegC = (double) Math.round(DegC * 100) / 100;
                        C = DegC.toString() + " C";
                        Output.setText(C);
                    } catch (NumberFormatException e) {
                        Log.i("Debugger", "invalid input");
                    }
                }
                else if (CtoF == true)
                {
                    C = Input.getText().toString();
                    try{
                        DegC = Double.parseDouble(C);
                        DegF = DegC*9.0/5.0 + 32;   //todo, dblcheck formula.
                        DegF = (double) Math.round(DegF * 100) / 100;
                        F = DegF.toString() + " F";
                        Output.setText(F);
                    } catch (NumberFormatException e)
                    {
                        Log.i("Debugger", "invalid input");
                    }
                }
            }
        });


    }

    //Useful Notes:
        // ctrl-O is a shortcut to override base methods
        // Alt-Ins is a shortcut to overriding base methods and more.

    @Override
    protected void onPause() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onPause State Transition");
        super.onPause();
    }


    @Override
    protected void onStart() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onStart State Transition");
        super.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onSaveInstanceState State Transition");
        Log.i(MyFlag, "Bundling State of our views before they get destroyed");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        Log.i(MyFlag, "Retrieving our saved state from before... ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        super.onResume();
    }


//Handy Helpers...
    public String intToStr(Integer i)
    {
        return i.toString();
    }

    public int strToInt(String S)
    {
       return Integer.parseInt(S);
    }


}

