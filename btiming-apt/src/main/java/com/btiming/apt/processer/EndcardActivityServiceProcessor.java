package com.btiming.apt.processer;



import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.tools.Diagnostic;

public class EndcardActivityServiceProcessor implements IProcessor {
    @Override
    public void process(RoundEnvironment roundEnv, ClassNameAbstractProcessor abstractProcessor) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(EndcardActivity.class);
        abstractProcessor.mMessager.printMessage(Diagnostic.Kind.NOTE, "process EndcardActivity");
        for (Element element:elements){
            EndcardActivity classNameUrl = element.getAnnotation(EndcardActivity.class);
            ClassName className = ClassName.get(classNameUrl.value(), classNameUrl.className());
            FieldSpec fieldSpec = FieldSpec.builder(String.class,"TAG",Modifier.PRIVATE,Modifier.STATIC,Modifier.FINAL)
                    .initializer("$T.class.getSimpleName()",className).build();
            FieldSpec webSpec = FieldSpec.builder(ParameterizedTypeName.get(ClassNameUtils.WeakReference(),ClassNameUtils.BTWebView()),"btWebview",Modifier.PRIVATE).build();
            TypeSpec helloWorld = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC)
                    .superclass(ClassNameUtils.BTBaseActivity())
                    .addSuperinterface(ClassNameUtils.MessageListener())
                    .addMethod(onCreate("onCreate"))
                    .addMethod(onResume("onResume"))
                    .addMethod(onPause("onPause"))
                    .addMethod(onDestroy("onDestroy"))
                    .addMethod(onBackPressed("onBackPressed"))
                    .addMethod(updateCloseBtnStatus("updateCloseBtnStatus"))
                    .addMethod(initView("initView"))
                    .addMethod(onReceiveMessage("onReceiveMessage"))
                    .addMethod(checkFinish("checkFinish"))
                    .addMethod(hideActivity("hideActivity"))
                    .addMethod(showActivity("showActivity"))
                    .addMethod(checkHide("checkHide"))
                    .addMethod(showAt("showAt"))
                    .addMethod(onBackprocess("onBackprocess"))
                    .addMethod(syncRelease("syncRelease"))
                    .addMethod(reportEvent("reportEvent"))
                    .addField(fieldSpec)
                    .addField(webSpec)
                    .build();

            try {
                JavaFile javaFile = JavaFile.builder(classNameUrl.value(),helloWorld).build();
                javaFile.writeTo(abstractProcessor.filer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private MethodSpec onCreate(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PROTECTED)
                .addAnnotation(ClassNameUtils.Override())
                .addParameter(ClassNameUtils.Bundle(),"savedInstanceState")
                .addStatement("$T.LogD(TAG, \"onCreate\")",ClassNameUtils.DeveloperLog())
                .addStatement("super.onCreate(savedInstanceState)")
                .addCode("if (!getIntent().hasExtra(\"path\")) {\n")
                .addStatement("     $T jsonEvent = $T.buildEvent(null, $T.kWvShowFail, new $T<$T, $T>(){{\n" +
                        "     put(\"error\", \"webview path invalid\");\n" +
                        "   }})",ClassNameUtils.JSONObject()
                        ,ClassNameUtils.EventBuilder()
                        ,ClassNameUtils.TrackEvent()
                        ,ClassNameUtils.HashMap(),String.class,Object.class)
                .addStatement("     $T.report(null, jsonEvent)",ClassNameUtils.LrHelper())
                .addStatement("     finish()")
                .addStatement("}")
                .addStatement("String path = getIntent().getStringExtra(\"path\")")
                .addStatement("initView(path)")
                .addStatement("setUiFlags()")
                .build();
    }
    private MethodSpec onResume(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PROTECTED)
                .addAnnotation(ClassNameUtils.Override())
                .addStatement("$T.LogD(TAG, \"onResume\")",ClassNameUtils.DeveloperLog())
                .addStatement("super.onResume()")
                .build();
    }

