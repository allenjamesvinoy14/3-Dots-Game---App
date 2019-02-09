package com.example.luci4.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
//import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Button restart = (Button)findViewById(R.id.button);

    //TextView text = (TextView)findViewById(R.id.Result);
    int[] gamestate = {2,2,2,2,2,2,2,2,2};

    int [][] winningpos = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};

    int activePlayer = 0;
    int countClicks = 0;
    boolean gameactive = true;
    public void dropIn(View view){

        countClicks++;

        ImageView counter = (ImageView)view;
        int tapcounter = Integer.parseInt(counter.getTag().toString());
        if(gamestate[tapcounter]==2 && gameactive) {

            counter.setTranslationY(-1500);


            gamestate[tapcounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);
            String winner="";
            for (int[] winpos : winningpos) {
                if (gamestate[winpos[0]-1] == gamestate[winpos[1]-1] && gamestate[winpos[1]-1] == gamestate[winpos[2]-1] && gamestate[winpos[0]-1] != 2) {
                    gameactive=false;
                    if (gamestate[winpos[0]-1] == 0)
                        winner = "Yellow";
                    else
                        winner = "Red";

                    Toast.makeText(this, winner + " has won!", Toast.LENGTH_SHORT).show();
                }
            }
            if(countClicks == 9 && winner.equals("")){
                Toast.makeText(this, "Nobody won!", Toast.LENGTH_SHORT).show();
            }

        }
    }
    public void restartClick(View view){
        activePlayer=0;
        countClicks=0;
        gameactive=true;
        for(int i=0;i<9;i++){
            gamestate[i]=2;
        }
        GridLayout grid = findViewById(R.id.gridLayout);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ImageView child = (ImageView)grid.getChildAt(i);
            child.setImageDrawable(null);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
