# Geomap Charts [](id=geomap-charts)

A Geomap Chart lets you visualize data based on geography, given a specified 
color range--a lighter color representing a lower rank and a darker a higher 
rank usually. The default configuration comes from the Clay charts 
[geomap component](https://github.com/liferay/clay/blob/develop/packages/clay-charts/src/Geomap.js#L244-L265):
which ranges from light-blue (#b1d4ff) to dark-blue (#0065e4) and ranks the 
geography based on the location's `pop_est` value (specified in the geomap's 
JSON file).

![Figure 1: A Geomap chart displays a heatmap representing the data.](../../../images/chart-taglib-geomap-default.png)

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

![Figure 2: Geomap charts can be customized to fit the look and feel you desire.](../../../images/chart-taglib-geomap-custom.png)

## Related Topics [](id=related-topics)

[Bar Charts](/develop/tutorials/-/knowledge_base/7-1/bar-charts)

[Gauge Charts](/develop/tutorials/-/knowledge_base/7-1/gauge-charts)

[Pie Charts](/develop/tutorials/-/knowledge_base/7-1/pie-charts)
