/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements.list;

import main.java.com.djrapitops.plan.utilities.html.elements.Container;

import java.util.Arrays;

/**
 * Represents any html list elements like {@code <ul>}.
 *
 * @author Rsl1122
 */
public class List extends Container<List> {

    public List(Li... contents) {
        this(Arrays.asList(contents));
    }

    public List(java.util.List<Li> contents) {
        super(contents);
        super.setTag("ul");
    }

    public List numbered() {
        return setTag("ol");
    }

    public List unordered() {
        return setTag("ul");
    }
}