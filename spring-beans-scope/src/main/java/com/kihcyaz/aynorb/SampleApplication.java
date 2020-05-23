package com.kihcyaz.aynorb;

import com.kihcyaz.aynorb.beans.Student;
import com.kihcyaz.aynorb.beans.Teacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SampleApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Teacher teacher1 = (Teacher) context.getBean("teacher");
        Teacher teacher2 = (Teacher) context.getBean("teacher");
        System.out.println(teacher1 == teacher2);
        Student student1 = (Student) context.getBean("student");
        Student student2 = (Student) context.getBean("student");
        System.out.println(student1 == student2);
    }
}
