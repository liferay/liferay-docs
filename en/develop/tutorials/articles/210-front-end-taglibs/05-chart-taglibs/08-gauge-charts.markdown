---
header-id: gauge-charts
---

# Gauge Charts

[TOC levels=1-4]

Gauge charts are percentage-based. A gauge chart shows where percentage-based
data falls over a given range. Each data set must be defined as a new instance
of the
[`SingleValueColumn` object](https://docs.liferay.com/portal/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html).
This tutorial shows how to configure your portlet to use gauge charts.

Follow these steps:

1.  Import the chart taglib along with the `GaugeChartConfig` and
    `SingleValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.gauge.GaugeChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        GaugeChartConfig _gaugeChartConfig = new GaugeChartConfig();

        _gaugeChartConfig.addColumn(
          new SingleValueColumn("data1", 85.4)
        );
        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_gaugeChartConfig`
    as the `config` attribute's value:

        <chart:gauge
          config="<%= _gaugeChartConfig %>"
        />

![Figure 1: A gauge chart shows where percentage-based data falls over a given range.](../../../images/chart-taglib-gauge.png)

## Related Topics

[Pie Charts](/docs/7-1/tutorials/-/knowledge_base/t/pie-charts)

[Donut Charts](/docs/7-1/tutorials/-/knowledge_base/t/donut-charts)

[Bar Charts](/docs/7-1/tutorials/-/knowledge_base/t/bar-charts)