---
header-id: migrating-off-of-velocity-templates
---

# Velocityテンプレートからの移行

[TOC levels=1-4]

VelocityテンプレートはLiferay Portal 7.0で非推奨となり、@product-ver@のFreeMarkerテンプレートを優先して削除されました。 この移行の主な理由は次のとおりです。

  - FreeMarkerは定期的に開発および保守されていますが、Velocityは現在積極的に開発されていません。

  - FreeMarkerはより高速で、より洗練されたマクロをサポートします。

  - FreeMarkerは、タグライブラリを表すメソッドを必要とせずに、タグライブラリを直接使用することをサポートしています。 それらにボディコンテンツ、パラメータなどを渡すことができます。

Velocityテンプレートは引き続き@product-ver@で機能しますが、FreeMarkerテンプレートに移行することを強く推奨しています。 このトピックの詳細については、[Upgrading Layout Templates](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-6-2-layout-templates)セクションを参照してください。
