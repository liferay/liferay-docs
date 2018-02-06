# Using the Chart Taglib in Your Portlets [](id=using-the-chart-taglib-in-your-portlets)

Lines, splines, bars, pies and more, the Liferay Chart tag Library provides 
everything you need to model data in @product@. To use the Liferay-Chart taglib, 
add the following declaration to your JSP:

    <%@ taglib prefix="liferay-chart" uri="http://liferay.com/tld/chart" %>

The 
[`com.liferay.frontend.taglib.chart.sample.web` portlet](https://github.com/liferay/liferay-portal/tree/master/modules/apps/foundation/frontend-taglib/frontend-taglib-chart-sample-web) 
is referenced throughout this tutorial as an example. It's included 
out-of-the-box with @product@. Open the Control Menu and add the 
*Chart Sample* portlet from the *Applications* &rarr; *Sample* menu to see it in 
action.

![Figure 1: The Chart Sample portlet demonstrates the various types of charts you can create with the chart taglbs.](../../images/chart-taglib-sample-portlet.png)

This tutorial covers the types of charts you can create with the Liferay Chart 
taglibs. Each section contains a set of chart examples along with sample Java 
data and a figure displaying the rendered results.

## Bar, Line, Scatter, Spline, and Step Charts [](id=line-scatter-spline-and-step-charts)

The example charts in this section use the Sample Java data below:

    <%
    List<Map<String, Object>> linearData = new ArrayList<>();

    Map<String, Object> linearDataCol1 = new HashMap<>();

    int[] linearDataCol1Data = {100, 20, 30};

    linearDataCol1.put("data", linearDataCol1Data);

    linearDataCol1.put("id", "data1");

    linearData.add(linearDataCol1);

    Map<String, Object> linearDataCol2 = new HashMap<>();

    int[] linearDataCol2Data = {20, 70, 100};

    linearDataCol2.put("data", linearDataCol2Data);

    linearDataCol2.put("id", "data2");

    linearData.add(linearDataCol2);
    %>

Bar chart:

    <chart:bar columns="<%= linearData %>" id="bar" />
    
![Figure 2: A bar chart models the data in bars](../../images/chart-taglib-bar.png)

<!-- Can this be vertical or horizontal? Default appears to be vertical -->

Line graph:

    <chart:line columns="<%= linearData %>" id="line" />

![Figure 3: A Line graph displays the data linearly.](../../images/chart-taglib-line.png)

Scatter graph:

    <chart:scatter columns="<%= linearData %>" id="scatter" />

![Figure 4: A scatter chart models the data as individual points.](../../images/chart-taglib-scatter.png)

Spline graph:

    <chart:spline columns="<%= linearData %>" id="spline" />

![Figure 5: A spline chart connects the points of data with a smooth curve.](../../images/chart-taglib-spline.png)

Step  graph:

    <chart:step columns="<%= linearData %>" id="step" />
    
![Figure 6: A step chart steps between the points of data, resembling steps.](../../images/chart-taglib-step.png)

## Combination Charts [](id=bar-and-combination-charts)

The example chart in this section uses the Sample Java data below:

    <%
    List<Map<String, Object>> combinationData = new ArrayList<>();

    Map<String, Object> combinationDataCol1 = new HashMap<>();

    int[] combinationDataCol1Data = {30, 20, 50, 40, 60, 50};

    combinationDataCol1.put("data", combinationDataCol1Data);

    combinationDataCol1.put("id", "data1");
    combinationDataCol1.put("type", "bar");

    combinationData.add(combinationDataCol1);

    Map<String, Object> combinationDataCol2 = new HashMap<>();

    int[] combinationDataCol2Data = {200, 130, 90, 240, 130, 220};

    combinationDataCol2.put("data", combinationDataCol2Data);

    combinationDataCol2.put("id", "data2");
    combinationDataCol2.put("type", "bar");

    combinationData.add(combinationDataCol2);

    Map<String, Object> combinationDataCol3 = new HashMap<>();

    int[] combinationDataCol3Data = {300, 200, 160, 400, 250, 250};

    combinationDataCol3.put("data", combinationDataCol3Data);

    combinationDataCol3.put("id", "data3");
    combinationDataCol3.put("type", "spline");

    combinationData.add(combinationDataCol3);

    Map<String, Object> combinationDataCol4 = new HashMap<>();

    int[] combinationDataCol4Data = {200, 130, 90, 240, 130, 220};

    combinationDataCol4.put("data", combinationDataCol4Data);

    combinationDataCol4.put("id", "data4");
    combinationDataCol4.put("type", "line");

    combinationData.add(combinationDataCol4);

    Map<String, Object> combinationDataCol5 = new HashMap<>();

    int[] combinationDataCol5Data = {130, 120, 150, 140, 160, 150};

    combinationDataCol5.put("data", combinationDataCol5Data);

    combinationDataCol5.put("id", "data5");
    combinationDataCol5.put("type", "bar");

    combinationData.add(combinationDataCol5);

    Map<String, Object> combinationDataCol6 = new HashMap<>();

    int[] combinationDataCol6Data = {90, 70, 20, 50, 60, 120};

    combinationDataCol6.put("data", combinationDataCol6Data);

    combinationDataCol6.put("id", "data6");
    combinationDataCol6.put("type", "area");

    combinationData.add(combinationDataCol6);

    String[] combinationGroup = {"data1", "data2"};

    List<String[]> combinationGroups = new ArrayList<>();

    combinationGroups.add(combinationGroup);
    %>

Combination chart:

    <chart:combination columns="<%= combinationData %>" groups="<%= combinationGroups %>" id="bar" />
    
![Figure 7:](../../images/chart-taglib-combination.png)

## Donut and Pie Charts [](id=donut-and-pie-charts)

The example charts in this section use the Sample Java data below:

    <%
    List<Map<String, Object>> percentageData = new ArrayList<>();

    Map<String, Object> percentageDataCol1 = new HashMap<>();

    int[] percentageDataCol1Data = {30};

    percentageDataCol1.put("data", percentageDataCol1Data);

    percentageDataCol1.put("id", "data1");

    percentageData.add(percentageDataCol1);

    Map<String, Object> percentageDataCol2 = new HashMap<>();

    int[] percentageDataCol2Data = {70};

    percentageDataCol2.put("data", percentageDataCol2Data);

    percentageDataCol2.put("id", "data2");

    percentageData.add(percentageDataCol2);
    %>

Donut chart:

    <chart:donut columns="<%= percentageData %>" id="donut" />
    
![Figure 8:](../../images/chart-taglib-donut.png)

Pie chart:

    <chart:pie columns="<%= percentageData %>" id="pie" />
    
![Figure 9:](../../images/chart-taglib-pie.png)

## Gauge Charts [](id=gauge-charts)

The example chart in this section uses the Sample Java data below:

    <%
    List<Map<String, Object>> gaugeData = new ArrayList<>();

    Map<String, Object> gaugeDataCol1 = new HashMap<>();

    double[] gaugeDataCol1Data = {85.4};

    gaugeDataCol1.put("data", gaugeDataCol1Data);

    gaugeDataCol1.put("id", "data1");

    gaugeData.add(gaugeDataCol1);
    %>

Gauge chart:

    <chart:gauge columns="<%= gaugeData %>" id="gauge" />
    
![Figure 10:](../../images/chart-taglib-guage.png)

Now you know how to use Chart taglibs to model data in @product@!

## Related topics

[Using Clay Taglibs in Your Portlet]()
