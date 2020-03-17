package com.example.jsonandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    String mRUL="https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new XuLyDuLieuJson().execute(mRUL);
    }

    class XuLyDuLieuJson extends AsyncTask<String,Void,String>{
// 3 đầu vào của Async là đường dẫn kiểu String, cập nhật lấy kqtra về nên Void, rồi kq trả về String
//          Alt Enter implement doinbackground
        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
            // người dùng truyền đường dẫn vào nên truyền string vô
        }
        // chứ năng onPost đọc dl, trả về string
        // trả về chuỗi s
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // format du lieu về dạng json object, convert dạng string s về object json
            try {
                JSONObject jsonObject = new JSONObject(s);
                String website = jsonObject.getString("website");
                Log.d("BBB", website);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            // create a url object
            // khai báo và gán biến
            URL url = new URL(theUrl);
            // create a urlconnection object
            // tạo connection vs đường dẫn đc truyền vào
            URLConnection urlConnection = url.openConnection();
            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
