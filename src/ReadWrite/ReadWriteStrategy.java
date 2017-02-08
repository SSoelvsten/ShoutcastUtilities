package ReadWrite;

/**
 * Objects whos responsibility is the reading and writing from/to disk.
 */
public interface ReadWriteStrategy {

    /**
     * Writes a specific string to a specific file
     *
     * @param filename The file "abc.txt" that is the target
     * @param content  The string to writeFullFile to the file
     */
    public void write(String filename, String content);

    /**
     * Reads a file and returns it as a singular string with linebreaks.
     *
     * @param filename The file to be read
     * @return The content of the file as a string
     */
    public String read(String filename);
}