    private MethodSpec onPause(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PROTECTED)
                .addAnnotation(ClassNameUtils.Override())
                .addStatement("$T.LogD(TAG, \"onPause\")",ClassNameUtils.DeveloperLog())
                .addStatement("super.onPause()")
                .addStatement("reportEvent($T.kWvPause)",ClassNameUtils.TrackEvent())
                .build();
    }

    private MethodSpec onBackPressed(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(ClassNameUtils.Override())
                .addStatement("$T.LogD(TAG, \"onBackPressed\")",ClassNameUtils.DeveloperLog())
                .addCode("if (onBackprocess()) {\n")
                .addStatement("     super.onBackPressed()")
                .addStatement("     reportEvent($T.kWvBack)",ClassNameUtils.TrackEvent())
                .addStatement("     syncRelease(true)")
                .addCode("}\n")
                .build();
    }

    private MethodSpec onDestroy(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(ClassNameUtils.Override())
                .addStatement("$T.LogD(TAG, \"onBackPressed\")",ClassNameUtils.DeveloperLog())
                .addStatement("reportEvent($T.kWvDestroy)",ClassNameUtils.TrackEvent())
                .addStatement("super.onDestroy()")
                .addStatement("setUiFlags()")
                .build();
    }

    private MethodSpec updateCloseBtnStatus(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addCode(" Runnable runnable = new Runnable() {\n")
                .addCode("      @$T\n",ClassNameUtils.Override())
                .addCode("      public void run() {\n")
                .addCode("              if (isBackEnable) {\n")
                .addCode("                  if (mDrawCrossMarkView != null) {\n")
                .addStatement("                     mDrawCrossMarkView.setVisibility(View.VISIBLE)"
                        ,ClassName.get("android.view","View"))
                .addStatement("                     $T animator = ObjectAnimator.ofFloat(mDrawCrossMarkView, \"alpha\", 0f, 1f)"
                        ,ClassNameUtils.ObjectAnimator())
                .addStatement("                     animator.setDuration(500)")
                .addStatement("                     animator.start()")
                .addCode("                  }\n")
                .addCode("              } else {\n")
                .addCode("                  if (mDrawCrossMarkView != null) {\n")
                .addStatement("                     mDrawCrossMarkView.setVisibility($T.GONE)",ClassNameUtils.View())
                .addCode("                  }\n")
                .addCode("              }\n")
                .addCode("      }\n")
                .addStatement("}")

                .addCode("if (mLytContainer != null) {\n")
                .addStatement("     mLytContainer.postDelayed(runnable, 0)")
                .addCode("}\n")
                .build();
    }

    private MethodSpec initView(String methodStr){
        ParameterSpec stringSpec =
                ParameterSpec.builder(String.class,"path",Modifier.FINAL).build();
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addParameter(stringSpec)
                .addStatement("$T.LogD(TAG, \"initView start\")",ClassNameUtils.DeveloperLog())
                .addStatement("$T webView = $T.getInstance().pull(path)"
                        ,ClassNameUtils.BTWebView()
                        ,ClassNameUtils.BTWebViewPool())
                .addCode("if (webView == null) {\n")
                .addStatement("     $T jsonEvent = $T.buildEvent(null, $T.kWvShowFail, new $T<$T, $T>(){{\n" +
                        "                put(\"error\", \"webview not found by \" + path);\n" +
                        "}})",ClassNameUtils.JSONObject()
                        ,ClassNameUtils.EventBuilder()
                        ,ClassNameUtils.TrackEvent(),
                        ClassNameUtils.HashMap(),String.class,Object.class)
                .addStatement("     $T.report(null, jsonEvent)",ClassNameUtils.LrHelper())
                .addStatement("     $T.LogD(TAG, $T.format(\"open webview failed, webview %d not found\", path))"
                        ,ClassNameUtils.DeveloperLog(),String.class)
                .addStatement("     finish()")
                .addStatement("     return")
                .addCode("}\n")
                .addCode("if (webView != null && webView.getParent() != null) {\n")
                .addStatement("     $T parent = (ViewGroup) webView.getParent()",ClassNameUtils.ViewGroup())
                .addStatement("     parent.removeView(webView)")
                .addStatement("     DeveloperLog.LogD(TAG, String.format(\"initView remote from parent, webview %d\", path))")
                .addCode("}\n")
                .addStatement("webView.setContainer(mLytContainer)")
                .addStatement("$T.LayoutParams wparams = new RelativeLayout.LayoutParams(\n" +
                        "                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)",ClassNameUtils.RelativeLayout())
                .addStatement("wparams.addRule(RelativeLayout.CENTER_IN_PARENT)")
                .addStatement("webView.setVisibility(View.VISIBLE)")
                .addStatement("mLytContainer.addView(webView, wparams)")
                .addStatement("webView.setMessageListener(this)")
                .addStatement("mDrawCrossMarkView = new $T(this, $T.GRAY)"
                        ,ClassNameUtils.DrawCrossMarkView()
                        ,ClassNameUtils.Color())
                .addStatement("mLytContainer.addView(mDrawCrossMarkView)")
                .addCode("mDrawCrossMarkView.setOnClickListener(new View.OnClickListener() {\n")
                .addCode("      @$T\n",ClassNameUtils.Override())
                .addCode("      public void onClick(View view) {\n" )
                .addStatement("         onBackPressed()")
                .addCode("      }\n")
                .addStatement("})")
                .addStatement("mDrawCrossMarkView.setVisibility(View.VISIBLE)")
                .addCode("if (webView.isHideCloseCalled()) {\n")
                .addStatement("     isBackEnable = false")
                .addStatement("     updateCloseBtnStatus()")
                .addCode("}\n")
                .addStatement("int size = $T.dip2px(this, 40)",ClassNameUtils.DensityUtil())
                .addStatement("RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(size, size)")
                .addStatement("params.addRule(RelativeLayout.ALIGN_PARENT_TOP)")
                .addStatement("params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)")
                .addStatement("params.setMargins(30, 30, 30, 30)")
                .addStatement("mDrawCrossMarkView.setLayoutParams(params)")
                .addStatement("webView.reportShowEvent()")
                .addStatement("btWebview = new WeakReference<>(webView)")
                .addStatement("DeveloperLog.LogD(TAG, \"initView end\")")
                .build();
    }

    private MethodSpec onReceiveMessage(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ParameterSpec.builder(String.class,"method").build())
                .addParameter(ParameterSpec.builder(ClassNameUtils.JSONObject(),"data").build())
                .addAnnotation(ClassNameUtils.Override())
                .addStatement("DeveloperLog.LogD(TAG, String.format(\"onReceiveMessage method: %s\", method))")
                .addCode("if($T.METHOD_CLOSEWV.equals(method)) {\n",ClassNameUtils.WvMethod())
                .addStatement("     checkFinish()")
                .addCode("} else if (WvMethod.METHOD_HIDEWV.equals(method)) {\n")
                .addStatement("     hideActivity()")
                .addCode("} else if (WvMethod.METHOD_SHOW_CLOSE.equals(method)) {\n")
                .addStatement("     isBackEnable = true")
                .addStatement("     updateCloseBtnStatus()")
                .addCode("} else if (WvMethod.METHOD_HIDE_CLOSE.equals(method)) {\n")
                .addStatement("     isBackEnable = false")
                .addStatement("     updateCloseBtnStatus()")
                .addCode("} else if (WvMethod.METHOD_SHOWAT.equals(method)) {\n")
                .addStatement("     showActivity()")
                .addCode("} else {\n")
                .addStatement("     $T cause = String.format(\"%s was not support\", method)",String.class)
                .addStatement("     LrHelper.reportSdkException(null, cause,\n" +
                        "                    $T.getFileName(), CodeAttributes.getMethodName())",ClassNameUtils.CodeAttributes())
                .addCode("}\n")
                .build();

    }

    private MethodSpec checkFinish(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addCode("if(mLytContainer != null && mLytContainer.getChildCount() > 1) {\n")
                .addStatement("     return")
                .addCode("}\n")
                .addStatement("syncRelease(false)")
                .addStatement("finish()")
                .build();
    }

    private MethodSpec hideActivity(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addCode("if ($T.getMainLooper() == Looper.myLooper()){\n",ClassNameUtils.Looper())
                .addStatement("     checkHide()")
                .addCode("} else {\n")
                .addCode("      runOnUiThread(new Runnable() {\n")
                .addCode("          @$T\n",ClassNameUtils.Override())
                .addCode("          public void run() {\n")
                .addStatement("             checkHide()")
                .addCode("          }\n")
                .addStatement("     })")
                .addCode("}\n")
                .build();
    }

    private MethodSpec showActivity(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addCode("if (Looper.getMainLooper() == Looper.myLooper()){\n")
                .addStatement("     showAt()")
                .addCode("} else {\n")
                .addCode("      runOnUiThread(new $T() {\n",ClassNameUtils.Runnable())
                .addCode("          @$T\n",ClassNameUtils.Override())
                .addCode("          public void run() {\n")
                .addStatement("             showAt()")
                .addCode("          }\n")
                .addStatement("     })")
                .addCode("}\n")
                .build();
    }

    private MethodSpec checkHide(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addCode(" for(int i=0; i < mLytContainer.getChildCount(); i++) {\n")
                .addStatement("     View view = mLytContainer.getChildAt(i)")
                .addCode("      if (mDrawCrossMarkView != null && view == mDrawCrossMarkView) {\n")
                .addStatement("         continue")
                .addCode("      }\n")
                .addStatement("     int visibility = view.getVisibility()")
                .addCode("      if(View.GONE != visibility) {\n")
                .addStatement("         return")
                .addCode("      }\n")
                .addCode("}\n")
                .addStatement("setVisible(false)")
                .build();
    }

    private MethodSpec showAt(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addStatement("setVisible(true)")
                .build();
    }

    private MethodSpec onBackprocess(String methodStr){
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .returns(boolean.class)
                .addStatement("int childCount = mLytContainer.getChildCount()")
                .addCode("if (childCount <= 1) {\n")
                .addStatement("     return true")
                .addCode("}\n")
                .addStatement("View view = mLytContainer.getChildAt(childCount - 2)")
                .addCode("if (view.getVisibility() == View.VISIBLE) {\n")
                .addStatement("     view.setVisibility(View.GONE)")
                .addCode("}\n")
                .addStatement("mLytContainer.removeView(view)")
                .addCode("if(mLytContainer.getChildCount() <= 1) {\n")
                .addStatement("     return true")
                .addCode("}\n")
                .addStatement("return false")
                .build();
    }

    private MethodSpec syncRelease(String methodStr){
        ParameterSpec stringSpec =
                ParameterSpec.builder(boolean.class,"recover",Modifier.FINAL).build();
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addParameter(stringSpec)
                .addCode(" $T.runOnUiThread(() -> {\n",ClassNameUtils.BTHandler())
                .addCode("      if (mLytContainer != null) {\n")
                .addCode("          if (mLytContainer.getChildCount() != 0) {\n")
                .addStatement("             mLytContainer.removeAllViews()")
                .addCode("          }\n")
                .addStatement("         mLytContainer = null")
                .addCode("      }\n")
                .addCode("      if(btWebview.get() != null) {\n")
                .addStatement("         btWebview.get().setContainer(null)")
                .addCode("      }\n")
                .addCode("      if(recover) {\n")
                .addStatement("         BTWebViewPool.getInstance().put(btWebview.get())")
                .addCode("      }\n")
                .addStatement("})")
                .build();
    }

    private MethodSpec reportEvent(String methodStr){
        ParameterSpec stringSpec =
                ParameterSpec.builder(String.class,"event").build();
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PRIVATE)
                .addParameter(stringSpec)
                .addStatement("$T pos = null",ClassNameUtils.Pos())
                .addCode("if(btWebview != null && btWebview.get() != null) {\n")
                .addStatement("     pos = btWebview.get().getPos()")
                .addCode("}\n")
                .addStatement("LrHelper.report(pos, EventBuilder.buildEvent(pos, event, null))")
                .build();
    }
}
