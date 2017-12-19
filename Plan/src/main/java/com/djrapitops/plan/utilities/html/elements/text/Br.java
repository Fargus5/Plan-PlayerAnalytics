/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements.text;

import main.java.com.djrapitops.plan.utilities.html.elements.Element;

/**
 * Element class for {@code <br>}.
 *
 * @author Rsl1122
 */
public class Br extends Element<Br> {

    @Override
    public String toHtml() {
        return "<br>";
    }

    @Override
    protected String getContent() {
        return null;
    }
}