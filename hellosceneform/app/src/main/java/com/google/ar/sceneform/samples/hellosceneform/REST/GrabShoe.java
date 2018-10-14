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

//    public volatile Shoe shoes[] = new Shoe[25];

//    public void addToIt(String name,int cost, int i){
//        Shoe tempShoe = new Shoe();
//        tempShoe.name = name;
//        tempShoe.cost = cost;
//        tempShoe.imageLink = HttpUtils.genImageUrl(name);
//        shoes[i] = tempShoe;
//    }
//
//    public Shoe[] getShoeArr(){
//        return shoes;
//    }
//
//    public GrabShoe() {
//    }
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

