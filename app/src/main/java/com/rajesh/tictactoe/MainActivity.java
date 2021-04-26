package com.rajesh.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //        0 - X
    //        1 - O
    int activePlayer = 0;
    boolean gameActive = true;
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    //        State
    //        0 - X
    //        1 - O
    //        2 - Null
    int [] [] winPositions = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    final Handler handler = new Handler();
    public void playerTap(final View view){
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
             gameReset(view);
        }
        if(gameState[tappedImg] == 2) {
            gameState[tappedImg] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - Tap to play");
                status.setTextColor(Color.GREEN);
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - Tap to play");
                status.setTextColor(Color.BLUE);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        else {
            Toast.makeText(getApplicationContext(),"You can't place there",Toast.LENGTH_SHORT).show();
        }
//       check for win
        for (int [] winPosition: winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]
            && gameState[winPosition[0]] != 2){
                String winnerStr;
                gameActive = false;
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gameReset(view);
                    }
                },3000);
                if(gameState[winPosition[0]]== 0){
                    winnerStr = "X's won the game";
                }
                else{
                    winnerStr = "O's won the game";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                status.setTextColor(Color.RED);
            }
        }
        int filled = 0;
        for (int i=0; i<gameState.length; i++){
            Log.i("draw","started");
            if(gameState[i] != 2){
                filled = filled + 1;
                Log.d("filled","value" + filled);
            }
        }
        if(filled == 9){
            TextView status = findViewById(R.id.status);
            status.setText("The Game is Draw Play again");
            status.setTextColor(Color.RED);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gameReset(view);
                }
            },3000);

        }
    }

    public void gameReset(View view){
        gameActive= true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's turn - Tap to play");
        status.setTextColor(Color.BLUE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
