/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.systems.tasks;

import com.djrapitops.plugin.api.Benchmark;
import com.djrapitops.plugin.api.TimeAmount;
import com.djrapitops.plugin.api.utility.log.Log;
import com.djrapitops.plugin.task.AbsRunnable;
import com.djrapitops.plugin.task.ITask;
import com.djrapitops.plugin.task.RunnableFactory;
import main.java.com.djrapitops.plan.Plan;
import main.java.com.djrapitops.plan.api.exceptions.PlanEnableException;
import main.java.com.djrapitops.plan.settings.Settings;
import main.java.com.djrapitops.plan.settings.locale.Locale;
import main.java.com.djrapitops.plan.settings.locale.Msg;
import main.java.com.djrapitops.plan.systems.info.InformationManager;

/**
 * //TODO Class Javadoc Comment
 *
 * @author Rsl1122
 */
public class PlanTaskSystem extends TaskSystem {

    // TODO Remove Plan.getInstance requirement.

    private ITask bootAnalysisTask;

    @Override
    public void init() throws PlanEnableException {
        registerTasks();
    }

    // TODO Clean Up
    private void registerTasks() {
        String bootAnalysisMsg = Locale.get(Msg.ENABLE_BOOT_ANALYSIS_INFO).toString();
        String bootAnalysisRunMsg = Locale.get(Msg.ENABLE_BOOT_ANALYSIS_RUN_INFO).toString();

        Benchmark.start("Task Registration");
        tpsCountTimer = new TPSCountTimer(Plan.getInstance());
        registerTask(tpsCountTimer).runTaskTimer(1000, TimeAmount.SECOND.ticks());

        // Analysis refresh settings
        int analysisRefreshMinutes = Settings.ANALYSIS_AUTO_REFRESH.getNumber();
        boolean analysisRefreshTaskIsEnabled = analysisRefreshMinutes > 0;
        long analysisPeriod = analysisRefreshMinutes * TimeAmount.MINUTE.ticks();

        Log.info(bootAnalysisMsg);

        InformationManager infoManager = Plan.getInstance().getInfoManager();

        bootAnalysisTask = RunnableFactory.createNew("BootAnalysisTask", new AbsRunnable() {
            @Override
            public void run() {
                Log.info(bootAnalysisRunMsg);
                infoManager.refreshAnalysis(Plan.getServerUUID());
                this.cancel();
            }
        }).runTaskLaterAsynchronously(30 * TimeAmount.SECOND.ticks());

        if (analysisRefreshTaskIsEnabled) {
            RunnableFactory.createNew("PeriodicalAnalysisTask", new AbsRunnable() {
                @Override
                public void run() {
                    infoManager.refreshAnalysis(Plan.getServerUUID());
                }
            }).runTaskTimerAsynchronously(analysisPeriod, analysisPeriod);
        }

        registerTask("PeriodicNetworkBoxRefreshTask", new AbsRunnable() {
            @Override
            public void run() {
                infoManager.updateNetworkPageContent();
            }
        }).runTaskTimerAsynchronously(TimeAmount.SECOND.ticks(), TimeAmount.MINUTE.ticks() * 5L);

        Benchmark.stop("Enable", "Task Registration");
    }

    public void cancelBootAnalysis() {
        try {
            bootAnalysisTask.cancel();
        } catch (Exception ignored) {
            /* Ignored */
        }
    }
}