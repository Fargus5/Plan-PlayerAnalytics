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
 * Represents a size related div element with col- tags.
 *
 * Size by default 12 for all sizes.
 *
 * @author Rsl1122
 */
public class Column extends Div<Column> {

    private int xs = 12;
    private int sm = 12;
    private int md = 12;
    private int lg = 12;

    public Column(Element... contents) {
        this(Arrays.asList(contents));
    }

    public Column(List<Element> contents) {
        super(contents);
    }

    public Column setSizes(int xs, int sm, int md, int lg) {
        return xs(xs).sm(sm).md(md).lg(lg);
    }

    @Override
    public String toHtml() {
        setClass("col-xs-" + xs + " col-sm-" + sm + " col-md-" + md + " col-lg-" + lg);
        return super.toHtml();
    }

    public Column xs(int xs) {
        this.xs = xs;
        return this;
    }

    public Column sm(int sm) {
        this.sm = sm;
        return this;
    }

    public Column md(int md) {
        this.md = md;
        return this;
    }

    public Column lg(int lg) {
        this.lg = lg;
        return this;
    }
}