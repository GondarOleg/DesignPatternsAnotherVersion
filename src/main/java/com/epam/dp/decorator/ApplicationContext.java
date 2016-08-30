package com.epam.dp.decorator;

import com.epam.dp.factory.BeanFactory;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by Oleg_Gondar on 8/30/2016.
 */
public class ApplicationContext {

    private Map<String, Object> beans = new HashMap<>();
    private BeanFactory beanFactory;


    public ApplicationContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        initializeClass(this.beans);
    }

    public Object getBean(final String id) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Object o = beanFactory.getBean(id);
//        return InjectRandomIntBeanPostProcessor(o);
        if(beans.get(id) == null){
            return beanFactory.getBean(id);
        }
        return beans.get(id);
    }



    private void initializeClass(Map<String, Object> beans) {

        Reflections reflections = new Reflections("com.epam.dp.factory");
        Set<Class<? extends BeanPostProcessor> > implemetInt = reflections.getSubTypesOf(BeanPostProcessor.class);

        for (Class<?> clazz : implemetInt) {
            try {
                beans.put(clazz.getSimpleName(), InjectRandomIntBeanPostProcessor(clazz.newInstance()));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }



    private Object InjectRandomIntBeanPostProcessor(Object o) throws IllegalAccessException {

            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(InjectRandomInt.class)) {
                    field.setAccessible(true);
                    field.setInt(o, new Random().nextInt(1000));
                }
            }

        return o;
    }
}
