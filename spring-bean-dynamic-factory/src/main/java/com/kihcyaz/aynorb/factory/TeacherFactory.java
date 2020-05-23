package com.kihcyaz.aynorb.factory;

import com.kihcyaz.aynorb.beans.Teacher;

public class TeacherFactory {

    public Teacher getInstance() {
        Teacher teacher = new Teacher();
        System.out.println("Creating teacher instance using custom factory");
        return teacher;
    }
}
