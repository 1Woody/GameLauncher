package com.example.gamelauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class TictactoeActivity extends AppCompatActivity {

    private class Cell {
        Button bt;
        String value;
        public Cell(Context THIS){
            bt = new Button(THIS);
            value="";
            bt.setLayoutParams(new LinearLayout.LayoutParams(buttonsize, buttonsize));
            bt.setTextSize(buttonsize/10);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (value == "" && !finished()){
                        value = turn;
                        turn = turn == "X" ? "O" : "X";
                        bt.setText(value);
                        finished();

                    }
                }
            });
        }
    }

    private boolean finished(int i, int j, int di, int dj){
        int iini =i;
        int wi=i;
        int wj=j;
        int jini=j;
        for (int step=0; step<3; step++,i+=di, j+=dj){
            if (table[i][j].value == "" || table[i][j].value!=table[iini][jini].value) return false;
        }
        win(wi, wj, di, dj);
        return true;
    }

    private void win(int i, int j, int di, int dj){
        for (int step=0; step<3; step++,i+=di, j+=dj){
            table[i][j].bt.setTextColor(Color.RED);
            // return true;
        }
    }

    private boolean finished(){
        for(int i =0; i<3; i++){
            if (finished(i, 0, 0, 1)) return true;
        }
        for(int j =0; j<3; j++){
            if (finished(0, j, 1, 0)) return true;
        }
        if (finished(0, 0, 1, 1)) return true;
        if (finished(0, 2, 1, -1)) return true;
        return false;
    }

    Button btreset;
    Cell[][] table;
    LinearLayout linlay;
    LinearLayout linlay2;
    GridLayout gr;
    String turn;
    int buttonsize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        buttonsize = point.x/3;
        linlay = new LinearLayout(this);
        linlay.setGravity(Gravity.CENTER_HORIZONTAL);
        linlay.setOrientation(LinearLayout.VERTICAL);
        linlay2 = new LinearLayout(this);
        gr = new GridLayout(this);
        gr.setColumnCount(3);
        table= new Cell[3][3];
        for (int i=0; i<3; i++){
            //table[i] = new Cell[3];
            for(int j=0; j<3; j++){
                table[i][j]= new Cell(this);
                gr.addView(table[i][j].bt);
            }
        }
        turn="X";
        btreset = new Button(this);
        btreset.setText("Restart");
        restart(btreset);
        linlay.addView(gr);
        linlay2.addView(btreset);
        linlay.addView(linlay2);
        setContentView(linlay);
    }

    private void restart(Button bt){
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        table[i][j].bt.setText("");
                        table[i][j].bt.setTextColor(Color.BLACK);
                        table[i][j].value = "";

                    }
                }
            }
        });
    }
}