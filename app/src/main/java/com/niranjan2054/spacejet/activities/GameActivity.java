package com.niranjan2054.spacejet.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.niranjan2054.spacejet.R;
import com.niranjan2054.spacejet.modal.GameView;

public class GameActivity extends AppCompatActivity {
private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);

        gameView = new GameView(this);
        setContentView(gameView);
    }
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
