# Combination Charts [](id=combination-charts)

Combination charts have minor differences from other charts. In a combination
chart, you must define the representation type of each data set: `AREA`,
`AREA_SPLINE`, `AREA_STEP`, `BAR`, `BUBBLE`, `DONUT`, `GAUGE`, `LINE`, `PIE`,
`SCATTER`, `SPLINE`, or `STEP`. Each data set in a combination chart is an
instance of the `TypedMultiValueColumn` object. Each object receives an ID, the
representation type, and values for the data. In the example below, data series
`data1` and `data2` are displayed together with the `addGroup()` method. 

Java sample data:

    import com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn.Type;
    import com.liferay.frontend.taglib.chart.model.combination.CombinationChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initCombinationChartConfig();
        }

        public combinationChartConfig getCombinationChartConfig() {
          return _combinationChartConfig;
        }

        private void _initCombinationChartConfig() {
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
              "data6", Type.AREA, 90, 70, 20, 50, 60, 120));

          _combinationChartConfig.addGroup("data1", "data2");
        }
        
        private CombinationChartConfig _combinationChartConfig = new CombinationChartConfig();

    }

JSP:

    <chart:combination config="<%= chartSampleDisplayContext.getCombinationChartConfig() %>" id="combination" />
    
![Figure 1: A combination chart displays a variety of data set types.](../../../images/chart-taglib-combination.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/pie-charts)
