package InputOutput;

import java.io.IOException;
import java.util.HashMap;

/**
 * A spy, which uses a hashMap to simulate folders.
 */
public class FileHandlerSpy implements FileHandler {

    public HashMap<String, String> files = new HashMap<>();

    public int filesCopied = 0;
    public int filesDeleted = 0;

    @Override
    public void removeFile(String dstFolder, String dstFile) throws IOException {
        files.remove(dstFolder, dstFile);
        filesDeleted++;
    }

    @Override
    public void copyFile(String srcFolder, String srcFile, String dstFolder, String dstFile) throws IOException {
        files.put(dstFolder, dstFile);
        filesCopied++;
    }

    @Override
    public boolean checkExistence(String srcFolder, String srcFile) {
        return files.containsKey(srcFolder) && files.containsKey(srcFile);
    }
}
