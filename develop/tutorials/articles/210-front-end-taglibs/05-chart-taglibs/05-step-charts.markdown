# Step Charts [](id=step-charts)

Step charts contain multiple sets of data. A step chart steps between the points 
of data, resembling steps. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. 

Step chart:

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.point.step.StepChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initStepChartConfig();
        }

        public StepChartConfig StepChartConfig() {
          return _stepChartConfig;
        }

        private void _initStepChartConfig() {
          _stepChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private StepChartConfig _stepChartConfig = new StepChartConfig();

    }

JSP:

    <chart:step config="<%= chartSampleDisplayContext.getStepChartConfig() %>" id="step" />
    
![Figure 1: A step chart steps between the points of data, resembling steps.](../../../images/chart-taglib-step.png)

You can also use an area step chart if you prefer. An area step chart highlights 
the area covered by a step graph. 

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.area.step.AreaStepChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initAreaStepChartConfig();
        }

        public areaStepChartConfig getAreaStepChartConfig() {
          return _areaStepChartConfig;
        }

        private void _initAreaStepChartConfig() {
          _areaStepChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private AreaStepChartConfig _areaStepChartConfig = new AreaStepChartConfig();

    }

JSP:

    <chart:area-step config="<%= chartSampleDisplayContext.getAreaStepChartConfig() %>" id="area-step" />
    
![Figure 2: An area step chart highlights the area covered by a step graph.](../../../images/chart-taglib-area-step.png)

## Related Topics [](id=related-topics)

[Line Charts](/develop/tutorials/-/knowledge_base/7-1/line-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Spline Charts](/develop/tutorials/-/knowledge_base/7-1/spline-charts)
