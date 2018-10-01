# Refreshing Charts to Reflect Real Time Data [](id=refreshing-charts-to-reflect-real-time-data)

The polling interval property is an optional property for all charts. It 
specifies the time in milliseconds for the chart's data to refresh. You can 
use this for charts that receive any kind of real time data, such as a JSON file 
that changes periodically. This ensures that the chart is up to date, reflecting 
the most recent data. This tutorial shows how to configure your portlet's chart 
to reflect real time data. Follow these steps:

1.  Open your portlet's *ChartDisplayContext* class and use the `*ChartConfig` 
    object's `setPollingInterval()` method to specify the chart's refresh rate. 
    The example below species a two second refresh rate for the line chart's 
    data:

        import com.liferay.frontend.taglib.chart.model.point.line.LineChartConfig;
        import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
        import javax.portlet.PortletRequest;

        public class ChartDisplayContext {

            public ChartSampleDisplayContext(PortletRequest portletRequest) {
              _portletRequest = portletRequest;
              _initPollingIntervalLineChartConfig();
            }

            public LineChartConfig getPollingIntervalLineChartConfig() {
          		return _pollingIntervalLineChartConfig;
          	}

            private void _initPollingIntervalLineChartConfig() {
              _pollingIntervalLineChartConfig.addColumns(
                new MultiValueColumn("data1", 100, 20, 30),
                new MultiValueColumn("data2", 20, 70, 100));

              _pollingIntervalLineChartConfig.setPollingInterval(2000);
            }

            private LineChartConfig _pollingIntervalLineChartConfig =
            new LineChartConfig();
            private final PortletRequest _portletRequest;

        }

2.  To feed real time data to your chart, you can use the chart's component ID 
    and the `Liferay.componentReady()` method. Use the chart's `data` attribute 
    to set the data, passing an array of objects with `data` and `id` entries. 
    The example below randomly generates new data once the chart component is 
    ready:

        <aui:script>
        	Liferay.componentReady('polling-interval-line-chart').then(
        		function(chart) {
        			chart.data = function() {
        				return Promise.resolve(
        					[
        						{
        							data:
                      [Math.random() * 100, Math.random() * 100, Math.random() * 100],
        							id: 'data1'
        						},
        						{
        							data:
                      [Math.random() * 100, Math.random() * 100, Math.random() * 100],
        							id: 'data2'
        						}
        					]
        				);
        			};
        		}
        	);
        </aui:script>

![Figure 1: The polling interval property lets you refresh charts at a given interval to reflect real time data.](../../../images/chart-polling-interval.gif)

## Related Topics [](id=related-topics)

[Combination Charts](/develop/tutorials/-/knowledge_base/7-1/combination-charts)

[Geomap Charts](/develop/tutorials/-/knowledge_base/7-1/geomap-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)
