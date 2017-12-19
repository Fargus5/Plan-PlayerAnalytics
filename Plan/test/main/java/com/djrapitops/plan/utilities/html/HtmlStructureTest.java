package main.java.com.djrapitops.plan.utilities.html;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests HtmlStructure class.
 *
 * @author Rsl1122
 */
public class HtmlStructureTest {

    @Test
    public void createInspectPageTabContentCalculating() {
        String tab = "<div class=\"tab\">" +
                "<div class=\"row clearfix\">" +
                "<div class=\"col-xs-12 col-sm-12 col-md-12 col-lg-12\">" +
                "<div class=\"card\">" +
                "<div class=\"header\"><h2><i class=\"fa fa-cubes\"></i> Plugin Data</h2></div>" +
                "<div class=\"body\">" +
                "<p><i class=\"fa fa-refresh fa-spin\"></i> Calculating Plugins tab, refresh (F5) shortly..</p>" +
                "</div></div>" +
                "</div></div></div>";
        assertEquals(tab, HtmlStructure.createInspectPageTabContentCalculating()[1]);
    }
}