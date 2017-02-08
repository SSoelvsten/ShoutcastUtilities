package ReadWrite;

/**
 * Adds an empty space to the start of every line,
 * but also removes it again when reading it back in.
 */
public class PreSpaceReadWriteStrategyDecorator implements ReadWriteStrategy {

    private final ReadWriteStrategy rws;

    public PreSpaceReadWriteStrategyDecorator(ReadWriteStrategy rws){
        this.rws = rws;
    }

    @Override
    public void write(String filename, String content) {
        content = content.replaceAll("\r\n", "\r\n ");
        rws.write(filename, " " + content);
    }

    @Override
    public String read(String filename) {
        String content = rws.read(filename);
        content = content.replace("\r\n ", "\r\n");
        return content.substring(1, content.length());
    }
}
