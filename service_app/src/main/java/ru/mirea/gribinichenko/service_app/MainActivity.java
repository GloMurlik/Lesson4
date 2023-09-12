package ru.mirea.gribinichenko.service_app;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import ru.mirea.gribinichenko.service_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int PermissionCode = 200;
    private boolean play = false;

    private final Handler handler = new Handler();
    private MediaPlayer mediaPlayer;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        View view = binding.getRoot();
        setContentView(view);

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            Log.d(MainActivity.class.getSimpleName().toString(), "Разрешения получены");
        } else {
            Log.d(MainActivity.class.getSimpleName().toString(), "Нет разрешений!");
            ActivityCompat.requestPermissions(this, new String[]{POST_NOTIFICATIONS, FOREGROUND_SERVICE}, PermissionCode);
        }


        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (play == false) {
                    play = true;
                    binding.button3.setImageResource(android.R.drawable.ic_media_pause);
                    binding.textView1.setText("Let Go");
                    binding.textView2.setText("Asking Alexandria");
                    binding.image.setImageResource(R.drawable.pic);

                    Intent serviceIntent = new Intent(MainActivity.this, PlayerService.class);
                    ContextCompat.startForegroundService(MainActivity.this, serviceIntent);
                } else {
                    stopService(new Intent(MainActivity.this, PlayerService.class));
                    binding.button3.setImageResource(android.R.drawable.ic_media_play);
                    binding.textView1.setText("SongName");
                    binding.textView2.setText("GroupName");
                    binding.image.setImageResource(R.drawable.ym);
                    play = false;
                }
            }
        });
    }
}