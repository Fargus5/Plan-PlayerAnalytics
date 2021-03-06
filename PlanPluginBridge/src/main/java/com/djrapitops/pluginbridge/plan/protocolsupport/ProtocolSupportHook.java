/* 
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package com.djrapitops.pluginbridge.plan.protocolsupport;

import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.pluginbridge.plan.Hook;
import com.djrapitops.pluginbridge.plan.viaversion.ProtocolTable;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.api.exceptions.DBCreateTableException;
import main.java.com.djrapitops.plan.data.plugin.HookHandler;
import main.java.com.djrapitops.plan.database.databases.SQLDB;

/**
 * Hook for ProtocolSupport plugin.
 *
 * @author Rsl1122
 */
public class ProtocolSupportHook extends Hook {

    private static PlayerVersionListener listener;

    public ProtocolSupportHook(HookHandler hookHandler) {
        super("protocolsupport.ProtocolSupport", hookHandler);
    }

    @Override
    public void hook() throws NoClassDefFoundError {
        if (!enabled) {
            return;
        }
        Plan plan = Plan.getInstance();
        ProtocolTable table = new ProtocolTable((SQLDB) plan.getDB());
        try {
            table.createTable();
        } catch (DBCreateTableException e) {
            Log.toLog(this.getClass().getName(), e);
            return;
        }
        if (listener == null) {
            listener = new PlayerVersionListener();
            plan.registerListener(listener);
        }
        addPluginDataSource(new ProtocolSupportData(table));
    }
}