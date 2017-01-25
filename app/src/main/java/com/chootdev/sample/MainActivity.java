package com.chootdev.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1,button2,button3,button4,button5,button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button5 = (Button)findViewById(R.id.button5);
        button6 = (Button)findViewById(R.id.button6);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Snackbar.with(this,null)
                        .type(Type.SUCCESS)
                        .message("Profile updated successfully!")
                        .duration(Duration.SHORT)
                        .show();
                break;
            case R.id.button2:
                Snackbar.with(this,null)
                        .type(Type.ERROR)
                        .message("Something went wrong!")
                        .duration(Duration.SHORT)
                        .show();
                break;
            case R.id.button3:
                Snackbar.with(this,null)
                        .type(Type.UPDATE)
                        .message("Please wait...")
                        .duration(Duration.SHORT)
                        .show();
                break;
            case R.id.button4:
                Snackbar.with(this,null)
                        .type(Type.WARNING)
                        .message("Be alert...")
                        .duration(Duration.SHORT)
                        .show();
                break;
            case R.id.button5:
                Snackbar.with(this,null)
                        .type(Type.CUSTOM, 0xff00A7A5)
                        .message("This is custom color.")
                        .duration(Duration.SHORT)
                        .show();
                break;
            case R.id.button6:
                View v = getLayoutInflater().inflate(R.layout.custom_view, null);

                Snackbar.with(this,null)
                        .type(Type.UPDATE)
                        .contentView(v, 76)
                        .duration(Duration.SHORT)
                        .show();
                break;
        }
    }
}
