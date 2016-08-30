package com.epam.dp.decorator;

import com.epam.dp.factory.BeanFactory;
import com.epam.dp.factory.SomeClass;


public class Main {

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InstantiationException {

        loadApplContext("TestClass");
        loadApplContext("AnotherClass");
        loadApplContext("AnotherOneClass");
        SomeClass someClass = (SomeClass) new ApplicationContext(new BeanFactory()).getBean("SomeClass");
        System.out.println(someClass.getTest());

    }

    public static void loadApplContext(String className) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ApplicationContext applicationContext = new ApplicationContext(new BeanFactory());
        BeanPostProcessor beanPostProcessor = (BeanPostProcessor) applicationContext.getBean(className);
        beanPostProcessor.showInt();
    }
}
