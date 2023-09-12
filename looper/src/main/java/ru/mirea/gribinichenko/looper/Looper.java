package ru.mirea.gribinichenko.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Looper extends Thread{
    public Handler mHandler;
    private Handler mainHandler;
    public Looper(Handler mainThreadHandler) {
        mainHandler =mainThreadHandler;
    }
    public void run() {
        Log.d("MyLooper", "run");
        android.os.Looper.prepare();
        mHandler = new Handler(android.os.Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String data = msg.getData().getString("KEY");
                Log.d("MyLooper get message: ", data);
                int count = data.length();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("Возраст автора %s %d лет ",data, 20));
                message.setData(bundle);
                mainHandler.sendMessage(message);
            }
        };
        android.os.Looper.loop();
    }
}