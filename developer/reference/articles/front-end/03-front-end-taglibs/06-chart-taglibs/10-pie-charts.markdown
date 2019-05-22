---
header-id: pie-charts
---

# Pie Charts

[TOC levels=1-4]

Pie charts are percentage-based. A pie chart models percentage-based data as 
individual slices of pie. Each data set must be defined as a new instance of the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html). 
Follow these steps to configure your portlet to use pie charts. 

1.  Import the chart taglib along with the `PieChartConfig` and 
    `SingleValueColumn` classes into your bundle's `init.jsp` file:

    ```markup
    <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
    <%@ page import="com.liferay.frontend.taglib.chart.model.percentage.pie.PieChartConfig" %>
    <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>
    ```

2.  Add the following Java scriptlet to the top of your `view.jsp`:

    ```java
    <%
    PieChartConfig _pieChartConfig = new PieChartConfig();

    _pieChartConfig.addColumn(
      new SingleValueColumn("data1", 85.4)
    );

    %>
    ```

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_pieChartConfig` 
    as the `config` attribute's value:

    ```html
    <chart:pie
      config="<%= _pieChartConfig %>"
    />
    ```

![Figure 1: A pie chart models percentage-based data as individual slices of pie.](../../../../images/chart-taglib-pie.png)

Awesome! Now you know how to create pie charts for your apps. 

## Related Topics

- [Donut Charts](/docs/7-2/reference/-/knowledge_base/r/donut-charts)
- [Pie Charts](/docs/7-2/reference/-/knowledge_base/r/pie-charts)
- [Using Clay Taglibs in Your Portlet](/docs/7-2/reference/-/knowledge_base/r/using-the-clay-taglib-in-your-portlets)
