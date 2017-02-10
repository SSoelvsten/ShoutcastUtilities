package ReadWrite;

/**
 * A Test Spy to test text output. On "read" returns what was
 * written to it last.
 */
public class ReadWriteStrategySpy1 implements ReadWriteStrategy {

    private String read = "";

    @Override
    public void write(String filename, String content) {
        read = content;
    }

    @Override
    public String read(String filename) {
        return read;
    }
}
