# Step Charts [](id=step-charts)

Step charts contain multiple sets of data. A step chart steps between the points 
of data, resembling steps. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. This tutorial shows how to configure your 
portlet to use step charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
    contents to it:

        import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
        import com.liferay.frontend.taglib.chart.model.point.step.StepChartConfig;

        public class StepChartSampleDisplayContext {

            public StepChartSampleDisplayContext(PortletRequest portletRequest) {
              _initStepChartConfig();
              _portletRequest = portletRequest;
            }

            public StepChartConfig getStepChartConfig() {
              return _stepChartConfig;
            }

            private void _initStepChartConfig() {
              _stepChartConfig.addColumns(
                new MultiValueColumn("data1", 100, 20, 30),
                new MultiValueColumn("data2", 20, 70, 100));
            }

            private StepChartConfig _stepChartConfig = new StepChartConfig();
            private final PortletRequest _portletRequest;

        }

3.  Open the *Portlet* class and override the `doView()` method to create a new 
    instance of the *DisplayContext*:

        import com.liferay.docs.mvcportlet.portlet.StepChartSampleDisplayContext;
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
            StepChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
            new StepChartSampleDisplayContext(portletRequest));

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
        <%@ page import="com.liferay.docs.mvcportlet.portlet.StepChartSampleDisplayContext" %>
        <%@ page import="com.liferay.docs.mvcportlet.constants.StepChartPortletKeys" %>

7.  Add the following Java scriptlet to the bottom of your `init.jsp`:

        <%
        StepChartSampleDisplayContext stepChartSampleDisplayContext =
        (StepChartSampleDisplayContext)request.getAttribute(
          StepChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
        );
        %>

8.  Add the `<chart>` taglib to the `view.jsp`. An example configuration is 
    shown below:

        <chart:step
          config="<%= stepChartSampleDisplayContext.getStepChartConfig() %>"
          id="step"
        />
    
![Figure 1: A step chart steps between the points of data, resembling steps.](../../../images/chart-taglib-step.png)

You can also use an area step chart if you prefer. An area step chart highlights 
the area covered by a step graph. 

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.area.step.AreaStepChartConfig;

    public class AreaStepChartSampleDisplayContext {

        public AreaStepChartSampleDisplayContext(PortletRequest portletRequest) {
          _initAreaStepChartConfig();
          _portletRequest = portletRequest;
        }

        public areaStepChartConfig getAreaStepChartConfig() {
          return _areaStepChartConfig;
        }

        private void _initAreaStepChartConfig() {
          _areaStepChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private AreaStepChartConfig _areaStepChartConfig = 
        new AreaStepChartConfig();
        private final PortletRequest _portletRequest;

    }

JSP:

    <chart:area-step 
      config="<%= stepAreaChartSampleDisplayContext.getAreaStepChartConfig() %>" 
      id="area-step" 
    />
    
![Figure 2: An area step chart highlights the area covered by a step graph.](../../../images/chart-taglib-area-step.png)

## Related Topics [](id=related-topics)

[Line Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Spline Charts](/develop/tutorials/-/knowledge_base/7-1/spline-charts)
