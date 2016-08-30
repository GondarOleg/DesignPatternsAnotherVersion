package com.epam.dp.factory;

import com.epam.dp.decorator.BeanPostProcessor;
import com.epam.dp.decorator.InjectRandomInt;

/**
 * Created by Oleg_Gondar on 8/30/2016.
 */
@Component("AnotherClass")
public class AnotherClass implements BeanPostProcessor{

    @InjectRandomInt
    private int a;

    @InjectRandomInt
    private int b;

    @Override
    public void showInt() {
        System.out.println("In AnotherClass int is: a = "+ a + ", b = " + b );
    }
}
