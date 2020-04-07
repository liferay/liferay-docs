---
header-id: specifying-where-to-import-your-themes-resources
---

# テーマのリソースをインポートする場所を指定する

[TOC levels=1-4]

デフォルトでは、リソースはテーマにちなんで命名された新しいサイトテンプレートにインポートされますが、リソースを新しいサイトまたは既存のサイトまたはサイトテンプレートにインポートすることもできます。 このチュートリアルでは、これらすべてのオプションについて説明します。

最初に、Resources Importerの開発者モードを有効にする必要があります。

## 開発者モードを有効にする

リソースをインポートする場所を指定する前に、テーマで開発者モードを有効にする必要があります。 これを行うには、テーマの `liferay-plugin-package.properties` ファイルに次のプロパティを追加します。

    `resources-importer-developer-mode-enabled=true`

これは、 [Liferay Theme Generator](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)生成されたテーマに対してデフォルトで有効になっています。 これは、テーマ開発者にとって便利な機能です。 この設定を有効にすると、既に存在するサイトまたはサイトテンプレートにリソースをインポートすると、サイトまたはサイトテンプレートが再作成されます。 リソースをサイトテンプレートにインポートすると、サイトテンプレートとそのリソースが、サイトテンプレートに基づくサイトに再適用されます。 `resources-importer-developer-mode-enabled = true`がない場合、テーマの `src / WEB-INF / src / resources-から変更を適用するたびに、Resources Importerによって構築されたサイトまたはサイトテンプレートを手動で削除する必要があります。インポーター` フォルダー。

| **警告：** `resources-importer-developer-mode-enabled = true` 設定は|影響を受けるサイトの削除</em> （および再作成） *またはサイトテンプレート。 開発中にのみ使用することを目的としています。 **使用しない|生産中。**</p>

リソースインポーターで開発者モードを有効にすると、テーマのリソースをインポートする場所を選択できます。

## 既存のサイトテンプレートおよびサイトへのリソースのインポート

デフォルトでは、リソースはテーマにちなんで命名された新しいサイトテンプレートにインポートされます。 リソースを既存のサイトテンプレートにインポートする場合は、テーマの `liferay-plugin-package.properties` ファイルの `resources-importer-target-value` プロパティの値を指定する必要があります。

    #resources-importer-target-class-name
    
    resources-importer-target-value=[site-template-name]

**あなたは必要** 、あなたにパブリックおよびプライベートの両方のページセットを定義した場合、サイトにインポートあなたのリソースを `sitemap.json`。 既存のサイトにリソースをインポートするには、 `resources-importer-target-class-name` プロパティのコメントを外し、 `com.liferay.portal.kernel.model.Group`設定します。

``` 
resources-importer-target-class-name=com.liferay.portal.kernel.model.Group

resources-importer-target-value=[site-name] 
```

指定する名前を再確認してください。 間違った値を指定すると、間違ったサイトまたはサイトテンプレートを削除（および再作成）してしまう可能性があります。

| **警告：** テーマリソースをサイトテンプレートにインポートする方が、|よりも安全です。実際のサイト。 | `resources-importer-target-class-name = com.liferay.portal.kernel.model.Group` |設定は開発とテストには便利ですが、慎重に使用する必要があります。 |本番Liferayインスタンスにデプロイされたテーマでこの設定を使用しないでください。 Liferay Marketplaceに送信されたテーマ。 |にデプロイするテーマを準備するには|実稼働Liferayインスタンスの場合、デフォルト設定を使用して、リソースが|サイトテンプレートにインポートされます。 これを明示的に行うには、|を設定します。 `resources-importer-target-class-name = com.liferay.portal.kernel.model.LayoutSetPrototype` |または暗黙的に|コメントアウトまたは削除して| `リソースインポート対象クラス名` プロパティ。

テーマとそのリソースを表示するには、テーマを展開し、管理者としてログインし、コントロールパネルの[サイト]または[サイトテンプレート]セクションをチェックして、リソースが正しく展開されていることを確認します。 コントロールパネルから、テーマとそのリソースを簡単に表示できます。

  - サイトテンプレートにインポートした場合は、アクションメニューを開き、[ *ページの表示* ]を選択して表示します。
  - サイトに直接インポートした場合は、アクションメニューを開き、[ *公開ページに移動* ]を選択して表示します。

テーマでリソースをインポートするのは簡単です！

## 関連トピック

[Resources Importer用のWebコンテンツの準備と整理](/docs/7-1/tutorials/-/knowledge_base/t/preparing-and-organizing-web-content-for-the-resources-importer)

[Resources Importerのサイトマップを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-sitemap-for-the-resources-importer)

[リソースインポーターのアセットの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-assets-for-the-resources-importer)
