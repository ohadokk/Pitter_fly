package com.pushtest.ohad.pitter_fly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Pit Pit;
    Button B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pit = findViewById(R.id.pit_XML);

        B = new Button(this);
        B.setText(R.string.Button);
        B.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Pit.Graph.addToPit();
            }
        });

        Pit.addView(B);
    }
}
