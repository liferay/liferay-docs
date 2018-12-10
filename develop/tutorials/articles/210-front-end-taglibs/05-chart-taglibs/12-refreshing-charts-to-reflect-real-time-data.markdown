# Refreshing Charts to Reflect Real Time Data [](id=refreshing-charts-to-reflect-real-time-data)

The polling interval property is an optional property for all charts. It 
specifies the time in milliseconds for the chart's data to refresh. You can 
use this for charts that receive any kind of real time data, such as a JSON file 
that changes periodically. This ensures that the chart is up to date, reflecting 
the most recent data. This tutorial shows how to configure your portlet's chart 
to reflect real time data. 

Use the `setPollingInterval()` method for your chart's configuration object to 
specify the refresh rate. An example `view.jsp` configuration is shown below:

    <%
    LineChartConfig _pollingIntervalLineChartConfig = new LineChartConfig();

    _pollingIntervalLineChartConfig.put("data", "/foo.json");
    
    _pollingIntervalLineChartConfig.setPollingInterval(2000);

    %>

    <chart:line
    	componentId="polling-interval-line-chart"
    	config="<%= _pollingIntervalLineChartConfig %>"
    />

![Figure 1: The polling interval property lets you refresh charts at a given interval to reflect real time data.](../../../images/chart-polling-interval.gif)

## Related Topics [](id=related-topics)

[Combination Charts](/develop/tutorials/-/knowledge_base/7-1/combination-charts)

[Geomap Charts](/develop/tutorials/-/knowledge_base/7-1/geomap-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)
