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
 * Element that represents {@code <div class="row clearfix">}.
 *
 * @author Rsl1122
 */
public class Row extends Div<Row> {

    public Row(Element... contents) {
        this(Arrays.asList(contents));

    }

    public Row(List<Element> contents) {
        super("row clearfix", null, contents);
    }
}