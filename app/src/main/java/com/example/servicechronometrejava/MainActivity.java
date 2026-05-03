package com.example.servicechronometrejava;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemps;
    private Button btnStart, btnStop;
    private ChronometreService service;
    private boolean isBound = false;
    private Handler handler = new Handler();

    private final ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            ChronometreService.LocalBinder localBinder =
                    (ChronometreService.LocalBinder) binder;
            service = localBinder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemps = findViewById(R.id.tvTemps);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        // Permission notifications Android 13+
        if (Build.VERSION.SDK_INT >= 33) {
            requestPermissions(new String[]{"android.permission.POST_NOTIFICATIONS"}, 1);
        }

        btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChronometreService.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(intent);
            } else {
                startService(intent);
            }

            bindService(intent, connection, Context.BIND_AUTO_CREATE);
        });

        btnStop.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChronometreService.class);
            intent.setAction("STOP");
            startService(intent);

            if (isBound) {
                unbindService(connection);
                isBound = false;
            }
        });

        // 🔥 Mise à jour du chrono à l’écran
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isBound && service != null) {
                    int sec = service.getSecondes();
                    int min = sec / 60;
                    int s = sec % 60;
                    tvTemps.setText(String.format("%02d:%02d", min, s));
                }
                handler.postDelayed(this, 1000);
            }
        }, 1000);
    }
}