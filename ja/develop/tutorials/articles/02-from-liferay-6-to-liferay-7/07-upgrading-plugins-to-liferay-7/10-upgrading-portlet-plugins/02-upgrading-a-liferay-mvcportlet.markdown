---
header-id: upgrading-a-liferay-mvc-portlet
---

# Liferay MVCポートレットのアップグレード

[TOC levels=1-4]

LiferayのMVCポートレットフレームワークは、Liferayのポートレットで広く使用されており、Liferay Portal 6.2ポートレット開発者によく使用されている選択肢です。 [`MVCPortlet`クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.html)は、`javax.portlet.GenericPortlet`の軽量拡張です。 その`init`メソッドにより、多くの定型コードを書く必要がなくなります。 MVCポートレットは、@product-ver@へスムーズにアップグレードできます。

Liferay MVCポートレットをアップグレードするには、Lifera Upgrade Plannerを使用して、コードを@product-ver@のAPIに適合させます。 プランナーの*アップグレード問題の修復*ステップを実行した時、既存の事象の多くが自動修正またはフラグ付けされました。 残りのエラーについては、記事[Resolving a Plugin's Dependencies](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies)を参照してください。

Liferayの[Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)は、新しいAPIの影響を受けるコードの特定、APIの変更とそれらに適合する方法の説明、そして多くの事例でコードを自動的に適合させるオプションの提供を行います。

ポートレットをアップグレードしたら、通常通りデプロイします。

サーバーは、次のポートレット状態を示すメッセージを出力します。

  - WAR processing
  - WAB startup
  - Availability to users

これで、Liferay MVCポートレットを@product-ver@インスタンスにアップグレードおよびデプロイできました。

**関連トピック**

[プラグインSDKプロジェクトをWorkspaceとGradleに移行する](/docs/7-1/tutorials/-/knowledge_base/t/migrating-traditional-plugins-to-workspace-web-applications)

[依存関係管理ツールの使用](/docs/7-1/tutorials/-/knowledge_base/t/resolving-a-plugins-dependencies#using-a-dependency-management-tool)

[WABジェネレーターの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)

[データアップグレードプロセスの移行](/docs/7-1/tutorials/-/knowledge_base/t/optimizing-app-upgrade-processes)
