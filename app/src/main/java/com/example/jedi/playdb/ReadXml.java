package com.example.jedi.playdb;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ReadXml extends AppCompatActivity {
    TextView outputText;
    EditText inputText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_xml);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        outputText = (TextView)findViewById(R.id.output_text);
        inputText = (EditText)findViewById(R.id.input_text);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void showText(View view){
        outputText = (TextView)findViewById(R.id.output_text);
        inputText = (EditText)findViewById(R.id.input_text);
        Log.i("Look am here now", "showText: this text should appear");
        String getOutputText = inputText.getText().toString();
        Log.i("Hey look here", "showText: " + getOutputText);
        outputText.setText("" + getOutputText);
    }

}
