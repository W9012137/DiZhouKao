package chencheng.bwie.com.dizhoukao.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2018/1/5.
 */

public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request request = chain.request();
        //get自定义拦截器
        if (request.method().equals("GET")){
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("source","android")
                    .build();
            //添加请求头
             Request newrequest = request.newBuilder()
                     .url(url)
                     .build();
             return chain.proceed(newrequest);
        }else{
             String url = request.url().url().toString();
            url+="&sourxe=android";
             Request newrequest = request.newBuilder().url(url).build();
             return chain.proceed(newrequest);
        }

    }
}
