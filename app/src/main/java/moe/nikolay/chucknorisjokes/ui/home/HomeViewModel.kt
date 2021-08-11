package moe.nikolay.chucknorisjokes.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import moe.nikolay.chucknorisjokes.data.ChuckNorrisModel
import moe.nikolay.chucknorisjokes.data.ChuckNorrisService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val TAG = this::class.java.simpleName
    private val service = ChuckNorrisService
    val jokeList: MutableLiveData<List<String>?> = MutableLiveData(null)

    fun initJokes(count: Int) {
        service.create().randomJoke(count).enqueue(object : Callback<ChuckNorrisModel?> {
            override fun onResponse(
                call: Call<ChuckNorrisModel?>,
                response: Response<ChuckNorrisModel?>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    val jokes: MutableList<String> = mutableListOf()
                    data?.value?.forEach {
                        jokes.add(it.joke)
                    }
                    jokeList.value = jokes
                }
                Log.d(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<ChuckNorrisModel?>, t: Throwable) {
                jokeList.value = null
                Log.e(TAG, "onFailure: $t")
            }
        })
    }

}