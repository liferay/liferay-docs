---
header-id: gauge-charts
---

# ゲージチャート

[TOC levels=1-4]

ゲージチャートはパーセンテージベースです。ゲージチャートは、パーセンテージベースのデータが指定された範囲のどこまで到達しているかを示します。各データセットは、[`SingleValueColumn`オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html)の新しいインスタンスとして定義する必要があります。
このチュートリアルでは、ゲージチャートを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`GaugeChartConfig`および`SingleValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.gauge.GaugeChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       GaugeChartConfig _gaugeChartConfig = new GaugeChartConfig();
       
       _gaugeChartConfig.addColumn(
       new SingleValueColumn("data1", 85.4)
       );
       
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_gaugeChartConfig`を`config`属性の値として渡します。

       <chart:gauge
       config="<%= _gaugeChartConfig %>"
       />
   
![図1：ゲージチャートは、パーセンテージベースのデータが指定された範囲のどこまで到達しているかを示します。](../../../images/chart-taglib-gauge.png)

## 関連トピック

[Gauge Charts](/docs/7-1/tutorials/-/knowledge_base/t/gauge-charts)

[Geomap Charts](/docs/7-1/tutorials/-/knowledge_base/t/geomap-charts)

[Pie Charts](/docs/7-1/tutorials/-/knowledge_base/t/pie-charts)
