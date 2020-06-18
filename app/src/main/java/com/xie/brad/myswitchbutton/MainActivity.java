package com.xie.brad.myswitchbutton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xie.brad.myswitchbutton.MyButton.SwitchButton;

public class MainActivity extends AppCompatActivity implements SwitchButton.OnSwitchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SwitchButton switchButton = (SwitchButton) findViewById(R.id.switchbutton);
        switchButton.setOnSwitchListener(this);
    }

    @Override
    public void openbutton() {
        Toast.makeText(this,"开",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closebutton() {
        Toast.makeText(this,"关",Toast.LENGTH_SHORT).show();
    }
}
