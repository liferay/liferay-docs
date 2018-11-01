# Donut Charts [](id=donut-charts)

Donut charts are percentage-based. A donut chart is similar to a pie chart, but 
it has a hole in the center. Each data set must be defined as a new instance of 
the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html). 
This tutorial shows how to configure your portlet to use donut charts. 

Follow these steps:

1.  Import the chart taglib along with the `DonutChartConfig` and 
    `SingleValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.percentage.donut.DonutChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        DonutChartConfig _donutChartConfig = new DonutChartConfig();

        _donutChartConfig.addColumns(
          new SingleValueColumn("data1", 30),
          new SingleValueColumn("data2", 70)
        );

        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_donutChartConfig` 
    as the `config` attribute's value:

        <chart:donut
          config="<%= _donutChartConfig %>"
        />
    
![Figure 1: A donut chart is similar to a pie chart, but it has a hole in the center.](../../../images/chart-taglib-donut.png)

## Related Topics [](id=related-topics)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Gauge Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)
