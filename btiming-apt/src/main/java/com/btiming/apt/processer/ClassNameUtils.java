package com.btiming.apt.processer;

import com.squareup.javapoet.ClassName;

public class ClassNameUtils {
    public static ClassName NotificationChannel(){
        return ClassName.get("android.app","NotificationChannel");
    }

    public static ClassName Build(){
        return ClassName.get("android.os", "Build");
    }

    public static ClassName TargetApi(){
        return ClassName.get("android.annotation","TargetApi");
    }

    public static ClassName Log(){
        return ClassName.get("android.util","Log");
    }

    public static ClassName Activity(){
        return ClassName.get("android.app","Activity");
    }

    public static ClassName NotificationManager(){
        return ClassName.get("android.app","NotificationManager");
    }

    public static ClassName Context(){
        return ClassName.get("android.content","Context");
    }

    public static ClassName Bundle(){
        return ClassName.get("android.os", "Bundle");
    }

    public static ClassName Override(){
        return ClassName.get("java.lang","Override");
    }

    public static ClassName RemoteMessage() {
        return ClassName.get("com.google.firebase.messaging","RemoteMessage");
    }

    public static ClassName NonNull(){
        return ClassName.get("androidx.annotation","NonNull");
    }

    public static ClassName FirebaseMessagingService(){
        return ClassName.get("com.google.firebase.messaging","FirebaseMessagingService");
    }

    public static ClassName Intent(){
        return ClassName.get("android.content","Intent");
    }

    public static ClassName DeveloperLog(){
        return ClassName.get("com.btiming.core.utils.log","DeveloperLog");
    }

    public static ClassName JSONObject(){
        return ClassName.get("org.json","JSONObject");
    }

    public static ClassName EventBuilder(){
        return ClassName.get("com.btiming.core.utils.log","EventBuilder");
    }

    public static ClassName TrackEvent(){
        return ClassName.get("com.btiming.core.constant","TrackEvent");
    }

    public static ClassName HashMap(){
        return ClassName.get("java.util","HashMap");
    }

    public static ClassName LrHelper(){
        return ClassName.get("com.btiming.core.utils.helper","LrHelper");
    }

    public static ClassName BTBaseActivity(){
        return ClassName.get("com.btiming.core.view","BTBaseActivity");
    }

    public static ClassName WeakReference(){
        return ClassName.get("java.lang.ref","WeakReference");
    }

    public static ClassName BTWebView(){
        return ClassName.get("com.btiming.entry.webview","BTWebView");
    }

    public static ClassName MessageListener(){
        return ClassName.get("com.btiming.core.webview.BTBaseJsBridge","MessageListener");
    }

    public static ClassName View(){
        return ClassName.get("android.view","View");
    }

    public static ClassName ObjectAnimator(){
        return ClassName.get("android.animation","ObjectAnimator");
    }

    public static ClassName BTWebViewPool(){
        return ClassName.get("com.btiming.entry.webview","BTWebViewPool");
    }

    public static ClassName ViewGroup(){
        return ClassName.get("android.view","ViewGroup");
    }

    public static ClassName RelativeLayout(){
        return ClassName.get("android.widget","RelativeLayout");
    }

    public static ClassName DrawCrossMarkView(){
        return ClassName.get("com.btiming.core.view","DrawCrossMarkView");
    }

    public static ClassName Color(){
        return ClassName.get("android.graphics","Color");
    }

    public static ClassName DensityUtil(){
        return ClassName.get("com.btiming.core.utils.screen","DensityUtil");
    }

    public static ClassName WvMethod(){
        return ClassName.get("com.btiming.core.constant","WvMethod");
    }

    public static ClassName CodeAttributes(){
        return ClassName.get("com.btiming.core.utils.log","CodeAttributes");
    }

    public static ClassName Looper(){
        return ClassName.get("android.os","Looper");
    }

    public static ClassName Runnable(){
        return ClassName.get("java.lang","Runnable");
    }

    public static ClassName BTHandler(){
        return ClassName.get("com.btiming.core.utils","BTHandler");
    }

    public static ClassName Pos(){
        return ClassName.get("com.btiming.core.model","Pos");
    }

    public static ClassName SignInManager(){
        return ClassName.get("com.btiming.login.core.signin","SignInManager");
    }

    public static ClassName SignInProviderResultHandler(){
        return ClassName.get("com.btiming.login.core.signin","SignInProviderResultHandler");
    }

    public static ClassName IdentityProvider(){
        return ClassName.get("com.btiming.login.core","IdentityProvider");
    }

    public static ClassName SignInResultHandler(){
        return ClassName.get("com.btiming.login.core","SignInResultHandler");
    }

    public static ClassName Exception(){
        return ClassName.get("java.lang","Exception");
    }

    public static ClassName Window(){
        return ClassName.get("android.view","Window");
    }

    public static ClassName WindowManager(){
        return ClassName.get("android.view","WindowManager");
    }

    public static ClassName NotchScreenManager(){
        return ClassName.get("com.btiming.core.utils.notch","NotchScreenManager");
    }

    public static ClassName SignInProvider(){
        return ClassName.get("com.btiming.login.core.signin","SignInProvider");
    }

    public static ClassName TextUtils(){
        return ClassName.get("android.text","TextUtils");
    }

    public static ClassName FacebookSignInProvider(){
        return ClassName.get("com.btiming.login.facebook","FacebookSignInProvider");
    }

    public static ClassName GoogleSignInProvider(){
        return ClassName.get("com.btiming.login.google","GoogleSignInProvider");
    }

    public static ClassName PhoneNumSignInProvider(){
        return ClassName.get("com.btiming.login.phonenum","PhoneNumSignInProvider");
    }

    public static ClassName AnonymousSignInProvider(){
        return ClassName.get("com.btiming.login.anonymous","AnonymousSignInProvider");
    }

    public static ClassName AttributeSet(){
        return ClassName.get("android.util","AttributeSet");
    }

    public static ClassName ScrollView(){
        return ClassName.get("android.widget","ScrollView");
    }

    public static ClassName TwitterAuthToken(){
        return ClassName.get("com.twitter.sdk.android.core","TwitterAuthToken");
    }

    public static ClassName TwitterSession(){
        return ClassName.get("com.twitter.sdk.android.core","TwitterSession");
    }

    public static ClassName Uri(){
        return ClassName.get("android.net","Uri");
    }

    public static ClassName LinearLayout(){
        return ClassName.get("android.widget","LinearLayout");
    }
}
