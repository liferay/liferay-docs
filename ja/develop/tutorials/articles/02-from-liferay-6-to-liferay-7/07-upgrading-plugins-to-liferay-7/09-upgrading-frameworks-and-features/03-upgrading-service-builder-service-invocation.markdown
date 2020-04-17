---
header-id: upgrading-service-builder-service-invocation
---

# Service Builderサービス呼び出しのアップグレード

[TOC levels=1-4]

[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)を活用してポートレットをアップグレードする場合、最初にService BuilderロジックをWARとして構築するか、モジュール化するかを決定する必要があります。

| **注:** Upgrade PlannerまたはBlade CLIの`convert`コマンドを使用してLiferay Workspace | へ自動的に移行されたService Builderポートレットは、自動的に | APIおよび実装モジュールに変換されたService Builderロジックを持ちます。 | これは@product-ver@のベストプラクティスです。

Service BuilderロジックをWARとして保持したい場合は、サービスを呼び出すサービストラッカーを実装する必要があります。 詳細については、記事[Service Trackers](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)をご覧ください。

モジュールからLiferayサービスを呼び出すためにService Builderロジックを最適化する場合は、記事[Invoking Local Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services)で詳細をご覧ください。
