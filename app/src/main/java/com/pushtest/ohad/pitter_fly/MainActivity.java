package com.pushtest.ohad.pitter_fly;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Pit pit;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pit = findViewById(R.id.pit_XML);

        b = new Button(this);
        b.setText(R.string.Button);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                pit.graph.addToPit();
            }
        });

        pit.addView(b);
    }
}
