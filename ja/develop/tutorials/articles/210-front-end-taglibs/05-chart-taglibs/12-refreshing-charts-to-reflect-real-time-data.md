---
header-id: refreshing-charts-to-reflect-real-time-data
---

# グラフを更新してリアルタイムのデータを反映する

[TOC levels=1-4]

ポーリング間隔プロパティは、すべてのグラフのオプションのプロパティです。このプロパティは、グラフのデータを更新する時間をミリ秒単位で指定します。このプロパティは、定期的に変更されるJSONファイルなど、あらゆる種類のリアルタイムのデータを受信するグラフに使用できます。これにより、グラフが最新のデータを反映して最新の状態になります。このチュートリアルでは、リアルタイムのデータを反映するようにポートレットのグラフを構成する方法を示します。

グラフの構成オブジェクトに`setPollingInterval()`メソッドを使用して、リフレッシュレートを指定します。`view.jsp`の構成例を以下に示します。

    <%
    LineChartConfig _pollingIntervalLineChartConfig = new LineChartConfig();
    
    _pollingIntervalLineChartConfig.put("data", "/foo.json");
    
    _pollingIntervalLineChartConfig.setPollingInterval(2000);
    
    %>
    
    <chart:line
    componentId="polling-interval-line-chart"
    config="<%= _pollingIntervalLineChartConfig %>"
    />

![図1：ポーリング間隔プロパティを使用すると、指定された間隔でグラフを更新して、リアルタイムのデータを反映できます。](../../../images/chart-polling-interval.gif)

## 関連トピック

[Combination Charts](/docs/7-1/tutorials/-/knowledge_base/t/combination-charts)

[Geomap Charts](/docs/7-1/tutorials/-/knowledge_base/t/geomap-charts)

[Scatter Charts](/docs/7-1/tutorials/-/knowledge_base/t/scatter-charts)
