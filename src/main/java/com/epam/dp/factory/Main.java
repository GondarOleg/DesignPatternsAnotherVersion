package com.epam.dp.factory;

import com.epam.dp.decorator.ApplicationContext;
import com.epam.dp.decorator.BeanPostProcessor;

/**
 * @author Ivan_Zhuravel
 */
public class Main {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        loadApplContext("TestClass");
        loadApplContext("AnotherClass");
        loadApplContext("AnotherOneClass");


    }

    public static void loadApplContext(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ApplicationContext applicationContext = new ApplicationContext(new BeanFactory());
        BeanPostProcessor beanPostProcessor = (BeanPostProcessor) applicationContext.getBean(className);
        beanPostProcessor.showInt();
    }
}
