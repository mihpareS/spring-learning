package com.kihcyaz.aynorb.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private final ConcurrentHashMap<String, BeanDefinition> beanDefinitions;

    private ConcurrentHashMap<String, Object> singletonObjects;

    public BeanFactory(ConcurrentHashMap<String, BeanDefinition> beanDefinitions) {
        this.beanDefinitions = beanDefinitions;
        singletonObjects = new ConcurrentHashMap<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitions.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.isSingleton()) {
                Object instance = createBean(beanDefinition);
                singletonObjects.put(entry.getKey(), instance);
            }
        }
    }

    public Object getBean(String beanName) {
        Object instance = getSingleton(beanName);
        if (instance == null) {
            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
            if (beanDefinition == null) {
                throw new RuntimeException("no such bean registered");
            }
            if (beanDefinition.isPrototype()) {
                instance = createBean(beanDefinition);
            }
        }
        return instance;
    }

    private Object createBean(BeanDefinition beanDefinition) {
        String classPath = beanDefinition.getClazz();
        Class clazz = null;
        Object instance = null;
        try {
            clazz = Class.forName(classPath);
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("create bean failed");
        }
        return instance;
    }

    private Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
