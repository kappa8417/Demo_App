package tw.itlab.zhaojun.demoapp.okhttp_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by zhaojun on 15/12/10.
 */

public class okhttp_POST extends AppCompatActivity {

    TextView show;

    EditText say;

    String content = null, cache = null;

    private static String url_location = "http://127.0.0.1/post";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.okhttp_post);

        say = (EditText) findViewById(R.id.enter);
        show = (TextView) findViewById(R.id.show);

    }

    public void clac(View view) {

        content = say.getText().toString();

        if (content.equals("")) {
            Toast.makeText(this, "請輸入文字", Toast.LENGTH_SHORT).show();
        } else {
            new LoadingDataAsyncTask().execute();
        }
    }

    public void call_ok_http(String url) {
        try {

            ok_http_core example = new ok_http_core();
            cache = example.post(url, content);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ok_http_core {

        OkHttpClient client = new OkHttpClient();

        String post(String url, String text) throws IOException {
            RequestBody body = new FormBody.Builder()
                    .add("content", text)
                    .build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
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

