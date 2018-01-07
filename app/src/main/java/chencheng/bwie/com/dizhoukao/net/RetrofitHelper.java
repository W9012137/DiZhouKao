package chencheng.bwie.com.dizhoukao.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2018/1/5.
 */

public class RetrofitHelper {
    private static OkHttpClient client;
    private static ServerApi serverApi;
    static {
        initRetro();
    }
    private static OkHttpClient initRetro(){
        //自定义拦截器
        final LoggingInterceptor interceptor = new LoggingInterceptor();

        if (client==null){
            synchronized (RetrofitHelper.class){
                if (client==null){
                    client=new OkHttpClient.Builder()
                            .addInterceptor(interceptor)
                            .connectTimeout(3000, TimeUnit.MILLISECONDS)
                            .build();


                }
            }
        }
        return client;
    }
    public static ServerApi getServerApi(){
        if (serverApi==null){
            synchronized (ServerApi.class){
                if (serverApi==null){
                    serverApi= RetrofitHelper.careteApi(ServerApi.class,Api.BASE);
                }
            }
        }
        return  serverApi;

    }
    public static <T> T careteApi(Class<T> clazz,String url){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .validateEagerly(true)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return  retrofit.create(clazz);

    }
}
