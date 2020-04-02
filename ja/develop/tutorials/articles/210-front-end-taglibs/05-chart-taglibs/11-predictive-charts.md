---
header-id: predictive-charts
---

# 予測チャート

[TOC levels=1-4]

予測チャートでは、指定された値の範囲内の現在のデータと、予測/予想データを視覚化できます。

![図1：予測/予想データは、可能な値の強調表示されたエリアで囲まれています。](../../../images/chart-taglib-predictive-value-range.png)

このチュートリアルでは、予測チャートを使用するようにポートレットを構成する方法を示します。
次の手順を実行します。

1. Chart taglibを`PredictiveChartConfig`および`MixedDataColumn`クラスとともにバンドルの`init.jsp`ファイルにインポートします。

       <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.predictive.PredictiveChartConfig" %>
       <%@ page import="com.liferay.frontend.taglib.chart.model.MixedDataColumn" %>
   
2. 次のJavaスクリプトレットを`view.jsp`の上部に追加します。 データ系列ごとに[`MixedDataColumn`オブジェクト](@platform-ref@/7.1-latest/apps/frontend-taglib-1.0.1/javadocs/com/liferay/frontend/taglib/chart/model/MixedDataColumn.html)（単一の数値と3つの数値の配列の両方をサポートする列）を追加し ます。単一の数値は、既存のデータを定義します。
数値の配列は予測/予想データとして使用され、3つの数値（最小値、推定値、および最大値）が含まれます。推定値は塗りつぶされ、最小値と最大値で指定された境界線を持つ強調表示されたエリアで囲まれます。これにより、推定値を視覚化できると同時に、可能な値の範囲を把握できます。`addDataColumn()`メソッドを使用して各データ系列を追加します。

       <%
       private PredictiveChartConfig _predictiveChartConfig = new
       PredictiveChartConfig();
       
       MixedDataColumn mixedDataColumn1 = new MixedDataColumn(
       "data1", 130, 340, 200, 500, 80, 240, 40,
       new Number[] {370, 400, 450}, new Number[] {210, 240, 270},
       new Number[] {150, 180, 210}, new Number[] {60, 90, 120},
       new Number[] {310, 340, 370});
       
       _predictiveChartConfig.addDataColumn(mixedDataColumn1);
       
       MixedDataColumn mixedDataColumn2 = new MixedDataColumn(
       "data2", 210, 160, 50, 125, 230, 110, 90,
       Arrays.asList(170, 200, 230), Arrays.asList(10, 40, 70),
       Arrays.asList(350, 380, 410), Arrays.asList(260, 290, 320),
       Arrays.asList(30, 70, 150));
       
       _predictiveChartConfig.addDataColumn(mixedDataColumn2);
       
       _predictiveChartConfig.setAxisXTickFormat("%b");
       
       _predictiveChartConfig.setPredictionDate("2018-07-01");
       
       List<String> timeseries = new ArrayList<>();
       
       timeseries.add("2018-01-01");
       timeseries.add("2018-02-01");
       timeseries.add("2018-03-01");
       timeseries.add("2018-04-01");
       timeseries.add("2018-05-01");
       timeseries.add("2018-06-01");
       timeseries.add("2018-07-01");
       timeseries.add("2018-08-01");
       timeseries.add("2018-09-01");
       timeseries.add("2018-10-01");
       timeseries.add("2018-11-01");
       timeseries.add("2018-12-01");
       
       _predictiveChartConfig.setTimeseries(timeseries);
       
       %>
   
   予測チャートには次のプロパティがあります。

   **axisXTickFormat：**X軸の時間形式を指定するオプションの文字列。指定できる形式の詳細については、[d3's time format README](https://github.com/d3/d3-time-format/blob/master/README.md#locale_format)を参照してください。
この値は`setAxisXTickFormat()`メソッドを使用して設定されます。

   **Prediction Date：**予測/予想が表示されてからのタイムラインのポイントを表す文字列としての日付。この値はJavaScriptのDateオブジェクトとして解析され、`setPredictionDate()`メソッドを使用して設定されます。

   **Time Series：**チャートのX軸に表示されるデータのタイムライン。この値は、日付の配列として設定されます（例：`2018-01-01`）

3. `<chart>` taglibを`view.jsp`に追加し、`_predictiveChartConfig`を`config`属性の値として渡します。

       <chart:predictive
       config="<%= _predictiveChartConfig %>"
       />
   
水色の長方形内に含まれているエリアは、予測/予想値が表示されるポイントです。

![図2：予測チャートを使用すると、推定される将来のデータを既存のデータとともに視覚化できます。](../../../images/chart-taglib-predictive.png)

## 関連トピック

[Combination Charts](/docs/7-1/tutorials/-/knowledge_base/t/combination-charts)

[Geomap Charts](/docs/7-1/tutorials/-/knowledge_base/t/geomap-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)
