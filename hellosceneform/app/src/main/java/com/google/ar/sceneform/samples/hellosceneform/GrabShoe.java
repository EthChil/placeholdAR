package com.google.ar.sceneform.samples.hellosceneform;

import android.preference.PreferenceActivity;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class GrabShoe {

    public Shoe shoes[] = new Shoe[25];

    public GrabShoe() {
        RequestParams rp = new RequestParams();
        rp.add("x-api-key", "B1sR9t386d6UVO6aI7KRf91gLaUywqEK1TLBGsXv");

        HttpUtils.get("https://gateway.stockx.com/public/v1/browse?limit=25", rp, new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                Log.d("asd", "---------------- this is response : " + response);
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    Log.i("policemangay","POTATO: starting tempShoe logs");
                    for(int i = 0; i < 25; i++){
                        Shoe tempShoe = new Shoe();
                        tempShoe.name = serverResp.getString("shortDescription");
                        tempShoe.cost = serverResp.getInt("retailPrice");

                        tempShoe.imageLink = HttpUtils.genImageUrl(tempShoe.name);
                        shoes[i] = tempShoe;
                    }

                    Log.i("policemangay", "POTATO: finished tempShoe logs");
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }

            public void onSuccess(int statusCode, PreferenceActivity.Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline

            }
        });

    }

}
