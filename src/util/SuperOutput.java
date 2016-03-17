package util;

import java.io.*;

/**
 * A simple class for outputting a string to both a file and the
 * terminal window
 *
 * @author Richard Stegman
 * @author Horizonistic
 * @version 1.2
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
     * Closes the SuperOutput object
     */
    public void close () {
        if (pw != null) {
            pw.close();
        }
    }
}
