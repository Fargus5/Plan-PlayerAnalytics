/*
 * Licence is provided in the jar as license.yml also here:
 * https://github.com/Rsl1122/Plan-PlayerAnalytics/blob/master/Plan/src/main/resources/license.yml
 */
package main.java.com.djrapitops.plan.utilities.html.elements;

/**
 * Abstract representation of a Html element.
 * <p>
 * Can be used to create elements like:
 * {@code <tag class="" style="">contents</tag>}
 * {@code <tag style="">contents</tag>}
 * {@code <tag class="">contents</tag>}
 * {@code <tag>contents</tag>}
 *
 * @author Rsl1122
 */
public abstract class Element<T extends Element> {

    private final T t;

    protected String tag = "";

    protected String htmlClass;
    protected String style;

    protected String extra;

    public Element() {
        t = (T) this;
    }

    public T setTag(String tag) {
        this.tag = tag;
        return t;
    }


    public T setClass(String htmlClass) {
        this.htmlClass = htmlClass;
        return t;
    }

    public T setStyle(String style) {
        this.style = style;
        return t;
    }

    public T setExtra(String extra) {
        this.extra = extra;
        return t;
    }

    protected abstract String getContent();

    public String toHtml() {
        StringBuilder b = new StringBuilder();

        if (!tag.isEmpty()) {
            b.append("<").append(tag);
            if (htmlClass != null) {
                b.append(" class=\"").append(htmlClass).append("\"");
            }
            if (style != null) {
                b.append(" style=\"").append(style).append("\"");
            }
            if (extra != null) {
                b.append(" ").append(extra);
            }
            b.append(">");
        }

        b.append(getContent());

        if (!tag.isEmpty()) {
            b.append("</").append(tag).append(">");
        }
        return b.toString();
    }

    @Override
    public String toString() {
        return toHtml();
    }
}