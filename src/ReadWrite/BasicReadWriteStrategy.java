package ReadWrite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to manage writing the txts
 */
public final class BasicReadWriteStrategy implements ReadWriteStrategy{

    private Scanner in;
    private FileWriter writer;

    /**
     * Constructor for when you want to read the file line for line, but need to do it in various calls.
     * Not really necessary anymore, but too interesting not to keep.
     */
    public BasicReadWriteStrategy(){
        //Anything to do here?
    }

    @Override
    public void write(String filename, String content)  {
        //Attempt to open file
        try {
            writer = new FileWriter(filename);
        } catch (IOException e) {
            System.err.println("Could not open: " + filename);
            return;
        } catch (NullPointerException e) {
            System.err.println("The filename is null");
            return;
        }

        //Write to file
        try {
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.err.println("Could not writeFullFile to: " + filename);
            return;
        } catch (NullPointerException e) {
            System.err.println("The filename is null");
            return;
        }

        //System.out.println("Writer wrote: " + content + ", to : " + filename);
    }

    @Override
    public String read(String filename)  {
        //Open the file
        try {
            in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.err.println("Could not find: " + filename);
            return "Error!";
        } catch (NullPointerException e) {
            System.err.println("The filename is null");
            return "Null Pointer";
        }

        //Read the whole file
        String res = "";
        while(in.hasNextLine()){
            res = res + in.nextLine() + "\n";
        }
        in.close();

        return res;
    }
}
