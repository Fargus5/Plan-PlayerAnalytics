/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements.text;

/**
 * Represents a clickable link element {@code <a>}
 *
 * @author Rsl1122
 */
public class Link extends TextElement<Link> {

    public Link(String text) {
        super(text, "a");
    }

    public Link(String text, String address) {
        super(text, "a");
        extra = "href=\"" + address + "\"";
    }
}