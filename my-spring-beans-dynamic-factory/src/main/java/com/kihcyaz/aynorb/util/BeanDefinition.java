package com.kihcyaz.aynorb.util;

public class BeanDefinition {

    final String SINGLETON_SCOPE = "singleton";

    final String PROTOTYPE_SCOPE = "prototype";

    private String beanName;

    private String clazz;

    private String scope = SINGLETON_SCOPE;

    private String factoryBean;

    private String factoryMethod;

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public boolean isSingleton() {
        return SINGLETON_SCOPE.equals(this.scope);
    }

    public boolean isPrototype() {
        return PROTOTYPE_SCOPE.equals(this.scope);
    }

    public String getFactoryBean() {
        return factoryBean;
    }

    public void setFactoryBean(String factoryBean) {
        this.factoryBean = factoryBean;
    }

    public String getFactoryMethod() {
        return factoryMethod;
    }

    public void setFactoryMethod(String factoryMethod) {
        this.factoryMethod = factoryMethod;
    }

    public boolean requireDependencies() {
        if (factoryBean != null && factoryMethod != null) {
            return true;
        }
        return false;
    }
}
