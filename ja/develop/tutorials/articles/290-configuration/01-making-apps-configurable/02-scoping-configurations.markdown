---
header-id: scoping-configurations
---

# スコープ設定

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アプリケーションは、スコープに応じて異なる構成を持つことができます：仮想インスタンス（別名 `Company`）、サイト（別名 `グループ`）、またはポートレットインスタンスごと。 Configuration Provider API（前のセクションで示した標準OSGi Configuration Admin APIに基づく）がこれを処理します。

構成のスコープは、構成値が設定またはオーバーライドされるスコープを指定しています。 粒度の低いスコープで設定されたものはすべて、構成のデフォルトです。 構成の現在のスコープで常にオーバーライドできます。 たとえば、サイトスコープの構成では、システムスコープで（システム設定を使用して）デフォルトを設定できます。 ただし、サイトスコープで構成が変更されると、上位レベルのスコープは永久に無視されます。 同じスコープの他の場所で構成することもできます。 データベースレベルでは、値が必要なコンテキストが別のサイトである場合、1つのサイトに設定された値は関係ないため、アプリケーションには複数の構成値があり、すべてサイトレベルにスコープされる可能性があります。 これについては、ここ</a>詳しく説明します

。</p> 

構成のスコープを設定する方法は次のとおりです。

1.  構成インターフェースでスコープを設定します。

2.  構成Bean宣言を作成して、スコープ検索の構成を有効にします。

3.  構成プロバイダーでスコープ構成を取得します。

3番目のステップは、 [構成プロバイダー](/docs/7-1/tutorials/-/knowledge_base/t/reading-configuration-values-from-a-configuration-provider) チュートリアルで説明されています。 この記事では、最初の2つのステップについて説明します。 構成インターフェースでスコープを設定することから始めます。



## ステップ1：構成スコープの設定

`@ExtendedObjectClassDefinition` アノテーションを使用して、構成のスコープを指定します。 選択するスコープは、 [構成プロバイダー](/docs/7-1/tutorials/-/knowledge_base/t/reading-configuration-values-from-a-configuration-provider) 構成プロバイダーを介して構成オブジェクトが取得される方法と一致する必要があります。 これらの有効なスコープオプションの1つを `@ExtendedObjectClassDefinition`渡します。

`Scope.GROUP`：サイトスコープ用 `Scope.COMPANY`：仮想インスタンススコープ用 `Scope.SYSTEM`：システムスコープ用 `Scope.PORTLET_INSTANCE`：ポートレットインスタンススコープ用

次に例を示します。

    @ExtendedObjectClassDefinition(
        category = "dynamic-data-mapping",
        scope = ExtendedObjectClassDefinition.Scope.GROUP
    )
    @Meta.OCD(
        id = "com.liferay.dynamic.data.mapping.form.web.configuration.
            DDMFormWebConfiguration",
        localization = "content/Language", 
        name = "ddm-form-web-configuration-name"
    )
    
    public interface DDMFormWebConfiguration {
    

スコーププロパティにより、システム設定に表示されるため、管理者はその値を変更できます。 将来のリリースでは、追加の目的に役立つ可能性があります。



## 手順2：スコープ付き検索の構成を有効にする

構成スコープを設定する場合、同じスコープから構成値を取得する必要があります。 スコープ構成を取得するには、 [構成プロバイダー](/docs/7-1/tutorials/-/knowledge_base/t/reading-configuration-values-from-a-configuration-provider)使用します。

    JournalGroupServiceConfiguration configuration =
        configurationProvider.getGroupConfiguration(
            JournalGroupServiceConfiguration.class, groupId);
    

これは、構成プロバイダーからサイトスコープの構成を取得するJournalモジュールの例です。 構成のスコープ付き取得を有効にするには、アプリケーションの構成を `ConfigurationBeanDeclaration`登録する必要があります。

| **注：** `ConfigurationProvider` はLiferayのカーネルAPIの一部です。それを使用するには新しい依存関係が必要です。 ただし、その実装は|として配布されます。 `portal-configuration-module-configuration`モジュールです。インストール済み。

構成Bean宣言を作成するには：

1.  `ConfigurationBeanDeclaration`実装して、構成クラスを登録します。
   
        @Component
        public class JournalGroupServiceConfigurationBeanDeclaration
            implements ConfigurationBeanDeclaration {
       

2.  このクラスには、前のセクションで作成したインターフェイスのクラスを返すメソッドが1つあります。 これにより、システムは構成の変更が発生したときにそれを追跡し、構成の要求を非常に高速に行うことができます。
   
        @Override
        public Class<?> getConfigurationBeanClass() {
            return JournalGroupServiceConfiguration.class;
        }
       



## ステップ3：スコープ設定の取得

構成スコープを設定した場合、同じスコープから構成値を取得する必要があります。 スコープ構成を取得するには、 [構成プロバイダー](/docs/7-1/tutorials/-/knowledge_base/t/reading-configuration-values-from-a-configuration-provider)使用します。

    JournalGroupServiceConfiguration configuration =
        configurationProvider.getGroupConfiguration(
            JournalGroupServiceConfiguration.class, groupId);
    

これは、構成プロバイダーからサイトスコープの構成を取得するJournalモジュールの例です。 `groupId` 変数は、構成値を読み取るサイトを識別するため重要です。

これですべてです。 これで、構成のスコープが設定され、スコープ検索がサポートされます。
