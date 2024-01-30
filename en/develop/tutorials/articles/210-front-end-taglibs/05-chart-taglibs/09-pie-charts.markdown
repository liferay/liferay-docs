---
header-id: pie-charts
---

# Pie Charts

[TOC levels=1-4]

Pie charts are percentage-based. A pie chart models percentage-based data as
individual slices of pie. Each data set must be defined as a new instance of the
[`SingleValueColumn` object](https://docs.liferay.com/portal/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html).
This tutorial shows how to configure your portlet to use pie charts.

Follow these steps:

1.  Import the chart taglib along with the `PieChartConfig` and
    `SingleValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.percentage.pie.PieChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        PieChartConfig _pieChartConfig = new PieChartConfig();

        _pieChartConfig.addColumn(
          new SingleValueColumn("data1", 85.4)
        );
        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_pieChartConfig`
    as the `config` attribute's value:

        <chart:pie
          config="<%= _pieChartConfig %>"
        />

![Figure 1: A pie chart models percentage-based data as individual slices of pie.](../../../images/chart-taglib-pie.png)

## Related Topics

[Donut Charts](/docs/7-1/tutorials/-/knowledge_base/t/donut-charts)

[Gauge Charts](/docs/7-1/tutorials/-/knowledge_base/t/gauge-charts)

[Spline Charts](/docs/7-1/tutorials/-/knowledge_base/t/spline-charts)