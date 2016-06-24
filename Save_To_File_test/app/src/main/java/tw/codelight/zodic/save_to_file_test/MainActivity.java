package tw.codelight.zodic.save_to_file_test;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView show;

    EditText enter;

    String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init() {

        show = (TextView) findViewById(R.id.show);
        enter = (EditText) findViewById(R.id.enter);

    }

    public void create(View view) {
        content = enter.getText().toString();
        FileOutputStream fos;
        try {
            fos = openFileOutput("Token.txt", Context.MODE_APPEND);

            fos.write(content.getBytes());
            fos.write('\n');
            fos.close();
            enter.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void look(View view) {
        FileInputStream fin;
        try {
            fin = openFileInput("Token.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String readStr, data = "";
            while ((readStr = reader.readLine()) != null) {
                data = readStr;
            }
            show.setText(data);
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
