package com.swaminathantrainer;

public class HandicapedStudent extends Student {
    String typeOfHandicap;

    @Override
    public void print() {
        super.print();

        System.out.print(" The type of handicap is " + typeOfHandicap);
    }
}
