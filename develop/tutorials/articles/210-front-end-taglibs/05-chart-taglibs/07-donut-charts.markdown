# Donut Charts [](id=donut-charts)

Donut charts are percentage-based. A donut chart is similar to a pie chart, but 
it has a hole in the center. Each data set must be defined as a new instance of 
the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html). 
This tutorial shows how to configure your portlet to use donut charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
   contents to it:

        import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
        import com.liferay.frontend.taglib.chart.model.percentage.donut.DonutChartConfig;

        public class DonutChartSampleDisplayContext {

           public DonutChartSampleDisplayContext(PortletRequest portletRequest) {
             _initDonutChartConfig();
             _portletRequest = portletRequest;
           }

           public DonutChartConfig getDonutChartConfig() {
             return _donutChartConfig;
           }

           private void _initDonutChartConfig() {
             _donutChartConfig.addColumns(
               new SingleValueColumn("data1", 30),
               new SingleValueColumn("data2", 70));
           }

           private DonutChartConfig _donutChartConfig = new DonutChartConfig();
           private final PortletRequest _portletRequest;

        }

3.  Open the *Portlet* class and override the `doView()` method to create a new 
   instance of the *DisplayContext*:

       import com.liferay.docs.mvcportlet.portlet.DonutChartSampleDisplayContext;
       import com.liferay.portal.kernel.util.JavaConstants;
       import java.io.IOException;
       import javax.portlet.PortletException;
       import javax.portlet.PortletRequest;
       import javax.portlet.RenderRequest;
       import javax.portlet.RenderResponse;

       @Override
       public void doView(
           RenderRequest renderRequest, RenderResponse renderResponse)
         throws IOException, PortletException {

         PortletRequest portletRequest =
           (PortletRequest)renderRequest.getAttribute(
             JavaConstants.JAVAX_PORTLET_REQUEST);

         renderRequest.setAttribute(
           DonutChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
           new DonutChartSampleDisplayContext(portletRequest));

         super.doView(renderRequest, renderResponse);
       }

4.  Add the following String to the generated *PortletKeys* class:

       public static final String CHART_SAMPLE_DISPLAY_CONTEXT =
         "CHART_SAMPLE_DISPLAY_CONTEXT";

5.  Add the following dependency to your bundle's `build.gradle` file:

       compileOnly group: "com.liferay",
       name: "com.liferay.frontend.taglib.chart",
       version: "1.0.9"

6.  Import the chart taglib along with your portlet's display context and 
   portlet keys into your bundle's `init.jsp` file:

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.docs.mvcportlet.portlet.DonutChartSampleDisplayContext" %>
       <%@ page import="com.liferay.docs.mvcportlet.constants.DonutChartPortletKeys" %>

7.  Add the following Java scriptlet to the bottom of your `init.jsp`:

       <%
       DonutChartSampleDisplayContext donutChartSampleDisplayContext =
       (DonutChartSampleDisplayContext)request.getAttribute(
         DonutChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
       );
       %>

8.  Add the `<chart>` taglib to the `view.jsp`. An example configuration is 
   shown below:

       <chart:donut
         config="<%= donutChartSampleDisplayContext.getDonutChartConfig() %>"
         id="donut"
       />
    
![Figure 1: A donut chart is similar to a pie chart, but it has a hole in the center.](../../../images/chart-taglib-donut.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Gauge Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)
