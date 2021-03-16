package com.elmoledmol.www;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitclient {
    private static final String base_url="http://clothesshopapi2.azurewebsites.net/";
    private static retrofitclient minstance;
    private final Retrofit retrofit;
    private static Gson gson;
    private retrofitclient(){
        retrofit=new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized retrofitclient getInstance(){
        if(minstance==null){
            minstance=new retrofitclient();
        }
        return minstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }

}
