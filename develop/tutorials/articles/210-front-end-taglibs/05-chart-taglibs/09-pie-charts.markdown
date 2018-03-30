# Pie Charts [](id=pie-charts)

Pie charts are percentage-based. A pie chart models percentage-based data as 
individual slices of pie. Each data set must be defined as a new instance of the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html).

Java sample data:

    import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
    import com.liferay.frontend.taglib.chart.model.percentage.pie.PieChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initPieChartConfig();
        }

        public pieChartConfig getPieChartConfig() {
          return _pieChartConfig;
        }

        private void _initPieChartConfig() {
          _pieChartConfig.addColumns(
            new SingleValueColumn("data1", 30),
            new SingleValueColumn("data2", 70));
        }
        
        private PieChartConfig _pieChartConfig = new PieChartConfig();

    }

JSP:

    <chart:pie config="<%= chartSampleDisplayContext.getPieChartConfig() %>" id="pie" />

![Figure 1: A pie chart models percentage-based data as individual slices of pie.](../../../images/chart-taglib-pie.png)

## Related Topics [](id=related-topics)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Gauge Charts](/develop/tutorials/-/knowledge_base/7-1/gauge-charts)

[Using Clay Taglibs in Your Portlet](/develop/tutorials/-/knowledge_base/7-1/using-the-clay-taglib-in-your-portlets)
