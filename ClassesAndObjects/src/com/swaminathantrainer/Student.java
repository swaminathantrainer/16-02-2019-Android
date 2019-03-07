package com.swaminathantrainer;

public class Student {
    public String name;
    public long rollNumber;
    public int standared;
    public int age;
    public String parentName;

    public void print() {
        String out = "The name of the student is " + name + " and his roll number is " + rollNumber
                + " and he studies in " + standared + " and his age is " + age;
        System.out.print(out);
    }

    public int calculateAgeInXyears(int x) {
        return age + x;
    }

//    public void test() {
//        String out = "Hello World";
//        System.out.println(out);
//    }

}
