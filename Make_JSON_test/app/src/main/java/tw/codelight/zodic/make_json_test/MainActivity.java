package tw.codelight.zodic.make_json_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String json, print;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clac(View view) {
        try {
            print = Make_JSON();

            TextView show = (TextView) findViewById(R.id.show);
            show.setText(print);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String Make_JSON() throws JSONException {

        JSONObject json_obj = new JSONObject();

        JSONObject data_obj = new JSONObject();

        JSONArray data_arr = new JSONArray();

        json_obj.put("status", "success");
        json_obj.put("message", "sub_annc_channel");

        data_obj.put("course_uid", "9e84b835");
        data_obj.put("course_year", "104");
        data_obj.put("course_semester", "2");
        data_obj.put("course_name", "資訊倫理與智慧財產權");
        data_obj.put("course_teacher", "蔡崇洲");
        data_obj.put("course_class", "四電通二Ａ");

        json_obj.put("data", data_arr.put(data_obj));

        json = json_obj.toString();

        return json;

    }
}
