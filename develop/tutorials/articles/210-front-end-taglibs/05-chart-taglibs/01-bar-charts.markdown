# Bar Charts [](id=bar-charts)

Bar charts contain multiple sets of data. A bar chart models the data in bars. 
Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. 

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.point.bar.BarChartConfig;
    
    public class ChartSampleDisplayContext {
  
        public ChartSampleDisplayContext() {
        	_initBarChartConfig();
        }

        public BarChartConfig getBarChartConfig() {
        	return _barChartConfig;
        }

        private void _initBarChartConfig() {
          _barChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private BarChartConfig _barChartConfig = new BarChartConfig();
    
    }

JSP:

    <chart:bar config="<%= chartSampleDisplayContext.getBarChartConfig() %>" id="bar" />
 
![Figure 1: A bar chart models the data in bars.](../../../images/chart-taglib-bar.png)

## Related Topics [](id=related-topics)

[Line Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Spline Charts](/develop/tutorials/-/knowledge_base/7-1/spline-charts)
