---
header-id: using-the-chart-taglib-in-your-portlets
---

# ポートレットでChart Taglibを使用する

[TOC levels=1-4]

折れ線、スプライン、バー、円など、Chartタグライブラリは、データのモデル化に必要なものを提供します。各taglibを使用して、対応する[Clayコンポーネント](https://github.com/liferay/clay/tree/develop/packages/clay-charts/src)にアクセスできます。
これらのコンポーネントには、UIのデフォルト構成が含まれています。

アプリでChart taglibを使用するには、JSPに次の宣言を追加します。

    <%@ taglib prefix="chart" uri="http://liferay.com/tld/chart" %>

チュートリアルのこのセクションでは、Chart taglibを使用して作成できるグラフの種類について説明します。各チュートリアルには、グラフの事例集と、サンプルJavaデータおよびレンダリング結果を表示する図が含まれています。

![図1：chart taglibを使用して、さまざまな種類のグラフを作成できます。](../../../images/chart-taglib-sample-portlet.png)
