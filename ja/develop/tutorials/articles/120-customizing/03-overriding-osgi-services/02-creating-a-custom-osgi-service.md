---
header-id: creating-a-custom-osgi-service
---

# カスタムOSGiサービスの作成

[TOC levels=1-4]

OSGiサービスを実装するときがきました。[サービスとサービス参照の詳細を確認](/docs/7-1/tutorials/-/knowledge_base/t/examining-an-osgi-service-to-override)していない場合は確認してください。ここでは、サービスインターフェイスを実装し、そのタイプのOSGiサービスとして宣言し、他のコンポーネントとのバインドに最適なカスタムサービスを作成します。

サンプルのカスタムサービス`CustomServiceImpl`（サンプルモジュール[`overriding-service-reference`](https://portal.liferay.dev/documents/113763090/114000186/overriding-service-reference.zip)より）では、サービスインターフェイス`SomeService`を実装し、`SomeService`サービスタイプのOSGiサービスとして宣言し、さらに既存のサービスに作業を委任します。
カスタムサービスを作成する手順に従って、このサンプルコードを調べます。

    @Component(
    property = {
    "service.ranking:Integer=100"
    },
    service = SomeService.class
    )
    public class CustomServiceImpl implements SomeService {
    
    @Override
    public String doSomething() {
    
    StringBuilder sb = new StringBuilder();
    sb.append(this.getClass().getName());
    sb.append(", which delegates to ");
    sb.append(_defaultService.doSomething());
    
    return sb.toString();
    }
    
    @Reference  (
    target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
    )
    private SomeService _defaultService;
    }

カスタムOSGiサービスを作成する手順は次のとおりです。

1. [モジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development)。

2. 目的の[サービスインターフェイス](/docs/7-1/tutorials/-/knowledge_base/t/examining-an-osgi-service-to-override#step-1-copy-the-service-interface-name)を`実装する`ようにカスタムサービスクラスを作成します。上記の例では、`CustomServiceImpl implements SomeService`です。
ステップ5（後述）に、インターフェイスメソッドの実装を示します。

3. クラスを、サービスインターフェイスへの参照に最適なDeclarative Servicesコンポーネントにします。

   - `@Component`アノテーションと`service`属性を使用して、クラスをDeclarative Services（DS）コンポーネントにします。これにより、クラスがOSGiサービスレジストリで使用できるOSGiサービスであると宣言されます。上記のサンプルクラスは、サービスタイプ`SomeService.class`のDSサービスコンポーネントです。

   - `service.ranking:Integer`コンポーネントプロパティを使用して、サービスを既存のサービスよりも上位にランク付けします。上記の`"service.ranking:Integer=100"`プロパティでは、この例のランキングが`100`に設定されています。

4. 既存のサービス実装を呼び出す場合は、既存のサービスへのDeclarative Services参照を使用するフィールドを宣言します。[サービスを調べたときにコピーした`component.name`](/docs/7-1/tutorials/-/knowledge_base/t/examining-an-osgi-service-to-override#step-2-copy-the-existing-service-name)を使用して、既存のサービスを対象にします。上記の例は、次のように既存のサービスを参照しています。

       @Reference  (
       target = "(component.name=override.my.service.reference.service.impl.SomeServiceImpl)"
       )
       private SomeService _defaultService;
   
   このフィールドでは、カスタムサービスで既存のサービスを呼び出すことができます。

5. インターフェイスのメソッドをオーバーライドします。オプションで、既存のサービス実装に作業を委任します（前の手順を参照）。

   サンプルのカスタムサービスの`doSomething` メソッドは、元のサービス実装に作業を委任しています。

6. [モジュールをデプロイして](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)、カスタムサービスをOSGiランタイムフレームワークに登録します。

実装したサービスタイプを参照し、その参照ポリシーオプションが`greedy`に設定されているコンポーネントは即座にカスタムサービスにバインドされます。既存のサービスにバインドされ、参照ポリシーオプションが`reluctant`に設定されているコンポーネントは、新しいサービスを使用するように動的に再構成できます。次で説明します。

## 関連トピック

[OSGi Services and Dependency Injection with Declarative Services](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)
