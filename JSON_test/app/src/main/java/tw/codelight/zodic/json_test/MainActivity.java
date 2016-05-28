package tw.codelight.zodic.json_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView show;

    String cache = null, get_status, get_messages, get_name, get_personid, get_school, get_department;


    private static String url_location = "http://127.0.0.1/json.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (TextView) findViewById(R.id.show);

    }

    public void Send(View view) {

        new LoadingDataAsyncTask().execute();

    }

    public void call_ok_http(String url) {
        try {

            ok_http_core example = new ok_http_core();

            cache = example.run(url);

            Decode_JSON(cache);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class ok_http_core {

        OkHttpClient client = new OkHttpClient();

        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

    }

    public void Decode_JSON(String JSON) throws JSONException {

        JSONObject json_obj = new JSONObject(JSON);
        get_status = json_obj.getString("status");
        get_messages = json_obj.optString("messages");
        JSONArray data_arr = json_obj.getJSONArray("data");

        for (int i = 0; i < data_arr.length(); i++) {

            get_name = data_arr.getJSONObject(i).getString("user_name");
            get_personid = data_arr.getJSONObject(i).getString("user_personid");
            get_school = data_arr.getJSONObject(i).getString("user_school");
            get_department = data_arr.getJSONObject(i).getString("user_department_name");

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

            show.setText("狀態：" + get_status + "\n" + "訊息：" + get_messages + "\n" +
                    "姓名：" + get_name + "\n" + "學號：" + get_personid + "\n" +
                    "學校：" + get_school + "\n" + "科系：" + get_department);

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