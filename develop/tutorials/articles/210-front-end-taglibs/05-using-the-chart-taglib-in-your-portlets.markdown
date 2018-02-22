# Using the Chart Taglib in Your Portlets [](id=using-the-chart-taglib-in-your-portlets)

Lines, splines, bars, pies and more, the Liferay Chart tag Library provides 
everything you need to model data in @product@. To use the Liferay-Chart taglib, 
add the following declaration to your JSP:

    <%@ taglib prefix="liferay-chart" uri="http://liferay.com/tld/chart" %>

The 
[`com.liferay.frontend.taglib.chart.sample.web` portlet](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-taglib/frontend-taglib-chart-sample-web) 
is referenced throughout this tutorial as an example. It's included 
out-of-the-box. Open the Control Menu and add the *Chart Sample* portlet from 
the *Applications* &rarr; *Sample* menu to see it in action. 

![Figure 1: The Chart Sample portlet demonstrates the various types of charts you can create with the chart taglbs.](../../images/chart-taglib-sample-portlet.png)

This tutorial covers the types of charts you can create with the Liferay Chart 
taglibs. Each section contains a set of chart examples along with sample Java 
data and a figure displaying the rendered results. 

## Bar, Line, Scatter, Spline, and Step Charts [](id=line-scatter-spline-and-step-charts)

The charts in this section contain multiple sets of data. Each data series 
(created with the `addColumns()` method) is defined with a new instance of the 
[`MultiValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html), 
which takes an ID and a set of values.

Bar chart:

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

Taglib:

    <chart:bar config="<%= chartSampleDisplayContext.getBarChartConfig() %>" id="bar" />
    
![Figure 2: A bar chart models the data in bars](../../images/chart-taglib-bar.png)

Line chart:

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

Taglib:

    <chart:line config="<%= chartSampleDisplayContext.getLineChartConfig() %>" id="line" />

![Figure 3: A Line chart displays the data linearly.](../../images/chart-taglib-line.png)

Scatter chart:

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

Taglib:

    <chart:scatter config="<%= chartSampleDisplayContext.getScatterChartConfig() %>" id="scatter" />

![Figure 4: A scatter chart models the data as individual points.](../../images/chart-taglib-scatter.png)

Spline chart:

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

Taglib:

    <chart:spline config="<%= chartSampleDisplayContext.getSplineChartConfig() %>" id="spline" />

![Figure 5: A spline chart connects the points of data with a smooth curve.](../../images/chart-taglib-spline.png)

Area Spline chart:

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

Taglib:

    <chart:area-spline config="<%= chartSampleDisplayContext.getAreaSplineChartConfig() %>" id="area-spline" />

![Figure 6: An area spline chart highlights the area under the spline curve.](../../images/chart-taglib-area-spline.png)

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

Taglib:

    <chart:step config="<%= chartSampleDisplayContext.getStepChartConfig() %>" id="step" />
    
![Figure 7: A step chart steps between the points of data, resembling steps.](../../images/chart-taglib-step.png)

Area Step chart:

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

Taglib:

    <chart:area-step config="<%= chartSampleDisplayContext.getAreaStepChartConfig() %>" id="area-step" />
    
![Figure 8: An area step chart highlights the area covered by the steps.](../../images/chart-taglib-area-step.png)

## Combination Charts [](id=bar-and-combination-charts)

Combination charts are configured slightly different from other charts. In a 
combination chart, you must define the representation type of each data set: 
`AREA`, `AREA_SPLINE`, `AREA_STEP`, `BAR`, `BUBBLE`, `DONUT`, `GAUGE`, `LINE`, 
`PIE`, `SCATTER`, `SPLINE`, or `STEP`. Each data set in a combination chart is 
an instance of the `TypedMultiValueColumn` object. Each object receives an ID, 
the representation type, and values for the data. In the example below, data 
series `data1` and `data2` are displayed together with the `addGroup()` method. 

Combination chart:

Java sample data:

    import com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn;
    import com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn.Type;
    import com.liferay.frontend.taglib.chart.model.combination.CombinationChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initCombinationChartConfig();
        }

        public combinationChartConfig getCombinationChartConfig() {
          return _combinationChartConfig;
        }

        private void _initCombinationChartConfig() {
          _combinationChartConfig.addColumns(
            new TypedMultiValueColumn(
              "data1", Type.BAR, 30, 20, 50, 40, 60, 50),
            new TypedMultiValueColumn(
              "data2", Type.BAR, 200, 130, 90, 240, 130, 220),
            new TypedMultiValueColumn(
              "data3", Type.SPLINE, 300, 200, 160, 400, 250, 250),
            new TypedMultiValueColumn(
              "data4", Type.LINE, 200, 130, 90, 240, 130, 220),
            new TypedMultiValueColumn(
              "data5", Type.BAR, 130, 120, 150, 140, 160, 150),
            new TypedMultiValueColumn(
              "data6", Type.AREA, 90, 70, 20, 50, 60, 120));

          _combinationChartConfig.addGroup("data1", "data2");
        }
        
        private CombinationChartConfig _combinationChartConfig = new CombinationChartConfig();

    }

Taglib:

    <chart:combination config="<%= chartSampleDisplayContext.getCombinationChartConfig() %>" id="combination" />
    
![Figure 9:](../../images/chart-taglib-combination.png)

## Donut, Gauge, and Pie Charts [](id=donut-gauge-and-pie-charts)

Percentage-based charts (i.e. donut, gauge, and pie charts) must define each 
data set as a new instance of the 
[`SingleValueColumn` object](@app-ref@/foundation/latest/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html).

Donut chart:

Java sample data:

    import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
    import com.liferay.frontend.taglib.chart.model.percentage.donut.DonutChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initDonutChartConfig();
        }

        public donutChartConfig getDonutChartConfig() {
          return _donutChartConfig;
        }

        private void _initDonutChartConfig() {
          _donutChartConfig.addColumns(
            new SingleValueColumn("data1", 30),
            new SingleValueColumn("data2", 70));
        }
        
        private DonutChartConfig _donutChartConfig = new DonutChartConfig();

    }

Taglib:

    <chart:donut config="<%= chartSampleDisplayContext.getDonutChartConfig() %>" id="donut" />
    
![Figure 10:](../../images/chart-taglib-donut.png)

Gauge chart:

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

Taglib:

    <chart:gauge config="<%= chartSampleDisplayContext.getGaugeChartConfig() %>" id="gauge" />
    
![Figure 11:](../../images/chart-taglib-guage.png)

Pie chart:

Java sample data:

    import com.liferay.frontend.taglib.chart.model.SingleValueColumn;
    import com.liferay.frontend.taglib.chart.model.percentage.pie.PieChartConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initPieChartConfig();
        }

        public pieChartConfig getPieChartConfig() {
          return _pieChartConfig;
        }

        private void _initPieChartConfig() {
          _pieChartConfig.addColumns(
            new SingleValueColumn("data1", 30),
            new SingleValueColumn("data2", 70));
        }
        
        private PieChartConfig _pieChartConfig = new PieChartConfig();

    }

Taglib:

    <chart:pie config="<%= chartSampleDisplayContext.getPieChartConfig() %>" id="pie" />
    
![Figure 12:](../../images/chart-taglib-pie.png)

Now you know how to use Chart taglibs to model data in @product@!

## Related topics

[Using Clay Taglibs in Your Portlet](/develop/tutorials/-/knowledge_base/7-1/using-the-clay-taglib-in-your-portlets)
