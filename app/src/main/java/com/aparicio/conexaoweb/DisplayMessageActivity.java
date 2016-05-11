package com.aparicio.conexaoweb;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    private TextView retornoTextView01;
    private TextView retornoTextView02;
    private TextView retornoTextView03;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        retornoTextView01 = (TextView) findViewById(R.id.a);
        retornoTextView02 = (TextView) findViewById(R.id.b);
        retornoTextView03 = (TextView) findViewById(R.id.c);

        Intent i = getIntent();

        String mensagem = i.getStringExtra("mensagem");

        String[] parts = mensagem.split(";");
        String a = parts[0];
        String b = parts[1];
        String c = parts[2];


        retornoTextView01.setText(a);
        retornoTextView02.setText(b);
        retornoTextView03.setText(c);


    }

}
