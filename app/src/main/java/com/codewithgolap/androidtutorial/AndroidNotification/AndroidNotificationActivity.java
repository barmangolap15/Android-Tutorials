package com.codewithgolap.androidtutorial.AndroidNotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.codewithgolap.androidtutorial.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class AndroidNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_notification);

        findViewById(R.id.buttonShowNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotificationWithImage();
            }
        });
    }

    //notification with image
    @SuppressLint("StaticFieldLeak")
    private void showNotificationWithImage(){
        new AsyncTask<String, Void, Bitmap>(){
            @Override
            protected Bitmap doInBackground(String... strings) {
                InputStream inputStream;
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    inputStream = connection.getInputStream();
                    return BitmapFactory.decodeStream(inputStream);
                }catch (Exception ignored){

                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                showNotification(bitmap);
            }
        }.execute("https://images.unsplash.com/photo-1512626120412-faf41adb4874?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=1050&q=80");
    }


    private void showNotification(Bitmap bitmap){

        int notificationId = new Random().nextInt(100);
        String channelId = "notification_channel_2";

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                getApplicationContext(), channelId );
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setContentTitle("Notification 2");
        builder.setContentText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s");

        //notification with big text style
        //builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"));
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));


        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if (notificationManager != null && notificationManager.getNotificationChannel(channelId) == null){
                NotificationChannel notificationChannel = new NotificationChannel(
                        channelId, "Notification Channel 1",
                        NotificationManager.IMPORTANCE_HIGH
                );
                notificationChannel.setDescription("This notification channel is used to notify user");
                notificationChannel.enableVibration(true);
                notificationChannel.enableLights(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        Notification notification = builder.build();
        if (notificationManager != null){
            notificationManager.notify(notificationId, notification);
        }
    }
}