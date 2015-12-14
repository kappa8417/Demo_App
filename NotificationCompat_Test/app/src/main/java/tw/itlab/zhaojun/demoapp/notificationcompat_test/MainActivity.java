package tw.itlab.zhaojun.demoapp.notificationcompat_test;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    // A integer, that identifies each notification uniquely
    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clac(View view) {

        //set adapter
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.smallkappa);

        //set onclick event
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.tw/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //set content
        builder.setContentIntent(pendingIntent);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.kappa));
        builder.setContentTitle("我是標題");
        builder.setContentText("我是副標題");
        builder.setSubText("我是內容，點一下會到Google喔");
        builder.setVibrate(new long[]{100, 200, 100, 200, 100, 200});
        builder.addAction(R.drawable.smallkappa, "OK", pendingIntent);

        //show
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}

