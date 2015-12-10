package tw.itlab.zhaojun.demoapp.okhttp_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void get(View view) {

        Intent jump_get = new Intent(MainActivity.this, okhttp_GET.class);
        startActivity(jump_get);

    }

    public void post(View view) {

        Intent jump_post = new Intent(MainActivity.this, okhttp_POST.class);
        startActivity(jump_post);

    }

}
