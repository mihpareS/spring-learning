package com.kihcyaz.aynorb;

import com.kihcyaz.aynorb.beans.Student;
import com.kihcyaz.aynorb.beans.Teacher;
import com.kihcyaz.aynorb.util.BeanDefinition;
import com.kihcyaz.aynorb.util.BeanFactory;

import java.util.concurrent.ConcurrentHashMap;

public class Test {

    public static void main(String[] args) {
        BeanDefinition teacherBeanDefinition = new BeanDefinition();
        teacherBeanDefinition.setBeanName("teacher");
        teacherBeanDefinition.setClazz("com.kihcyaz.aynorb.beans.Teacher");
        teacherBeanDefinition.setScope("prototype");

        BeanDefinition studentBeanDefinition = new BeanDefinition();
        studentBeanDefinition.setBeanName("student");
        studentBeanDefinition.setClazz("com.kihcyaz.aynorb.beans.Student");

        ConcurrentHashMap<String, BeanDefinition> map = new ConcurrentHashMap<>();
        map.put(teacherBeanDefinition.getBeanName(), teacherBeanDefinition);
        map.put(studentBeanDefinition.getBeanName(), studentBeanDefinition);

        BeanFactory beanFactory = new BeanFactory(map);

        Teacher teacher1 = (Teacher) beanFactory.getBean("teacher");
        Teacher teacher2 = (Teacher) beanFactory.getBean("teacher");

        System.out.println(teacher1 == teacher2);

        Student student1 = (Student) beanFactory.getBean("student");
        Student student2 = (Student) beanFactory.getBean("student");
        System.out.println(student1 == student2);
    }
}
