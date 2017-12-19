/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements.list;

import main.java.com.djrapitops.plan.utilities.html.elements.Container;
import main.java.com.djrapitops.plan.utilities.html.elements.Element;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a {@code <li>}-element.
 *
 * @author Rsl1122
 */
public class Li extends Container<Li> {

    public Li(Element... contents) {
        this(Arrays.asList(contents));
    }

    public Li(List<Element> contents) {
        super(contents);
        super.setTag("li");
    }

    public Li() {
        setTag("li");
    }

    @Override
    protected String getContent() {
        return null;
    }
}