---
header-id: resolving-breaking-changes
---

# 重大な変更の解決

[TOC levels=1-4]

Liferayは、後方互換性を維持するために最大限の努力を行っています。 @product@を改善するには、時には重大な変更が必要です。 重大な変更はコードアップグレードプロセスに影響する場合があるため、それらを解決する必要があります。 重大な変更には、以下の点が含まれます。

  - 削除または置換される機能
  - APIの非互換性：パブリックJavaまたはJavaScript APIの変更
  - テンプレートで利用可能なコンテキスト変数の変更
  - Liferayテーマおよびポートレットで利用可能なCSSクラスの変更
  - 設定の変更：`portal.properties`、`system.properties`などの設定ファイルの変更
  - 実行要件：Javaバージョン、J2EEバージョン、ブラウザーバージョンなど
  - 非推奨またはサポート終了：たとえば、次のバージョンで特定の機能またはAPIが停止されると警告している
  - 推奨事項：たとえば、後方互換性のためにLiferay Portalに古いAPIが保持されているにもかかわらず、古いAPIに代わる、新しく導入されたAPIを使用することを推奨している

Liferayは、主要なリリースごとに重大な変更点のリストを提供し、アップグレードプロセス中にコードを簡単に適合できるようにします。

  - [@product@ 7.0重大な変更](/docs/7-0/reference/-/knowledge_base/r/breaking-changes)
  - [@product-ver@重大な変更](/docs/7-1/reference/-/knowledge_base/r/breaking-changes)
  - [@product@ 7.2重大な変更](/docs/7-2/reference/-/knowledge_base/r/breaking-changes)

重大な変更を解決する最も簡単な方法は、[Liferay Upgrade Planner](/docs/7-1/tutorials/-/knowledge_base/t/liferay-upgrade-planner)を使用することです。 文書化されたすべての重大な変更を自動的に検出し、それらの一部を自動的に解決できます。

重大な変更をマニュアルで解決する場合、複数のバージョンにわたってコードをアップグレードする場合は、それぞれの重大な変更のドキュメントを必ず調査してください。 たとえば、Liferay Portal 6.2から@product-ver@にアップグレードする場合、上記の3つのドキュメントに記載されているすべての重大な変更を解決する必要があります。

重大な変更を解決したら、次はサービスビルダーサービスをアップグレードする方法を学習します。
