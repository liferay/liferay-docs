---
header-id: dynamic-deployment
---

# 動的展開

[TOC levels=1-4]

OSGiでは、すべてのコンポーネント、Javaクラス、リソース、および記述子はモジュールを介してデプロイされます。 `MANIFEST.MF` ファイルには、モジュールがエクスポートおよびインポートするパッケージなど、モジュールの物理的特性が記述されています。 モジュールのコンポーネント記述ファイルは、その機能特性（つまり、コンポーネントが提供および消費するサービス）を指定します。 また、モジュールとそのコンポーネントには独自のライフサイクルと管理APIがあります。 宣言型サービスとシェルツールにより、モジュールとコンポーネントの展開をきめ細かく制御できます。

モジュールの内容はアクティベーションに依存するため、アクティベーション手順を検討してください。

1.  *インストール*：モジュールJARを@product@の `[Liferay Home] / deploy` フォルダーにコピーすると、モジュールがOSGiフレームワークにインストールされ、モジュールに `INSTALLED`とマークされます。

2.  *解決策*：モジュールのすべての要件が満たされると（たとえば、インポートするすべてのパッケージが利用可能になると）、フレームワークはモジュールのエクスポートされたパッケージを公開し、モジュール `解決`

3.  *有効化*：モジュールはデフォルトで *積極的に有効化されます*。 つまり、フレームワークで開始され、解決時に `ACTIVE` とマークされます。 アクティブなモジュールのコンポーネントが有効になります。 以下のマニフェストヘッダーに示すように、モジュールが `遅延` アクティベーションポリシーを指定している場合、別のモジュールがそのクラスの1つを要求した後にのみ、モジュールがアクティベートされます。
   
        Bundle-ActivationPolicy: lazy

次の図は、モジュールのライフサイクルを示しています。

![図1：この状態図は、モジュールのライフサイクルを示しています。](../../images/module-state-diagram.png)

[Apache Felix Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell) では、モジュールのライフサイクルを管理できます。 モジュールをインストール/アンインストールし、それらを開始/停止できます。 モジュールを更新し、更新を使用するように依存モジュールに通知できます。 など、Liferayのツール、 [のLiferay IDE @ @](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)、 [Liferayのワークスペース](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)、および [ブレードCLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli) OSGiの管理APIを使用して提供同様のシェルコマンド。

モジュールをアクティブ化すると、そのコンポーネントが有効になります。 しかし、唯一の *アクティブに* コンポーネントを使用することができます。 コンポーネントをアクティブ化するには、その参照サービスがすべて満たされている必要があります。 つまり、それが参照するすべてのサービスを登録する必要があります。 参照に一致する最高ランクのサービスがコンポーネントにバインドされます。 コンテナは、コンポーネントが参照するすべてのサービスを見つけてバインドすると、コンポーネントを登録します。 これでアクティベーションの準備ができました。

コンポーネントは、 *遅延* （デフォルト）または *即時* アクティベーションポリシーを使用できます。 即時のアクティブ化を指定するには、開発者は属性 `immediate = true` を `@Component` アノテーションに追加します。

    @Component(
        immediate = true,
        ...
    )

即時アクティベーションが指定されていない限り、コンポーネントのアクティベーションは遅延します。 つまり、コンポーネントのオブジェクトが作成され、コンポーネントが要求されるとそのクラスがロードされます。 このように、アクティベーションを遅らせると、起動時間が短縮され、リソースが節約されます。

Gogo Shellの [Service Component Runtimeコマンド](http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html#shell-command) を使用すると、コンポーネントを管理できます。

  - `scr：list [bundleID]`：モジュール（バンドル）のコンポーネントをリストします。

  - `scr：info [componentID | fullClassName]`：コンポーネントのステータスと、提供するサービスを記述します。

  - `scr：enable [componentID | fullClassName]`：コンポーネントを有効にします。

  - `scr：disable [componentID | fullClassName]`：コンポーネントを無効にします。 サーバーが再起動されるまで、サーバー（またはクラスター内の現在のサーバーノード）では無効になっています。

サービス参照は静的であり、デフォルトでは消極的です。 つまり、挿入されたサービスは、サービスが無効になるまで参照コンポーネントにバインドされたままになります。 または、 *貪欲* サービスポリシーを参照に指定できます。 上位の一致サービスが登録されるたびに、フレームワークは下位のサービスをコンポーネント（サービスポリシーが貪欲である）からバインド解除し、新しいサービスをその場所に自動的にバインドします。 貪欲なポリシーを使用する `@Reference` アノテーションは次のとおりです。

    @Reference(policyOption = ReferencePolicyOption.GREEDY)

宣言型サービスの注釈を使用すると、コンポーネントのアクティブ化およびサービスポリシーを指定できます。 Gogo Shellコマンドを使用すると、モジュールとコンポーネントを制御できます。

|学習パスの一部としてこのチュートリアルにアクセスした場合| [Liferayポータル6から7.1](/docs/7-1/tutorials/-/knowledge_base/t/from-liferay-6-to-liferay-7)、ここに [](/docs/7-1/tutorials/-/knowledge_base/t/more-ways-osgi-improves-development-on-liferay) 行くことができます</a> |続行します。

## 関連トピック

[モジュール開発の開始](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development)

[プラグインのアップグレードの計画](migrating-existing-code-to-liferay-7/migrating-existing-code-to-liferay-7)
