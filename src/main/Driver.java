package main;

import util.SuperOutput;

/**
 * The main driver.  This class acts as a controller, managing
 * SuperOutput and the MFQ.
 *
 * @author Horizonistic
 * @version 1.0
 */
public class Driver
{
    public static void main(String[] args)
    {
        SuperOutput so = new SuperOutput("output.txt");
        MFQ mfq = new MFQ(so);

        mfq.readFile();
        mfq.printHeader();
        mfq.runEmulator();
        mfq.printStats();

        so.close();
    }
}
