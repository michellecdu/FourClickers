package com.dumichelle.fourclickers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
EditText edText;
int seekValue;
    TextView topleftbutton;
    TextView toprightbutton;
    TextView bottomleftbutton;
    TextView bottomrightbutton;
SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("values", MODE_PRIVATE);
        sharedPreferences = getSharedPreferences(String.valueOf(seekValue), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        topleftbutton = findViewById(R.id.topleftbutton);
        bottomleftbutton = findViewById(R.id.bottomleftbutton);
        toprightbutton = findViewById(R.id.toprightbutton);
        bottomrightbutton = findViewById(R.id.bottomrightbutton);
        edText = findViewById(R.id.editText);
        seekBar = findViewById(R.id.seeker);
        topleftbutton.setOnClickListener(this);
        bottomleftbutton.setOnClickListener(this);
        toprightbutton.setOnClickListener(this);
        bottomrightbutton.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekValue = i;
                //progress of seekbar in integer value

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                topleftbutton.setTextSize(seekValue);
                toprightbutton.setTextSize(seekValue);
                bottomleftbutton.setTextSize(seekValue);
                bottomrightbutton.setTextSize(seekValue);
            }
        });
        setInitialValues();
    }

    private void updateTextSize(int progress) {
        Toast.makeText(getApplicationContext(), "seekbar progress: " + progress, Toast.LENGTH_LONG);
    }

    //@Override
    public void onClick(View v) {
        TextView x = (TextView) v;
        x.setText(""+(Integer.parseInt(x.getText().toString())+1));
        storeValues();
    }

    private void setInitialValues() {
        topleftbutton.setText(""+sharedPreferences.getInt("topleft", 0));
        toprightbutton.setText(""+sharedPreferences.getInt("topright", 0));
        bottomleftbutton.setText(""+sharedPreferences.getInt("bottomleft", 0));
        bottomrightbutton.setText(""+sharedPreferences.getInt("bottomright", 0));
    }

    private void storeValues() {
        editor.putInt("topleft", Integer.parseInt(topleftbutton.getText().toString()));
        editor.putInt("topright", Integer.parseInt(toprightbutton.getText().toString()));
        editor.putInt("bottomleft", Integer.parseInt(bottomleftbutton.getText().toString()));
        editor.putInt("bottomright", Integer.parseInt(bottomrightbutton.getText().toString()));
        editor.apply();
    }

}