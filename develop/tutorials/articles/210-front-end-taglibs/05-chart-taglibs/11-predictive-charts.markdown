# Predictive Charts [](id=predictive-charts)

Predictive charts take a combination of existing and predicted/forecasted 
data and render it together, so you can visualize future values with current 
values. Each data series (created with the `addDataColumn()` method) is defined 
with a new instance of the 
[`MixedDataColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MixedDataColumn.html), 
which is a column that supports both single number values and arrays of three 
numbers. 

Single number values define existing data. The arrays are used as the 
prediction/forecast data and contain three numbers: a minimum value, an 
estimated value, and a maximum value. The estimated value is rendered solid and 
surrounded by a highlighted area with borders specified by the minimum and 
maximum values. This lets you visualize your estimated values while also giving 
you an idea of the possible value ranges. 

![Figure 1: Predicted/forecasted data is surrounded by a highlighted area of possible values.](../../../images/chart-taglib-predictive-value-range.png)

Predictive chart data has the following properties:

**axisXTickFormat:** An optional string, which is used for the time formatting 
on the X axis. For more information on which formats can be specified please 
refer to 
[d3's time format README](https://github.com/d3/d3-time-format/blob/master/README.md#locale_format). 
This value is set using the `setAxisXTickFormat()` method. 

**Prediction Date:** A date as a string that represents the point in the 
timeline from when the forecast/prediction is shown. This value is parsed as a 
Date object in JavaScript and set using the `setPredictionDate()` method. 

**Time Series:** A timeline for the data, which is displayed on the X axis of 
the chart. This value is set as an array of dates (`2018-01-01` for example). 

Below is an example configuration containing two sets of data:

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MixedDataColumn;
    import com.liferay.frontend.taglib.chart.model.predictive.PredictiveChartConfig;

    public class ChartSampleDisplayContext {

      private void _initPredictiveChartConfig() {
    		MixedDataColumn mixedDataColumn1 = new MixedDataColumn(
    			"data1", 130, 340, 200, 500, 80, 240, 40,
    			new Number[] {370, 400, 450}, new Number[] {210, 240, 270},
    			new Number[] {150, 180, 210}, new Number[] {60, 90, 120},
    			new Number[] {310, 340, 370});

    		_predictiveChartConfig.addDataColumn(mixedDataColumn1);

    		MixedDataColumn mixedDataColumn2 = new MixedDataColumn(
    			"data2", 210, 160, 50, 125, 230, 110, 90,
    			Arrays.asList(170, 200, 230), Arrays.asList(10, 40, 70),
    			Arrays.asList(350, 380, 410), Arrays.asList(260, 290, 320),
    			Arrays.asList(30, 70, 150));

    		_predictiveChartConfig.addDataColumn(mixedDataColumn2);

    		_predictiveChartConfig.setAxisXTickFormat("%b");
    		_predictiveChartConfig.setPredictionDate("2018-07-01");

    		List<String> timeseries = new ArrayList<>();

    		timeseries.add("2018-01-01");
    		timeseries.add("2018-02-01");
    		timeseries.add("2018-03-01");
    		timeseries.add("2018-04-01");
    		timeseries.add("2018-05-01");
    		timeseries.add("2018-06-01");
    		timeseries.add("2018-07-01");
    		timeseries.add("2018-08-01");
    		timeseries.add("2018-09-01");
    		timeseries.add("2018-10-01");
    		timeseries.add("2018-11-01");
    		timeseries.add("2018-12-01");

    		_predictiveChartConfig.setTimeseries(timeseries);
    	}

      private PredictiveChartConfig _predictiveChartConfig = new
      PredictiveChartConfig();

    }

JSP:

    <div class="container-fluid">
    	<div class="row">
    		<div class="col predictive">
    			<chart:predictive
    				componentId="predictive-chart"
    				config="<%= chartSampleDisplayContext.getPredictiveChartConfig() %>"
    				id="predictive-chart"
    			/>
    		</div>
    	</div>
    </div>

You can also access the chart by its component ID in JavaScript, using the 
`Liferay.componentReady()` method. You can use this to add data after the chart 
is loaded, as shown in the example below:

    <aui:script>

    	Liferay.componentReady('predictive-chart').then(
    		function(chart) {
    			var oldData = chart.data.slice();

    			setTimeout(
    				function() {
    					var newData = {
    						data: [
    							[230, 230, 230],
    							[20, 20, 20],
    							[120, 120, 120],
    							[450, 450, 450],
    							[70, 70, 70],
    							[280, 280, 280],
    							[60, 60, 60],
    							[140, 140, 140],
    							[220, 245, 305],
    							[240, 275, 295],
    							[200, 235, 325],
    							[110, 145, 235],
    						],
    						id: 'data3'
    					};

    					chart.data = new Promise(
    						function(resolve, reject) {
    							oldData.push(newData);
    							resolve(oldData);
    						}
    					);

    				},
    				4000
    			);
    		}
    	);
    </aui:script>

The area contained within the light-blue rectangle is the point from which the 
predicted/forecasted values are shown:

![Figure 2: A predictive chart lets you visualize estimated future data alongside existing data.](../../../images/chart-taglib-predictive.png)

## Related Topics [](id=related-topics)

[Combination Charts](/develop/tutorials/-/knowledge_base/7-1/combination-charts)

[Geomap Charts](/develop/tutorials/-/knowledge_base/7-1/geomap-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)
