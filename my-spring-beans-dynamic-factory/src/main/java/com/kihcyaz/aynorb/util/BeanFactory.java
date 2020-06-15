package com.kihcyaz.aynorb.util;

import java.lang.reflect.Method;
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
            if (beanDefinition.requireDependencies()) {
                resolveDependencies(beanDefinition);
            }
            String factoryBean = beanDefinition.getFactoryBean();
            String factoryMethod = beanDefinition.getFactoryMethod();
            if (factoryBean != null && factoryMethod != null) {
                Object object = singletonObjects.get(factoryBean);
                if (object == null) {
                    throw new RuntimeException(String.format("no such factory bean %s", factoryBean));
                }
                Class factoryClazz = object.getClass();
                Method method = factoryClazz.getDeclaredMethod(factoryMethod, null);
                method.setAccessible(true);
                instance = method.invoke(object, null);
            } else {
                instance = clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException("create bean failed");
        }
        return instance;
    }

    private void resolveDependencies(BeanDefinition beanDefinition) {
        if (beanDefinition.requireDependencies()) {
            String factoryName = beanDefinition.getFactoryBean();
            Object factory = singletonObjects.get(factoryName);
            if (factory == null) {
                BeanDefinition factoryBeanDefinition = this.beanDefinitions.get(factoryName);
                if (factoryBeanDefinition == null) {
                    throw new RuntimeException(String.format("no such bean definition named %s", factoryName));
                }
                resolveDependencies(factoryBeanDefinition);
                String classPath = beanDefinition.getClazz();
                Class clazz = null;
                try {
                    clazz = Class.forName(classPath);
                    Object object = clazz.getDeclaredConstructor().newInstance(null);
                    singletonObjects.put(beanDefinition.getBeanName(), object);
                    return;
                } catch (Exception e) {
                    throw new RuntimeException("create root factory bean failed");
                }
            }
        } else {
            String classPath = beanDefinition.getClazz();
            Class clazz = null;
            try {
                clazz = Class.forName(classPath);
                Object object = clazz.getDeclaredConstructor().newInstance(null);
                singletonObjects.put(beanDefinition.getBeanName(), object);
                return;
            } catch (Exception e) {
                throw new RuntimeException("create root factory bean failed");
            }
        }

    }

    private Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }
}
