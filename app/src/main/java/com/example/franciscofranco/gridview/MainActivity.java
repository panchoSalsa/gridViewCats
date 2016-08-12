package com.example.franciscofranco.gridview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private GridviewAdapter gridviewAdapter;
    private ArrayList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<String>();

        makeRequest();

        gridView = (GridView) findViewById(R.id.gridView);

        gridviewAdapter = new GridviewAdapter(MainActivity.this, items, getLayoutInflater());

        gridView.setAdapter(gridviewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DisplayImage.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.putExtra("url", items.get(position));
                startActivity(intent);
            }
        });
    }

    public void makeRequest() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("format", "xml");
        params.put("results_per_page", "10");
        client.get("http://thecatapi.com/api/images/get", params, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, String res) {
                        // called when response HTTP status is "200 OK"
                        JSONObject json;
                        try {
                            json = XML.toJSONObject(res);

                            JSONArray images = json.getJSONObject("response")
                                    .getJSONObject("data")
                                    .getJSONObject("images")
                                    .getJSONArray("image");
                            for (int i = 0; i < images.length(); ++i) {
                                items.add(images.getJSONObject(i).getString("url"));
                            }

                            gridviewAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            Log.e("JSON exception", e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    }
                }
        );
    }
}
