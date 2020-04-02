---
header-id: calling-non-osgi-code-that-uses-osgi-services
---

# OSGiサービスを使用する非OSGiコードの呼び出し

[TOC levels=1-4]

@product@の静的サービスユーティリティ（例：`UserServiceUtil`、`CompanyServiceUtil`、`GroupServiceUtil`など）は、OSGiサービスを使用する非OSGiコードの例です。Service Builderは、下位互換性の目的としてのみ、それらを生成します。`*ServiceUtil`クラスを呼び出したい場合、または既存のコードがそのクラスを呼び出したい場合は、次の代替手段のいずれかを使用して代わりに`*Service`に直接アクセスします。

- クラスがDeclarative Servicesコンポーネントの場合は、[`@Reference`アノテーション](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)を使用して`*Service`クラスにアクセスします。

- 
クラスがDeclarative Servicesコンポーネントではない場合は、[`ServiceTracker`](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)を使用して`*Service`クラスにアクセスします。


@product@のサービスの状態は[Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)で確認できます。
`scr:list` Gogoシェルコマンドは、不満足な依存関係からの非アクティブなコンポーネントを含むすべてのDeclarative Servicesコンポーネントを表示します。 Service Builderサービスの不満足な依存関係を見つけるには、Dependency Managerの`dependencymanager:dm wtf`コマンドを使用します。これらのコマンドは、依存関係が不満足なためにアクティブ化されていないコンポーネントのみを表示することに注意してください。依存関係が不満足なため、サービスを待機している純粋なサービストラッカーは表示されません。

## 関連トピック

[Detecting Unresolved OSGi Components](/docs/7-1/tutorials/-/knowledge_base/t/detecting-unresolved-osgi-components)

[Felix Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)

[OSGi Basics For Liferay Development](/docs/7-1/tutorials/-/knowledge_base/t/osgi-basics-for-liferay-development)
