---
header-id: examining-an-osgi-service-to-override
---

# オーバーライドするOSGiサービスの確認

[TOC levels=1-4]

既存のサービスの代わりにカスタムサービスを作成して挿入するには、次の3つのことが必要です。

- サービスインターフェイスの理解
- 既存のサービス
- サービスへの参照

カスタムサービスは、サービスインターフェイスを実装し、必要な参照と一致させる必要があり、場合によっては既存のサービスを呼び出す必要があります。

コンポーネントにカスタムサービスをすぐに採用させるには、サービスへの参照を再構成する必要があります。ここでは、これらの決定を行うためにサービスの詳細を具体化します。

## サービスに関する情報の収集

1. コンポーネントサービスの参照は拡張ポイントであるため、チュートリアル[Finding Extension Points](/docs/7-1/tutorials/-/knowledge_base/t/finding-extension-points)に従って、オーバーライドするサービスとそのサービスを使用するコンポーネントを特定することから始めます。

2. サービスとそのサービスを使用するコンポーネントがわかったら、Gogo ShellのService Component Runtime（SCR）を使用してコンポーネントを検査し、サービスと参照の詳細を取得します。[Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)コマンド`scr:info [componentName]`は、コンポーネントの属性とサービス参照をリストします。

次に、コンポーネント`override.my.service.reference.OverrideMyServiceReference`（サンプルモジュール[override-my-service-reference](https://portal.liferay.dev/documents/113763090/114000186/override-my-service-reference.zip)より）およびそのタイプのサービスへの参照`override.my.service.reference.service.api.SomeService`を記述する`scr:info`コマンドと結果（`...`で略記）の例を示します。

    > scr:info override.my.service.reference.OverrideMyServiceReference
    
    ...
    Component Description:
    Name: override.my.service.reference.portlet.OverrideMyServiceReferencePortlet
    ...
    Reference: _someService
    Interface Name: override.my.service.reference.service.api.SomeService
    Cardinality: 1..1
    Policy: static
    Policy option: reluctant
    Reference Scope: bundle
    ...
    Component Configuration:
    ComponentId: 2399
    State: active
    SatisfiedReference: _someService
    Target: null
    Bound to:        6840
    Properties:
    component.id = 2400
    component.name = override.my.service.reference.service.impl.SomeServiceImpl
    objectClass = [override.my.service.reference.service.api.SomeService]
    service.bundleid = 524
    service.id = 6840
    service.scope = bundle
    ...

上記の内容と同様に、`scr:info`結果には、カスタムサービスの挿入に関連する情報が含まれています。この情報を使用して、次のことを行います。

1. [サービスインターフェイス名をコピーする](#step-1-copy-the-service-interface-name)


2. [既存のサービス名をコピーする](#step-2-copy-the-existing-service-name)

3. [参照構成の詳細を収集する（再構成が必要な場合）](#step-3-gather-reference-configuration-details-if-reconfiguration-is-needed)

サービスインターフェイスから始めます。

## ステップ1：サービスインターフェイス名をコピーする


参照のインターフェイス名は、サービスインターフェイスの完全修飾名です。

    ...
    Reference: _someService
    Interface Name: override.my.service.reference.service.api.SomeService
    ...

**インターフェイス名をコピーして保存します。**これは、カスタムサービスが実装する必要があるタイプです。

@product@サービスインターフェイスのJavadocは次の場所にあります。
|
| - [@product@コアのJavadoc](@platform-ref@/7.1-latest/javadocs/)
| - [@product@アプリのJavadoc](@app-ref@)
| - [MVNRepository](https://mvnrepository.com/)
|   および
|   [Maven Central](https://search.maven.org/)
|   （LiferayおよびLiferay以外のアーチファクトJavadoc用）。

## ステップ2：既存のサービス名をコピーする

カスタムサービスとともに既存のサービスを呼び出す場合は、既存のサービス名を取得します。

`src:info`結果のコンポーネントの構成セクションには、既存のサービスの完全修飾名がリストされます。たとえば、`OverrideMyServiceReferencePortlet`コンポーネントの参照`_someService`は、完全修飾名が`override.my.service.reference.service.impl.SomeServiceImpl`のサービスコンポーネントにバインドされます。

    Component Configuration:
    ...
    SatisfiedReference: _someService
    ...
    Bound to:        6840
    Properties:
    ...
    component.name = override.my.service.reference.service.impl.SomeServiceImpl

**`component.name`をコピーして、**[カスタムサービス](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-custom-osgi-service)でサービスを参照できるようにします。

上記のサービスを参照する例を次に示します。

    @Reference  (
    target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
    )
    private SomeService _defaultService;

## ステップ3：参照構成の詳細を収集する（再構成が必要な場合）

サービス参照のポリシーとポリシーオプションによって、特定のサービスを採用するためのコンポーネントの条件が決まります。

- 参照のポリシーオプションが`greedy`の場合、一致する最高ランクのサービスにすぐにバインドされます。サービスを採用するために参照を再構成する必要はありません。

- ただし、ポリシーが`static`で、ポリシーオプションが`reluctant`の場合は、使用するサービスを現在参照している既存のサービスから、一致する最高ランクのサービスに切り替える（つまり、カスタムサービスを最高ランクに位置付ける）ために、コンポーネントに次のいずれかの条件が必要です。

   1. コンポーネントを再アクティブ化する
   2. コンポーネントの既存の参照サービスを利用不可にする
   3. コンポーネントの参照を、既存のサービスとは一致しないが新しいサービスとは一致するように変更する

[参照の再構成](/docs/7-1/tutorials/-/knowledge_base/t/reconfiguring-components-to-use-your-service)は、コンポーネントが新しいサービスを採用する最も迅速な方法です。

**次の詳細を収集します。**

- *コンポーネント名*： *Component Description* &rarr; *Name*にあります。例：

       Component Description:
       Name: override.my.service.reference.portlet.OverrideMyServiceReferencePortlet
       ...
   
- *参照名*： *参照*値（例：`Reference: _someService`）。

- *カーディナリティ*：参照がバインドできるサービスインスタンスの数。

**注**：Declarative Servicesは、OSGiの[Configuration Admin]を介してすべてのコンポーネントを構成可能にします。ソースコード内の各`@Reference`アノテーションには、アノテーションで*明示的に*設定されるか、またはアノテーションが使用されるメンバーの名前から*暗黙的に*派生する名前プロパティがあります。
-   参照名プロパティが使用されておらず、`@Reference`がフィールドにある場合、参照名はフィールド名です。たとえば、`@Reference`が`_someService`というフィールドにある場合、参照名は`_someService`となります。
-   `@Reference`がメソッド上にある場合、ヒューリスティックスにより参照名が導き出されます。メソッド名のサフィックスが使用され、`set`、`add`、`put`のようなプレフィックスは無視されます。たとえば、`@Reference`が`setSearchEngine(SearchEngine se)`というメソッド上にある場合、参照名は`SearchEngine`となります。

[カスタムサービスを作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-custom-osgi-service)した後（次のステップ）、ここで収集した詳細を使用して、[カスタムサービスを使用するようにコンポーネントを構成](/docs/7-1/tutorials/-/knowledge_base/t/reconfiguring-components-to-use-your-service)します。

OSGiサービスをオーバーライドするために必要な詳細を取得できました。

## 関連トピック

[OSGi Services and Dependency Injection with Declarative Services](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)

[Finding Extension Points](/docs/7-1/tutorials/-/knowledge_base/t/finding-extension-points)

[Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)
