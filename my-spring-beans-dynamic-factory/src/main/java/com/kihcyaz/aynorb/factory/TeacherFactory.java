package com.kihcyaz.aynorb.factory;

import com.kihcyaz.aynorb.beans.Teacher;

public class TeacherFactory {
    public Teacher getInstance() {
        System.out.println("Creating teacher instance using custom factory");
        return new Teacher();
    }

}
