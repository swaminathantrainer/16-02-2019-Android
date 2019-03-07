package com.swaminathantrainer;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Hello World");

        Student s1 = new Student();
        s1.name = "Ashwin";
        s1.standared = 10;
        s1.rollNumber = 1041040209;
        s1.age = 15;

        s1.print();

        System.out.println();

        Student s2 = new Student();
        s2.name = "Swami";
        s2.standared = 5;
        s2.rollNumber = 1041040210;
        s2.age = 10;

        s2.print();

        System.out.println();

        PartTimeStudent s3 = new PartTimeStudent();
        s3.name = "Viswa";
        s3.standared = 12;
        s3.rollNumber = 123456789;
        s3.age = 18;
        s3.parentName = "ABC";
        s3.isPartTime = true;

        s3.print();

        System.out.println();

        HandicapedStudent s4 = new HandicapedStudent();
        s4.name = "XYZ";
        s4.standared = 12;
        s4.rollNumber = 123456789;
        s4.age = 18;
        s4.typeOfHandicap = "Hand";

        int newAge = s4.calculateAgeInXyears(10);
        System.out.println("New age " + newAge);

        s4.print();

        System.out.println();

    }
}
