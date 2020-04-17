---
header-id: combination-charts
---

# 複合グラフ

[TOC levels=1-4]

複合グラフは、他のグラフとは少し異なります。複合グラフでは、各データセットの表現型を定義する必要があります：`AREA`、`AREA_SPLINE`、`AREA_STEP`、`BAR`、`BUBBLE`、`DONUT`、`GAUGE`、`LINE`、`PIE`、`SCATTER`、`SPLINE`、または`STEP`複合グラフの各データセットは、`TypedMultiValueColumn`オブジェクトのインスタンスです。各オブジェクトは、データのID、表現型、および値を受け取ります。このチュートリアルでは、複合グラフを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`CombinationChartConfig`、`MultiValueColumn`および`MultiValueColumn.Type`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.combination.CombinationChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.TypedMultiValueColumn.Type" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       CombinationChartConfig _combinationChartConfig =
       new CombinationChartConfig();
       
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
       
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_combinationChartConfig`を`config`属性の値として渡します。

       <chart:combination
       config="<%= _combinationChartConfig %>"
       />
   
![図1：複合グラフには、さまざまなデータセットのタイプが表示されます。](../../../images/chart-taglib-combination.png)

## 関連トピック

[Bar Charts](/docs/7-1/tutorials/-/knowledge_base/t/bar-charts)

[Donut Charts](/docs/7-1/tutorials/-/knowledge_base/t/donut-charts)

[Pie Charts](/docs/7-1/tutorials/-/knowledge_base/t/pie-charts)
