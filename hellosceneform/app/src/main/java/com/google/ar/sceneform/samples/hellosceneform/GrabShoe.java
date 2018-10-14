package com.google.ar.sceneform.samples.hellosceneform;

import android.preference.PreferenceActivity;
import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;


import static com.loopj.android.http.AsyncHttpClient.LOG_TAG;

public class GrabShoe {

    Shoe shoes[] = new Shoe[25];


    private void getShoes() {
        StockXinterface service = StockXinterface.retrofit.create(StockXinterface.class);

        retrofit2.Call<StockX> call = service.getShoes();

        call.enqueue(new Callback<StockX>() {
            @Override
            public void onResponse(retrofit2.Call<StockX> call, Response<StockX> response) {
                Log.i(LOG_TAG, "Received response: " + response.toString());
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    System.out.print("POTATO: starting tempShoe logs");
                    for (int i = 0; i < 25; i++) {
                        Shoe tempShoe = new Shoe();
                        tempShoe.name = serverResp.getString("shortDescription");
                        tempShoe.cost = serverResp.getInt("retailPrice");

                        tempShoe.imageLink = HttpUtils.genImageUrl(tempShoe.name);
                        shoes[i] = tempShoe;
                    }
                } catch (JSONException e) {
                }


            }

            @Override
            public void onFailure (Call<StockX> call, Throwable t){
                Log.i(LOG_TAG, t.getMessage());
            }

        });
    }
}



//    public BufferedImage getImageFormat(String link){
//
//        BufferedImage img = null;
//
//        try {
//            response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//            URL url = new URL(link);
//            img = ImageIO.read(url);
//        }catch (IOException e){
//
//        };
//
//        ImageFilter filter = new RGBImageFilter() {
//            int transparentColor = Color.white.getRGB() | 0xFF000000;
//
//            public final int filterRGB(int x, int y, int rgb) {
//                if ((rgb | 0xFF000000) == transparentColor) {
//                    return 0x00FFFFFF & rgb;
//                } else {
//                    return rgb;
//                }
//            }
//        };
//
//        ImageProducer filteredImgProd = new FilteredImageSource(icon.getImage().getSource(), filter);
//        Image transparentImg = Toolkit.getDefaultToolkit().createImage(filteredImgProd);
//
//
//        label.setIcon(new ImageIcon(transparentImg));
//
//        frame.add(label);
//
//    }

