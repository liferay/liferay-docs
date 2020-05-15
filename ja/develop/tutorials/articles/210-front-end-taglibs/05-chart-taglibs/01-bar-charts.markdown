---
header-id: bar-charts
---

# 棒グラフ

[TOC levels=1-4]

棒グラフには複数のデータセットが含まれます。棒グラフは、データを棒でモデル化します。
各データ系列（`addColumns()`メソッドで作成）は、IDと値のセットを取る[`MultiValueColumn` オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html)の新しいインスタンスで定義され ます。このチュートリアルでは、棒グラフを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`BarChartConfig`および`MultiValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.point.bar.BarChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       BarChartConfig _barChartConfig = new BarChartConfig();
       
       _barChartConfig.addColumns(
       new MultiValueColumn("data1", 100, 20, 30),
       new MultiValueColumn("data2", 20, 70, 100)
       );
       
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_barChartConfig`を`config`属性の値として渡します。

       <chart:bar
       config="<%= _barChartConfig %>"
       />
   
![図1：棒グラフは、データを棒でモデル化します。](../../../images/chart-taglib-bar.png)

## 関連トピック

[Line Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)

[Spline Charts](/docs/7-1/tutorials/-/knowledge_base/t/spline-charts)
