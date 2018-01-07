package chencheng.bwie.com.dizhoukao.net;


import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by dell on 2018/1/5.
 */

public interface ServerApi {
    @GET(Api.GORY)
    Flowable<GroyBean> getGory();
}
