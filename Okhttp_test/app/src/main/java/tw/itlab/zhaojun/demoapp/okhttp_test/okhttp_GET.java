package tw.itlab.zhaojun.demoapp.okhttp_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaojun on 15/12/10.
 */

public class okhttp_GET extends AppCompatActivity {

    TextView show;

    String cache = null;

    private static String url_location = "http://120.114.104.124/json/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_get);

        show = (TextView) findViewById(R.id.show);

    }

    public void clac(View view) {

        new LoadingDataAsyncTask().execute();

    }

    public void call_ok_http(String url) {
        try {

            ok_http_core example = new ok_http_core();
            cache = example.run(url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ok_http_core {
        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder().url(url).build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }
    }

    class LoadingDataAsyncTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected Integer doInBackground(String... param) {

            call_ok_http(url_location);

            return null;
        }

        @Override
        protected void onPostExecute(Integer result) {
            super.onPostExecute(result);

            show.setText(cache);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

    }


}
