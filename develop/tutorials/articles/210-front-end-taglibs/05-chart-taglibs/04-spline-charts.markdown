# Spline Charts [](id=spline-charts)

Spline charts contain multiple sets of data. A spline chart connects points of 
data with a smooth curve. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. This tutorial shows how to configure your 
portlet to use spline charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
    contents to it:

        import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
        import com.liferay.frontend.taglib.chart.model.point.spline.SplineChartConfig;

        public class SplineChartSampleDisplayContext {

            public SplineChartSampleDisplayContext(PortletRequest portletRequest) {
              _initSplineChartConfig();
              _portletRequest = portletRequest;
            }

            public SplineChartConfig getSplineChartConfig() {
              return _splineChartConfig;
            }

            private void _initSplineChartConfig() {
              _splineChartConfig.addColumns(
                new MultiValueColumn("data1", 100, 20, 30),
                new MultiValueColumn("data2", 20, 70, 100));
            }

            private SplineChartConfig _splineChartConfig = 
            new SplineChartConfig();
            private final PortletRequest _portletRequest;

        }

3.  Open the *Portlet* class and override the `doView()` method to create a new 
    instance of the *DisplayContext*:

        import com.liferay.docs.mvcportlet.portlet.SplineChartSampleDisplayContext;
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
            SplineChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
            new SplineChartSampleDisplayContext(portletRequest));

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
        <%@ page import="com.liferay.docs.mvcportlet.portlet.SplineChartSampleDisplayContext" %>
        <%@ page import="com.liferay.docs.mvcportlet.constants.SplineChartPortletKeys" %>

7.  Add the following Java scriptlet to the bottom of your `init.jsp`:

        <%
        SplineChartSampleDisplayContext splineChartSampleDisplayContext =
        (SplineChartSampleDisplayContext)request.getAttribute(
          SplineChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
        );
        %>

8.  Add the `<chart>` taglib to the `view.jsp`. An example configuration is 
    shown below:

        <chart:spline 
          config="<%= splineChartSampleDisplayContext.getSplineChartConfig() %>" 
          id="spline" 
        />

![Figure 1: A spline chart connects points of data with a smooth curve.](../../../images/chart-taglib-spline.png)

You can also use an area spline chart if you prefer. An area spline chart 
highlights the area under the spline curve.

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.area.spline.AreaSplineChartConfig;

    public class AreaSplineChartSampleDisplayContext {

        public AreaSplineChartSampleDisplayContext(PortletRequest portletRequest) {
          _initAreaSplineChartConfig();
          _portletRequest = portletRequest;
        }

        public areaSplineChartConfig getAreaSplineChartConfig() {
          return _areaSplineChartConfig;
        }

        private void _initAreaSplineChartConfig() {
          _areaSplineChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private AreaSplineChartConfig _areaSplineChartConfig = 
        new AreaSplineChartConfig();
        private final PortletRequest _portletRequest;

    }

JSP:

    <chart:area-spline 
      config="<%= splineAreaChartSampleDisplayContext.getAreaSplineChartConfig() %>" 
      id="area-spline" 
    />

![Figure 2: An area spline chart highlights the area under the spline curve.](../../../images/chart-taglib-area-spline.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Step Charts](/develop/tutorials/-/knowledge_base/7-1/step-charts)
