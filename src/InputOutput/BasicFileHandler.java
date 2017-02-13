package InputOutput;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class BasicFileHandler implements FileHandler {

    @Override
    public void removeFile(String dstFolder, String dstFile) throws IOException {
        Files.deleteIfExists(FileSystems.getDefault().getPath(dstFolder, dstFile));
    }

    @Override
    public void copyFile(String srcFolder, String srcFile, String dstFolder, String dstFile) throws IOException {
        Files.copy(FileSystems.getDefault().getPath(srcFolder, srcFile),
                FileSystems.getDefault().getPath(dstFolder, dstFile),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public boolean checkExistence(String srcFolder, String srcFile) {
        return Files.exists(FileSystems.getDefault().getPath(srcFolder, srcFile));
    }
}
