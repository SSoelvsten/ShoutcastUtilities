package InputOutput;

import java.io.IOException;

/**
 * Handles copying files and deleting, the purpose of which
 * is to copy files from a library folder to the output.
 */
public interface FileHandler {

    /**
     * Removes the file at the path given
     * @param dstFolder
     * @param dstFile
     * @throws IOException
     */
    public void removeFile(String dstFolder, String dstFile) throws IOException;

    /**
     * Copies a file from one place to another
     * @param srcFolder
     * @param srcFile
     * @param dstFolder
     * @param dstFile
     * @throws IOException
     */
    public void copyFile(String srcFolder, String srcFile,
                         String dstFolder, String dstFile) throws IOException;

    /**
     * Check for a file to exist
     * @param srcFolder
     * @param srcFile
     * @return True if the file exists
     */
    public boolean checkExistence(String srcFolder, String srcFile);
}
