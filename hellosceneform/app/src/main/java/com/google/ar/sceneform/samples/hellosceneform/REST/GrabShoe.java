package com.google.ar.sceneform.samples.hellosceneform.REST;

import android.util.Log;

import com.google.ar.sceneform.samples.hellosceneform.HttpUtils;
import com.google.ar.sceneform.samples.hellosceneform.Shoe;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Call;


import static com.loopj.android.http.AsyncHttpClient.LOG_TAG;
import static com.loopj.android.http.AsyncHttpClient.log;

public class GrabShoe {

    public Shoe shoes[] = new Shoe[25];


    public GrabShoe() {
        StockXinterface service = StockXinterface.retrofit.create(StockXinterface.class);

        retrofit2.Call<ShoesResponse> call = service.getShoes();

        call.enqueue(new Callback<ShoesResponse>() {
            @Override
            public void onResponse(retrofit2.Call<ShoesResponse> call, Response<ShoesResponse> response) {
                Log.i(LOG_TAG, "Received response: " + response.toString());
                try {
                    JSONObject serverResp = new JSONObject(response.toString());
                    System.out.print("POTATO: starting tempShoe logs");
                    for (int i = 0; i < 25; i++) {
                        Shoe tempShoe = new Shoe();
                        Log.i("POTATO", serverResp.getString("shortDescription"));
                        tempShoe.name = serverResp.getString("shortDescription");
                        tempShoe.cost = serverResp.getInt("retailPrice");

                        tempShoe.imageLink = HttpUtils.genImageUrl(tempShoe.name);
                        shoes[i] = tempShoe;
                    }
                } catch (JSONException e) {
                    Log.i("POTATO", "OOOOOOOF");
                }


            }

            @Override
            public void onFailure (Call<ShoesResponse> call, Throwable t){
                Log.i(LOG_TAG, t.getMessage());
            }

        });
        try {
            Thread.sleep(1000);
        }catch (Exception e){ }
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

