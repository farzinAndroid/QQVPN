package pro.sparrow_team.qq.data.remote

import pro.sparrow_team.qq.model.mainscreen.ConfigurationResponse
import pro.sparrow_team.qq.model.mainscreen.Status
import pro.sparrow_team.qq.model.mainscreen.UpdateTokenModel
import pro.sparrow_team.qq.model.mainscreen.UserAuthenticityResponse
import pro.sparrow_team.qq.model.mainscreen.UserPublicIDModel
import pro.sparrow_team.qq.model.mainscreen.UserRegisterModel
import pro.sparrow_team.qq.model.mainscreen.UserRegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface MainScreenApiInterface {

    @GET("status/")
    @Headers("Cache-Control: no-cache")
    suspend fun getStatus() : Response<Status>

    @POST("users/")
    suspend fun registerUser(
        @Body userRegisterModel: UserRegisterModel
    ) : Response<UserRegisterResponse>

    @GET("users/{id}/")
    @Headers("Cache-Control: no-cache")
    suspend fun getUserAuthenticity(
        @Path(
            "id",
            encoded = true
        ) id:String
    ) : Response<UserAuthenticityResponse>


    @PATCH("users/{id}/")
    @Headers("Cache-Control: no-cache")
    suspend fun updateUserToken(
        @Path(
            "id",
            encoded = true
        ) id:String,
        @Body updateTokenModel: UpdateTokenModel
    ) : Response<UserRegisterResponse>


    @PATCH("users/{id}/")
    @Headers("Cache-Control: no-cache")
    suspend fun sendUserPublicID(
        @Path(
            "id",
            encoded = true
        ) id:String,
        @Body userPublicIDModel: UserPublicIDModel
    ) : Response<UserRegisterResponse>


    @GET("users/{id}/configurations/")
    @Headers("Cache-Control: no-cache")
    suspend fun getConfigurations(
        @Path(
            "id",
            encoded = true
        ) id:String
    ) : Response<List<ConfigurationResponse>>

}