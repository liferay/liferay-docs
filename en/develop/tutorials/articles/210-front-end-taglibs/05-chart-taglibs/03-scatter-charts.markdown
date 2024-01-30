---
header-id: scatter-charts
---

# Scatter Charts

[TOC levels=1-4]

Scatter charts contain multiple sets of data. A scatter chart models the data as
individual points. Each data series (created with the `addColumns()` method) is
defined with a new instance of the
[`MultiValueColumn` object](https://docs.liferay.com/portal/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html),
which takes an ID and a set of values. This tutorial shows how to configure your
portlet to use scatter charts.

Follow these steps:

1.  Import the chart taglib along with the `ScatterChartConfig` and
    `MultiValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.point.scatter.ScatterChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        ScatterChartConfig _scatterChartConfig = new ScatterChartConfig();

        _scatterChartConfig.addColumns(
          new MultiValueColumn("data1", 100, 20, 30),
          new MultiValueColumn("data2", 20, 70, 100)
        );
        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_scatterChartConfig`
    as the `config` attribute's value:

        <chart:scatter
          config="<%= _scatterChartConfig %>"
        />

![Figure 1: A scatter chart models the data as individual points.](../../../images/chart-taglib-scatter.png)

## Related Topics

[Line Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Step Charts](/docs/7-1/tutorials/-/knowledge_base/t/step-charts)

[Predictive Charts](/docs/7-1/tutorials/-/knowledge_base/t/predictive-charts)