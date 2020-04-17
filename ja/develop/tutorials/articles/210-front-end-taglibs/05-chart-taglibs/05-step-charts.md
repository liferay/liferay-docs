---
header-id: step-charts
---

# ステップチャート

[TOC levels=1-4]

ステップチャートには複数のデータセットが含まれます。ステップチャートは、データのポイント間に段差があることから、階段に似ています。各データ系列（`addColumns()`メソッドで作成）は、IDと値のセットを取る[`MultiValueColumn` オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MultiValueColumn.html)の新しいインスタンスで定義され ます。このチュートリアルでは、ステップチャートを使用するようにポートレットを構成する方法を示します。

次の手順を実行します。

1. Chart taglibを`StepChartConfig`および`MultiValueColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.point.step.StepChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.MultiValueColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。

       <%
       StepChartConfig _stepChartConfig = new StepChartConfig();
       
       _stepChartConfig.addColumns(
       new MultiValueColumn("data1", 100, 20, 30),
       new MultiValueColumn("data2", 20, 70, 100));
       }
       %>
   
3. `<chart>` taglibを`view.jsp`に追加し、`_stepChartConfig`を`config`属性の値として渡します。

       <chart:step
       config="<%= _stepChartConfig %>"
       />
   
![図1：ステップチャートは、データのポイント間に段差があることから、階段に似ています。](../../../images/chart-taglib-step.png)

必要に応じて、エリアステップチャートを使用することもできます。エリアステップチャートは、ステップグラフでカバーされるエリアを強調表示します。

    <chart:area-step
    config="<%= _stepChartConfig %>"
    />

![図2：エリアステップチャートは、ステップグラフでカバーされるエリアを強調表示します。](../../../images/chart-taglib-area-step.png)

## 関連トピック

[Line Charts](/docs/7-1/tutorials/-/knowledge_base/t/line-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)

[Spline Charts](/docs/7-1/tutorials/-/knowledge_base/t/spline-charts)
