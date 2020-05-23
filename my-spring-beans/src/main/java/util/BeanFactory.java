package util;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitions;


    public ConcurrentHashMap<String, BeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }

    public void setBeanDefinitions(ConcurrentHashMap<String, BeanDefinition> beanDefinitions) {
        this.beanDefinitions = beanDefinitions;
    }

    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = beanDefinitions.get(beanName);
        if (beanDefinition == null) {
            throw new RuntimeException("no such bean registered");
        }
        Object instance = null;
        String classPath = beanDefinition.getClazz();
        Class clazz = null;
        try {
            clazz = Class.forName(classPath);
            instance = clazz.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("class not found");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("no such constructors");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("create instance failed");
        }
        return instance;
    }
}
