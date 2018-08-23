# Line Charts [](id=line-charts)

Line charts contain multiple sets of data. A Line chart displays the data 
linearly. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. 

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.point.line.LineChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initLineChartConfig();
        }

        public LineChartConfig getLineChartConfig() {
          return _lineChartConfig;
        }

        private void _initLineChartConfig() {
          _lineChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private LineChartConfig _lineChartConfig = new LineChartConfig();
    
    }

JSP:

    <chart:line config="<%= chartSampleDisplayContext.getLineChartConfig() %>" id="line" />

![Figure 1: A Line chart displays the data linearly.](../../../images/chart-taglib-line.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Step Charts](/develop/tutorials/-/knowledge_base/7-1/step-charts)
