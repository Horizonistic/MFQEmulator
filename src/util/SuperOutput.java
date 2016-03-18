package util;

import java.io.*;

/**
 * A simple class for outputting a string to both a file and the
 * terminal window
 *
 * @author Richard Stegman
 * @author Horizonistic
 * @version 1.3
 */
public class SuperOutput {
    PrintWriter pw;

    /**
     * Constructor for SuperOutput objects
     * @param fileName the name of the file to print to
     */
    public SuperOutput(String fileName) {
        try {
            pw = new PrintWriter(new FileWriter(fileName));
        }
        catch (IOException e) {
            System.out.printf("File %s could not be opened for output.\n", fileName);
            pw = null;
        }
    }

    /**
     * Forwards a string of format specifiers and varargs
     * to two printf statements
     * @param args  each item to print
     */

    public void print(Object... args) {
        if (pw == null)
            return;
        for (Object arg : args)
        {
            System.out.print(arg);
            pw.print(arg);
        }
    }

    /**
     * Prints the provided string and vargs using printf to
     * both the console and the file
     * @param string  The String to print
     * @param args  The arguments for printf
     */
    public void printf(String string, Object... args)
    {
        if (pw == null)
            return;
        System.out.printf(string, args);
        pw.printf(string, args);
    }


    /**
     * Outputs solely a newline character to the console and the file
     */
    public void println()
    {
        if (pw == null)
            return;
        System.out.printf("\n");
        pw.printf("\n");
    }

    /**
     * Same as this.print(), only prints on its own line
     * @param args  arguments to pass to print
     */
    public void println(Object... args) {
        if (pw == null)
            return;
        for (Object arg : args)
        {
            System.out.printf("\n" + arg);
            pw.printf("\n" + arg);
        }
    }

    /**
     * Prints the provided string and vargs on a new line using
     * printf to both the console and the file
     * @param string  The String to print
     * @param args  The arguments for printf
     */
    public void printlnf(String string, Object... args)
    {
        if (pw == null)
            return;
        System.out.printf("\n" + string, args);
        pw.printf("\n" + string, args);
    }

    /**
     * Forwards a string of format specifiers and varargs
     * to just the file. This is used for echoing items
     * the user may have input.
     * @param args  arguments to pass to print
     */
    public void printFile(Object... args) {
        if (pw == null)
            return;
        for (Object arg : args)
        {
            pw.print(arg);
        }
    }

    /**
     * Prints the provided string and vargs on a new line using
     * printf to just the file
     * @param string  The String to print
     * @param args  The arguments for printf
     */
    public void printFilef(String string, Object... args)
    {
        if (pw == null)
            return;
        pw.printf(string, args);
    }


    /**
     * The same as this.printFile(), only prints on its own line
     *
     * @param args  arguments to pass to print
     */
    public void printlnFile(Object... args) {
        if (pw == null)
            return;
        for (Object arg : args)
        {
            pw.printf("\n" + arg);
        }
    }

    /**
     * Outputs solely a newline character to the file
     */
    public void printlnFile() {
        if (pw == null)
            return;
        pw.printf("\n");
    }

    /**
     * Prints the provided string and vargs on a new line using
     * printf to both the console and the file
     * @param string  The String to print
     * @param args  The arguments for printf
     */
    public void printlnFilef(String string, Object... args)
    {
        if (pw == null)
            return;
        pw.printf("\n" + string, args);
    }

    /**
     * Closes the SuperOutput object
     */
    public void close () {
        if (pw != null) {
            pw.close();
        }
    }
}
