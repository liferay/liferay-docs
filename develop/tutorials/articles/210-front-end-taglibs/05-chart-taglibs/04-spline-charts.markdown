# Spline Charts [](id=spline-charts)

Spline charts contain multiple sets of data. A spline chart connects points of 
data with a smooth curve. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values. 

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.point.spline.SplineChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initSplineChartConfig();
        }

        public splineChartConfig getSplineChartConfig() {
          return _splineChartConfig;
        }

        private void _initSplineChartConfig() {
          _splineChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private SplineChartConfig _splineChartConfig = new SplineChartConfig();

    }

JSP:

    <chart:spline config="<%= chartSampleDisplayContext.getSplineChartConfig() %>" id="spline" />

![Figure 1: A spline chart connects points of data with a smooth curve.](../../../images/chart-taglib-spline.png)

You can also use an area spline chart if you prefer. An area spline chart 
highlights the area under the spline curve.

Java sample data:

    import com.liferay.frontend.taglib.chart.model.MultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.area.spline.AreaSplineChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initAreaSplineChartConfig();
        }

        public areaSplineChartConfig getAreaSplineChartConfig() {
          return _areaSplineChartConfig;
        }

        private void _initAreaSplineChartConfig() {
          _areaSplineChartConfig.addColumns(
            new MultiValueColumn("data1", 100, 20, 30),
            new MultiValueColumn("data2", 20, 70, 100));
        }
        
        private AreaSplineChartConfig _areaSplineChartConfig = new AreaSplineChartConfig();

    }

JSP:

    <chart:area-spline config="<%= chartSampleDisplayContext.getAreaSplineChartConfig() %>" id="area-spline" />

![Figure 2: An area spline chart highlights the area under the spline curve.](../../../images/chart-taglib-area-spline.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Scatter Charts](/develop/tutorials/-/knowledge_base/7-1/scatter-charts)

[Step Charts](/develop/tutorials/-/knowledge_base/7-1/step-charts)
