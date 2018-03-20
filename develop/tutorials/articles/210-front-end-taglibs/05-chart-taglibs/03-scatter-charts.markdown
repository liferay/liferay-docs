# Scatter Charts [](id=scatter-charts)

Scatter charts contain multiple sets of data. A scatter chart models the data as individual points. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. 

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.point.scatter.ScatterChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initScatterChartConfig();
        }

        public ScatterChartConfig getScatterChartConfig() {
          return _scatterChartConfig;
        }

        private void _initScatterChartConfig() {
          _scatterChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private ScatterChartConfig _scatterChartConfig = new ScatterChartConfig();
    
    }

JSP:

    <chart:scatter config="<%= chartSampleDisplayContext.getScatterChartConfig() %>" id="scatter" />

![Figure 1: A scatter chart models the data as individual points.](../../../images/chart-taglib-scatter.png)

## Related Topics [](id=related-topics)

[Step Charts](/develop/tutorials/-/knowledge_base/7-1/step-charts)

[Line Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Spline Charts](/develop/tutorials/-/knowledge_base/7-1/spline-charts)
