/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract representation of a Html element that holds other elements inside itself.
 *
 * Represents something like:
 * {@code
 * <tag>
 *     <element></element>
 *     <element></element>
 * </tag>
 * }
 *
 * @author Rsl1122
 */
public abstract class Container<T extends Container> extends Element<T> {

    private List<? extends Element> contents;

    public Container(Element... contents) {
        this(Arrays.asList(contents));
    }

    public Container(List<? extends Element> contents) {
        this.contents = contents;
    }

    @Override
    protected String getContent() {
        StringBuilder b = new StringBuilder();
        for (Element content : contents) {
            b.append(content.toHtml());
        }
        return b.toString();
    }
}