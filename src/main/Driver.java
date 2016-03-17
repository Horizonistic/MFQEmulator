package main;

import util.SuperOutput;

public class Driver
{
    public static void main(String[] args)
    {
        SuperOutput so = new SuperOutput("output.txt");

        MFQ mfq = new MFQ(so);

        mfq.readFile();
        mfq.runEmulator();
    }
}
