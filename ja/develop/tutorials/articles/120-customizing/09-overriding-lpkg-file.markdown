---
header-id: overriding-lpkg-files
---

# lpkgファイルのオーバーライド

[TOC levels=1-4]

アプリケーションはLiferay Marketplaceを介して*lpkg*ファイルとして配信されます。これは、@product@にデプロイされる.jarファイルを含む単純な圧縮ファイル形式です。 Marketplaceからアプリケーションを調べたい場合は、.lpkgファイルを解凍して、その中にある.jarファイルを確認するだけです。

アプリケーションを調べた後、.jarのいずれかを[カスタマイズ](/docs/7-1/tutorials/-/knowledge_base/t/customizing)できます。.jarのコピーでカスタマイズを行います。ただし、通常アプリケーションをデプロイする方法でデプロイしないでください。.lpkgをオーバーライドすることで、元の.lpgkファイルを変更せずにアプリケーションモジュールを更新できます。手順は次のとおりです。

1. @product@をシャットダウンします。

2. `[Liferay Home]/osgi/marketplace`フォルダ]に`override`というフォルダを作成します（/docs/7-0/deploy/-/knowledge_base/d/installing-product#liferay-home）。

3. 更新した.jarに、元の.lpkgの.jarと同じ名前からバージョン情報を除いた名前を付けます。たとえば、`Liferay CE Amazon
Rankings.lpkg`の`com.liferay.amazon.rankings.web-1.0.5.jar`をオーバーライドしている場合は、.jarの名前を`com.liferay.amazon.rankings.web.jar`にします。

4. この.jarをステップ1で作成した`override`フォルダにコピーします。

これはMarketplaceのアプリケーションで機能しますが、コアLiferayテクノロジーとサードパーティユーティリティ（サーブレットAPI、Apacheユーティリティなど）を含む静的.lpkgもあります。これらの.jarファイルのいずれかをカスタマイズまたはパッチ適用する場合は、次のプロセスを実行します。

1. カスタマイズを行い、.jarファイルにパッケージ化します。

2. カスタマイズした.jarに元の.jarと同じ名前からバージョン情報を除いた名前を付けます。
たとえば、カスタマイズした`com.liferay.portal.profile-1.0.4.jar`の名前は`com.liferay.portal.profile.jar`になります。

3. .jarを`[Liferay Home]/osgi/static`フォルダにコピーします。

これで@product@を開始できます。この方法で.jarを追加および削除する場合は常に、@product@をシャットダウンしてから再起動して、変更を有効にする必要があります。

カスタマイズをロールバックする必要がある場合は、オーバーライドする.jarファイルを削除します。@product@は、次回の起動時に元の.jarを使用します。
