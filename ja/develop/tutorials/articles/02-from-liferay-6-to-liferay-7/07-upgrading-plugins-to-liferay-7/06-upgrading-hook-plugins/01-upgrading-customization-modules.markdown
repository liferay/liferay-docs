---
header-id: upgrading-customization-modules
---

# カスタマイゼーションモジュールのアップグレード

[TOC levels=1-4]

カスタマイズモジュールには、別のモジュールのカスタマイズに使用されるモジュール拡張またはオーバーライドが含まれます。 これらのタイプのモジュールの例については、[`extensions`](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/extensions)および[`overrides`](https://github.com/liferay/liferay-blade-samples/tree/7.1/liferay-workspace/overrides)サンプルプロジェクトをご覧ください。

@product-ver@で実行されているカスタマイゼーションモジュールを取得するには、2つの手順が必要です。

1.  Liferay Upgrade Plannerを使用して、コードを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行した時、既存の事象の多くが自動修正またはフラグ付けされました。 残りのエラーについては、記事[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。

2.  モジュールをデプロイします。

| **注: **フラグメントは、過去のバージョン | @product@の一般的なカスタマイゼーションモジュールでした。 フラグメントは現在推奨されていません。; 動的インクルードまたはポートレットフィルタにフラグメント | をアップグレードする必要があります。 推奨されている | @product-ver@でJSPをカスタマイズする方法については、 | [Customizing JSPs](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps) | セクションをご覧ください。

これでカスタマイゼーションモジュールが、@product-ver@\にアップグレードされました。
