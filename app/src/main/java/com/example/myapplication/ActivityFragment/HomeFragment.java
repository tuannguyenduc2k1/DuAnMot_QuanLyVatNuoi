package com.example.myapplication.ActivityFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.Adapter.AdapterHome;
import com.example.myapplication.Model.TinTuc;
import com.example.myapplication.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    ListView lv_tin_tuc;
    View mView;
    String link = "https://nongnghiep.vn/chan-nuoi.rss";
    List<TinTuc> tin_tucList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView =  inflater.inflate(R.layout.fragment_home, container, false);
        lv_tin_tuc = (ListView)mView.findViewById(R.id.lv_tin_tuc);
        tin_tucList = new ArrayList<>();
        loadData();
        lv_tin_tuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String link = tin_tucList.get(position).getLink();
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));

                Intent intent = new Intent(getActivity() , OpenBao.class);
                Bundle bundle = new Bundle();
                bundle.putString("link" , tin_tucList.get(position).getLink());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        return mView;
    }

    public void test_data(String link){
        Log.e("---------------------" , "K???t n???i ?????n link ");
        try {
//          ki???m tra link ????ng hay sai
            URL url = new URL(link);
//          M??? k???t n???i
            HttpURLConnection urlConnection =
                    (HttpURLConnection) url.openConnection();
//          l???y data v??o qua inputStream ( m???t ?????i t?????ng cho ph??p ?????c )
            InputStream inputStream = urlConnection.getInputStream();
//          D??ng c??c ?????i t?????ng sau ????? x??? l?? file ho???c d??? li???u ki???u xml
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(false);

            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(inputStream, "utf-8");
//            lay su kien
            int event = xmlPullParser.getEventType();

            TinTuc tinTuc = null;
            String text = null;

            Log.e("\n\tb???t ?????u l???y data " , "step 1");

            while (event != XmlPullParser.END_DOCUMENT) {

                String tag = xmlPullParser.getName();
                switch (event) {
                    case XmlPullParser.START_TAG:
                        if ( tag.equalsIgnoreCase("item") ){
                            tinTuc = new TinTuc();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = xmlPullParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tinTuc != null){
                            if (tag.equalsIgnoreCase("title")){
                                tinTuc.title = text;
                            }

                            if (tag.equalsIgnoreCase("description")){
                                tinTuc.des = text;
                            }

                            if (tag.equalsIgnoreCase("link")){
                                tinTuc.link = text;
                            }

                            if (tag.equalsIgnoreCase("pubDate")){
                                tinTuc.pubDate = text;
                            }

                            if (tag.equalsIgnoreCase("item")){
                                tin_tucList.add(tinTuc);
                            }
                        }
                        break;
                }

                event = xmlPullParser.next();
            }
            Log.e("----------------------" , " " + tin_tucList.size() + "\t\n\t\t Finish");

        } catch (MalformedURLException e) {
            Log.e("Error 1 :  " , String.valueOf(e.getMessage()));
//            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Error 2 :  " , String.valueOf(e.getMessage()));
//            e.printStackTrace();
        } catch (XmlPullParserException e) {
            Log.e("Error 3 :  " , String.valueOf(e.getMessage()));
//            e.printStackTrace();
        }

    }
    void loadData(){
        show(link);
    }
    public void show(String link){
        tin_tucList.clear();
        Log.e("-----------------------" , "vao luong phu 1111111111111");
        //      t???o lu???ng ph??? x??? l??
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Log.e("-----------------------" , "B???t ?????u x??? l?? xml");
                test_data(link);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
//                duoc goi khi luong ket thuc
//                khai bao adapter
//                Log.e("getId :  " + tin_tucList.get(0).getId() ,"getTitle :  " + tin_tucList.get(0).getTitle());
//                Log.e("\n getDes :  " + tin_tucList.get(0).getDes() , "\n getPubDate :  " + tin_tucList.get(0).getPubDate());
                AdapterHome adapter = new AdapterHome(getContext() , tin_tucList);
                //
//                bo array vao adapter
//                set adapter cho listview
                adapter.notifyDataSetChanged();
                lv_tin_tuc.setAdapter(adapter);

            }
        };
        asyncTask.execute();
    }
}