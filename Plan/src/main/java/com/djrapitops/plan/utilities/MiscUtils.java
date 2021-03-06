package main.java.com.djrapitops.plan.utilities;

import com.djrapitops.plugin.api.Check;
import com.djrapitops.plugin.api.TimeAmount;
import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.plugin.command.CommandUtils;
import com.djrapitops.plugin.command.ISender;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.PlanBungee;
import main.java.com.djrapitops.plan.api.IPlan;
import main.java.com.djrapitops.plan.database.Database;
import main.java.com.djrapitops.plan.settings.Permissions;
import main.java.com.djrapitops.plan.settings.Settings;
import main.java.com.djrapitops.plan.settings.locale.Locale;
import main.java.com.djrapitops.plan.settings.locale.Msg;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility method class containing various static methods.
 *
 * @author Rsl1122
 * @since 2.0.0
 */
public class MiscUtils {

    /**
     * Constructor used to hide the public constructor
     */
    private MiscUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Used to get the current time as milliseconds.
     *
     * @return Epoch ms.
     */
    public static long getTime() {
        return System.currentTimeMillis();
    }

    public static int getTimeZoneOffsetHours() {
        if (Settings.USE_SERVER_TIME.isTrue()) {
            return -TimeZone.getDefault().getOffset(MiscUtils.getTime()) / (int) TimeAmount.HOUR.ms();
        }
        return 0;
    }

    /**
     * Get a players name that matches the given arguments or name of the sender.
     *
     * @param args   Arguments of command.
     * @param sender Sender of command
     * @return Player name.
     */
    public static String getPlayerName(String[] args, ISender sender) {
        return getPlayerName(args, sender, Permissions.INSPECT_OTHER);
    }

    /**
     * Used by the inspect command.
     *
     * @param args   Arguments of a command, must not be empty if console sender.
     * @param sender Command sender
     * @param perm   Permission to use when checking. No permission will notify user.
     * @return The name of the player (first argument or sender)
     */
    public static String getPlayerName(String[] args, ISender sender, Permissions perm) {
        String playerName = "";
        boolean isConsole = !CommandUtils.isPlayer(sender);
        if (isConsole) {
            playerName = args[0];
        } else if (args.length > 0) {
            if (sender.hasPermission(perm.getPermission())) {
                playerName = args[0];
            } else if (args[0].equalsIgnoreCase(sender.getName())) {
                playerName = sender.getName();
            } else {
                sender.sendMessage(Locale.get(Msg.CMD_FAIL_NO_PERMISSION).toString());
            }
        } else {
            playerName = sender.getName();
        }
        return playerName;
    }

    /**
     * Get matching player names from the offline players.
     *
     * @param search Part of a name to search for.
     * @return Alphabetically sorted list of matching player names.
     */
    public static List<String> getMatchingPlayerNames(String search) {
        Database db = getIPlan().getDB();
        List<String> matches;
        try {
            matches = db.getUsersTable().getMatchingNames(search);
        } catch (SQLException e) {
            Log.toLog("MiscUtils.getMatchingPlayerNames", e);
            return new ArrayList<>();
        }
        Collections.sort(matches);
        return matches;
    }

    public static <T> List<T> flatMap(Collection<List<T>> coll) {
        return coll.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    public static void close(Closeable... close) {
        for (Closeable c : close) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException ignored) {
                    /* Ignored */
                }
            }
        }
    }

    public static void close(AutoCloseable... close) {
        for (AutoCloseable c : close) {
            if (c != null) {
                try {
                    c.close();
                } catch (Exception ignored) {
                    /* Ignored */
                }
            }
        }
    }

    public static String getPlanVersion() {
        if (Check.isBukkitAvailable()) {
            return Plan.getInstance().getDescription().getVersion();
        } else {
            return PlanBungee.getInstance().getDescription().getVersion();
        }
    }

    public static IPlan getIPlan() {
        if (Check.isBukkitAvailable()) {
            return Plan.getInstance();
        } else {
            return PlanBungee.getInstance();
        }
    }
}
