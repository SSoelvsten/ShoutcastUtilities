package ReadWrite;

/**
 * Adds an empty space to the start of every line
 */
public class PreSpaceReadWriteStrategyDecorator implements ReadWriteStrategy {

    private final ReadWriteStrategy rws;

    public PreSpaceReadWriteStrategyDecorator(ReadWriteStrategy rws){
        this.rws = rws;
    }

    @Override
    public void write(String filename, String content) {
        content = content.replaceAll("\r\n", "\r\n ");
        rws.write(filename, content);
    }

    @Override
    public String read(String filename) {
        return rws.read(filename);
    }
}
