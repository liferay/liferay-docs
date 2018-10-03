# Spline Charts [](id=spline-charts)

Spline charts contain multiple sets of data. A spline chart connects points of 
data with a smooth curve. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. This tutorial shows how to configure your 
portlet to use spline charts. 

Follow these steps:

1.  Import the chart taglib along with the `SplineChartConfig` and 
    `MultiValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.point.spline.SplineChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        SplineChartConfig _splineChartConfig = new SplineChartConfig();
        
        _splineChartConfig.addColumns(
          new MultiValueColumn("data1", 100, 20, 30),
          new MultiValueColumn("data2", 20, 70, 100));
        }
        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_splineChartConfig` 
    as the `config` attribute's value:

        <chart:spline
          config="<%= _splineChartConfig %>"
        />

![Figure 1: A spline chart connects points of data with a smooth curve.](../../../images/chart-taglib-spline.png)

You can also use an area spline chart if you prefer. An area spline chart 
highlights the area under the spline curve.

    <chart:area-spline 
      config="<%= _splineChartConfig %>" 
    />

![Figure 2: An area spline chart highlights the area under the spline curve.](../../../images/chart-taglib-area-spline.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Step Charts](/develop/tutorials/-/knowledge_base/7-1/step-charts)
