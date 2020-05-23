package com.kihcyaz.aynorb;

import com.kihcyaz.aynorb.beans.Student;
import com.kihcyaz.aynorb.beans.Teacher;
import util.BeanDefinition;
import util.BeanFactory;

import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        BeanDefinition studentBean = new BeanDefinition();
        studentBean.setBeanName("student");
        studentBean.setClazz("com.com.kihcyaz.com.kihcyaz.beans.Student");

        BeanDefinition teacherBean = new BeanDefinition();
        teacherBean.setBeanName("teacher");
        teacherBean.setClazz("com.com.kihcyaz.com.kihcyaz.beans.Teacher");

        ConcurrentHashMap<String, BeanDefinition> map = new ConcurrentHashMap<>();
        map.put(studentBean.getBeanName(), studentBean);
        map.put(teacherBean.getBeanName(), teacherBean);

        BeanFactory beanFactory = new BeanFactory();
        beanFactory.setBeanDefinitions(map);

        Teacher teacher = (Teacher) beanFactory.getBean("teacher");
        Student student = (Student) beanFactory.getBean("student");

        System.out.println(teacher);
        System.out.println(student);
    }

}
