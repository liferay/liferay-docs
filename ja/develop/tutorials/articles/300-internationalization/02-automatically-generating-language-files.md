---
header-id: automatically-generating-language-files
---

# 言語ファイルの自動生成

[TOC levels=1-4]

[ユーザーインターフェイスメッセージの言語キー](/docs/7-1/tutorials/-/knowledge_base/t/localizing-your-application)を保持する`Language.properties`ファイル、またはこれらのキーを保持する言語モジュールがすでにあれば、このチュートリアルを開始する準備ができています。このチュートリアルでは、次の機能について説明します。

- 1つのコマンドによる、サポートされている各ロケールの言語プロパティファイルの生成。 これにより、各ロケールの言語プロパティファイルを手動で作成する必要がなくなります。 また、同じコマンドは、デフォルトの言語ファイルからすべての翻訳ファイルにキーを伝播します。

- MicrosoftのTranslator Text APIを使用した自動翻訳の生成。これにより、各メッセージを手動で翻訳しなくてもよくなります。

## サポートされているロケールの言語ファイルの生成

すべての[サポートされているロケール](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)にファイルを自動的に生成する場合は、アプリケーションのビルドファイルを若干変更する必要があります。

1. モジュールのビルドに[`com.liferay.lang.builder`](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-lang-builder)プラグインが含まれているか、ビルドスクリプトのクラスパスに入れることで確認します。[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)を使用している場合、Lang Builderがモジュールですでに利用可能です。

   `build.gradle`ファイルでの`com.liferay.lang.builder`プラグインの構成は次のように見えます。

       buildscript {
       dependencies {
       classpath 'com.liferay:com.liferay.gradle.plugins.lang.builder:latest.release'
       }
       
       repositories {
       maven {
       url "http://repository-cdn.liferay.com/nexus/content/groups/public"
       }
       }
       }
       
       apply plugin: "com.liferay.lang.builder"
       
       repositories {
       maven {
       url "http://repository-cdn.liferay.com/nexus/content/groups/public"
       }
       }
   
2. `src/main/resources/content`フォルダにデフォルトの`Language.properties`ファイルを作成します（必要な場合）。

3. プロジェクトのルートフォルダから`gradlew buildLang`タスクを実行して、デフォルトの翻訳ファイルを生成します。

   生成されたファイルには、デフォルトの`Language.properties`ファイル内のすべてのキーと値のコピーが含まれています。デフォルトの言語ファイルを変更するたびに`buildLang`タスクを実行します。

   タスクが完了すると、次のログ出力とともに`BUILD SUCCESSFUL`が出力されます。

       Translation is disabled because credentials are not specified
   
   資格情報を提供して翻訳サービスを有効にする方法については、次のセクションを参照してください。

これで、アプリケーションのメッセージの翻訳を開始できます。 Microsoft Translator Text APIを使用して自動翻訳を生成するようにアプリを構成する場合は、引き続き読み進めてください。

## 言語キーの自動翻訳

アプリに`com.liferay.lang.builder`プラグインを設定済みの場合は、ほぼ準備は出来ています。次に、言語キーの自動翻訳を生成できるように、[MicrosoftのTranslator Text API](https://azure.microsoft.com/en-us/services/cognitive-services/translator-text-api/)を設定する必要があります。ただし、LiferayのLang Builderを使用して、HTMLを含む言語キー（`<em>`、`<b>`、`<code>`など）を自動的に翻訳することはできません。HTMLを含む言語キーは、サポートされているすべての言語ファイルに自動的に*コピー*されます。

**注：**これらの翻訳は最初の段階で使用するのに最適です。機械翻訳は、言語に堪能な実在の人物による精度に匹敵するものではありません。繰り返しになりますが、あなたが英語しか話せない状況でハンガリー語の翻訳が必要な場合は、手動翻訳を試みるよりは最適かつ迅速な手段になります。

1. Microsoft Translator Text APIの翻訳サブスクリプションキーを生成します。[こちら](http://docs.microsofttranslator.com/text-translate.html)の手順に従ってください。

2. `translateSubscriptionKey`プロパティを設定して、`buildLang`タスクが翻訳にサブスクリプションキーを使用することを認識していることを確認します。

       buildLang {
       translateSubscriptionKey = "my-key"
       }
   
   セキュリティ上の理由から、アプリケーションのビルドスクリプトに直接渡すことは避けたほうがよいでしょう。代わりに、[ローカルビルド環境](https://docs.gradle.org/current/userguide/build_environment.html)に保存されているプロパティに資格情報を渡し、そのプロパティをアプリケーションのビルドスクリプトに渡してください。

       buildLang {
       translateSubscriptionKey = langTranslateSubscriptionKey
       }
   
上記のすべての手順を実行すると、完全な`buildLang`構成はどのようになるでしょうか。

    buildscript {
    dependencies {
    classpath 'com.liferay:com.liferay.gradle.plugins.lang.builder:latest.release'
    }
    
    repositories {
    maven {
    url "http://repository-cdn.liferay.com/nexus/content/groups/public"
    }
    }
    }
    
    apply plugin: "com.liferay.lang.builder"
    
    buildLang {
    translateSubscriptionKey = langTranslateSubscriptionKey
    }
    
    repositories {
    maven {
    url "http://repository-cdn.liferay.com/nexus/content/groups/public"
    }
    }

 言語ファイルを生成し、言語キーの自動翻訳を提供できるようになりました。
