---
header-id: overriding-global-language-keys
---

# グローバル言語キーのオーバーライド

[TOC levels=1-4]

言語ファイルには、[アプリケーションのユーザーインターフェイスメッセージの翻訳](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application)が含まれています。
ただし、デフォルトの言語キーをグローバルに、および他のアプリケーション（独自のアプリケーションを含む）でオーバーライドすることもできます。言語キーをオーバーライドする手順は次のとおりです。

1. [オーバーライドする言語キーを決定する](#determine-the-language-keys-to-override)
2. [新しい言語プロパティファイルのキーをオーバーライドする](#override-the-keys-in-a-new-language-properties-file)
3. [リソースバンドルのサービスコンポーネントを作成する](#create-a-resource-bundle-service-component)

**注**：かつてLiferay Portal 6.2の一部であった多くのアプリケーションは、現在モジュール化されています。それらの言語キーは、Liferayの言語プロパティファイルからアプリケーションのモジュールの1つに移動されている可能性があります。[モジュールの言語キーをオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-a-modules-language-keys)するプロセスは、Liferayの言語キーをオーバーライドするプロセスとは異なります。

## オーバーライドする言語キーを決定する

それでは、グローバル言語キーを探します。それらは、ソースコードまたはバンドル内の`Language[xx_XX].properties`ファイルにあります。

- ソースの場合：

   `/portal-impl/src/content/Language[xx_XX].properties`

- バンドルの場合：

   `portal-impl.jar`

すべての言語プロパティファイルには、言語設定プロパティなど、オーバーライド可能なプロパティが含まれています。

    ##
    ## Language settings
    ##
    
    ...
    lang.user.name.field.names=prefix,first-name,middle-name,last-name,suffix
    lang.user.name.prefix.values=Dr,Mr,Ms,Mrs
    lang.user.name.required.field.names=last-name
    lang.user.name.suffix.values=II,III,IV,Jr,Phd,Sr
    ...

デフォルトのメッセージとラベルを更新するためにオーバーライドできる単純なキーも多数あります。

    ##
    ## Category titles
    ##
    
    category.admin=Admin
    category.alfresco=Alfresco
    category.christianity=Christianity
    category.cms=Content Management
    ...

たとえば、図1は、Liferayのデフォルトの`publish`言語キーを使用するボタンを示しています。

    `publish=Publish`

![図1：Liferayのユーザーインターフェイスに表示されるメッセージはカスタマイズできます。](../../../images/standard-publish.png)

次に、このキーをオーバーライドする方法を学びます。

## 新しい言語プロパティファイルのキーをオーバーライドする

オーバーライドするキーがわかったら、必要なロケールの言語プロパティファイル（またはデフォルトの`Language.properties`ファイル）をモジュールの`src/main/resources/content`フォルダに作成します。ファイルで、キーを定義します。たとえば、`publish`キーをオーバーライドするとします。

    publish=Publish Override

変更を有効にするには、言語ファイルを参照するリソースバンドルのサービスコンポーネントを作成する必要があります。

### リソースバンドルのサービスコンポーネントを作成する

モジュールで、オーバーライドするロケールに合わせて`java.util.ResourceBundle`を拡張するクラスを作成します。次に、`en_US`ロケールのリソースバンドルクラスの例を示します。

    @Component(
    property = { "language.id=en_US" },
    service = ResourceBundle.class
    )
    public class MyEnUsResourceBundle extends ResourceBundle {
    
    @Override
    protected Object handleGetObject(String key) {
    return _resourceBundle.getObject(key);
    }
    
    @Override
    public Enumeration<String> getKeys() {
    return _resourceBundle.getKeys();
    }
    
    private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
    "content.Language_en_US", UTF8Control.INSTANCE);
    
    }

クラスの`_resourceBundle`フィールドには`ResourceBundle`が割り当てられます。 `ResourceBundle.getBundle`の呼び出しには2つのパラメーターが必要です。 `content.Language_en_US`パラメーターは、モジュールの`src/main/resources`フォルダに関する言語ファイルの修飾名です。2番目のパラメーターは、リソースバンドルの言語構文を設定する`control`です。Liferayの構文と同じ言語構文を使用するには、Liferayの`com.liferay.portal.kernel.language.UTF8Control`クラスをインポートし、2番目のパラメーターを`UTF8Control.INSTANCE`に設定します。

クラスの`@Component`アノテーションは、それをOSGi `ResourceBundle`サービスコンポーネントとして宣言します。その`language.id`プロパティによって、`en_US`ロケールに指定されます。

    @Component(
    property = { "language.id=en_US" },
    service = ResourceBundle.class
    )

クラスは次のメソッドをオーバーライドします。

- **`handleGetObject`**：モジュールのリソースバンドル（モジュールの言語プロパティファイルに基づく）でキーを検索し、キーの値を`Object`として返します。

- **`getKeys`**：リソースバンドルのキーの`Enumeration`を返します。

リソースバンドルのサービスコンポーネントは、デフォルトの言語キーをモジュールの言語キーのオーバーライドにリダイレクトします。

**注**：複数のロケールのグローバル言語キーのオーバーライドには、ロケールごとに個別のモジュールが必要です。各モジュールの`ResourceBundle`拡張クラス（上記の`MyEnUsResourceBundle`クラスなど）は、`language.id`コンポーネントプロパティの定義および言語ファイルの修飾名パラメーターでロケールを指定する必要があります。
たとえば、スペイン語ロケールの場合は次のようになります。
|
| コンポーネント定義：
|
|     @Component(
|         property = { "language.id=es_ES" },
|         service = ResourceBundle.class
|     )
|
| リソースバンドルの割り当て：
|
|     private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
|         "content.Language_es_ES", UTF8Control.INSTANCE);

**重要**：お使いのモジュールが[別のモジュールの言語キーを使用](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application#using-a-language-module)していて、[他のモジュールのキーのいずれかをオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application#using-a-language-module-from-a-module)する場合は、OSGiヘッダーを使用して、モジュールが必要とし、提供する機能を指定してください。これにより、モジュールのリソースバンドルに優先順位を付けることができます。

Liferay言語キーのオーバーライドが機能していることを確認するには、[モジュールをデプロイ](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)し、キーを使用しているポートレットとページにアクセスしてください。

![図2：このボタンは、オーバーライドされた`publish` キーを使用しています。](../../../images/localized-publish.png)

Liferayの言語キーをオーバーライドする手順は以上です。

## 関連トピック

[Resource Bundle Override Sample Project](/docs/7-1/reference/-/knowledge_base/r/resource-bundle-override)

[Upgrading Core Language Key Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks)

[Internationalization](/docs/7-1/tutorials/-/knowledge_base/t/internationalization)
