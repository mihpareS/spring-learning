package com.kihcyaz.aynorb;

import com.kihcyaz.aynorb.beans.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SampleApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Teacher teacher = (Teacher) context.getBean("teacher");
        Teacher factoryTeacher = (Teacher) context.getBean("factoryTeacher");
        System.out.println(teacher);
        System.out.println(factoryTeacher);
    }
}
