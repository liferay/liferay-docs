---
header-id: donut-charts
---

# ドーナツグラフ

[TOC levels=1-4]

ドーナツグラフはパーセンテージベースです。ドーナツグラフは円グラフに似ていますが、中央に穴があります。各データセットは、[`SingleValueColumn`オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html)の新しいインスタンスとして定義する必要があります。
このチュートリアルでは、ドーナツグラフを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`DonutChartConfig`および`SingleValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.percentage.donut.DonutChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       DonutChartConfig _donutChartConfig = new DonutChartConfig();
       
       _donutChartConfig.addColumns(
       new SingleValueColumn("data1", 30),
       new SingleValueColumn("data2", 70)
       );
       
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_donutChartConfig`を`config`属性の値として渡します。

       <chart:donut
       config="<%= _donutChartConfig %>"
       />
   
![図1：ドーナツグラフは円グラフに似ていますが、中央に穴があります。](../../../images/chart-taglib-donut.png)

## 関連トピック

[Donut Charts](/docs/7-1/tutorials/-/knowledge_base/t/donut-charts)

[Gauge Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Pie Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)
