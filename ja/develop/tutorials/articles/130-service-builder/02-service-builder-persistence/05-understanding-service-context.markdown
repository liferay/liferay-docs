---
header-id: understanding-servicecontext
---

# ServiceContextを理解する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

`ServiceContext` クラスは、サービスのコンテキスト情報を保持します。 許可、タグ付け、分類など、Liferayのポートレット全体で使用される機能に必要な情報を集約します。 このチュートリアルでは、次の `ServiceContext` クラスのトピックについて説明します。

  - [サービスコンテキストフィールド](#service-context-fields)
  - [Javaでのサービスコンテキストの作成と入力](#creating-and-populating-a-service-context)
  - [JavaScriptでのサービスコンテキストの作成と入力](#creating-and-populating-a-service-context-in-javascript)
  - [サービスコンテキストデータへのアクセス](#accessing-service-context-data)

`ServiceContext` フィールドが最初です。

## サービスコンテキストフィールド

`ServiceContext` クラスには多くのフィールドがあります。 [`ServiceContext` クラスJavadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html) はそれらを記述します。

一般的に使用されるいくつかのサービスコンテキストフィールドのカテゴリリストを次に示します。

  - 行動：
      - `_command`
      - `_workflowAction`
  - 属性：
      - `_attributes`
      - `_expandoBridgeAttributes`
  - 分類：
      - `_assetCategoryIds`
      - `_assetTagNames`
  - 例外
      - `_failOnPortalException`
  - IDとスコープ：
      - `_companyId`
      - `_portletPreferencesIds`
      - `_plid`
      - `_scopeGroupId`
      - `_userId`
      - `_uuid`
  - 言語：
      - `_languageId`
  - その他：
      - `_headers`
      - `_signedIn`
  - 許可：
      - `_addGroupPermissions`
      - `_addGuestPermissions`
      - `_deriveDefaultPermissions`
      - `_modelPermissions`
  - 要求
      - `_request`
  - リソース：
      - `_assetEntryVisible`
      - `_assetLinkEntryIds`
      - `_assetPriority`
      - `_createDate`
      - `_formDate`
      - `_indexingEnabled`
      - `_modifiedDate`
      - `_timeZone`
  - URL、パス、アドレス：
      - `_currentURL`
      - `_layoutFullURL`
      - `_layoutURL`
      - `_pathMain`
      - `_pathFriendlyURLPrivateGroup`
      - `_pathFriendlyURLPrivateUser`
      - `_pathFriendlyURLPublic`
      - `_portalURL`
      - `_remoteAddr`
      - `_remoteHost`
      - `_userDisplayURL`

`ServiceContext` フィールドがどのように入力されるのか疑問に思っていますか？ 良い\！ 次にそれについて学びます。

## サービスコンテキストの作成と入力

`ServiceContext` クラスのフィールドはすべてオプションですが、データをスコープに格納するサービスは、少なくともスコープグループIDを指定する必要があります。 `ServiceContext` インスタンスを作成し、LiferayサービスAPIにパラメーターとして渡す例を次に示します。

    ServiceContext serviceContext = new ServiceContext();
    serviceContext.setScopeGroupId(myGroupId);
    
    ...
    
    _blogsEntryService.addEntry(..., serviceContext);

サーブレット、Strutsアクション、または `PortletRequest`アクセスできる他のフロントエンドクラスからサービスを呼び出す場合、 `つのServiceContextFactory.getInstance（...）` メソッドのいずれかを使用します。 これらのメソッドは、リクエストから `ServiceContext` オブジェクトを作成し、リクエストで指定されたすべての値をそのフィールドに自動的に入力します。 上記の例は、サーブレットからサービスを呼び出す場合は異なって見えます。

    ServiceContext serviceContext =
        ServiceContextFactory.getInstance（BlogsEntry.class.getName（）、portletRequest）;
    
    ...
    
    _blogsEntryService.addEntry(..., serviceContext);

`ServiceContextFactory.getInstance（...）` メソッドのコードで、要求オブジェクトからの情報を `ServiceContext` 取り込む例を見ることができます。 方法は次のようにパラメータを設定する方法を示して *スコープグループID*、 *、会社ID*、 *言語ID*などを。 彼らはまた、どのようにアクセスすることを実証するなど、より複雑なコンテキストパラメータ移入 *タグ*、 *カテゴリ*、 *資産リンク*、 *ヘッダー*、及び *属性* パラメーター。 `ServiceContextFactory.getInstance（String className、PortletRequest portletRequest）`呼び出すことにより、Expandoブリッジ属性が `ServiceContext`設定されていることを確認できます。 Expandosは、Liferayのエンティティのカスタムフィールドのバックエンド実装です。

## JavaScriptでのサービスコンテキストの作成と入力

LiferayのAPIは、Java以外の言語で呼び出すことができます。 一部のメソッドでは、 `ServiceContext` パラメーターが必要または許可されます。 LiferayのJSON Webサービスを介してこのようなメソッドを呼び出す場合、JavaScriptで `ServiceContext` オブジェクトを作成してデータを設定することができます。 JavaScriptで `ServiceContext` オブジェクトを作成することは、JavaScriptで他のオブジェクトを作成することと同じです。

`ServiceContext` オブジェクトを使用するJSON Webサービスの呼び出しを調べる前に、JavaScriptで簡単なJSON Webサービスの例を確認すると役立ちます。

    Liferay.Service(
        '/user/get-user-by-email-address`,
        {
            companyId: Liferay.ThemeDisplay.getCompanyId(),
            emailAddress: 'test@example.com`
        },
        function(obj) {
            console.log(obj);
        }
    );

このコードを実行すると、 *<test@example.com>* ユーザー（JSONオブジェクト）がJavaScriptコンソールに記録されます。

`Liferay.Service（...）` 関数は3つの引数を取ります。

1.  呼び出されるサービスを表す文字列

2.  パラメータオブジェクト

3.  コールバック関数

コールバック関数は、サービス呼び出しの結果を引数として受け取ります。

Liferay JSON Webサービスページ（そのURLは <localhost:8080> Liferayをポート8080でローカルに実行している場合）は、Webサービスを呼び出すためのサンプルコードを生成します。 特定のサービスに対して生成されたコードを表示するには、サービスの名前をクリックし、必要なパラメーターを入力して、 *Invoke*をクリックします。 サービス呼び出しのJSON結果が表示されます。 LiferayのJSONのWebサービスを呼び出すための複数の方法がありますをクリックしてください *JavaScriptの例* のJavaScriptを経由してWebサービスを呼び出す方法を確認するために、をクリックして *カール例* カールを経由してWebサービスを呼び出す、またはをクリックする方法については、 *URL例* 、URLを介してWebサービスを呼び出す方法を示しています。

![図1：LiferayのJSON Webサービスページからサービスを呼び出すと、サービス呼び出しの結果と、JavaScript、curl、またはURLを介してサービスを呼び出すためのサンプルコードを表示できます。](../../../images/jsonws-simple-example.png)

LiferayのJSON Webサービスの詳細については、 [JSON Web Services](/docs/7-1/tutorials/-/knowledge_base/t/registering-json-web-services) チュートリアルをご覧ください。

次に、 `ServiceContext` オブジェクトから情報にアクセスする方法を学習します。

## サービスコンテキストデータへのアクセス

このセクションでは、 `BlogsEntryLocalServiceImpl.addEntry（...、ServiceContext）`からコードスニペットを見つけます。 このコードは、 `ServiceContext` から情報にアクセスする方法を示し、コンテキスト情報の使用方法の例を示します。

上記のように、スコープを持つエンティティのサービスは、 `ServiceContext` オブジェクトからスコープグループIDを取得する必要があります。 これは、ブログエントリサービスのスコープグループIDがブログエントリ（永続化されているエンティティ）のスコープを提供するためです。 Blogsエントリの場合、スコープグループIDは次の方法で使用されます。

  - `BlogsEntry` エンティティの `groupId` として使用されます。
  - ブログエントリの一意のURLを生成するために使用されます。
  - ブログエントリのコメントのスコープを設定するために使用されます。

対応するコードスニペットを次に示します。

    long groupId = serviceContext.getScopeGroupId();
    
    ...
    
    entry.setGroupId(groupId);
    
    ...
    
    entry.setUrlTitle(getUniqueUrlTitle(entryId, groupId, title));
    
    ...
    
    // Message boards
    
    if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
        mbMessageLocalService.addDiscussionMessage(
            userId, entry.getUserName(), groupId,
            BlogsEntry.class.getName(), entryId,
            WorkflowConstants.ACTION_PUBLISH);
    }

`ServiceContext` を使用して、ブログエントリのUUIDにアクセスできますか？ 絶対に\！ `ServiceContext` を使用して、ブログエントリが追加された時刻を設定できますか？ 確かにできます。 こちらをご覧ください：

    entry.setUuid(serviceContext.getUuid());
    ...
    entry.setCreateDate(serviceContext.getCreateDate(now));

`ServiceContext` 、リソースのアクセス許可の設定に使用できますか？ あなたは賭けます！ ブログエントリを追加する場合、次のように、エントリに新しい権限を追加したり、既存の権限を適用したりできます。

    // Resources
    
    if (serviceContext.isAddGroupPermissions() ||
        serviceContext.isAddGuestPermissions()) {
    
        addEntryResources(
            entry, serviceContext.isAddGroupPermissions(),
            serviceContext.isAddGuestPermissions());
    }
    else {
        addEntryResources(
            entry, serviceContext.getGroupPermissions(),
            serviceContext.getGuestPermissions());
    }

`ServiceContext` は、カテゴリ、タグ名、およびリンクエントリIDをアセットエントリにも適用するのに役立ちます。 資産リンクは、Liferayの関連資産のバックエンド用語です。

    // Asset
    
    updateAsset(
        userId, entry, serviceContext.getAssetCategoryIds(),
        serviceContext.getAssetTagNames(),
        serviceContext.getAssetLinkEntryIds());

`ServiceContext` は、ブログエントリのワークフローインスタンスを開始する役割も果たしますか？ 聞いてください

    // Workflow
    
    if ((trackbacks != null) && (trackbacks.length > 0)) {
        serviceContext.setAttribute("trackbacks", trackbacks);
    }
    else {
        serviceContext.setAttribute("trackbacks", null);
    }
    
    _workflowHandlerRegistry.startWorkflowInstance(
        user.getCompanyId(), groupId, userId, BlogsEntry.class.getName(),
        entry.getEntryId(), entry, serviceContext);

上記のスニペットは、ブログエントリサービスの標準属性である `トラックバック` 属性も示しています。 ブログエントリサービスにカスタム属性を渡す必要がある場合があります。 Expando属性を使用して、 `ServiceContext`カスタム属性を含めます。 Expando属性は、追加されたブログエントリで次のように設定されます。

    entry.setExpandoBridgeAttributes(serviceContext);

`ServiceContext` を使用して、サービスに役立つ多くの情報を転送できることがわかります。 Liferayで `ServiceContext` がどのように使用されるかを理解すると、Liferayアプリケーション開発で `ServiceContext` をいつどのように使用するかを決定するのに役立ちます。

## 関連トピック

[ローカルサービスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-local-services)

[ローカルサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-local-services)
