package com.android.ipshita.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v("String","running");

        final Button play=(Button)findViewById(R.id.play);
        Button pause=(Button)findViewById(R.id.pause);
        Button volume=(Button)findViewById(R.id.volume);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.hpaudio);



        assert play != null;
        play.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the play View is clicked on.
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Playing sound", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(getApplicationContext(), "I'm Done!", Toast.LENGTH_SHORT).show();
                    }
                });
                mediaPlayer.release();
            }
        });


        assert pause != null;
        pause.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the pause View is clicked on.
            @Override
            public void onClick(View view) {

                mediaPlayer.pause();

            }
        });

        assert volume != null;
        volume.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the pause View is clicked on.
            @Override
            public void onClick(View view) {

                if(mediaPlayer!=null) mediaPlayer.setVolume(0.2f, 0.2f);
            }
        });
    }

}
