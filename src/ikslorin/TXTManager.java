package ikslorin;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to manage writing the txts
 */
public final class TXTManager {

    Scanner in;

    /**
     * Constructor for when you want to readFullFile the file line for line, but
     */
    public TXTManager(String filename){
        in = null;

        //Open the file
        try {
            in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Could not find: " + filename);
        }
    }

    public String readLine(){
        if (in == null){
            return "";
        } else {
            return in.nextLine();
        }
    }

    public void close() {
        if (in != null){
            in.close();
        }
    }



    /**
     * Writes a specific string to a specific file
     * @param filename The file "abc.txt" that is the target
     * @param content The string to writeFullFile to the file
     */
    public static void writeFullFile(String filename, String content)  {
        FileWriter writer = null;

        //Attempt to open file
        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            System.err.println("Could not open: " + filename);
            return;
        }

        //Write to file
        try {
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("Could not writeFullFile to: " + filename);
            return;
        }

        //System.out.println("Writer wrote: " + content + ", to : " + filename);
    }

    /**
     * Reads a file and returns it as a singular string without any linebreaks.
     * @param filename The file to be readFullFile
     * @return The content of the file as a string
     */
    public static String readFullFile(String filename)  {
        Scanner in = null;

        //Open the file
        try {
            in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Could not find: " + filename);
            return "Error!";
        }

        //Read the whole file
        String res = "";
        while(in.hasNextLine()){
            res = res + in.nextLine();
            //res = res + in.nextLine() + "\n";
        }
        in.close();

        return res;
    }
}
