/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements.text;

import main.java.com.djrapitops.plan.utilities.html.elements.Element;

/**
 * Represents a Font Awesome Icon {@code <i class="fa fa-xxx">}
 *
 * @author Rsl1122
 */
public class Icon extends Element<Icon> {


    public Icon(String iconName) {
        tag = "i";
        htmlClass = "fa fa-" + iconName;
    }

    @Override
    protected String getContent() {
        return "";
    }

    public Icon spin() {
        htmlClass += " fa-spin";
        return this;
    }
}