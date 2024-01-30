---
header-id: line-charts
---

# Line Charts

[TOC levels=1-4]

Line charts contain multiple sets of data. A Line chart displays the data
linearly. Each data series (created with the `addColumns()` method) is defined
with a new instance of the
[`MultiValueColumn` object](https://docs.liferay.com/portal/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html),
which takes an ID and a set of values. This tutorial shows how to configure your
portlet to use line charts.

Follow these steps:

1.  Import the chart taglib along with the `LineChartConfig` and
    `MultiValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.point.line.LineChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        LineChartConfig _lineChartConfig = new LineChartConfig();

        _lineChartConfig.addColumns(
          new MultiValueColumn("data1", 100, 20, 30),
          new MultiValueColumn("data2", 20, 70, 100)
        );
        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_lineChartConfig`
    as the `config` attribute's value:

        <chart:line
          config="<%= _lineChartConfig %>"
        />

![Figure 1: A Line chart displays the data linearly.](../../../images/chart-taglib-line.png)

## Related Topics

[Spline Charts](/docs/7-1/tutorials/-/knowledge_base/t/spline-charts)

[Step Charts](/docs/7-1/tutorials/-/knowledge_base/t/step-charts)

[Predictive Charts](/docs/7-1/tutorials/-/knowledge_base/t/predictive-charts)