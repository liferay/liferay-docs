# Using the Chart Taglib in Your Portlets [](id=using-the-chart-taglib-in-your-portlets)

Lines, splines, bars, pies and more, the Liferay Chart tag Library provides 
everything you need to model data. Each taglib gives you access to the
corresponding 
[Clay component](https://github.com/liferay/clay/tree/develop/packages). These 
components contain the default configuration for the UI.

To use the Liferay-Chart taglib, add the following declaration to your JSP:

    <%@ taglib prefix="liferay-chart" uri="http://liferay.com/tld/chart" %>

The 
[`com.liferay.frontend.taglib.chart.sample.web` portlet](https://github.com/liferay/liferay-portal/tree/master/modules/apps/frontend-taglib/frontend-taglib-chart-sample-web) 
is referenced throughout this tutorial as an example. It's included 
out-of-the-box. Open the Control Menu and add the *Chart Sample* portlet from 
the *Applications* &rarr; *Sample* menu to see it in action. 

![Figure 1: The Chart Sample portlet demonstrates the various types of charts you can create with the chart taglbs.](../../../images/chart-taglib-sample-portlet.png)

This section of tutorials covers the types of charts you can create with the 
Liferay Chart taglibs. Each tutorial contains a set of chart examples along with 
sample Java data and a figure displaying the rendered results.
