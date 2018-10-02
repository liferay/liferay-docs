# Scatter Charts [](id=scatter-charts)

Scatter charts contain multiple sets of data. A scatter chart models the data as 
individual points. Each data series (created with the `addColumns()` method) is 
defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. This tutorial shows how to configure your 
portlet to use scatter charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Add the following dependency to your bundle's `build.gradle` file:

        compileOnly group: "com.liferay",
        name: "com.liferay.frontend.taglib.chart",
        version: "1.0.9"

3.  Import the chart taglib along with the `ScatterChartConfig` and 
    `MultiValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.point.scatter.ScatterChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>

4.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        ScatterChartConfig _scatterChartConfig = new ScatterChartConfig();
        
        _scatterChartConfig.addColumns(
          new MultiValueColumn("data1", 100, 20, 30),
          new MultiValueColumn("data2", 20, 70, 100));
        }
        %>

5.  Add the `<chart>` taglib to the `view.jsp`, passing the `_scatterChartConfig` 
    as the `config` attribute's value:

        <chart:scatter
          config="<%= _scatterChartConfig %>"
        />

![Figure 1: A scatter chart models the data as individual points.](../../../images/chart-taglib-scatter.png)

## Related Topics [](id=related-topics)

[Step Charts](/develop/tutorials/-/knowledge_base/7-1/step-charts)

[Line Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Spline Charts](/develop/tutorials/-/knowledge_base/7-1/spline-charts)
