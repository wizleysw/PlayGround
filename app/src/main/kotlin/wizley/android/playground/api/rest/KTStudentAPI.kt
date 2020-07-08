package wizley.android.playground.api.rest

import retrofit2.Call
import retrofit2.http.*

interface KTStudentAPI {
    @GET("/api/student/{name}")
    fun query(
            @Path("name") name: String
    ) : Call<KTStudent>

    @FormUrlEncoded
    @POST("/api/student/")
    fun insert(
            @Field("name") name : String,
            @Field("age") age : Int
    ) : Call<Void>

    @DELETE("/api/student/delete/{name}")
    fun delete(
            @Path("name") name : String
    ) : Call<Void>
}