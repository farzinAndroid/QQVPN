package pro.sparrow_team.qq.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

abstract class BaseApiResponse {


    /**
     * This method is better for having a safer API call
     * and not having to change the dispatchers or getting the body of the call...
     * for every api call
     */

    suspend fun <T> safeApiCall(apiCall:suspend () -> Response<T>) : NetworkResult<T> =
        withContext(Dispatchers.IO){
            try {
                val response = apiCall()
                if (response.isSuccessful){
                    val body = response.body()
                    body?.let {
                        return@withContext NetworkResult.Success(body.toString(),body)
                    }
                }
                return@withContext error("code : ${response.code()} , message : ${response.message()}")
            }catch (e:Exception){
                return@withContext error(e.message ?: e.toString())
            }
        }


    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error("Api call failed : $errorMessage")
}