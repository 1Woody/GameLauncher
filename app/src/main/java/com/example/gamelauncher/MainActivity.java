package com.example.gamelauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout linlay;
    TextView tv;

    private class ButtonMenu{
        Button bt;
        public ButtonMenu(final String text, Context THIS){
            bt = new Button(THIS);
            bt.setText(text);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    if(text.equals("TICTACTOE")) intent = new Intent(MainActivity.this, TictactoeActivity.class);
                    else if (text.equals("CONNECT4")) intent = new Intent(MainActivity.this, Connect4Activity.class);
                    else intent = new Intent(MainActivity.this, SudokuActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] gamenames = {"TICTACTOE","CONNECT4","SUDOKU",};
        linlay = new LinearLayout(this);
        tv = new TextView(this);
        tv.setText("CLASSIC GAMES");
        tv.setTextSize(40);
        linlay.addView(tv);
        for(int i=0; i<3; i++){
            linlay.addView((new ButtonMenu(gamenames[i],this)).bt);
        }
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        setContentView(linlay);
    }
}