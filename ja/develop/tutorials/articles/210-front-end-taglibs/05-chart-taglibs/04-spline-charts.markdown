---
header-id: spline-charts
---

# スプライングラフ

[TOC levels=1-4]

スプライングラフには複数のデータセットが含まれます。スプライングラフは、データの点を滑らかな曲線で接続します。各データ系列（`addColumns()`メソッドで作成）は、IDと値のセットを取る[`MultiValueColumn` オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html)の新しいインスタンスで定義され ます。このチュートリアルでは、スプライングラフを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`SplineChartConfig`および`MultiValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.point.spline.SplineChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       SplineChartConfig _splineChartConfig = new SplineChartConfig();
       
       _splineChartConfig.addColumns(
       new MultiValueColumn("data1", 100, 20, 30),
       new MultiValueColumn("data2", 20, 70, 100));
       }
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_splineChartConfig`を`config`属性の値として渡します。

       <chart:spline
       config="<%= _splineChartConfig %>"
       />
   
![図1：スプライングラフは、データの点を滑らかな曲線で接続します。](../../../images/chart-taglib-spline.png)

必要に応じて、エリアスプライングラフを使用することもできます。エリアスプライングラフは、スプライン曲線の下のエリアを強調表示します。

    <chart:area-spline
    config="<%= _splineChartConfig %>"
    />

![図2：エリアスプライングラフは、スプライン曲線の下のエリアを強調表示します。](../../../images/chart-taglib-area-spline.png)

## 関連トピック

[Bar Charts](/docs/7-1/tutorials/-/knowledge_base/t/bar-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)

[Step Charts](/docs/7-1/tutorials/-/knowledge_base/t/step-charts)
