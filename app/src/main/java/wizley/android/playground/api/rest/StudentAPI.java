package wizley.android.playground.api.rest;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StudentAPI {
    @GET("/api/student/{name}")
    Call<Student> query(@Path("name") String name);

    @FormUrlEncoded
    @POST("/api/student/")
    Call<Void> insert(@Field("name") String name, @Field("age") int age);

    @DELETE("/api/student/delete/{name}")
    Call<Void> delete(@Path("name") String name);
}
