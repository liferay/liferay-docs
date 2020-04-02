---
header-id: line-charts
---

# 折れ線グラフ

[TOC levels=1-4]

折れ線グラフには複数のデータセットが含まれます。折れ線グラフは、データを直線的に表示します。各データ系列（`addColumns()`メソッドで作成）は、IDと値のセットを取る[`MultiValueColumn` オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html)の新しいインスタンスで定義され ます。このチュートリアルでは、折れ線グラフを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`LineChartConfig`および`MultiValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.point.line.LineChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       LineChartConfig _lineChartConfig = new LineChartConfig();
       
       _lineChartConfig.addColumns(
       new MultiValueColumn("data1", 100, 20, 30),
       new MultiValueColumn("data2", 20, 70, 100));
       }
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_lineChartConfig`を`config`属性の値として渡します。

       <chart:line
       config="<%= _lineChartConfig %>"
       />
   
![図1：折れ線グラフは、データを直線的に表示します。](../../../images/chart-taglib-line.png)

## 関連トピック

[Bar Charts](/docs/7-1/tutorials/-/knowledge_base/t/bar-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)

[Step Charts](/docs/7-1/tutorials/-/knowledge_base/t/step-charts)
