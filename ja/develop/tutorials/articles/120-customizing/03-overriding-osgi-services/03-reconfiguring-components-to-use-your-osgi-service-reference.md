---
header-id: reconfiguring-components-to-use-your-service
---

# OSGiサービスを使用するようにコンポーネントを再構成する

[TOC levels=1-4]

多くの場合、[カスタムサービス](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-custom-osgi-service)に高いランキングを割り当てると、コンポーネントは現在のサービスからバインドを解除し、カスタムサービスにバインドするようになります。それ以外の場合、コンポーネントは現在のサービスを使用し続けます。それはなぜでしょう。そして、コンポーネントにサービスを採用させるにはどうしたらよいでしょうか。
コンポーネントの[サービス参照ポリシーオプション](/docs/7-1/tutorials/-/knowledge_base/t/examining-an-osgi-service-to-override#step-3-gather-reference-configuration-details-if-reconfiguration-is-needed)が、サービスを決定する鍵となります。

ポリシーのオプションは次のとおりです。

`greedy`：コンポーネントは、一致する最高ランクのサービスが利用可能になるとすぐに使用します。

`reluctant`：コンポーネントは、次のイベント時に利用可能な一致する最高ランクのサービスを使用します。

- コンポーネントが（再）アクティブ化される
- コンポーネントの既存の参照サービスを利用できなくなった
- コンポーネントの参照が、既存のバインドされたサービスと一致しなくなるように変更される

つまり、greedyポリシーオプションを使用した参照では、すぐに上位のサービスが採用されますが、reluctantポリシーオプションを使用した参照では特定のイベントが必要になります。@product@の[Configuration Admin]では、構成ファイル（コンフィグファイル）またはAPIを使用して、サービス参照の変更をその場で切り替えることができます。ここでは、構成ファイルを使用してカスタムサービスをすぐに使用するようにサービス参照を再構成します。

このチュートリアルでは、サンプルモジュール`override-my-service-reference`と`overriding-service-reference`を使用して、サービス参照の再構成を示し、コンポーネントを別のサービスにバインドします。モジュールをダウンロードして、Gradle（各モジュールに同梱されている）を使用してビルドするか、チュートリアルの手順を適用して独自のカスタマイズを構成できます。各サンプルモジュールルートで`gradlew jar`を実行すると、モジュールJARが`build/libs`フォルダに生成されます。

- `override-my-service-reference`（[ダウンロード](https://portal.liferay.dev/documents/113763090/114000186/override-my-service-reference.zip)）：このモジュールのポートレットコンポーネント`OverrideMyServiceReferencePortlet`のフィールド`_someService`は、タイプが`SomeService`のサービスを参照します。参照のポリシーは静的でReluctantです。デフォルトでは、`SomeServiceImpl`と呼ばれる実装にバインドします。

- `overriding-service-reference`（[ダウンロード](https://portal.liferay.dev/documents/113763090/114000186/overriding-service-reference.zip)）：`CustomServiceImpl`と呼ばれるカスタム`SomeService`実装を提供します。
モジュールの構成ファイルは、`CustomServiceImpl`にバインドするように`OverrideMyServiceReferencePortlet`の`SomeService`参照をオーバーライドします。

カスタムサービスを対象とするようにコンポーネントのサービス参照を再構成する準備が整いました。

## サービス参照の再構成

@product@の[Configuration Admin]では、構成ファイルを使用して、サービス参照をその場で切り替えることができます。

1. 参照コンポーネントにちなんで名付けられた[システム構成ファイルを作成](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)します。命名規則`[component].config`に従って、`[component]`を[コンポーネント名](/docs/7-1/tutorials/-/knowledge_base/t/examining-an-osgi-service-to-override#step-3-gather-reference-configuration-details-if-reconfiguration-is-needed)に置き換えます。
サンプルコンポーネント`override.my.service.reference.portlet.OverrideMyServiceReferencePortlet`の構成ファイル名は次のとおりです。

       override.my.service.reference.portlet.OverrideMyServiceReferencePortlet.config
   
2. 構成ファイルに、カスタムサービスでフィルター処理する参照ターゲットエントリを追加します。エントリは次の形式に従ってください。

       [reference].target=[filter]
   
   `[reference]`をオーバーライドする参照名に置き換えます。
`[filter]`をカスタムサービスでフィルター処理するサービスプロパティに置き換えます。

   次の例では、`component.name`サービスプロパティをフィルター処理します。

       _someService.target="(component.name\=overriding.service.reference.service.CustomServiceImpl)"
   
   次の例では、`service.vendor`サービスプロパティをフィルター処理します。

       _someService.target="(service.vendor\=Acme, Inc.)"
   
3. オプションで、参照が使用できるサービスの数を指定する`cardinality.minimum`エントリを追加できます。形式は次のとおりです。

       [reference].cardinality.minimum=[int]
   
   カーディナリティの最小値の例を次に示します。

       _someService.cardinality.minimum=1
   
4. 構成ファイルをフォルダ`[Liferay_Home]/osgi/configs`にコピーして、構成をデプロイします。 

コンポーネントで`scr:info`を実行すると、カスタムサービスが参照にバインドされていることがわかります。

たとえば、`scr:info override.my.service.reference.portlet.OverrideMyServiceReferencePortlet`を実行すると、次の情報が報告されます。

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
    Target: (component.name=overriding.service.reference.CustomServiceImpl)
    Bound to:        6841
    Properties:
    _defaultService.target = (component.name=overriding.service.reference.service.CustomServiceImpl)
    component.id = 2398
    component.name = overriding.service.reference.service.CustomServiceImpl
    objectClass = [override.my.service.reference.service.api.SomeService]
    service.bundleid = 525
    service.id = 6841
    service.scope = bundle
    Component Configuration Properties:
    _someService.target = (component.name=overriding.service.reference.service.CustomServiceImpl)
    ...

サンプルコンポーネントの`_someService`参照は、カスタムサービスコンポーネント`overriding.service.reference.service.CustomServiceImpl`を対象としています
`CustomServiceImpl`はデフォルトのサービス`SomeServiceImpl`を参照して、作業を委任しています。

![図1：サンプルコンポーネントのサービス参照は構成ファイルのデプロイによってオーバーライドされるため、ポートレットはカスタムサービスを呼び出していることを示しています。](../../../images/overriding-service-refs-result.png)

@product@は構成ファイルを処理し、サービス参照を挿入しました。これにより、カスタムサービスが参照コンポーネントにバインドされました。

## 関連トピック

[OSGi Services and Dependency Injection with Declarative Services](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)

[Finding Extension Points](/docs/7-1/tutorials/-/knowledge_base/t/finding-extension-points)

[Using Felix Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)
