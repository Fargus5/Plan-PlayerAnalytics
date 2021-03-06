package main.java.com.djrapitops.plan.utilities.comparators;

import main.java.com.djrapitops.plan.data.container.Session;

import java.util.Comparator;

/**
 * @author Rsl1122
 */
public class SessionStartComparator implements Comparator<Session> {

    @Override
    public int compare(Session s1, Session s2) {
        return -Long.compare(s1.getSessionStart(), s2.getSessionStart());
    }
}
