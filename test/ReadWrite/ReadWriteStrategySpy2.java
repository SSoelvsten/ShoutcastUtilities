package ReadWrite;

import java.util.HashMap;

public class ReadWriteStrategySpy2 implements ReadWriteStrategy {

    private HashMap<String, String> written;

    public String getOutputForFile(String file){
        written.get(file);
    }

    @Override
    public void write(String filename, String content) {
        written.put(filename, content);
    }

    @Override
    public String read(String filename) {
        return written.get(filename);
    }
}
