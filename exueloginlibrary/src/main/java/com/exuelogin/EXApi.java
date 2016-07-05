package com.exuelogin;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.exuelogin.base.BaseApplication;
import com.exuelogin.config.Global;
import com.exuelogin.utils.CommonTools;
import com.exuelogin.utils.NetworkUtils;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by liuyuan on 2016/6/30 0030.
 */
 public class EXApi {
    private static EXApi mExApi;
    private static String APP_ID="";
    private static Intent intent;
    private static boolean state;
    private static String appName="";
    private static String packageName="";
    private static String type="20";//type=20代表第三方登录
    private EXApi(String APP_ID) {
        this.APP_ID=APP_ID;
    }
    public static EXApi createEXAPI(String APP_ID){
        if(mExApi==null){
            synchronized (mExApi){
                if(mExApi==null){
                    PackageManager packageManager = BaseApplication.getmContext().getPackageManager();
                    intent = packageManager.getLaunchIntentForPackage("com.juziwl.xiaoxin");
                    mExApi=new EXApi(APP_ID);
                    checkIDisAvaliable();
                }
            }
        }
        return  mExApi;
    }
    public static void openEXApp(){
        if(state==true){
            start();
        }else{
            CommonTools.showToast(BaseApplication.getmContext(), "APP_ID验证失败");
        }
    }

    private static void checkIDisAvaliable() {
        if (NetworkUtils.isNetworkAvailable(BaseApplication.getmContext())) {
            RequestParams requestParams = new RequestParams(Global.BASEURL);
            requestParams.addQueryStringParameter("appkey",APP_ID);
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("app_id",APP_ID);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            requestParams.setBodyContent(jsonObject.toString());
//            requestParams.setAsJsonContent(true);
            try {
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println("第三方验证返回的数据是:"+result);
//                        try {
//                            JSONObject object=new JSONObject(result);
//                            appName=object.getString("appName");
//                            packageName=object.getString("packageName");
//                            state=object.getBoolean("state");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        state=false;
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                    }

                    @Override
                    public void onFinished() {
                    }
                });

            }catch (Exception e){
                      e.printStackTrace();
            }

        }
    }

    private static void start() {
        if(intent!=null){
            Intent intent1=new Intent(Intent.ACTION_MAIN,null);
            intent1.addCategory(Intent.CATEGORY_LAUNCHER);
            intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp=new ComponentName("com.juziwl.xiaoxin","com.juziwl.xiaoxin.splash.main.SplashActivity");
            intent1.setComponent(comp);
            intent1.putExtra("appname",appName);
            intent1.putExtra("type",type);
            intent1.putExtra("packageName",packageName);
            BaseApplication.getmContext().startActivity(intent1);
            //finish();

        }else{
            CommonTools.showToast(BaseApplication.getmContext(),"你暂时没有安装e学软件");
        }

    }
}
