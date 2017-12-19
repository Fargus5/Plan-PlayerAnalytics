/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements;

import java.util.Arrays;
import java.util.List;

/**
 * Class that represents the {@code <div>}-elements.
 *
 * @author Rsl1122
 */
public class Div<T extends Div> extends Container<T> {

    public Div(Element... contents) {
        this(Arrays.asList(contents));
    }

    public Div(List<Element> contents) {
        this(null, null, contents);
    }

    public Div(String htmlClass, String style, List<Element> contents) {
        super(contents);
        super.setTag("div");
        super.setClass(htmlClass);
        super.setStyle(style);
    }

    public Div(String htmlClass, Element... contents) {
        this(htmlClass, null, Arrays.asList(contents));
    }
    public Div(String htmlClass, List<Element> contents) {
        this(htmlClass, null, contents);
    }
}