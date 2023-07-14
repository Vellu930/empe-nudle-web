package cz.vellu.nudleweb.util;

import org.thymeleaf.context.Context;

/**
 * Prepares html templates context.
 */
public class TemplateProcessor {

    /** Thymeleaf template context - converts data collected from the form into html email template context
     * @param dataModel data
     * @param htmlTag name of th:object template tag
     * @return Context with Form data
     */
    public static Context buildContext(Object dataModel, String htmlTag) {
        Context context = new Context();
        context.setVariable(htmlTag, dataModel);

        return context;
    }
}
