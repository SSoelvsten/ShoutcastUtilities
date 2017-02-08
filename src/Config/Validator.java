package Config;

/**
 * Given a Config object, determines if any values are of the incorrect type
 * or if any values are absent
 */
public interface Validator {

    public void ValidateConfig(Config config);
}
