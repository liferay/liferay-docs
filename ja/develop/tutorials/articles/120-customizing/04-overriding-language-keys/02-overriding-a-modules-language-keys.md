---
header-id: overriding-a-modules-language-keys
---

# モジュールの言語キーのオーバーライド

[TOC levels=1-4]

変更する言語キーがLiferayのアプリケーションのいずれか、またはソースコードを制御できない別のモジュール内にある場合はどうしたらよいでしょうか。モジュールの言語キーはそれぞれのモジュール内にあるため、モジュールの言語キーをオーバーライドするプロセスは、[Liferayの言語キーをオーバーライドするプロセス](/docs/7-1/tutorials/-/knowledge_base/t/overriding-language-keys)とは異なります。

プロセスは次のとおりです。

1. モジュールとそのメタデータおよび言語キーを見つける
2. カスタム言語キーの値を記述する
3. モジュールのリソースバンドルに優先順位を付ける

## モジュールとそのメタデータおよび言語キーを見つける

[Gogo shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)で、ポートレットの表示名に一致するキーワードのバンドルとgrepをリストします。
言語キーは、ポートレットのWebモジュール（バンドル）にあります。バンドルを見つけたら、そのID番号をメモします。

たとえば、ブログポートレットを見つけるには、Gogoのコマンドと出力は次のようになります。

    g!lb | grep Blogs
    152|Active     |    1|Liferay Blogs Service (1.0.2)
    184|Active     |    1|Liferay Blogs Editor Config (2.0.1)
    202|Active     |    1|Liferay Blogs Layout Prototype (2.0.2)
    288|Active     |    1|Liferay Blogs Recent Bloggers Web (1.0.2)
    297|Active     |    1|Liferay Blogs Item Selector Web (1.0.2)
    374|Active     |    1|Liferay Blogs Item Selector API (2.0.1)
    448|Active     |    1|Liferay Blogs API (3.0.1)
    465|Active     |    1|Liferay Blogs Web (1.0.6)
    true

IDを`headers`コマンドに渡すことにより、バンドルのヘッダーをリストします。

    g!headers 465
    
    Liferay Blogs Web (465)
    -----------------------
    Manifest-Version = 1.0
    Bnd-LastModified = 1459866186018
    Bundle-ManifestVersion = 2
    Bundle-Name = Liferay Blogs Web
    Bundle-SymbolicName = com.liferay.blogs.web
    Bundle-Version: 1.0.6
    ...
    Web-ContextPath = /blogs-web
    g!

`Bundle-SymbolicName`、`Bundle-Version`、および`Web-ContextPath`をメモします。`Web-ContextPath`値の`/`の後は、サーブレットコンテキスト名です。

**重要**：サーブレットコンテキスト名、バンドルのシンボル名、およびバージョンを記録します。これらは後のプロセスでリソースバンドルローダーを作成するために使用します。

たとえば、LiferayブログのWebモジュールの値は次のとおりです。

- バンドルのシンボル名：`com.liferay.blogs.web`
- バンドルバージョン：`1.0.6`
- サーブレットコンテキスト名：`blogs-web`

次に、モジュールのJARファイルを見つけて、その言語キーを調べます。Liferayは、次のモジュールJARファイルの命名規則に従っています。

    [bundle symbolic name]-[version].jar

たとえば、ブログWebバージョン1.0.6モジュールは`com.liferay.blogs.web-1.0.6.jar`にあります。

モジュールJARの検索場所は次のとおりです。

- Liferayの[Nexusリポジトリ](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/)
- `[Liferay Home]/osgi/modules`
- `[Liferay
Home]/osgi/marketplace`のアプリケーションまたはアプリケーションスイートのLPKGファイルに埋め込まれている。

言語プロパティファイルは、モジュールの`src/main/resources/content`フォルダにあります。`Language[_xx].properties`ファイルでオーバーライドする言語キーを特定します。

チェックポイント：モジュールの言語キーをオーバーライドするために必要な以下の情報がそろっていることを確認してください。

- 言語キー
- バンドルのシンボル名
- サーブレットコンテキスト名

次に、言語キーの新しい値を記述します。

## カスタム言語キーの値を記述する

