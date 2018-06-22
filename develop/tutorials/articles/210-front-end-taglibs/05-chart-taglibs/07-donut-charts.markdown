# Donut Charts [](id=donut-charts)

Donut charts are percentage-based. A donut chart is similar to a pie chart, but 
it has a hole in the center. Each data set must be defined as a new instance of 
the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html).

Java sample data:

    import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
    import com.liferay.frontend.taglib.chart.model.percentage.donut.DonutChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initDonutChartConfig();
        }

        public donutChartConfig getDonutChartConfig() {
          return _donutChartConfig;
        }

        private void _initDonutChartConfig() {
          _donutChartConfig.addColumns(
            new SingleValueColumn("data1", 30),
            new SingleValueColumn("data2", 70));
        }
        
        private DonutChartConfig _donutChartConfig = new DonutChartConfig();

    }

JSP:

    <chart:donut config="<%= chartSampleDisplayContext.getDonutChartConfig() %>" id="donut" />
    
![Figure 1: A donut chart is similar to a pie chart, but it has a hole in the center.](../../../images/chart-taglib-donut.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Gauge Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)
