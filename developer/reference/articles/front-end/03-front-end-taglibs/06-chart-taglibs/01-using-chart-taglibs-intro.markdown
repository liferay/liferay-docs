---
header-id: using-the-chart-taglib-in-your-portlets
---

# Using the Chart Taglib in Your Portlets

[TOC levels=1-4]

Lines, splines, bars, pies and more, the Chart tag Library provides everything 
you need to model data. Each taglib gives you access to the corresponding 
[Clay component](https://github.com/liferay/clay/tree/develop/packages/clay-charts/src). 
These components contain the default configuration for the UI.

To use the Chart taglib in your apps, add the following declaration to your JSP:

```markup
<%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
```

This section covers the types of charts you can create with the Chart taglibs. 
Each article contains a set of chart examples along with sample Java data and a 
figure displaying the rendered results. 

![Figure 1: You can create many different types of charts with the chart taglibs.](../../../../images/chart-taglib-sample-portlet.png)
