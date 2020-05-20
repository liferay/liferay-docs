---
header-id: getting-started-with-the-documents-and-media-api
---

# Documents and Media APIの使用開始

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Documents and Media APIの使用を開始する前に、次のことを学ぶ必要があります。

  - [**主要なインターフェイス：**](#key-interfaces) APIの使用中に最もよく使用するインターフェイス。
  - [**サービス参照の取得：**](#getting-a-service-reference) APIのサービスを呼び出すことができるサービス参照を取得する方法。
  - [**リポジトリの指定：**](#specifying-repositories) 必要なドキュメントおよびメディアリポジトリの指定方法。
  - [**フォルダーの指定：**](#specifying-folders) 必要なフォルダーを指定する方法。

## 主要なインターフェース

Documents and Media APIには、いくつかの主要なインターフェイスが含まれています。

  - **ドキュメントおよびメディアサービス：** これらのインターフェイスは、利用可能なすべてのドキュメントおよびメディア機能を公開します。

      - [`DLAppLocalService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppLocalService.html)：ローカルサービス。
      - [`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)：リモートサービス。 このサービスは、ローカルサービスメソッドを許可チェックでラップします。

    Liferayはこれらのサービスを作成するために [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) を使用したことに注意してください。 リモートサービスは、権限チェックが含まれているため、それはです [のベストプラクティス](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services#using-service-builder-to-generate-remote-services) ローカルサービスの代わりにそれを呼び出すこと。 サービス参照を取得する手順については、以下のセクションを参照してください。

  - **エンティティインターフェイス：** これらのインターフェイスは、ドキュメントとメディアライブラリのエンティティを表します。 使用する主なものは次のとおりです。

      - `FileEntry`：ファイルを表します。
      - `フォルダー`：フォルダーを表します。
      - `FileShortcut`：ファイルへのショートカットを表します。

## サービス参照の取得

Documents and Media APIで何かをする前に、サービス参照を取得する必要があります。 あなたはOSGiのモジュールを使用している場合は、使用 `@Reference` に注釈を [宣言型サービスを経由してOSGiのコンポーネントでのサービス参照を取得](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)。 たとえば、このコードは `DLAppService`への参照を取得します。

    @Reference
    private DLAppService _dlAppService;

標準のWebモジュール（WARファイル）を使用している場合は、代わりに [Service Tracker](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers) を使用してサービスへの参照を取得します。

この方法で参照を取得すると、OSGiの [依存関係管理](/docs/7-1/tutorials/-/knowledge_base/t/leveraging-dependencies) 機能を確実に活用できます。 OSGiコンポーネントの外部（JSPなど）でDocumentsおよびMediaサービスを使用する必要がある場合、サービスのstatic `* Util` クラスを使用できます。

  - [`DLAppServiceUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppServiceUtil.html)
  - [`DLAppLocalServiceUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppLocalServiceUtil.html)

## リポジトリの指定

Documents and Media APIの多くのメソッドには、操作が実行されるDocuments and Mediaリポジトリを識別する `repositoryId` パラメーターが含まれています。 サイト（グループ）は複数のリポジトリを持つことができますが、ポータルUIからアクセスできるリポジトリは1つだけです。 これは、サイト（グループ）リポジトリと呼ばれ、事実上サイトのデフォルトリポジトリです。 APIを介してこのリポジトリにアクセスするには、グループIDを `repositoryId`として指定します。

ファイル（`FileEntry`）、フォルダー（`Folder`）、およびファイルショートカット（`FileShortcut`）エンティティを介して `repositoryId` を取得することもできます。 これらの各エンティティには、リポジトリのIDを取得する `getRepositoryId` メソッドがあります。 たとえば、次のコードは `FileEntry` オブジェクト `fileEntry`のリポジトリIDを取得します。

    long repositoryId = fileEntry.getRepositoryId();

`リポジトリ` オブジェクトが必要な場合もあります。 `RepositoryProvider` 参照を作成し、リポジトリIDをその `getRepository` メソッドに渡すことで取得できます。

    @Reference
    private RepositoryProvider repositoryProvider;
    
    Repository repository = repositoryProvider.getRepository(repositoryId);

エンティティID（ファイルIDやフォルダIDなど）のみを持っている場合でも、 `RepositoryProvider` を使用して `Repository` オブジェクトを取得できます。 これを行うには、エンティティIDを引数として使用して、エンティティタイプの `RepositoryProvider` メソッドを呼び出します。 たとえば、次のコードは、フォルダーのIDを指定して `RepositoryProvider` メソッド `getFolderRepository` を呼び出して、フォルダーの `リポジトリ` 取得します。

    Repository repository = repositoryProvider.getFolderRepository(folderId);

他のエンティティタイプのメソッドのリストについては、 [`RepositoryProvider` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/repository/RepositoryProvider.html) を参照してください。

特定のアプリ専用のリポジトリなど、リポジトリをプログラムで作成する方法があることに注意してください。 ただし、簡単にするために、ここのチュートリアルではデフォルトのサイトリポジトリにアクセスします。

## フォルダーの指定

多くのAPIメソッドでは、操作を実行するフォルダーのIDが必要です。 たとえば、このようなメソッドには、 `folderId` または `parentFolderId`などのパラメーターが含まれる場合があります。 また、定数 `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` を使用して、現在のリポジトリのルートフォルダーを指定できることに注意してください。

## 関連トピック

[サービスビルダー](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)

[OSGiサービスと宣言型サービスによる依存性注入](/docs/7-1/tutorials/-/knowledge_base/t/osgi-services-and-dependency-injection-with-declarative-services)

[依存関係の活用](/docs/7-1/tutorials/-/knowledge_base/t/leveraging-dependencies)

[サービストラッカー](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)
