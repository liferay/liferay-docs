---
header-id: combination-charts
---

# Combination Charts

[TOC levels=1-4]

Combination charts have minor differences from other charts. In a combination
chart, you must define the representation type of each data set: `AREA`,
`AREA_SPLINE`, `AREA_STEP`, `BAR`, `BUBBLE`, `DONUT`, `GAUGE`, `LINE`, `PIE`,
`SCATTER`, `SPLINE`, or `STEP`. Each data set in a combination chart is an
instance of the [`TypedMultiValueColumn` object](https://docs.liferay.com/portal/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/TypedMultiValueColumn.html). Each object receives an ID, the
representation type, and values for the data. This tutorial shows how to
configure your portlet to use combination charts.

Follow these steps:

1.  Import the chart taglib along with the `CombinationChartConfig`,
    `MultiValueColumn`, and `MultiValueColumn.Type` classes into your bundle's
    `init.jsp` file:

        <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.combination.CombinationChartConfig" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
        <%@ page import="com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn.Type" %>

2.  Add the following Java scriptlet to the top of your `view.jsp`:

        <%
        CombinationChartConfig _combinationChartConfig =
        new CombinationChartConfig();

        _combinationChartConfig.addColumns(
          new TypedMultiValueColumn(
            "data1", Type.BAR, 30, 20, 50, 40, 60, 50),
          new TypedMultiValueColumn(
            "data2", Type.BAR, 200, 130, 90, 240, 130, 220),
          new TypedMultiValueColumn(
            "data3", Type.SPLINE, 300, 200, 160, 400, 250, 250),
          new TypedMultiValueColumn(
            "data4", Type.LINE, 200, 130, 90, 240, 130, 220),
          new TypedMultiValueColumn(
            "data5", Type.BAR, 130, 120, 150, 140, 160, 150),
          new TypedMultiValueColumn(
            "data6", Type.AREA, 90, 70, 20, 50, 60, 120)
          );

        _combinationChartConfig.addGroup("data1", "data2");
        %>

3.  Add the `<chart>` taglib to the `view.jsp`, passing the
    `_combinationChartConfig` as the `config` attribute's value:

        <chart:combination
          config="<%= _combinationChartConfig %>"
        />

![Figure 1: A combination chart displays a variety of data set types.](../../../images/chart-taglib-combination.png)

## Related Topics

[Bar Charts](/docs/7-1/tutorials/-/knowledge_base/t/bar-charts)

[Line Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Geomap Charts](/docs/7-1/tutorials/-/knowledge_base/t/geomap-charts)