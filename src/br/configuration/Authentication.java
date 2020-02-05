package br.configuration;

import br.common.models.teacher.Teacher;

public class Authentication {
    private static Teacher teacher;

    public static void setTeacher(Teacher teacher) {
        Authentication.teacher = teacher;
    }

    public static Teacher getTeacher() {
        return teacher;
    }
}
