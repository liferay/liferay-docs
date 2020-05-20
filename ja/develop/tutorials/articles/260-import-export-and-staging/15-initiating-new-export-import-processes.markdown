---
header-id: initiating-new-export-import-processes
---

# 新しいエクスポート/インポートプロセスの開始

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ステージングおよびエクスポート/インポート機能は、サイトを作成、管理、および公開するための構成要素です。 これらの機能は、 *Publishing Tools* メニューからアクセスできます。 ただし、これらのプロセスをプログラムで開始することもできます。 これにより、新しいインターフェイスを提供したり、独自のアプリケーションでこれらの機能の機能を模倣したりできます。

アプリケーションのアセットをステージングする機能を提供すると、アプリケーションの使用がサイト管理者にとってより使いやすくなります。 新しいアセットは、公開の準備ができるまでオフサイトのどこかに保存する必要がなくなりました。 それらをステージング環境に公開し、使いやすさをテストして、ページに保存できます。 公開のタイミングが適切になったら、マウスを1回クリックするだけで、アプリケーションのアセットをライブサイトに公開できます。 エクスポート/インポート機能は同様の便利さを提供します：別の場所で使用するためにアプリケーションのアセットをエクスポートしたい場合、またはデータをクリアしてコピーを保存する必要がある場合、エクスポート機能を実装できます。 インポート機能を実装すると、アセット/データをアプリケーションに戻すことができます。

エクスポート/インポートまたはステージングプロセスを開始するには、 [ExportImportConfiguration](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/model/ExportImportConfiguration.html) オブジェクトを渡す必要があります。 このオブジェクトは、エクスポート/インポートの実行中に必要な多くのパラメーターと設定をカプセル化します。 必要なすべてのデータを含む単一のオブジェクトを持つことで、これらのフレームワークをすばやく簡単に実行できます。

たとえば、エクスポート機能を実装する場合、 [ExportImportService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportService.html) インターフェイスによって提供されるサービスを呼び出す必要があります。 このインターフェイスのすべてのメソッドには、 `ExportImportConfiguration` オブジェクトが必要です。 @product@は、これらの構成オブジェクトを生成する方法を提供するため、サービスメソッドで簡単にそれらを渡すことができます。

また、 `ExportImportConfiguration` は@product@エンティティであり、 `User` または `Group`に似ていることを知っておくことが重要です。 これは、 `ExportImportConfiguration` フレームワークがローカルおよびリモートのサービス、モデル、永続クラスなどを提供することを意味します。

このチュートリアルでは、 `ExportImportConfiguration` フレームワークと、提供されたサービスとファクトリを利用してこれらのコントローラーオブジェクトを作成する方法について学習します。 作成したら、必要なインポート/エクスポート機能を簡単に実装できます。

最初のステップは、 `ExportImportConfiguration` オブジェクトを作成し、それを使用してカスタムのエクスポート/インポートまたはステージングプロセスを開始することです。

1.  Export / Import Configurationファクトリクラスを使用して、 `ExportImportConfiguration` オブジェクトを構築します。 一般的な方法は次のとおりです。
   
        Map<String, Serializable> exportLayoutSettingsMap =
            ExportImportConfigurationSettingsMapFactory.
                buildExportLayoutSettingsMap(...);
       
        ExportImportConfiguration exportImportConfiguration =
            exportImportConfigurationLocalService.
                addDraftExportImportConfiguration(
                    user.getUserId(),
                    ExportImportConfigurationConstants.TYPE_EXPORT_LAYOUT,
                    exportLayoutSettingsMap);

    この例では、 [ExportImportConfigurationSettingsMapFactory](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationSettingsMapFactory.html) を使用して、レイアウトのエクスポート設定マップを作成します。 次に、このマップをパラメーターとして使用して、エンティティのローカルサービスインターフェイスで *add* メソッドを呼び出して `ExportImportConfiguration` を作成します。 [ExportImportConfigurationLocalService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportConfigurationLocalService.html) は、カスタム `ExportImportConfiguration`を作成および変更するためのいくつかの便利なメソッドを提供します。

    `ExportImportConfigurationSettingsMapFactory` は、レイアウトとポートレットのインポート、エクスポート、公開など、さまざまなシナリオの設定マップを作成するための多くの `ビルド` メソッドを提供します。 この特定のシナリオの例については、 [UserGroupLocalServiceImpl.exportLayouts（...）](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/service/impl/UserGroupLocalServiceImpl.html#exportLayouts-long-java.util.Map-) および [GroupLocalServiceImpl.addDefaultGuestPublicLayoutsByLAR（...）](@platform-ref@/7.1-latest/javadocs/portal-impl/com/liferay/portal/service/impl/GroupLocalServiceImpl.html#addDefaultGuestPublicLayoutsByLAR-com.liferay.portal.kernel.model.Group-java.io.File-)を参照できます。

    `ExportImportConfiguration` オブジェクトの作成中に役立つ、このフレームワークによって提供される他の2つの重要なファクトリーがあります。

      - [ExportImportConfigurationFactory](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationFactory.html)：このファクトリーは、デフォルトのローカル/リモート公開に使用される `ExportImportConfiguration` オブジェクトをビルドします。
      - [ExportImportConfigurationParameterMapFactory](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/configuration/ExportImportConfigurationParameterMapFactory.html)：このファクトリーは、エクスポート/インポートおよび公開中に必要なパラメーターマップを作成します。

2.  適切なサービスを呼び出して、エクスポート/インポートまたはステージングプロセスを開始します。 ：あなたは、エクスポート、インポート、およびステージングの例で使用できる二つの重要なサービス・インターフェースがあります [ExportImportLocalService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/ExportImportLocalService.html) 及び [StagingLocalService](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/service/StagingLocalService.html)。 前の手順のサンプルコードスニペットでは、レイアウトのエクスポートを目的とした `ExportImportConfiguration` オブジェクトを作成しました。 そのプロセスを開始する方法は次のとおりです。

        ファイル[0] = exportImportLocalService.exportLayoutsAsFile（ exportImportConfiguration）;

    このインターフェイスのメソッドを呼び出すことにより、レイアウトを@product@から `java.io.File` 配列にエクスポートします。 `ExportImportConfiguration` オブジェクトがメソッドに必要な唯一のパラメーターであることに注意してください。 Your configuration object holds all the required parameters and settings necessary to export your layouts from @product@. このサンプルコードは@product@にありますが、独自のプロジェクトからこのフレームワークを簡単に使用できます。

    | **注：** エクスポート/インポートまたはステージングサービスメソッドを呼び出さない場合| OSGiモジュールからは、インターフェースを使用しないでください。 ライフレイ| OSGiコンテナーは、インターフェース参照を自動的に処理します。これが理由です。インターフェイスの使用はモジュールで許可されています。 お電話の場合|モジュール外のエクスポート/インポートまたはステージングサービスメソッドを使用する場合は、それらのサービスUtilクラス（例： `ExportImportLocalServiceUtil`）。

簡単です！ 独自のエクスポート/インポートまたはステージングプロセスを開始するには、提供されている3つの `ExportImportConfiguration` ファクトリーの組み合わせを使用して、 `ExportImportConfiguration` オブジェクトを作成する必要があります。 構成オブジェクトを取得したら、目的のプロセスを開始するために、エクスポート/インポートまたはステージングインターフェイスで利用可能な多くのサービスメソッドの1つでパラメーターとして提供します。
