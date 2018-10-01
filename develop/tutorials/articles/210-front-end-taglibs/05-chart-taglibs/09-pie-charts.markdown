# Pie Charts [](id=pie-charts)

Pie charts are percentage-based. A pie chart models percentage-based data as 
individual slices of pie. Each data set must be defined as a new instance of the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html). 
This tutorial shows how to configure your portlet to use pie charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
    contents to it:

        import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
        import com.liferay.frontend.taglib.chart.model.percentage.pie.PieChartConfig;

        public class PieChartSampleDisplayContext {

           public PieChartSampleDisplayContext(PortletRequest portletRequest) {
             _initPieChartConfig();
             _portletRequest = portletRequest;
           }

           public PieChartConfig getPieChartConfig() {
             return _pieChartConfig;
           }

           private void _initPieChartConfig() {
             _pieChartConfig.addColumns(
               new SingleValueColumn("data1", 30),
               new SingleValueColumn("data2", 70));
           }

           private PieChartConfig _pieChartConfig = new PieChartConfig();
           private final PortletRequest _portletRequest;

        }

3.  Open the *Portlet* class and override the `doView()` method to create a new 
   instance of the *DisplayContext*:

       import com.liferay.docs.mvcportlet.portlet.PieChartSampleDisplayContext;
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
           PieChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
           new PieChartSampleDisplayContext(portletRequest));

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
       <%@ page import="com.liferay.docs.mvcportlet.portlet.PieChartSampleDisplayContext" %>
       <%@ page import="com.liferay.docs.mvcportlet.constants.PieChartPortletKeys" %>

7.  Add the following Java scriptlet to the bottom of your `init.jsp`:

       <%
       PieChartSampleDisplayContext pieChartSampleDisplayContext =
       (PieChartSampleDisplayContext)request.getAttribute(
         PieChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
       );
       %>

8.  Add the `<chart>` taglib to the `view.jsp`. An example configuration is 
   shown below:

       <chart:pie
         config="<%= pieChartSampleDisplayContext.getPieChartConfig() %>"
         id="pie"
       />

![Figure 1: A pie chart models percentage-based data as individual slices of pie.](../../../images/chart-taglib-pie.png)

## Related Topics [](id=related-topics)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Gauge Charts](/develop/tutorials/-/knowledge_base/7-1/gauge-charts)

[Using Clay Taglibs in Your Portlet](/develop/tutorials/-/knowledge_base/7-1/using-the-clay-taglib-in-your-portlets)
