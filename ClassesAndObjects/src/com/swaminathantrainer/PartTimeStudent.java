package com.swaminathantrainer;

public class PartTimeStudent extends Student {
    public boolean isPartTime;

    @Override
    public void print() {
        super.print();

        // your logic goes here
        if (isPartTime)
            System.out.print(" He is a part time student");
        else
            System.out.print(" He is not a part time student");
    }
}
