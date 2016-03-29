package com.example.xyzreader.network;

import com.example.xyzreader.network.model.DataModel;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import co.uk.rushorm.core.RushSearch;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;

/**
 * Created by aditlal on 26/03/16.
 */
public class ItemService {
    public static final String API_ENDPOINT = "https://dl.dropboxusercontent.com/u/231329/xyzreader_data";
    private static ItemApiInterface sItemApiInterface;

    public static ItemApiInterface getApi() {
        if (sItemApiInterface == null) {
            sItemApiInterface = null;
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(API_ENDPOINT)
                    .setClient(new OkClient(new OkHttpClient()))
                    .build();

            sItemApiInterface = restAdapter.create(ItemApiInterface.class);
        }
        return sItemApiInterface;
    }

    public interface ItemApiInterface {
        @GET("/data.json")
        List<DataModel> getItems();
    }

    public static List<DataModel> getLocalItems() {
        return new RushSearch().find(DataModel.class);
    }

}
