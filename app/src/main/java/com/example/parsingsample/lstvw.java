package com.example.parsingsample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class lstvw extends AppCompatActivity {
    AsyncHttpClient client;


    RequestParams params;
    ListView lstv;
    LayoutInflater inflater;
    ArrayList<String> jobarr;
    ArrayList<String> desarr;
    ArrayList<String> deadarr;
    String url="http://sicsglobal.co.in/Service_App/API/ViewJobAsPlace.aspx?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lstvw);
        client = new AsyncHttpClient();
        params = new RequestParams();
        lstv=findViewById(R.id.listview);
        jobarr= new ArrayList<String>();
        desarr= new ArrayList<String>();
        deadarr= new ArrayList<String>();
        SharedPreferences shared=getApplicationContext().getSharedPreferences("pref",MODE_PRIVATE);
         String editstr=shared.getString("key",null);
         params.put("place",editstr);
          client.get(url, params,new AsyncHttpResponseHandler()
                  {
                      @Override
                      public void onSuccess(String content) {
                          super.onSuccess(content);
                         try {
                         JSONObject jsonObject=new JSONObject(content);
                         JSONArray jsonArray=jsonObject.getJSONArray("Details");
                         for(int i=0;i<jsonArray.length();i++)
                         {                      
                           JSONObject jsonObject1=jsonArray.getJSONObject(i);
                           String title=jsonObject1.getString("jobtitle");
                           jobarr.add(title);
                           String des=jsonObject1.getString("jobdes");
                           desarr.add(des);
                           String dead=jsonObject1.getString("deadline");
                           deadarr.add(dead);

                         }
                         adapter adpt=new adapter();
                         lstv.setAdapter(adpt);

                         }
                         catch (Exception e){

                         }
                      }
                  }
          );
    }
    class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return jobarr.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.layy,null);
            TextView jobh=convertView.findViewById(R.id.job);
            TextView descriph=convertView.findViewById(R.id.descrip);
            TextView deadh=convertView.findViewById(R.id.deadl);

            jobh.setText(jobarr.get(position));
            descriph.setText(desarr.get(position));
            deadh.setText(deadarr.get(position));

            return convertView;
        }
    }
}
