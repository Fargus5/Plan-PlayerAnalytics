/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements.placement;

import main.java.com.djrapitops.plan.utilities.html.elements.Div;
import main.java.com.djrapitops.plan.utilities.html.elements.Element;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a div with tab class {@code <div class="tab">}
 *
 * @author Rsl1122
 */
public class Tab extends Div<Tab> {

    public Tab(Element... contents) {
        this(Arrays.asList(contents));
    }

    public Tab(List<Element> contents) {
        super(contents);
        htmlClass = "tab";
    }
}