リソースバンドルローダーとカスタム言語キーを保持する新しいモジュールを作成します。

モジュールの`src/main/resources/content`フォルダで、キーをオーバーライドするロケールごとに[言語プロパティファイル](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application#what-are-language-keys)を作成します。各言語プロパティファイルで、言語キーのオーバーライドを指定します。

次に、モジュールの言語キーをターゲットモジュールのリソースバンドルとして優先順位付けします。

## モジュールのリソースバンドルに優先順位を付ける

言語キーが配置されたので、OSGiマニフェストヘッダーを使用して、ターゲットモジュール用の言語キーを指定します。ターゲットモジュールのリソースバンドルを補完するには、リソースバンドルをターゲットモジュールのリソースバンドルに集約します。最初にモジュールをリストして、そのリソースバンドルをターゲットモジュールのリソースバンドルよりも優先させます。以下のサンプルモジュール`com.liferay.docs.l10n.myapp.lang`では、そのリソースバンドルがターゲットモジュール`com.liferay.blogs.web`のリソースバンドルよりも優先されます。

    Provide-Capability:\
    liferay.resource.bundle;resource.bundle.base.name="content.Language",\
    liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang),(bundle.symbolic.name=com.liferay.blogs.web)";bundle.symbolic.name=com.liferay.blogs.web;resource.bundle.base.name="content.Language";service.ranking:Long="2";\
    servlet.context.name=blogs-web

このサンプルの`Provide-Capability`ヘッダーには、次の2つの部分があります。

1. `liferay.resource.bundle;resource.bundle.base.name="content.Language"`は、モジュールがベース名`content.language`のリソースバンドルを提供することを宣言します。

2. `liferay.resource.bundle;resource.bundle.aggregate:String=...`ディレクティブは、集約するリソースバンドル、ターゲットバンドル、ターゲットバンドルのリソースバンドル名、およびこのサービスのランキングを含むバンドルのリストを指定します。

   - `"(bundle.symbolic.name=com.liferay.docs.l10n.myapp.lang),(bundle.symbolic.name=com.liferay.blogs.web)"`：
このサービスは、バンドル`com.liferay.docs.l10n.myapp.lang`および`com.liferay.blogs.web`からリソースバンドルを集約します。
必要な数のバンドルを集約します。リストされたバンドルは、降順で優先順位が付けられます。
   - `bundle.symbolic.name=com.liferay.blogs.web;resource.bundle.base.name="content.Language"`：
`com.liferay.blogs.web`バンドルの`content.Language`というリソースバンドルをオーバーライドします。

   - `service.ranking:Long="2"`：リソースバンドルのサービスランキングは`2`です。OSGiフレームワークは、`com.liferay.blogs.web`の`content.Language`リソースバンドルをターゲットとする他のすべてのリソースバンドルサービスよりランクが上回る場合、このサービスを適用します。
   - `servlet.context.name=blogs-web`：ターゲットリソースバンドルはサーブレットコンテキスト`blogs-web`にあります。

[モジュールをデプロイ](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#building-and-deploying-a-module)して、オーバーライドした言語キーを確認します。

**ヒント**：オーバーライドが表示されない場合は、[Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)を使用して、競合するリソースバンドルサービスがないか確認してください。ランクが上の別のサービスがある可能性があります。たとえば、集約に`com.liferay.blogs.web`のリソースバンドルが含まれている競合するリソースバンドルサービスがないか確認するには、次のGogo Shellコマンドを実行します。

services "(bundle.symbolic.name=com.liferay.login.web)"

ランキングが高いリソースバンドル集約サービスの結果を検索します。

LiferayのOSGiランタイムでモジュールの言語キーを変更できるようになりました。
オーバーライドする言語キーは、実際にはLiferayのコアにある可能性があることに留意してください。[Liferayの言語キーもオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-language-keys)できます。

## 関連トピック

[Resource Bundle Override Sample Project](/docs/7-1/reference/-/knowledge_base/r/resource-bundle-override)

[Upgrading Core Language Key Hooks](/docs/7-1/tutorials/-/knowledge_base/t/upgrading-core-language-key-hooks)

[Internationalization](/docs/7-1/tutorials/-/knowledge_base/t/internationalization)
