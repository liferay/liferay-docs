# Geomap Charts [](id=geomap-charts)

A Geomap Chart lets you visualize data based on geography, given a specified 
color range---a lighter color representing a lower rank and a darker a higher 
rank usually. The default configuration comes from the Clay charts 
[geomap component](https://github.com/liferay/clay/blob/develop/packages/clay-charts/src/Geomap.js#L233-L276):
which ranges from light-blue (#b1d4ff) to dark-blue (#0065e4) and ranks the 
geography based on the location's `pop_est` value (specified in the geomap's 
JSON file).

![Figure 1: A Geomap chart displays a heatmap representing the data.](../../../images/chart-taglib-geomap-default.png)

This tutorial shows how to configure your portlet to use geomap charts. Follow 
these steps:

1.  [Create an MVC portlet project](/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

2.  Create an additional *ChartDisplayContext* class and add the following 
    contents to it:

        import com.liferay.frontend.taglib.chart.model.geomap.GeomapColor;
        import com.liferay.frontend.taglib.chart.model.geomap.GeomapColorRange;
        import com.liferay.frontend.taglib.chart.model.geomap.GeomapConfig;

        public class GeomapChartSampleDisplayContext {

            public GeomapChartSampleDisplayContext(PortletRequest portletRequest) {
              _initGeomapConfig();
              _portletRequest = portletRequest;
            }

            public GeomapConfig getGeomapConfig1() {
          		return _geomapConfig1;
          	}
          
          	public GeomapConfig getGeomapConfig2() {
          		return _geomapConfig2;
          	}
            
        }

3.  Add an `_initGeomapConfig()` method to provide the configuration for your 
    geomap. The colors---a color for minimum and a color for maximum---are 
    completely configurable, as shown in the second example configuration below: 
    `_geomapConfig2`. Create a new `GeomapColorRange` and set the minimum and 
    maximum color values with the `setMax()` and `setMin()` methods. Specify the 
    highlight color---the color displayed when you mouse over an area---with the 
    `setSelected()` method:

            private void _initGeomapConfig() {
              GeomapColor geomapColor = new GeomapColor();
          		GeomapColorRange geomapColorRange = new GeomapColorRange();
          
          		geomapColorRange.setMax("#b2150a");
          		geomapColorRange.setMin("#ee3e32");
          
          
          		geomapColor.setGeomapColorRange(geomapColorRange);

          		geomapColor.setSelected("#a9615c");
              
4.  Still inside the `_initGeomapConfig()` method, use the 
    `geomapColor.setValue()` method to specify the JSON property to determine 
    the geomap's ranking. Specify the JSON filepath with the `setDataHREF()` 
    method. The example below displays a geomap based on the length of each 
    location's name:
              
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

5.  Open the *Portlet* class and override the `doView()` method to create a new 
    instance of the *DisplayContext*:

       import com.liferay.docs.mvcportlet.portlet.GeomapChartSampleDisplayContext;
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
           GeomapChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT,
           new GeomapChartSampleDisplayContext(portletRequest));

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
       <%@ page import="com.liferay.docs.mvcportlet.portlet.GeomapChartSampleDisplayContext" %>
       <%@ page import="com.liferay.docs.mvcportlet.constants.GeomapChartPortletKeys" %>

9.  Add the following Java scriptlet to the bottom of your `init.jsp`:

       <%
       GeomapChartSampleDisplayContext geomapChartSampleDisplayContext =
       (GeomapChartSampleDisplayContext)request.getAttribute(
         GeomapChartPortletKeys.CHART_SAMPLE_DISPLAY_CONTEXT
       );
       %>

10.  Add the `<chart>` taglib to the `view.jsp` along with any styling 
     information for the geomap, such as the size and margins as shown below:

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
        	config="<%= geomapChartSampleDisplayContext.getGeomapConfig1() %>"
        	id="geomap-default-colors"
        />

        <chart:geomap
        	config="<%= geomapChartSampleDisplayContext.getGeomapConfig2() %>"
        	id="geomap-custom-colors"
        />

![Figure 2: Geomap charts can be customized to fit the look and feel you desire.](../../../images/chart-taglib-geomap-custom.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Gauge Charts](/develop/tutorials/-/knowledge_base/7-1/gauge-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/pie-charts)
