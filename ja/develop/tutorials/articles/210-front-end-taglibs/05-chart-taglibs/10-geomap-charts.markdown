---
header-id: geomap-charts
---

# ジオマップグラフ

[TOC levels=1-4]

ジオマップグラフでは、地形に基づくデータを指定の色範囲（通常、明るい色は低いランクを表し、暗い色は高いランクを表す）で視覚化できます。デフォルトの構成は、Clayグラフの[ジオマップコンポーネント](https://github.com/liferay/clay/blob/develop/packages/clay-charts/src/Geomap.js#L233-L276)に由来します。これは、水色（＃b1d4ff）から濃い青色（#0065e4）の範囲で、場所の`pop_est`値（ジオマップのJSONファイルで指定）に基づいて地形をランク付けします。

![図1：ジオマップグラフには、データを表すヒートマップが表示されます。](../../../images/chart-taglib-geomap-default.png)

このチュートリアルでは、ジオマップグラフを使用するようにポートレットを構成する方法を示します。次の手順を実行します。

1. Chart taglibを`GeomapConfig`、`GeomapColor`、および`GeomapColorRange`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.geomap.GeomapConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.geomap.GeomapColor" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.geomap.GeomapColorRange" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。以下の2番目の構成例`_geomapConfig2`に示すように、色（最小の色と最大の色）は完全に構成可能です。新しい`GeomapColorRange`を作成し、`setMax()`および`setMin()` メソッドで最小および最大の色の値を設定します。`setSelected()`メソッドを使用して、ハイライトの色（エリア上にマウスを置いたときに表示される色）を指定します 。`geomapColor.setValue()`メソッドを使用して、JSONプロパティを指定し、ジオマップのランキングを決定します。`setDataHREF()`メソッドでJSONファイルパスを指定します。次の例では、各場所の名前の長さに基づいてジオマップを表示しています。

       <%
       GeomapConfig _geomapConfig1 = new GeomapConfig();
       GeomapConfig _geomapConfig2 = new GeomapConfig();
       
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
       );
       
       %>
   
3. 次に示すように、サイズやマージンなどのジオマップのスタイル情報とともに`<chart>` taglibを`view.jsp`に追加します。

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
       config="<%= _geomapConfig1 %>"
       id="geomap-default-colors"
       />
       
       <chart:geomap
       config="<%= _geomapConfig2 %>"
       id="geomap-custom-colors"
       />
   
![図2：ジオマップグラフは、希望する外観と雰囲気に合わせてカスタマイズできます。](../../../images/chart-taglib-geomap-custom.png)

## 関連トピック

[Bar Charts](/docs/7-1/tutorials/-/knowledge_base/t/bar-charts)

[Gauge Charts](/docs/7-1/tutorials/-/knowledge_base/t/gauge-charts)

[Geomap Charts](/docs/7-1/tutorials/-/knowledge_base/t/geomap-charts)
