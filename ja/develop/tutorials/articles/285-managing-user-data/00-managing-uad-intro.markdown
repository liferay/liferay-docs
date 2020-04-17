---
header-id: managing-user-associated-data-stored-by-custom-applications
---

# カスタムアプリケーションによって保存されたユーザー関連データの管理

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product-ver@は、管理者が</a> ユーザー関連データ（UAD）を

削除または匿名化できるようにし、EUの一般データ保護規則（GDPR）に準拠するための便利なツールを提供します。 初期状態では、このツールはLiferayアプリケーション（ブログ、Webコンテンツなど）のみをサポートしていますが、カスタムアプリに保存されているデータを匿名化することもできます。</p> 

アプリがService Builderを使用して作成された場合、匿名化は簡単です。 次の手順を実行します：

1.  サービスモジュールのビルドスクリプトに、 `com.liferay.petra.string` および `com.liferay.portal.kernel` 依存関係を含めます。

2.  サービスモジュールの `service.xml` ファイルで匿名化する必要があるフィールドを特定します。

3.  Service Builderを実行します。 生成される `-uad` モジュールのビルドスクリプトを提供します。

4.  アプリケーションの名前を匿名化UIに提供します。 この手順をスキップすると、 `-uad` モジュールの `bnd.bnd` ファイルから `Bundle-SymbolicName` を使用してアプリにラベルが付けられます。

Service Builderを使用して作成されていないアプリの匿名化については、別途説明します。



## 依存関係を含める

Service Builderが生成するコードをコンパイルするには、サービスモジュールの `build.gradle`にあるPetraおよび3.23.0以降のLiferay `カーネル` に依存する必要があります。

    dependencies {
        compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.23.0"
        compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "2.0.0"
        ...
    }
    



## 匿名化するフィールドを選択

次に、 `-service` モジュールの `service.xml` ファイルの要素に匿名化属性を添付して、匿名化するフィールドを識別する必要があります。 これを行うには2つの方法があります。

  - `uad-anonymize-field-name` 属性は、UAD削除プロセスで匿名ユーザーの値に置き換えられる値を持つフィールドを示します。

  - `uad-nonanonymizable` 属性は、自動的に匿名化できないデータを示し、管理者が確認する必要があります。

たとえば、ブログアプリケーションでは、 `service.xml``userName` 列に `uad-anonymize-field-name = "fullName"` が追加されます。

    <column name="userName" type="String" uad-anonymize-field-name="fullName" />
    

これは、ブログエントリの作成者のユーザー名を、匿名ユーザーの `fullName`置き換える必要があることを示しています。

対照的に、ブログ投稿のコンテンツは自動的に匿名化することはできません。

    <column name="content" type="String" uad-nonanonymizable="true" />
    

`uad-nonanonymizable` 値 `true` は、ブログ作成者のUADを削除するために、管理者が `コンテンツ` フィールドを確認する必要があることを示します。



## Service Builder \！を実行します。

この時点で、Service Builderを実行する準備が整いました。 これにより、 `service.xml`追加した値に基づいて、新しい `-uad` モジュールが生成されます。 新しいモジュールはビルドスクリプトなしで生成されるため、提供する必要があります。 これは、依存関係を含まなければならない `osgi.service.component.annotations`、 `カーネル`、 `ペトラ`、 `-api` UADアプリケーションのモジュール、ならびに独自のアプリケーションの `-api` モジュール。 ビルドスクリプトは次のようになります。

    dependencies {
      compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "3.23.0"
      compileOnly group: "com.liferay", name: "com.liferay.user.associated.data.api", version: "3.0.2"
      compileOnly group: "com.liferay", name: "com.liferay.petra.string", version: "1.0.0"
      compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations", version: "1.3.0"
      compileOnly project(":modules:custom:custom-api")
      ...
      }
    

この時点で、アプリケーションをコンパイルできます。 ただし、展開する前に、UADアプリケーションが管理者にとって意味のある方法でそれを認識していることを確認する必要があります。

| **注：** プロジェクトの作成方法によって異なります。たとえば、使用した場合| Liferay Dev Studioではなく、BladeのService Builderテンプレート---する前に、新しい `-uad` モジュールを `settings.gradle` ファイルに含める|コンパイル：| | 「myapp-api」、「myapp-service」、「myapp-uad」が含まれます



## アプリの名前をUIに提供する

アプリの名前を匿名化UIに提供する最も簡単な方法は、 `Language.properties` ファイルに言語キーを含めることです： `application.name。[Bundle-SymbolicName]=` ここで、括弧で囲まれたテキストは、 `-uad` モジュールの `bnd.bnd` ファイルからの `Bundle-SymbolicName` です。 例： `application.name.com.liferay.docs.custom.portlet = Custom App`

これはカスタムアプリの推奨されるアプローチですが、@product@自体のソースコードを見ると、使用されていないことがわかります。 何故なの？ 単一のアプリケーションにラベルを付けるために複数の言語キーを作成するという欠点があるため、混乱を招く可能性があります。 言語キーの重複を避けるため、Liferayアプリケーションは `com.liferay.lang.mergerを使用し<code> 。` プラグイン。 これは次のようなものです。

    apply plugin: "com.liferay.lang.merger"
    
    dependencies {
    
    ...
    
    }
    
    mergeLang {
        setting("../blogs-web/src/main/resources/content") {
            transformKey "javax.portlet.title.com_liferay_blogs_web_portlet_BlogsPortlet", "application.name.com.liferay.blogs.uad"
        }
    
        sourceDirs = ["../blogs-web/src/main/resources/content"]
    }
    

これは、@product@のBlogsアプリケーションにある `-uad` モジュールの `build.gradle` ファイルからのものです。 `設定` プロパティは、 `Language.properties` ファイルの場所を識別します（Gradle規則により、 `sourceDirs` プロパティは `設定`一致する必要があります）。 `transformKey` は、最初にアプリケーション名の言語キーを渡し、次に `-uad` モジュールの `bnd.bnd` ファイルから `Bundle-SymbolicName` を渡します。 プラグインは最初のパラメーターの値を取得し、2番目のパラメーターに割り当てます。 最終結果は、 `Language.properties` キーが匿名化UIにアプリケーションの名前を提供することです。ただし、追加の言語キーを作成または維持する必要はありません。

それでおしまい\！ アプリに保存されたユーザー関連データを削除または匿名化できるようになりました。
