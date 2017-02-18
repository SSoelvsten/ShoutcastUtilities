package Config;

/**
 * Class containing all keys expected to be present in a config
 */
public abstract class ConfigKeys {
    //Setup
    public static final String team_amount = "team_amount";
    public static final String map_amount = "map_amount";
    public static final String clock_amount = "clock_amount";

    //Folder
    public static final String folder_map_src = "folder_map_img_src";
    public static final String folder_map_dst = "folder_map_img_dst";
    public static final String folder_txt_dst = "folder_state_txt_dst";

    //Files
    public static final String file_map_unknown = "file_map_unknown";

    //Strings
    public static final String string_pre_game_number = "string_pre_game_number";

    //Keybinds
    public static final String enable_keybindings = "enable_keybindings";

    public static final String number_modifiers = "number_modifiers";
    public static final String modifier1_key = "modifier1_key";
    public static final String modifier2_key = "modifier2_key";

    public static final String team_0_increment_key = "team_0_increment_key";
    public static final String team_0_decrement_key = "team_0_decrement_key";
    public static final String team_1_increment_key = "team_1_increment_key";
    public static final String team_1_decrement_key = "team_1_decrement_key";
    public static final String swap_teams_key = "swap_teams_key";
    public static final String unpause_key = "unpause_key";
}
