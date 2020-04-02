---
header-id: pie-charts
---

# 円グラフ

[TOC levels=1-4]

円グラフはパーセンテージベースです。円グラフは、パーセンテージベースのデータを円の個々のスライスとしてモデル化します。各データセットは、[`SingleValueColumn`オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/SingleValueColumn.html)の新しいインスタンスとして定義する必要があります。
このチュートリアルでは、円グラフを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`PieChartConfig`および`SingleValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.percentage.pie.PieChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.SingleValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       PieChartConfig _pieChartConfig = new PieChartConfig();
       
       _pieChartConfig.addColumn(
       new SingleValueColumn("data1", 85.4)
       );
       
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_pieChartConfig`を`config`属性の値として渡します。

       <chart:pie
       config="<%= _pieChartConfig %>"
       />
   
![図1：円グラフは、パーセンテージベースのデータを円の個々のスライスとしてモデル化します。](../../../images/chart-taglib-pie.png)

## 関連トピック

[Donut Charts](/docs/7-1/tutorials/-/knowledge_base/t/donut-charts)

[Pie Charts](/docs/7-1/tutorials/-/knowledge_base/t/pie-charts)

[Using Clay Taglibs in Your Portlet](/docs/7-1/tutorials/-/knowledge_base/t/using-the-clay-taglib-in-your-portlets)
