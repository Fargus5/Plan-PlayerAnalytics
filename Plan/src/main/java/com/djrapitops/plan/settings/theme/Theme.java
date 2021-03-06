/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.settings.theme;

import com.djrapitops.plugin.api.utility.EnumUtility;
import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.plugin.utilities.Verify;
import main.java.com.djrapitops.plan.api.exceptions.PlanEnableException;
import main.java.com.djrapitops.plan.settings.Settings;
import main.java.com.djrapitops.plan.systems.SubSystem;
import main.java.com.djrapitops.plan.systems.Systems;

import java.io.IOException;
import java.util.List;

/**
 * Enum that contains available themes.
 * <p>
 * Change config setting Theme.Base to select one.
 *
 * @author Rsl1122
 */
public class Theme implements SubSystem {

    private ThemeConfig config;

    public static Theme getInstance() {
        return Systems.getInstance().getThemeSystem();
    }

    @Override
    public void init() throws PlanEnableException {
        String themeName = Settings.THEME_BASE.toString();
        try {
            config = new ThemeConfig(themeName);
        } catch (IOException e) {
            throw new PlanEnableException("Default theme could not be loaded.", e);
        }
    }

    @Override
    public void close() {

    }

    public String getColor(ThemeVal variable) {
        String path = variable.getThemePath();
        try {
            String value = config.getString(path);

            if (value.contains(".")) {
                return "url(\"" + value + "\")";
            } else {
                return value;
            }
        } catch (Exception | NoSuchFieldError e) {
            Log.error("Something went wrong with getting variable " + variable.name() + " for: " + path);
        }
        return variable.getDefaultValue();
    }

    public String replaceThemeColors(String resourceString) {
        String replaced = resourceString;
        List<ThemeVal> themeVariables = EnumUtility.getSupportedEnumValues(ThemeVal.class, "RED", "PINK", "PURPLE",
                "DEEP_PURPLE", "INDIGO", "BLUE", "LIGHT_BLUE", "CYAN", "TEAL", "GREEN", "LIGHT_GREEN", "LIME",
                "YELLOW", "AMBER", "ORANGE", "DEEP_ORANGE", "BROWN", "GREY", "BLUE_GREY", "BLACK", "WHITE",
                "GRAPH_PUNCHCARD", "GRAPH_PLAYERS_ONLINE", "GRAPH_TPS_HIGH", "GRAPH_TPS_MED", "GRAPH_TPS_LOW",
                "GRAPH_CPU", "GRAPH_RAM", "GRAPH_CHUNKS", "GRAPH_ENTITIES", "GRAPH_WORLD_PIE", "GRAPH_GM_PIE",
                "GRAPH_ACTIVITY_PIE", "GRAPH_SERVER_PREF_PIE", "FONT_STYLESHEET", "FONT_FAMILY");
        for (ThemeVal variable : themeVariables) {
            String value = getColor(variable);
            String defaultValue = variable.getDefaultValue();
            if (Verify.equalsOne(value, defaultValue)) {
                continue;
            }
            if (value.contains("url")) {
                String[] colorAndUrl = value.split(" ");
                if (colorAndUrl.length >= 2) {
                    replaced = replaced.replace("background: " + defaultValue, "background: " + colorAndUrl[1]);
                    replaced = replaced.replace(defaultValue, colorAndUrl[0]);
                    return replaced;
                }
            } else {
                replaced = replaced.replace(defaultValue, value);
            }
        }
        return replaced;
    }

    public String getThemeValue(ThemeVal color) {
        return config.getString(color.getThemePath());
    }

    public static String getValue(ThemeVal variable) {
        return getInstance().getThemeValue(variable);
    }

    public static String replaceColors(String resourceString) {
        return getInstance().replaceThemeColors(resourceString);
    }
}