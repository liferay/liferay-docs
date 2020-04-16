---
header-id: understanding-system-configuration-files
---

# システム構成ファイルについて

[TOC levels=1-4]

[System Settingsアプリケーション](/docs/7-1/user/-/knowledge_base/u/system-settings) は、システムスコープの構成変更を行い、他の [スコープ](/docs/7-1/user/-/knowledge_base/u/setting-up#configuration-scope)デフォルト構成を設定するのに便利です。 ただし、サポートされている別の構成アプローチがあります。構成ファイルです。 構成ファイルを使用して、本番前のシステムから本番システムに、または他の@ product @システム間で構成を転送できます。 開発者は、構成ファイルを介してアプリケーションのデフォルト構成を配布することを選択する場合があります。 理由が何であれ、構成ファイルは別の構成アプローチを提供します。

構成ファイルは、 [Apache Felix構成管理フレームワーク](http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html)によって定義された `.config` プロパティ値の形式を使用します。

| **重要：** テンプレートを使用して生成されたコンテンツ（FreeMarkerテンプレートやアプリケーション表示テンプレートなど）はキャッシュされます。 キャッシュされたコンテンツが反映されない可能性があります|キャッシュが無効になる（クリアされる）まで、構成は変更されます。 | [サーバー管理→リソースタブ](/docs/7-1/user/-/knowledge_base/u/server-administration-resources) |キャッシュ消去オプションを提供します。

| **注：** `.cfg` ファイル形式はOSGi環境で一般的であり、|サポートされている形式ですが、 `.config` ファイルが推奨されます。 プロパティを指定できます|値のタイプで、複数の値を持つプロパティがあります。 これらで説明されている構文|記事は `.config` ファイル用です。
