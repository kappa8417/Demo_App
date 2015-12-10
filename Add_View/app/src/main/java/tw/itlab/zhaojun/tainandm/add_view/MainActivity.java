package tw.itlab.zhaojun.tainandm.add_view;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    //json string
    private String localjsonString = "{\"store_data\":[{\"id\":\"1\",\"store_name\":\"郭媽媽\",\"address\":\"710台南縣永康市大灣路901號\",\"phone_number\":\"06-2718041\",\"open_time\":\"11:00\",\"close_time\":\"21:30\"}," +
            "{\"id\":\"2\",\"store_name\":\"竇爸\",\"address\":\"710台南縣永康市大灣路901號\",\"phone_number\":\"06-2710022\",\"open_time\":\"10:30\",\"close_time\":\"20:00\"}," +
            "{\"id\":\"3\",\"store_name\":\"神座拉麵\",\"address\":\"710台南縣永康市大灣路901號\",\"phone_number\":\"06-2718041\",\"open_time\":\"11:00\",\"close_time\":\"20:30\"}," +
            "{\"id\":\"4\",\"store_name\":\"原始禪飲\",\"address\":\"710台南縣永康市大灣路901號\",\"phone_number\":\"06-2718041\",\"open_time\":\"11:00\",\"close_time\":\"19:30\"}]}";

    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    private SimpleAdapter adapter;

    ListView menu;
    ProgressDialog progressDialog;

    int json_total = 0;
    int store_total = 0;

    String total = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = (ListView) findViewById(android.R.id.list);

        progressDialog = ProgressDialog.show(MainActivity.this, "提醒", "正在取得資訊請稍候");

        new LoadingDataAsyncTask().execute();

    }

    public void get_info() {

        try {

            String cat = "get_store_info";
            String urlParameters = "cat=" + URLEncoder.encode(cat, "UTF-8");
            URL url;

            HttpURLConnection connection = null;

            try {

                url = new URL("http://store.itlab.tw/json");

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
                connection.setDoInput(true);
                connection.setDoOutput(true);

                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                Log.w("mydebug_cat", urlParameters);

                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = rd.readLine()) != null) {
                    total = total + line;
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void put_info() {

        try {

            JSONObject obj = new JSONObject(localjsonString);
            JSONArray arr = obj.optJSONArray("store_data");

            json_total = arr.length();

            final String[] store_name_arr = new String[json_total];
            final String[] phone_number_arr = new String[json_total];
            final String[] address_arr = new String[json_total];

            for (int i = 0; i < json_total; i++) {
                JSONObject out = arr.getJSONObject(i);
                String store_name = out.optString("store_name");
                String phone_number = out.optString("phone_number");
                String address = out.optString("address");

                store_total = store_name_arr.length;

                store_name_arr[i] = store_name;
                phone_number_arr[i] = phone_number;
                address_arr[i] = address;

            }

            //把資料加入ArrayList中
            for (int i = 0; i < store_total; i++) {
                HashMap<String, String> item = new HashMap<String, String>();
                item.put("store_name", store_name_arr[i]);
                item.put("phone_number", phone_number_arr[i]);
                item.put("address", address_arr[i]);
                list.add(item);
            }

            //新增SimpleAdapter
            adapter = new SimpleAdapter(this, list, R.layout.listview_item,
                    new String[]{"store_name", "phone_number", "address"},
                    new int[]{R.id.store_name, R.id.store_phone, R.id.store_address});

            progressDialog.dismiss();

            //ListActivity設定adapter
            setListAdapter(adapter);
            menu.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(), "你選擇的是" + store_name_arr[position], Toast.LENGTH_SHORT).show();
                }
            });

            //啟用按鍵過濾功能，這兩行資料都會進行過濾
            getListView().setTextFilterEnabled(true);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    class LoadingDataAsyncTask extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            get_info();

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            put_info();

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
