package fr.unice.iut.resto;

import com.google.gson.JsonArray;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Interface pour gérer les appels aux web services d'un serveur
 */
interface Requests {

    String URL = "http://XXX.XXX.XXX.XXX:8080/restaurant-ws/rest/";

    /* LoginActivity */
    @FormUrlEncoded
    @POST("connexion")
    Call<Void> sendUser(@Field("phone") String phone,
                        @Field("password") String password);

    /* NfcActivity */
    @FormUrlEncoded
    @POST("commande")
    Call<Void> sendCommand(@Field("order") String order);

    /* SelectActivity */
    @GET("menu/{target}")
    Call<JsonArray> getMenu(@Path("target") String target);

    /* SignActivity */
    @FormUrlEncoded
    @POST("inscription")
    Call<Void> sendData(@Field("firstName") String firstName,
                        @Field("lastName") String lastName,
                        @Field("phone") String phone,
                        @Field("password") String password);
}
