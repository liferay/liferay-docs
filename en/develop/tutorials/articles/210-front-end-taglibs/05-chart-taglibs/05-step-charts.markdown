---
header-id: step-charts
---

# Step Charts

[TOC levels=1-4]

Step charts contain multiple sets of data. A step chart steps between the points
of data, resembling steps. Each data series
(created with the `addColumns()` method) is defined with a new instance of the
[`MultiValueColumn` object](https://docs.liferay.com/portal/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html),
which takes an ID and a set of values. This tutorial shows how to configure your
portlet to use step charts.

Follow these steps:

1.  Import the chart taglib along with the `StepChartConfig` and
    `MultiValueColumn` classes into your bundle's `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.point.step.StepChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        StepChartConfig _stepChartConfig = new StepChartConfig();

        _stepChartConfig.addColumns(
          new MultiValueColumn("data1", 100, 20, 30),
          new MultiValueColumn("data2", 20, 70, 100)
        );
        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the `_stepChartConfig`
    as the `config` attribute's value:

        <chart:step
          config="<%= _stepChartConfig %>"
        />

![Figure 1: A step chart steps between the points of data, resembling steps.](../../../images/chart-taglib-step.png)

You can also use an area step chart if you prefer. An area step chart highlights
the area covered by a step graph.

    <chart:area-step
      config="<%= _stepChartConfig %>"
    />

![Figure 2: An area step chart highlights the area covered by a step graph.](../../../images/chart-taglib-area-step.png)

## Related Topics

[Line Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Spline Charts](/docs/7-1/tutorials/-/knowledge_base/t/spline-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)