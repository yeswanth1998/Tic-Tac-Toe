package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainWindow extends AppCompatActivity {

    ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    TextView winningMessage;
    String Message;
    Button playAgain;
    int activePlayer = 1;
    int gameState[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    int [][] winningPositions= {{0,4,8},{2,4,8},{0,3,6},{0,1,2},{3,4,5},{6,7,8},{1,4,7},{2,5,8}};
    boolean isGameActive = true;
    boolean isDraw = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        playAgain = findViewById(R.id.playAgain);

        winningMessage = findViewById(R.id.winningMessage);



    }

    public void ImageViewClicked(View view) {

        ImageView tappedImage = (ImageView)view;
        int tappedPosition = Integer.parseInt(tappedImage.getTag().toString())-1;

        if(gameState[tappedPosition]==-1 && isGameActive)
        {
            gameState[tappedPosition] = activePlayer;
            if(activePlayer==1) {
                activePlayer = 2;
                tappedImage.setImageResource(R.drawable.red);
            }
            else {
                activePlayer = 1;
                tappedImage.setImageResource(R.drawable.yellow);
            }

            for(int[] winningPosition : winningPositions)
            {
                if((gameState[winningPosition[0]] == gameState[winningPosition[1]]) &&
                        (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&
                        (gameState[winningPosition[2]] != -1))
                {
                    isGameActive = false;

                    if(gameState[winningPosition[0]] == 1)
                        Message = "Player1 won the match";
                    else
                        Message = "Player2 won the match";

                }
                else
                {
                    for(int state : gameState)
                        if(state == 0)
                                isDraw = false;
                    if(isDraw) {
                        Message = "This is a Draw Match";
                        isGameActive = false;
                    }
                }

                if(!isGameActive) {
                    winningMessage.setText(Message);
                    winningMessage.setVisibility(View.VISIBLE);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }

        }
        else
        {
            if(isGameActive)
            Toast.makeText(this, "Tapped on occupied box", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Click Play Again Button to Start a New Game", Toast.LENGTH_SHORT).show();
        }



    }

    public void PlayAgainButtonClicked(View view) {

        image1.setImageResource(0);
        image2.setImageResource(0);
        image3.setImageResource(0);
        image4.setImageResource(0);
        image5.setImageResource(0);
        image6.setImageResource(0);
        image7.setImageResource(0);
        image8.setImageResource(0);
        image9.setImageResource(0);

        isGameActive = true;
        activePlayer = 1;

        for (int i =0; i<9; i++) {
            gameState[i] = -1;
        }

        winningMessage.setVisibility(View.INVISIBLE);
        playAgain.setVisibility(View.INVISIBLE);

        Toast.makeText(this, "Starting New Game", Toast.LENGTH_SHORT).show();
    }
}