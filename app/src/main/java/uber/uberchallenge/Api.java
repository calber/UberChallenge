package uber.uberchallenge;

import java.util.Map;

import models.Response;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by calber on 18/4/15.
 */
public interface Api {

    @GET("/images")
    void getImages(@QueryMap Map<String, String> options, Callback<Response> callback);
}
