package com.btiming.apt.processer;


import javax.annotation.processing.RoundEnvironment;

/**
 * Created by baixiaokang on 16/10/8.
 * 注解处理器接口
 */

public interface IProcessor {
    void process(RoundEnvironment roundEnv, ClassNameAbstractProcessor mAbstractProcessor);
}
