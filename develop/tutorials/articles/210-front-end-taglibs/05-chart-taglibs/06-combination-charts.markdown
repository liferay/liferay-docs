# Combination Charts [](id=combination-charts)

Combination charts have minor differences from other charts. In a combination
chart, you must define the representation type of each data set: `AREA`,
`AREA_SPLINE`, `AREA_STEP`, `BAR`, `BUBBLE`, `DONUT`, `GAUGE`, `LINE`, `PIE`,
`SCATTER`, `SPLINE`, or `STEP`. Each data set in a combination chart is an
instance of the `TypedMultiValueColumn` object. Each object receives an ID, the
representation type, and values for the data. This tutorial shows how to 
configure your portlet to use combination charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
    contents to it. In the example below, data series `data1` and `data2` are 
    displayed together with the `addGroup()` method:

        import com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn;
        import com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn.Type;
        import com.liferay.frontend.taglib.chart.model.combination.CombinationChartConfig;

        public class CombinationChartSampleDisplayContext {

            public CombinationChartSampleDisplayContext(PortletRequest portletRequest) {
              _initCombinationChartConfig();
              _portletRequest = portletRequest;
            }

            public combinationChartConfig getCombinationChartConfig() {
              return _combinationChartConfig;
            }

            private void _initCombinationChartConfig() {
              _combinationChartConfig.addColumns(
                new TypedMultiValueColumn(
                  "data1", Type.BAR, 30, 20, 50, 40, 60, 50),
                new TypedMultiValueColumn(
                  "data2", Type.BAR, 200, 130, 90, 240, 130, 220),
                new TypedMultiValueColumn(
                  "data3", Type.SPLINE, 300, 200, 160, 400, 250, 250),
                new TypedMultiValueColumn(
                  "data4", Type.LINE, 200, 130, 90, 240, 130, 220),
                new TypedMultiValueColumn(
                  "data5", Type.BAR, 130, 120, 150, 140, 160, 150),
                new TypedMultiValueColumn(
                  "data6", Type.AREA, 90, 70, 20, 50, 60, 120));

              _combinationChartConfig.addGroup("data1", "data2");
            }
            
            private CombinationChartConfig _combinationChartConfig = 
            new CombinationChartConfig();
            private final PortletRequest _portletRequest;

        }

3.  Open the *Portlet* class and override the `doView()` method to create a new 
    instance of the *DisplayContext*:

        import com.liferay.docs.mvcportlet.portlet.CombinationChartSampleDisplayContext;
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
            CombinationChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
            new CombinationChartSampleDisplayContext(portletRequest));

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
        <%@ page import="com.liferay.docs.mvcportlet.portlet.CombinationChartSampleDisplayContext" %>
        <%@ page import="com.liferay.docs.mvcportlet.constants.CombinationChartPortletKeys" %>

7.  Add the following Java scriptlet to the bottom of your `init.jsp`:

        <%
        CombinationChartSampleDisplayContext combinationChartSampleDisplayContext =
        (CombinationChartSampleDisplayContext)request.getAttribute(
          CombinationChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
        );
        %>

8.  Add the `<chart>` taglib to the `view.jsp`. An example configuration is 
    shown below:

        <chart:combination
          config="<%= combinationChartSampleDisplayContext.getCombinationChartConfig() %>"
          id="combination"
        />
    
![Figure 1: A combination chart displays a variety of data set types.](../../../images/chart-taglib-combination.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/pie-charts)
