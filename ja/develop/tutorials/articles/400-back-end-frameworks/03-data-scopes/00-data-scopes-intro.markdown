---
header-id: data-scopes
---

# データスコープ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アプリは、データを特定のスコープに制限できます。 スコープは、アプリケーションのデータのコンテキストを提供します。

*グローバル：* ポータルインスタンス全体に</em>つのデータセット。

*サイト：* 追加されたサイトごとに個別のデータセット。

*ページ：* 追加される各ページの個別のデータセット。

たとえば、サイトスコープのアプリは、単一のサイトにデータを表示できます。 スコープの詳細な説明については、ユーザーガイドの記事 [Widget Scope](/docs/7-1/user/-/knowledge_base/u/widget-scope)参照してください。 アプリケーションにスコープを与えるには、そのサポートを手動で追加する必要があります。 このチュートリアルでは、その方法を示します。

## エンティティのスコープ

サービスレイヤーでは、エンティティはポータルインスタンスによるスコープを有効にするためにタイプ `long` `companyId` 属性を持ち、サイトによるスコープを有効にするためにタイプ `long` `groupId` 属性を持つ必要があります。 これを行う最も簡単な方法は、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) を使用することです。 [Service Builder Persistence](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-persistence) および [Service Logic with Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/business-logic-with-service-builder) チュートリアルでは、その方法を示します。

## スコープの有効化

アプリでスコープを有効にするには、ポートレットクラスの `@Component` アノテーションでプロパティ `"com.liferay.portlet.scopeable = true"` します。 たとえば、 [Webコンテンツ表示ポートレットのポートレットクラス](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/journal/journal-content-web/src/main/java/com/liferay/journal/content/web/internal/portlet/JournalContentPortlet.java) は、このコンポーネントプロパティを設定します。

    @Component(
        immediate = true,
        property = {
            ...
            "com.liferay.portlet.scopeable=true",
            ...,
        },
        service = Portlet.class
    )
    public class JournalContentPortlet extends MVCPortlet {...

それでおしまい\！ 次に、コードでアプリのスコープにアクセスします。

## アプリのスコープにアクセスする

ユーザーは通常、アプリのスコープをページ、サイト、またはポータル全体に設定できます。 アプリのデータを処理するには、現在のスコープでアクセスする必要があります。 アプリのスコープは次の方法で利用できます。

1.  `<liferay-theme:defineObjects />` タグを使用するJSPに挿入される `scopeGroupId` 変数を介して。 この変数には、アプリの現在のスコープが含まれます。 たとえば、Liferayブックマークアプリの [`view.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/bookmarks/bookmarks-web/src/main/resources/META-INF/resources/bookmarks/view.jsp#L122-L125) は、 `scopeGroupId` を使用して、現在のスコープ内のブックマークとブックマークの総数を取得します。
   
        ...
        total = BookmarksEntryServiceUtil.getGroupEntriesCount(scopeGroupId, groupEntriesUserId);
       
        bookmarksSearchContainer.setTotal(total);
        bookmarksSearchContainer.setResults(BookmarksEntryServiceUtil.getGroupEntries(scopeGroupId, groupEntriesUserId, bookmarksSearchContainer.getStart(), bookmarksSearchContainer.getEnd()));
        ...

2.  要求の [`ThemeDisplay` インスタンス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html)`getScopeGroupId（）` メソッドを呼び出すことにより。 このメソッドは、アプリの現在のスコープを返します。 たとえば、Liferayブログアプリの [`EditEntryMVCActionCommand` クラス](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/action/EditEntryMVCActionCommand.java#L371-L383) は、 `サブスクライブ` および `サブスクライブ解除` メソッドでこれを行います。
   
        protected void subscribe(ActionRequest actionRequest) throws Exception {
            ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
                WebKeys.THEME_DISPLAY);
       
            _blogsEntryService.subscribe(themeDisplay.getScopeGroupId());
        }
       
        protected void unsubscribe(ActionRequest actionRequest) throws Exception {
            ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
                WebKeys.THEME_DISPLAY);
       
            _blogsEntryService.unsubscribe(themeDisplay.getScopeGroupId());
        }

    アプリが常にポータルインスタンスIDを必要とすることがわかっている場合は、 `themeDisplay.getCompanyId（）`使用します。

3.  `ServiceContext` オブジェクトで `getScopeGroupId（）` メソッドを呼び出すことにより。 チュートリアル [理解 `ServiceContext`](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext) 例と詳細があります。 アプリが常にポータルインスタンスIDを必要とすることがわかっている場合は、 `ServiceContext` オブジェクトの `getCompanyId（）` メソッドを使用します。

驚くばかり\！ これで、アプリのスコープを取得する方法がわかりました。 次に、特別なユースケースについて学習します。別のアプリに属するエンティティのSiteスコープを取得します。

## アプリ間でサイトスコープにアクセスする

ページまたはポータルを範囲とするアプリから、異なるアプリのサイトスコープのデータにアクセスする必要がある場合があります。 たとえば、Webコンテンツ記事は、ページ、サイト、またはポータルスコープで作成できます。 [ただし、このような記事の構造とテンプレート](/docs/7-1/user/-/knowledge_base/u/designing-uniform-content) は、サイトスコープにのみ存在します。 上記の手法は、アプリのスコープを返しますが、これはサイトスコープではない場合があります。 なんとピクルス！ 決して恐れない、 `ThemeDisplay` メソッド `getSiteGroupId（）` はここにあります！ このメソッドは、アプリの現在のスコープに関係なく、常にSiteスコープを取得します。 たとえば、 [Webコンテンツアプリの `edit_feed.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/journal/journal-web/src/main/resources/META-INF/resources/edit_feed.jsp#L40) はこのメソッドを使用して、構造の取得に必要なサイトIDを取得します。

    ...
    
    ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(themeDisplay.getSiteGroupId(), 
        PortalUtil.getClassNameId(JournalArticle.class), ddmStructureKey, true);
    
    ...

すばらしいです\！ これで、アプリのスコープを設定し、スコープにアクセスし、他のアプリに属するエンティティのサイトスコープを取得する方法がわかりました。

## 関連トピック

[ウィジェットの範囲](/docs/7-1/user/-/knowledge_base/u/widget-scope)

[サービスビルダー](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)

[サービスビルダーの永続性](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-persistence)

[Service Builderを使用したビジネスロジック](/docs/7-1/tutorials/-/knowledge_base/t/business-logic-with-service-builder)
