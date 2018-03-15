# Using the Chart Taglib in Your Portlets [](id=using-the-chart-taglib-in-your-portlets)

Lines, splines, bars, pies and more, the Liferay Chart tag Library provides 
everything you need to model data. Each taglib gives you access to the
corresponding 
[Clay component](https://github.com/liferay/clay/tree/develop/packages). These 
components contain the default configuration for the UI.

To use the Liferay-Chart taglib, add the following declaration to your JSP:

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

JSP:

    <chart:bar config="<%= chartSampleDisplayContext.getBarChartConfig() %>" id="bar" />
 
![Figure 2: A bar chart models the data in bars.](../../images/chart-taglib-bar.png)

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

JSP:

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

JSP:

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

JSP:

    <chart:spline config="<%= chartSampleDisplayContext.getSplineChartConfig() %>" id="spline" />

![Figure 5: A spline chart connects points of data with a smooth curve.](../../images/chart-taglib-spline.png)

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

JSP:

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

JSP:

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

JSP:

    <chart:area-step config="<%= chartSampleDisplayContext.getAreaStepChartConfig() %>" id="area-step" />
    
![Figure 8: An area step chart highlights the area covered by a step graph.](../../images/chart-taglib-area-step.png)

## Combination Charts [](id=bar-and-combination-charts)

Combination charts have minor differences from other charts. In a combination
chart, you must define the representation type of each data set: `AREA`,
`AREA_SPLINE`, `AREA_STEP`, `BAR`, `BUBBLE`, `DONUT`, `GAUGE`, `LINE`, `PIE`,
`SCATTER`, `SPLINE`, or `STEP`. Each data set in a combination chart is an
instance of the `TypedMultiValueColumn` object. Each object receives an ID, the
representation type, and values for the data. In the example below, data series
`data1` and `data2` are displayed together with the `addGroup()` method. 

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

JSP:

    <chart:combination config="<%= chartSampleDisplayContext.getCombinationChartConfig() %>" id="combination" />
    
![Figure 9: A combination chart displays a variety of data set types.](../../images/chart-taglib-combination.png)

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

JSP:

    <chart:donut config="<%= chartSampleDisplayContext.getDonutChartConfig() %>" id="donut" />
    
![Figure 10: A donut chart is similar to a pie chart, but it has a hole in the center.](../../images/chart-taglib-donut.png)

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

JSP:

    <chart:gauge config="<%= chartSampleDisplayContext.getGaugeChartConfig() %>" id="gauge" />
    
![Figure 11: A gauge chart shows where percentage-based data falls over a given range.](../../images/chart-taglib-gauge.png)

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

JSP:

    <chart:pie config="<%= chartSampleDisplayContext.getPieChartConfig() %>" id="pie" />

![Figure 12: A pie chart models percentage-based data as individual slices of pie.](../../images/chart-taglib-pie.png)

## Geomap Charts [](id=geomap-charts)

A Geomap Chart lets you visualize data based on geography, given a specified 
color range--a lighter color representing a lower rank and a darker a higher 
rank usually. The default configuration comes from the Clay charts 
[geomap component](https://github.com/liferay/clay/blob/develop/packages/clay-charts/src/Geomap.js#L244-L265):
which ranges from light-blue (#b1d4ff) to dark-blue (#0065e4) and ranks the 
geography based on the location's `pop_est` value (specified in the geomap's 
JSON file).

![Figure 13: A Geomap chart displays a heatmap representing the data.](../../images/chart-taglib-geomap-default.png)

These colors--a color for minimum and a color for maximum--are completely 
configurable, as shown in the example class below for `_geomapConfig2`. The 
geomap colors are defined as a `GeomapColorRange` with a minimum and maximum 
color value. When you mouse over an area, it is highlighted with the color 
specified with the `setSelected()` method. 

The example below displays a geomap based on the length of each location's 
name, as specified with the line `geomapColor.setValue("name_len");`. The 
`setValue()` method defines which JSON property is applied to the geomap. The 
JSON filepath is specified with the `setDataHREF()` method.

Java sample data:

    import com.liferay.frontend.taglib.chart.model.geomap.GeomapColor;
    import com.liferay.frontend.taglib.chart.model.geomap.GeomapColorRange;
    import com.liferay.frontend.taglib.chart.model.geomap.GeomapConfig;

    public class ChartSampleDisplayContext {

        public ChartSampleDisplayContext() {
          _initGeomapConfig();
        }

        public GeomapConfig getGeomapConfig1() {
      		return _geomapConfig1;
      	}
      
      	public GeomapConfig getGeomapConfig2() {
      		return _geomapConfig2;
      	}

        private void _initGeomapConfig() {
          GeomapColor geomapColor = new GeomapColor();
      		GeomapColorRange geomapColorRange = new GeomapColorRange();
      
      		geomapColorRange.setMax("#b2150a");
      		geomapColorRange.setMin("#ee3e32");
      
      
      		geomapColor.setGeomapColorRange(geomapColorRange);

      		geomapColor.setSelected("#a9615c");
      		geomapColor.setValue("name_len");
      
      		_geomapConfig2.setColor(geomapColor);
      
      		StringBuilder sb = new StringBuilder();
      
      		sb.append(_portletRequest.getScheme());
      		sb.append(StringPool.COLON);
      		sb.append(StringPool.SLASH);
      		sb.append(StringPool.SLASH);
      		sb.append(_portletRequest.getServerName());
      		sb.append(StringPool.COLON);
      		sb.append(_portletRequest.getServerPort());
      		sb.append(_portletRequest.getContextPath());
      		sb.append(StringPool.SLASH);
      		sb.append("geomap.geo.json");
      
      		_geomapConfig1.setDataHREF(sb.toString());
      		_geomapConfig2.setDataHREF(sb.toString());
      	}
        
        private GeomapConfig _geomapConfig1 = new GeomapConfig();
      	private GeomapConfig _geomapConfig2 = new GeomapConfig();
        private final PortletRequest _portletRequest;

    }
    
The JSP can contain basic styling information for the geomap, such as the size 
and margins as shown below:

    <style type="text/css">
    	.geomap {
    		margin: 10px 0 10px 0;
    	}
    	.geomap svg {
    		width: 100%;
    		height: 500px !important;
    	}
    </style>

    <chart:geomap
    	config="<%= chartSampleDisplayContext.getGeomapConfig1() %>"
    	id="geomap-default-colors"
    />

    <chart:geomap
  		config="<%= chartSampleDisplayContext.getGeomapConfig2() %>"
  		id="geomap-custom-colors"
    />

![Figure 14: Geomap charts can be customized to fit the look and feel you desire.](../../images/chart-taglib-geomap-custom.png)

Now you know how to use Chart taglibs to model data in @product@!

## Related topics [](id=related-topics)

[Using Clay Taglibs in Your Portlet](/develop/tutorials/-/knowledge_base/7-1/using-the-clay-taglib-in-your-portlets)
