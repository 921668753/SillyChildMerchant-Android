package com.yinglan.scm.message.interactivemessage.rongcloud;

import android.content.Context;

import com.yinglan.scm.message.interactivemessage.rongcloud.json.JsonMananger;
import com.yinglan.scm.message.interactivemessage.rongcloud.network.http.HttpException;
import com.yinglan.scm.message.interactivemessage.rongcloud.network.http.SyncHttpClient;

import java.util.List;


/**
 * Created by AMing on 16/1/14.
 * Company RongCloud
 */
public class BaseAction {

    private static final String DOMAIN = "http://api.sealtalk.im";
    protected Context mContext;
    protected SyncHttpClient httpManager;


    /**
     * 构造方法
     *
     * @param context 上下文
     */
    public BaseAction(Context context) {
        this.mContext = context;
        this.httpManager = SyncHttpClient.getInstance(context);
    }

    /**
     * JSON转JAVA对象方法
     *
     * @param json json
     * @param cls  class
     * @throws HttpException
     */
    public <T> T jsonToBean(String json, Class<T> cls) throws HttpException {
        return JsonMananger.jsonToBean(json, cls);
    }

    /**
     * JSON转JAVA数组方法
     *
     * @param json json
     * @param cls  class
     * @throws HttpException
     */
    public <T> List<T> jsonToList(String json, Class<T> cls) throws HttpException {
        return JsonMananger.jsonToList(json, cls);
    }

    /**
     * JAVA对象转JSON方法
     *
     * @param obj object
     * @throws HttpException
     */
    public String BeanTojson(Object obj) throws HttpException {
        return JsonMananger.beanToJson(obj);
    }


    /**
     * 获取完整URL方法
     *
     * @param url url
     */
    protected String getURL(String url) {
        return getURL(url, new String[] {});
    }

    /**
     * 获取完整URL方法
     *
     * @param url    url
     * @param params params
     */
    protected String getURL(String url, String... params) {
        StringBuilder urlBuilder = new StringBuilder(DOMAIN).append("/").append(url);
        if (params != null) {
            for (String param : params) {
                if (!urlBuilder.toString().endsWith("/")) {
                    urlBuilder.append("/");
                }
                urlBuilder.append(param);
            }
        }
        return urlBuilder.toString();
    }
}
