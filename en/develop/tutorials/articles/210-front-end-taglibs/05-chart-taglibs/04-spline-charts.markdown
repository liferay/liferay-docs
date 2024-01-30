---
header-id: spline-charts
---

# Spline Charts

[TOC levels=1-4]

Spline charts contain multiple sets of data. A spline chart connects points of
data with a smooth curve. Each data series
(created with the `addColumns()` method) is defined with a new instance of the
[`MultiValueColumn` object](https://docs.liferay.com/portal/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html),
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
          new MultiValueColumn("data2", 20, 70, 100)
        );
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

## Related Topics

[Line Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Step Charts](/docs/7-1/tutorials/-/knowledge_base/t/step-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)