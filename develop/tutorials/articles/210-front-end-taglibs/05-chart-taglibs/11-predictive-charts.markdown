# Predictive Charts [](id=predictive-charts)

Predictive charts let you visualize current data along with predicted/forecasted 
data within a given value range. 

![Figure 1: Predicted/forecasted data is surrounded by a highlighted area of possible values.](../../../images/chart-taglib-predictive-value-range.png)

This tutorial shows how to configure your portlet to use predictive charts. 
Follow these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
    contents to it:

        import com.liferay.frontend.taglib.chart.model.MixedDataColumn;
        import com.liferay.frontend.taglib.chart.model.predictive.PredictiveChartConfig;

        public class PredictiveChartSampleDisplayContext {

          public PredictiveChartSampleDisplayContext(PortletRequest portletRequest) {
            _initPredictiveChartConfig();
            _portletRequest = portletRequest;
          }

          public PredictiveChartConfig getPredictiveChartConfig() {
            return _predictiveChartConfig;
          }
          
        }

3.  Add an `_initPredictiveChartConfig()` method to provide the configuration 
    for your predictive chart. Create a new instance of the 
    [`MixedDataColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MixedDataColumn.html)
    ---a column that supports both single number values and arrays of three 
    numbers---for each data series. Single number values define existing data. 
    Arrays of numbers are used as the prediction/forecast data and contain three 
    numbers: a minimum value, an estimated value, and a maximum value. The 
    estimated value is rendered solid and surrounded by a highlighted area with 
    borders specified by the minimum and maximum values. This lets you visualize 
    your estimated values, while also giving you an idea of the possible value 
    ranges. Use the `addDataColumn()` method to add each data series:

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

4.  Still inside the `_initPredictiveChartConfig()` method, specify the 
    following properties:
   
   **axisXTickFormat:** An optional string which specfies the time formatting 
   on the X axis. For more information on which formats can be specified please 
   refer to 
   [d3's time format README](https://github.com/d3/d3-time-format/blob/master/README.md#locale_format). 
   This value is set using the `setAxisXTickFormat()` method. 
   
   **Prediction Date:** A date as a string that represents the point in the 
   timeline from when the forecast/prediction is shown. This value is parsed as 
   a Date object in JavaScript and set using the `setPredictionDate()` method. 
   
   **Time Series:** A timeline for the data which is displayed on the X axis of 
   the chart. This value is set as an array of dates (`2018-01-01` for example). 
   
   An example configuration is shown below:

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
         private final PortletRequest _portletRequest;

        }

5.  Open the *Portlet* class and override the `doView()` method to create a new 
    instance of the *DisplayContext*:

       import com.liferay.docs.mvcportlet.portlet.PredictiveChartSampleDisplayContext;
       import com.liferay.portal.kernel.util.JavaConstants;
       import java.io.IOException;
       import javax.portlet.PortletException;
       import javax.portlet.PortletRequest;
       import javax.portlet.RenderRequest;
       import javax.portlet.RenderResponse;

       @Override
       public void doView(
           RenderRequest renderRequest, RenderResponse renderResponse)
         throws IOException, PortletException {

         PortletRequest portletRequest =
           (PortletRequest)renderRequest.getAttribute(
             JavaConstants.JAVAX_PORTLET_REQUEST);

         renderRequest.setAttribute(
           PredictiveChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
           new PredictiveChartSampleDisplayContext(portletRequest));

         super.doView(renderRequest, renderResponse);
       }

6.  Add the following String to the generated *PortletKeys* class:

       public static final String CHART_SAMPLE_DISPLAY_CONTEXT =
         "CHART_SAMPLE_DISPLAY_CONTEXT";

7.  Add the following dependency to your bundle's `build.gradle` file:

       compileOnly group: "com.liferay",
       name: "com.liferay.frontend.taglib.chart",
       version: "1.0.9"

8.  Import the chart taglib along with your portlet's display context and 
   portlet keys into your bundle's `init.jsp` file:

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.docs.mvcportlet.portlet.PredictiveChartSampleDisplayContext" %>
       <%@ page import="com.liferay.docs.mvcportlet.constants.PredictiveChartPortletKeys" %>

9.  Add the following Java scriptlet to the bottom of your `init.jsp`:

       <%
       PredictiveChartSampleDisplayContext predictiveChartSampleDisplayContext =
       (PredictiveChartSampleDisplayContext)request.getAttribute(
         PredictiveChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
       );
       %>

10.  Add the `<chart>` taglib to the `view.jsp`. An example configuration is 
     shown below:

        <chart:predictive
         componentId="predictive-chart"
         config="<%= predictiveChartSampleDisplayContext.getPredictiveChartConfig() %>"
         id="predictive-chart"
        />

11.  Finally, this example adds data via an `aui:script`. It references the 
     chart by its component ID in JavaScript, using the 
     `Liferay.componentReady()` method. You can use this to add data after the 
     chart is loaded:

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
