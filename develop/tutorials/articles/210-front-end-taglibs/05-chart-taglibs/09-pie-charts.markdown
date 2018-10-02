# Pie Charts [](id=pie-charts)

Pie charts are percentage-based. A pie chart models percentage-based data as 
individual slices of pie. Each data set must be defined as a new instance of the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html). 
This tutorial shows how to configure your portlet to use pie charts. 

Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Add the following dependency to your bundle's `build.gradle` file:

        compileOnly group: "com.liferay",
        name: "com.liferay.frontend.taglib.chart",
        version: "1.0.9"

3.  Import the chart taglib along with the `PieChartConfig` and 
    `SingleValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.percentage.pie.PieChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>

4.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        PieChartConfig _pieChartConfig = new PieChartConfig();

        _pieChartConfig.addColumn(
          new SingleValueColumn("data1", 85.4)
        );

        %>

5.  Add the `<chart>` taglib to the `view.jsp`, passing the `_pieChartConfig` 
    as the `config` attribute's value:

        <chart:pie
          config="<%= _pieChartConfig %>"
        />

![Figure 1: A pie chart models percentage-based data as individual slices of pie.](../../../images/chart-taglib-pie.png)

## Related Topics [](id=related-topics)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/pie-charts)

[Using Clay Taglibs in Your Portlet](/develop/tutorials/-/knowledge_base/7-1/using-the-clay-taglib-in-your-portlets)
