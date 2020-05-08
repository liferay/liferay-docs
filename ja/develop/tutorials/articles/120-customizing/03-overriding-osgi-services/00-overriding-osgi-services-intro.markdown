---
header-id: overriding-service-references
---

# OSGiサービスのオーバーライド

[TOC levels=1-4]

コンポーネントは、OSGiサービスレジストリにサービスとして登録します。サービスコンポーネントの可用性、ランキング、および属性によって、サービスタイプを参照するコンポーネントがその特定のサービスにバインドするかどうかが決まります。@product@のOSGIコンテナは、サービスが行き来してオーバーライドできる動的な環境です。つまり、動作を変更したいサービスがある場合、それをオーバーライドできます。サービスをオーバーライドする手順は次のとおりです。

1. [サービスとサービス参照の詳細を取得する](/docs/7-1/tutorials/-/knowledge_base/t/examining-an-osgi-service-to-override)

2. [カスタムサービスを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-custom-osgi-service)

3. [カスタムサービスを使用するようにコンポーネントを構成する](/docs/7-1/tutorials/-/knowledge_base/t/reconfiguring-components-to-use-your-service)

**注**： [portal-impl](@platform-ref@/7.1-latest/javadocs/portal-impl/)の[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)サービスは、LiferayがOSGiサービスとして提供するSpring Beanです。

オーバーライドするサービスを調べることから始めます
