# Gauge Charts [](id=gauge-charts)

Gauge charts are percentage-based. A gauge chart shows where percentage-based 
data falls over a given range. Each data set must be defined as a new instance 
of the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html).

Java sample data:

    import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
    import com.liferay.frontend.taglib.chart.model.gauge.GaugeChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initGaugeChartConfig();
        }

        public gaugeChartConfig getGaugeChartConfig() {
          return _gaugeChartConfig;
        }

        private void _initGaugeChartConfig() {
        	_gaugeChartConfig.addColumn(new SingleValueColumn("data1", 85.4));
        }
        
        private GaugeChartConfig _gaugeChartConfig = new GaugeChartConfig();

    }

JSP:

    <chart:gauge config="<%= chartSampleDisplayContext.getGaugeChartConfig() %>" id="gauge" />
    
![Figure 1: A gauge chart shows where percentage-based data falls over a given range.](../../../images/chart-taglib-gauge.png)

## Related Topics [](id=related-topics)

[Donut Charts](/develop/tutorials/-/knowledge_base/7-1/donut-charts)

[Geomap Charts](/develop/tutorials/-/knowledge_base/7-1/geomap-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/pie-charts)
