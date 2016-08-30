package com.epam.dp.decorator;

import com.epam.dp.factory.BeanFactory;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by Oleg_Gondar on 8/30/2016.
 */
public class ApplicationContext {

    BeanFactory beanFactory;

    public ApplicationContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object getBean(final String id) throws ClassNotFoundException, IllegalAccessException, InstantiationException {


        Object o = beanFactory.getBean(id);
        if (o instanceof BeanPostProcessor) {

            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectRandomInt.class)) {
                    field.setAccessible(true);
                    field.setInt(o, new Random().nextInt(1000));
                }
            }
        }
        return o;
    }
}
