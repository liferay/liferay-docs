---
header-id: localizing-your-application
---

# アプリケーションのローカライズ

[TOC levels=1-4]

Liferayのアプリケーションを作成しているあなたは、非常に優れた才能のある人物であり、そのアプリケーションは世界中で使用されることでしょう。さらに、そのメッセージを対象国の言語に翻訳できれば、なおよいでしょう。
Liferayを使えば、アプリケーションの言語キーの翻訳を簡単に行うことができます。

**注：**アプリケーションを複数の言語に翻訳する予定がない場合でも、ユーザーインターフェイスに表示されるメッセージには、ここに示すローカライズパターンを使用してください。メッセージのインスタンスをそれぞれ検索してJSPおよびJavaクラスで置換するよりも、言語プロパティファイルを更新することでメッセージを変更する方がはるかに簡単です。

必要な作業は、デフォルトの言語プロパティファイル（`Language.properties`）と、サポートする対象言語ごとに1つのファイル（たとえば、フランス語の翻訳の場合は `Language_fr.properties` ）を作成し、それをアプリケーションの正しい場所に配置するだけです。翻訳する言語に対応する2文字のロケールをファイル名に使用します（たとえば、`Language_es.properties`は各キーにスペイン語翻訳を提供します）。

<!-- Discuss adding a new locale? -->

アプリケーションのローカライズのトピック：

