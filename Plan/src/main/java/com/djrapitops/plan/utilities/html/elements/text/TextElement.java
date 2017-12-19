/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements.text;

import main.java.com.djrapitops.plan.utilities.html.elements.Element;

/**
 * Abstract element class for all elements that contain text inside them.
 *
 * @author Rsl1122
 */
public abstract class TextElement<T extends Element> extends Element<T> {

    private final String text;

    public TextElement(String text, String tag) {
        setTag(tag);
        this.text = text;
    }

    @Override
    protected String getContent() {
        return getText();
    }

    public String getText() {
        return text;
    }
}