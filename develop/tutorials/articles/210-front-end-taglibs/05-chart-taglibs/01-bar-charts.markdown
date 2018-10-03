# Bar Charts [](id=bar-charts)

Bar charts contain multiple sets of data. A bar chart models the data in bars. 
Each data series (created with the `addColumns()` method) is defined with a new 
instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. This tutorial shows how to configure your 
portlet to use bar charts. 

Follow these steps:

1.  Import the chart taglib along with the `BarChartConfig` and 
    `MultiValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.point.bar.BarChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        BarChartConfig _barChartConfig = new BarChartConfig();

        _barChartConfig.addColumns(
        	new MultiValueColumn("data1", 100, 20, 30),
        	new MultiValueColumn("data2", 20, 70, 100)
        );

        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_barChartConfig` as 
    the `config` attribute's value:

        <chart:bar
          config="<%= _barChartConfig %>"
        />

![Figure 1: A bar chart models the data in bars.](../../../images/chart-taglib-bar.png)

## Related Topics [](id=related-topics)

[Line Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Spline Charts](/develop/tutorials/-/knowledge_base/7-1/spline-charts)