- [言語キーとは](#what-are-language-keys)
- [デフォルトで利用可能なロケール](#what-locales-are-available-by-default)
- [言語ファイルの配置場所](#where-do-i-put-language-files)
- [言語モジュールの作成](#creating-a-language-module)
- [言語モジュールの使用](#using-a-language-module)
- [ @product@の言語プロパティの使用](#using-liferays-language-properties)

## 言語キーとは

各言語プロパティファイルには、キーと値のペアが含まれています。キーはすべての言語プロパティファイルで同じですが、値は各ファイルで変換されます。ユーザーインターフェイスコードでキーを指定すると、Liferayで使用されているロケールに応じて、適切に翻訳されたメッセージがユーザーに自動的に返されます。Liferayをローカルで実行している場合は、翻訳内容を確認するにはサポートされているロケールを含むURLを追加します（たとえば、`localhost:8080/es`と入力します）。

![図1：実行中のLiferayのURLにロケールを追加し、Liferayの実際の翻訳能力をご覧ください。](../../images/locale-message-spain.png)

言語キーは、ユーザーインターフェイスコードでハードコードされ、完全に変換された文字列値の代わりに使用するキーにすぎません。JSPでは、言語キーに`<liferay-ui:message />`タグを使用します。

メッセージをハードコーディングする場合は、次のようなタグを使用します。

    <liferay-ui:message key="Howdy, Partner!" />

その場合、アプリケーションでは大文字と小文字が正しく区別されたメッセージが表示されます。

代わりに、最終値に単純なキーを指定します。

    <liferay-ui:message key="howdy-partner" />

このようにして、デフォルトの言語プロパティファイル（`Language.properties`）でキーの翻訳を提供できます。

    howdy-partner=Howdy, Partner!

いずれにしても、同じ出力が得られます。プロパティファイルを使用すると、すべてのメッセージを1か所に配置でき、さらに言語プロパティファイルを追加して後で翻訳することができます。必要なのは、翻訳に対応しているロケールがあることを確認することだけです。

ロケールが指定されていない場合、デフォルトの`Language.properties`ファイルの値が表示されます。ロケールが指定されている場合は、そのローカルに対応するファイルからキーが取得されます。たとえば、スペイン語の翻訳が必要な場合は、適切な値を提供するために`Language_es.properties`ファイルが存在する必要があります。存在しない場合、デフォルトの言語プロパティ（`Language.properties`ファイルから）が使用されます。

## デフォルトで利用可能なロケール

Liferayにはデフォルトで多数のロケールが用意されています。`portal.properties`[ファイル](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)で確認できます。

    locales=ar_SA,eu_ES,bg_BG,ca_AD,ca_ES,zh_CN,zh_TW,hr_HR,cs_CZ,da_DK,nl_NL,
    nl_BE,en_US,en_GB,en_AU,et_EE,fi_FI,fr_FR,fr_CA,gl_ES,de_DE,el_GR,
    iw_IL,hi_IN,hu_HU,in_ID,it_IT,ja_JP,ko_KR,lo_LA,lt_LT,nb_NO,fa_IR,
    pl_PL,pt_BR,pt_PT,ro_RO,ru_RU,sr_RS,sr_RS_latin,sl_SI,sk_SK,es_ES,
    sv_SE,tr_TR,uk_UA,vi_VN

これらのロケールのいずれかの翻訳を提供するには、翻訳されたキーを含むファイル名にロケールを指定します（たとえば、`Langauge_es.properties`はスペイン語の翻訳を保持します）。

## 言語ファイルの配置場所

すべてのアプリケーションのビュー（たとえば、すべてのJSP）とポートレットコンポーネントを保持するモジュールが1つしかないアプリケーションでは、そのモジュールに`src/main/resources/content`フォルダを作成し、`Language.properties`および`Language_xx.properties`ファイルをそこに配置します。

その後、モジュール内のポートレットコンポーネント（`-Portlet`クラスの`@Component`アノテーション）に次のプロパティが含まれていることを確認します。

    "javax.portlet.resource-bundle=content.Language"

翻訳された言語プロパティファイルを提供し、ポートレットコンポーネントで`javax.portlet.resource-bundle`プロパティを指定するだけで、翻訳時に@product@を指定できます。ユーザーには、選択したロケールの翻訳が表示されます。

より複雑で、適切にモジュール化されたアプリケーションでは、ポートレットコンポーネントとJSPファイルを提供する複数のモジュールに言語キーが分散されている場合があります。 
さらに、モジュール間でかなりの数の重複した言語キーが存在する場合があります。便利なことに、モジュールごとに言語プロパティファイルを管理する必要はありません。

## 言語モジュールの作成

モジュール性を重視している場合は（そうであるべきです）、ビューレイヤーを提供する複数のモジュールを備えたアプリケーションを作成できます。これらのモジュールは、多くの場合Webモジュールと呼ばれます。

    my-application/
    my-application-web/
    my-admin-application-web/
    my-application-content-web/
    my-application-api/
    my-application-service/

これらの各モジュールには、維持する言語キーと翻訳を含めることができ、おそらく重複キーが存在します。同じキーに対して異なる値を使用したり、言語キーを複数の場所で管理するのは避けたいことです。この場合は、モジュール性にさらに気を配り、新しいモジュールを作成する必要があります。これを言語モジュールと呼びます。

ルートプロジェクトフォルダ（サービス、API、およびWebモジュールを保持するフォルダ）で、アプリの言語キーを保持する[新しいモジュールを作成](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development#creating-a-module)します。たとえば、以下は`my-application-lang`という言語モジュールのフォルダ構造を示しています。

    my-application-lang/
    bnd.bnd
    src/
    main/
    resources/
    content/
    Language.properties
    Language_ar.properties
    Language_bg.properties
    ...

言語モジュールで、`src/main/resources/content`フォルダを作成します。ここに言語プロパティファイルを配置します。`Language.properties`ファイルは次のようになります。

    application=My Application
    add-entity=Add Entity

必要な翻訳を作成し、言語ファイル名に翻訳ロケールIDを追加します。 ファイル`Language_es.properties`は次のようになります。

    my-app-title=Mi Aplicación
    add-entity=Añadir Entity

言語モジュールの構築時に、@product@の`ResourceBundleLoaderAnalyzerPlugin`は`content/Language.properties`ファイルを検出し、モジュールにリソースバンドル[*機能*](http://blog.osgi.org/2015/12/using-requirements-and-capabilities.html)を追加します。機能は、@product@のOSGiフレームワークに対してモジュールが宣言する契約です。機能を使用すると、サービスを提供するモジュールにサービスを関連付けることができます。この場合、@product@はリソースバンドル機能の[ResourceBundleLoader](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html)サービスを登録します。

次に、言語モジュールのリソースバンドルを使用するようにWebモジュールを構成します。

## 言語モジュールの使用

モジュールまたは従来のLiferayプラグインは、別のモジュールのリソースバンドルを使用し、オプションで独自のリソースバンドルを含めることができます。OSGiマニフェストヘッダーの`Require-Capability`と`Provide-Capability`を使用することで可能になります。これはLiferayプロジェクトテンプレートから生成されたモジュールでは特に簡単です。
言語モジュールの使用手順は、次の環境に分かれています。

- [モジュールから言語モジュールを使用する](#using-a-language-module-from-a-module)
- [従来のプラグインから言語モジュールを使用する](#using-a-language-module-from-a-traditional-plugin)

MavenまたはGradleでbndを使用している場合、Liferayの`-liferay-aggregate-resource-bundle:` bnd命令を指定するだけで、ビルド時にLiferayのbndプラグインがその命令を`Require-Capability`および`Provide-Capability`パラメーターに自動的に変換します。両方のアプローチについて説明します。

### モジュールから言語モジュールを使用する

Liferayプロジェクトテンプレートから生成されたモジュールには、`-liferay-aggregate-resource-bundles`と呼ばれるLiferay bndビルド時の指示があります。これにより、他のリソースバンドル（言語キーなど）を独自のものと一緒に使用できます。
方法は次のとおりです。

1. モジュールの`bnd.bnd`ファイルを開きます。

2. `-liferay-aggregate-resource-bundles:` bnd命令を追加し、それに現在のモジュールのリソースバンドルと集約するリソースバンドルを持つモジュールのバンドルシンボル名を割り当てます。

       -liferay-aggregate-resource-bundles: \
       [bundle.symbolic.name1],\
       [bundle.symbolic.name2]
   
たとえば、モジュール`com.liferay.docs.l10n.myapp1.lang`および`com.liferay.docs.l10n.myapp2.lang`のリソースバンドルを使用するモジュールは、以下を`bnd.bnd`ファイルに設定します。

    -liferay-aggregate-resource-bundles: \
    com.liferay.docs.l10n.myapp1.lang,\
    com.liferay.docs.l10n.myapp2.lang

現在のモジュールのリソースバンドルは、リストされているモジュールのリソースバンドルよりも優先されます。

[共有言語キーのサンプルプロジェクト](/docs/7-1/reference/-/knowledge_base/r/shared-language-keys)は、リソースバンドルの集約を示す実用的な例です。Gradle、Maven、Liferay Workspaceのビルド環境にデプロイできます。

ビルド時に、Liferayのbndプラグインはbnd命令を`Require-Capability`および`Provide-Capability`パラメーターに自動的に変換します。従来のLiferayプラグインでは、パラメーターを手動で指定する必要があります。

**注：**次のセクションで示すように、`Require-Capability`および`Provide-Capability` OSGiマニフェストヘッダーはいつでも手動で指定できます。

### 従来のプラグインから言語モジュールを使用する

言語モジュールを使用するには、従来のLiferayプラグインから、プラグインの`liferay-plugin-package.properties`ファイルの`Require-Capability`および`Provide-Capability`  OSGiマニフェストヘッダーを使用して言語モジュールを指定する必要があります。

言語モジュールを使用するように従来のプラグインを構成するには、次の手順に従います。

1. プラグインの`liferay-plugin-package.properties`ファイルを開き、言語モジュールのリソースバンドル機能をフィルタリングする`Require-Capability`ヘッダーを追加します。たとえば、言語モジュールのシンボル名が`myapp.lang`の場合、次のように要件を指定します。

       Require-Capability: liferay.resource.bundle;filter:="(bundle.symbolic.name=myapp.lang)"
   
2. 同じ`liferay-plugin-package.properties`ファイルで、言語モジュールのリソースバンドルをこのプラグイン（`myapp.web`プラグイン）の独自のリソースバンドル*として*追加する`Provide-Capability`ヘッダーを追加します。

       Provide-Capability:\
       liferay.resource.bundle;resource.bundle.base.name="content.Language",\
       liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=myapp.lang)";bundle.symbolic.name=myapp.web;resource.bundle.base.name="content.Language";service.ranking:Long="4";\
       servlet.context.name=myapp-web
   
この場合、`myapp.web`プラグインは、言語モジュールのリソースバンドルのみを使用します。リソースバンドルの集計には、言語モジュール`myapp.lang`のみが含まれます。

リソースバンドルの集約は、プラグインのリソースバンドル*に加えて*言語モジュールのリソースバンドルを使用する場合に役立ちます。これらの手順は、現在のプラグインのリソースバンドルを言語モジュールのリソースバンドルよりも優先させながら、これを行う方法を示しています。このようにして、言語モジュールの言語キーはプラグインの言語キーを補完します。

たとえば、バンドルのシンボル名が`myapp.web`であるポートレットは、独自のキーに加えて、言語モジュール`myapp.lang`のキーを使用します。ポートレットの`Provide-Capability`および`Web-ContextPath` OSGiヘッダーが使用されます。

    Provide-Capability:\
    liferay.resource.bundle;resource.bundle.base.name="content.Language",\
    liferay.resource.bundle;resource.bundle.aggregate:String="(bundle.symbolic.name=myapp.web),(bundle.symbolic.name=myapp.lang)";bundle.symbolic.name=myapp.web;resource.bundle.base.name="content.Language";service.ranking:Long="4";\
    servlet.context.name=myapp-web

サンプルの`Provide-Capability`ヘッダーには2つの部分があります。

1. `liferay.resource.bundle;resource.bundle.base.name="content.Language"`は、モジュールがベース名が`content.language`のリソースバンドルを提供することを宣言します。

2. `liferay.resource.bundle;resource.bundle.aggregate:String=...`ディレクティブは、リソースバンドルが集約されるバンドルのリスト、ターゲットバンドル、ターゲットバンドルのリソースバンドル名、およびこのサービスのランキングを指定します。

   - `"(bundle.symbolic.name=myapp.web),(bundle.symbolic.name=myapp.lang)"`：このサービスは、バンドル`bundle.symbolic.name=myapp.web`（現在のモジュール）および`bundle.symbolic.name=myapp.lang`からリソースバンドルを集約します。 
必要な数のバンドルを集約します。 リストされたバンドルは、降順で優先順位が付けられます。
   - `bundle.symbolic.name=myapp.web;resource.bundle.base.name="content.Language"`：`myapp.web`バンドルの`content.Language`という名前のリソースバンドルをオーバーライドします。
   - `service.ranking:Long="4"`：リソースバンドルのサービスランキングは`4`です。OSGiフレームワークは、`myapp.web`の`content.Language`リソースバンドルをターゲットとする他のすべてのリソースバンドルサービスよりランクが上の場合に、このサービスを適用します。
   - `servlet.context.name=myapp-web`：ターゲットリソースバンドルは、サーブレットコンテキスト`myapp-web`にあります。

これで、集約されたリソースバンドルの言語キーがプラグインの言語キーを補完します。

@product@のコア言語キーもモジュールで利用できることをご存知ですか。次にそれについて説明します。

## Liferayの言語プロパティの使用

@product@のソースコードがある場合は、`portal-impl/src/main/content`フォルダで、@product@のコア言語プロパティを確認できます。それ以外の場合は、Liferayバンドルにある`portal-impl.jar`で確認できます。

    liferay-portal/portal-impl/src/content/Language_xx.properties
    
    [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

これらのキーは実行時に使用できるため、@product@のデフォルトキーのいずれかをユーザーインターフェイスコードで使用すると、適切に変換された値に自動的に交換されます。可能な限り@product@のキーを使用することで時間を節約し、アプリケーションがLiferayのUI規約に従うようにします。

サポートされている各ロケールの言語ファイルを自動的に生成する場合、またはMicrosoft Translator APIを使用して翻訳を自動的に生成するようにアプリケーションを構成する場合は、[Automatically Generating Language Files](/docs/7-1/tutorials/-/knowledge_base/t/automatically-generating-language-files)のチュートリアルをご覧ください。
