---
header-id: categorizing-the-configuration
---

# 構成の分類

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アプリケーションやサービスを簡単に構成できるため、デフォルトで@product@にはすでに多くの構成オプションがあります。 カスタムアプリケーションとサービスを展開している場合は、さらに多くがあります。 ポータル管理者が適切な構成オプションを見つけやすくするために、自動生成されたシステム設定UIで構成のカテゴリを指定します。

<!-- Here's how the System Settings UI looks:

IMAGES NEED TO BE UPDATED WHEN UI STABILIZES ![Figure 1: Navigate to the Control Panel, click on *Configuration* and then
*System Settings*. You'll find five categories of configurations, including
Other. Click on any configuration to access a form through which the
configuration values can be updated.](../../images/system-settings-ui.png) -->

デフォルトでは、次のシステム設定セクションが定義されています。 利用可能なすべてのカテゴリは、これらのセクションの下にネストされています。

1.  コンテンツ管理

2.  ソーシャル

3.  プラットフォーム

4.  セキュリティ

5.  コマース

6.  その他

| **注：** セクションが少なくとも1つの構成カテゴリを含む場合に表示されます。 |カテゴリに少なくとも1つの構成が含まれている場合に表示されます。 目に見える|セクションとカテゴリはデプロイされたモジュールに依存します。

カテゴリを指定しない場合、アプリケーションの構成はプラットフォーム→サードパーティにあります。 通常、構成を既存のカテゴリに配置するか、独自のカテゴリを作成します。

<!-- Add Figure of Platform Third Party section when UI stabilizes -->.

## 構成カテゴリの指定

Liferay Forms構成インターフェイス（Forms & ワークフロースイートのLiferay動的データマッピングフォームWebモジュールにあります）のソースコードを見ると、上記のコードスニペットに何かが残っていることがわかります。 `@ Meta.OCD` アノテーションの直前には

``` 
    @ExtendedObjectClassDefinition(
        category = "dynamic-data-mapping",
        scope = ExtendedObjectClassDefinition.Scope.GROUP
    )
```

このアノテーションは2つのことを行います。

1.  `dynamic-data-mapping` カテゴリーを指定します。

2.  構成の [スコープ](/docs/7-1/user/-/knowledge_base/u/system-settings#configuration-scope) を設定します。

`@ExtendedObjectClassDefinition` クラスの完全修飾クラス名は `com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition`です。

注：システム設定で使用されるインフラストラクチャでは、 `configurationPid` がインターフェイスの完全修飾クラス名と同じであると想定しています。 それらが一致しない場合、 `ExtendedObjectClassConfiguration`介して情報を提供できません。

`@ExtendedObjectClassDefinition` アノテーションを介して配信される `com.liferay.portal.configuration.metatype` モジュール、そのことができます [の依存関係として設定](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)。

## 新しいセクションとカテゴリの作成

管理者が見つけられるように、構成は最も直感的な場所（セクションとカテゴリ）に配置する必要があります。 構成が既存のカテゴリまたはカテゴリセクションに収まらない場合は、 `ConfigurationCategory` インターフェイスを実装して独自の構成を作成します。

*コンテンツ管理* セクションと *ダイナミックデータマッピング* カテゴリを作成するコードは次のとおりです。

``` 
    @Component
    public class DynamicDataMappingConfigurationCategory
        implements ConfigurationCategory {

        @Override
        public String getCategoryIcon() {
            return "dynamic-data-list";
        }

        @Override
        public String getCategoryKey() {
            return _KEY;
        }

        @Override
        public String getCategorySection() {
            return _CATEGORY_SET_KEY;
        }

        private static final String _CATEGORY_SET_KEY = "content-management";

        private static final String _KEY = "dynamic-data-mapping";

    }
```

`getCategorySection` メソッドは、新しいセクションのキーを含むストリングを返します。 同様に、 `getCategoryKey` は新しいカテゴリのキーを返します。 モジュールの `src / main / resources / content / Language.properties` ファイルでこれらのキーのローカライズされた値を提供します。

次に、アプリケーションの構成のスコープを指定します。
