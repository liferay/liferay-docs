---
header-id: upgrading-web-plugins
---

# Webプラグインのアップグレード

[TOC levels=1-4]

Webプラグインは通常の[Java EE Webモジュール](https://docs.oracle.com/cd/E19226-01/820-7627/bnadx/index.html)で、@product@と動作するように設計されています。 これらのプラグインは、以前のプラグインSDKの`webs`フォルダに保存されていました。

Liferay Webプラグインのアップグレードには、次の手順が含まれます。

1.  Liferay Upgrade Plannerを使用して、プラグインを@product-ver@のAPIに適合させます。 プランナーの*アップグレードの問題の修復*ステップを実行すると、既存の事象の多くが自動修正されます。 残りの事象については、プランナーは新しいAPIの影響を受けるコードとそれを適応させる方法を特定します。

2.  [依存関係を解決する](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)

3.  デプロイします。

アップグレードされたポートレットをデプロイした後、サーバーは次のポートレット状態を示すメッセージを出力します。

  - WAR processing
  - WAB startup
  - Availability to users

これで、@product-ver@インスタンスへのLiferay Webプラグインのアップグレードとデプロイが完了しました。
