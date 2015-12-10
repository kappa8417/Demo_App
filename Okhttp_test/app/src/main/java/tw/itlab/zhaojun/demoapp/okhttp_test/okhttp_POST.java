package tw.itlab.zhaojun.demoapp.okhttp_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by zhaojun on 15/12/10.
 */

public class okhttp_POST extends AppCompatActivity {

    TextView show;

    EditText enter;

    String content = null, cache = null;

    private static String url_location = "http://120.114.104.124/json/";

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_post);

        show = (TextView) findViewById(R.id.show);

        enter = (EditText) findViewById(R.id.enter);

    }

    public void clac(View view) {

        new LoadingDataAsyncTask().execute();

        content = enter.getText().toString();

    }

    public void call_ok_http(String url, String text) {
        try {

            ok_http_core example = new ok_http_core();
            cache = example.post(url, text);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ok_http_core {

        OkHttpClient client = new OkHttpClient();

        String post(String get_url, String get_text) throws IOException {

            RequestBody formBody = new FormEncodingBuilder()
                    .add("cat", "1")
                    .add("say", get_text)
                    .build();

            Request request = new Request.Builder()
                    .url(get_url)
                    .post(formBody)
                    .build();

            Response response = client.newCall(request).execute();

            return response.body().string();

        }
    }

    class LoadingDataAsyncTask extends AsyncTask<String, Integer, Integer> {

        @Override
        protected Integer doInBackground(String... param) {

            call_ok_http(url_location, content);

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

