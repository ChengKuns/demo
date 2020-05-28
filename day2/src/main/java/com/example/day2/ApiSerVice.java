package com.example.day2;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiSerVice {
    //http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
    @GET("ios/cf/dish_list.php?stage_id=1&limit=20")
    Observable<ProjiectBean> getDate(@Query("page") int page);
}
