---
header-id: scatter-charts
---

# 散布図

[TOC levels=1-4]

散布図には複数のデータセットが含まれます。散布図は、データを個々の点としてモデル化します。各データ系列（`addColumns()`メソッドで作成）は、IDと値のセットを取る[`MultiValueColumn` オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html)の新しいインスタンスで定義され ます。このチュートリアルでは、散布図を使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`ScatterChartConfig`および`MultiValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.point.scatter.ScatterChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       ScatterChartConfig _scatterChartConfig = new ScatterChartConfig();
       
       _scatterChartConfig.addColumns(
       new MultiValueColumn("data1", 100, 20, 30),
       new MultiValueColumn("data2", 20, 70, 100));
       }
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_scatterChartConfig`を`config`属性の値として渡します。

       <chart:scatter
       config="<%= _scatterChartConfig %>"
       />
   
![図1：散布図は、データを個々の点としてモデル化します。](../../../images/chart-taglib-scatter.png)

## 関連トピック

[Step Charts](/docs/7-1/tutorials/-/knowledge_base/t/step-charts)

[Line Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Spline Charts](/docs/7-1/tutorials/-/knowledge_base/t/spline-charts)
