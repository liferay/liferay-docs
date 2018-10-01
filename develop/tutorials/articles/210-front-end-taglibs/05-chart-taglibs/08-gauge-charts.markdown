# Gauge Charts [](id=gauge-charts)

Gauge charts are percentage-based. A gauge chart shows where percentage-based 
data falls over a given range. Each data set must be defined as a new instance 
of the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html). 
This tutorial shows how to configure your portlet to use gauge charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
    contents to it:

        import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
        import com.liferay.frontend.taglib.chart.model.gauge.GaugeChartConfig;

        public class GaugeChartSampleDisplayContext {

           public GaugeChartSampleDisplayContext(PortletRequest portletRequest) {
             _initGaugeChartConfig();
             _portletRequest = portletRequest;
           }

           public GaugeChartConfig getGaugeChartConfig() {
             return _gaugeChartConfig;
           }

           private void _initGaugeChartConfig() {
           	_gaugeChartConfig.addColumn(new SingleValueColumn("data1", 85.4));
           }

           private GaugeChartConfig _gaugeChartConfig = new GaugeChartConfig();
           private final PortletRequest _portletRequest;

        }

3.  Open the *Portlet* class and override the `doView()` method to create a new 
   instance of the *DisplayContext*:

       import com.liferay.docs.mvcportlet.portlet.GaugeChartSampleDisplayContext;
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
           GaugeChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
           new GaugeChartSampleDisplayContext(portletRequest));

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
       <%@ page import="com.liferay.docs.mvcportlet.portlet.GaugeChartSampleDisplayContext" %>
       <%@ page import="com.liferay.docs.mvcportlet.constants.GaugeChartPortletKeys" %>

7.  Add the following Java scriptlet to the bottom of your `init.jsp`:

       <%
       GaugeChartSampleDisplayContext gaugeChartSampleDisplayContext =
       (GaugeChartSampleDisplayContext)request.getAttribute(
         GaugeChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
       );
       %>

8.  Add the `<chart>` taglib to the `view.jsp`. An example configuration is 
   shown below:

       <chart:gauge
         config="<%= gaugeChartSampleDisplayContext.getGaugeChartConfig() %>"
         id="gauge"
       />
    
![Figure 1: A gauge chart shows where percentage-based data falls over a given range.](../../../images/chart-taglib-gauge.png)

## Related Topics [](id=related-topics)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Geomap Charts](/develop/tutorials/-/knowledge_base/7-1/geomap-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/pie-charts)
