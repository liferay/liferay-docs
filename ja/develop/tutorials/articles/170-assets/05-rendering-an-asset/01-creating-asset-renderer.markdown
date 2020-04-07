---
header-id: creating-an-asset-renderer
---

# アセットレンダラーの作成

[TOC levels=1-4]

このチュートリアルでは、 `Asset Renderer` を作成し、JSPテンプレートをそれに関連付ける方法、および他のいくつかのオプションを構成する方法を学びます。

アセットレンダラーの作成方法を学習するには、既存の [`BlogsEntryAssetRenderer`](https://github.com/liferay/liferay-portal/blob/7.1.3-ga4/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/asset/BlogsEntryAssetRenderer.java) クラスを作成します。これは、ブログアプリケーションのアセットレンダラーフレームワークを構成します。

1.  資産関連クラスの既存のプロジェクトに新しいパッケージを作成します。 たとえば、 `BlogsEntryAssetRenderer` クラスは、 `com.liferay.blogs.web` モジュールの `com.liferay.blogs.web.asset` パッケージにあります。

2.  新しい `-.asset` パッケージでアプリケーションの `-AssetEntry` クラスを作成し、 [`AssetEntry`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/asset/kernel/model/AssetEntry.html) インターフェースを実装します。 例として `BlogsEntryAssetRenderer` クラスを考えてみましょう。
   
        public class BlogsEntryAssetRenderer
            extends BaseJSPAssetRenderer<BlogsEntry> implements TrashRenderer {

    `BlogsEntryAssetRenderer` クラスは、 [`BaseJSPAssetRenderer`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/asset/kernel/model/BaseJSPAssetRenderer.html)拡張します。これは、JSPテンプレートを使用してアセットのHTMLを生成する予定の人向けの拡張クラスです。 `BaseJSPAssetRenderer` クラスは、 `AssetRenderer` インターフェイスを実装します。 アセットレンダラーが [`TrashRenderer`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/trash/TrashRenderer.html) インターフェイスも実装していることに気付くでしょう。 これは多くのアプリケーションで一般的な方法であるため、@product@のごみ箱を使用できます。

3.  アセットレンダラークラスのコンストラクターを定義します。これは通常、アセットレンダラークラスで使用するアセットオブジェクトを設定します。
   
        public BlogsEntryAssetRenderer(
            BlogsEntry entry, ResourceBundleLoader resourceBundleLoader) {
       
            _entry = entry;
            _resourceBundleLoader = resourceBundleLoader;
        }

    `BlogsEntryAssetRenderer` は、モジュールの言語キーをロードするリソースバンドルローダーも設定します。 [言語キーのオーバーライド](/docs/7-0/tutorials/-/knowledge_base/t/overriding-language-keys) チュートリアルで、リソースバンドルローダーの詳細を学ぶことができます。

    また、クラスの `_entry` および `_resourceBundleLoader` フィールドを必ず定義してください。
   
        private final BlogsEntry _entry;
        private final ResourceBundleLoader _resourceBundleLoader;

4.  クラス宣言とコンストラクターがブログ資産レンダラー用に定義されたので、資産レンダラーを資産に接続し始める必要があります。 これを実現するゲッターメソッドは次のとおりです。
   
        @Override
        public BlogsEntry getAssetObject() {
            return _entry;
        }
       
        @Override
        public String getClassName() {
            return BlogsEntry.class.getName();
        }
       
        @Override
        public long getClassPK() {
            return _entry.getEntryId();
        }
       
        @Override
        public long getGroupId() {
            return _entry.getGroupId();
        }
       
        @Override
        public String getType() {
            return BlogsEntryAssetRendererFactory.TYPE;
        }
       
        @Override
        public String getUuid() {
            return _entry.getUuid();
        }

    `getAssetObject（）` メソッドは、追跡するアセットとしてコンストラクターで設定された `BlogsEntry` を設定します。 同様に、 `getType（）` メソッドは、アセットレンダラーがレンダリングするアセットのタイプのブログアセットレンダラーファクトリを参照します。 もちろん、アセットレンダラーのタイプは `blog`であり、後で工場で設定します。

5.  アセットレンダラーは、エンティティを所有するポートレットにリンクする必要があります。 ブログアセットの場合、そのポートレットIDはブログアプリケーションにリンクする必要があります。
   
        @Override
        public String getPortletId() {
            AssetRendererFactory<BlogsEntry> assetRendererFactory =
                getAssetRendererFactory();
       
            return assetRendererFactory.getPortletId();
        }

    `getPortletId（）` メソッドは、 `BlogsEntry` アセットレンダラーファクトリをインスタンス化し、ブログエントリの表示に使用されるポートレットのポートレットIDを取得します。

6.  アセットのワークフローを有効にすることに興味がある場合は、ブログアプリケーションで実行したのと同様の次のメソッドを追加します。
   
        @Override
        public int getStatus() {
            return _entry.getStatus();
        }

    このメソッドは、アセットのワークフローステータスを取得します。

7.  多くの開発者が資産に求めているもう1つの人気のある機能は、コメントすることです。 これは、ブログアプリケーションで次の方法で有効になります。
   
        @Override
        public String getDiscussionPath() {
            if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
                return "edit_entry_discussion";
            }
            else {
                return null;
            }
        }

    コメントセクションは、null以外の値を返す場合に使用可能なオプションです。 アセットのコメントセクションを表示するには、Asset Publisherの *オプション* （![Options](../../../images/icon-app-options.png)）→ *構成* → *セットアップ* → *ディスプレイ設定* セクションで有効にする必要があります。

8.  少なくとも、アセットのタイトルと概要を作成する必要があります。 `BlogsEntryAssetRenderer` が行う方法は次のとおりです。
   
        @Override
        public String getSummary(
            PortletRequest portletRequest, PortletResponse portletResponse) {
       
            int abstractLength = AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH;
       
            if (portletRequest != null) {
                abstractLength = GetterUtil.getInteger(
                    portletRequest.getAttribute(
                        WebKeys.ASSET_ENTRY_ABSTRACT_LENGTH),
                    AssetUtil.ASSET_ENTRY_ABSTRACT_LENGTH);
            }
       
            String summary = _entry.getDescription();
       
            if (Validator.isNull(summary)) {
                summary = HtmlUtil.stripHtml(
                    StringUtil.shorten(_entry.getContent(), abstractLength));
            }
       
            return summary;
        }
       
        @Override
        public String getTitle(Locale locale) {
            ResourceBundle resourceBundle =
                _resourceBundleLoader.loadResourceBundle(
                    LanguageUtil.getLanguageId(locale));
       
            return BlogsEntryUtil.getDisplayTitle(resourceBundle, _entry);
        }

    これら2つのメソッドはアセットに関する情報を返すため、アセット発行者はそれを表示できます。 タイトルと概要は何でもかまいません。

    Blogsの `getSummary（...）` メソッドは、ブログアセットの抽象的な説明を返します。 アブストラクトの説明が存在しない場合、ブログのコンテンツがアブストラクトとして使用されます。 要約やその他のコンテンツ仕様については、後ほど詳しく説明します。

    Blogsの `getTitle（...）` メソッドは、コンストラクターで構成したリソースバンドルローダーを使用して、モジュールのリソースバンドルをロードし、アセットの表示タイトルを返します。

9.  アセットに一意のURLを提供する場合は、URLタイトルを指定できます。 URLタイトルは、アセットに直接アクセスするために使用されるURLです（例：localhost：8080 /-/ this-is-my-blog-asset）。 これを行うには、次の方法を提供します。
   
        @Override
        public String getUrlTitle() {
            return _entry.getUrlTitle();
        }

10. `isPrintable（）` メソッドを挿入します。これにより、アセットのAsset Publisherの印刷機能が有効になります。
    
         @Override
         public boolean isPrintable() {
             return true;
         }

    アセットがAsset Publisherに表示されると、印刷アイコンが表示されます。 アイコンを表示するには、Asset Publisherの *オプション* → *構成* → *セットアップ* → *ディスプレイ設定* セクションで有効にする必要があります。

    ![図1：Asset Publisherで印刷を有効にして、アセットの印刷アイコンを表示します。](../../../images/asset-publisher-printing.png)

11. アセットが権限で保護されている場合、アセットレンダラーを介してアセットの権限を設定できます。 `BlogsEntryAssetRenderer` クラスで使用される例については、以下のロジックを参照してください。
    
         @Override
         public long getUserId() {
             return _entry.getUserId();
         }
        
         @Override
         public String getUserName() {
             return _entry.getUserName();
         }
        
         public boolean hasDeletePermission(PermissionChecker permissionChecker) {
             return BlogsEntryPermission.contains(
                 permissionChecker, _entry, ActionKeys.DELETE);
         }
        
         @Override
         public boolean hasEditPermission(PermissionChecker permissionChecker) {
             return BlogsEntryPermission.contains(
                 permissionChecker, _entry, ActionKeys.UPDATE);
         }
        
         @Override
         public boolean hasViewPermission(PermissionChecker permissionChecker) {
             return BlogsEntryPermission.contains(
                 permissionChecker, _entry, ActionKeys.VIEW);
         }

    ユーザーにアセットを表示する権限があるかどうかを確認する前に、 `getUserId（）` および `getUserName（）` を使用して、エントリのユーザーIDとユーザー名をそれぞれ取得する必要があります。 次に、ユーザーがブログエントリを表示、編集、または削除できるかどうかをチェックする3つのブール型のアクセス許可メソッドがあります。 これらの権限は、特定のエンティティインスタンス用です。 ブログエントリのグローバルアクセス許可は工場で実装されますが、これは後で行います。

驚くばかり\！ ブログアセットレンダラーを設定する方法を学習しました。

  - 資産に接続する
  - アセットのポートレットに接続します
  - ワークフロー管理を使用する
  - コメントセクションを使用する
  - アセットのタイトルと概要を取得する
  - アセットの一意のURLを生成します
  - 印刷アイコンを表示する
  - アセットの権限を確認してください

次に、HTMLをレンダリングするテンプレートを作成する必要があります。 `BlogsEntryAssetRenderer` は、JSPテンプレートを使用してAsset PublisherのHTMLを生成するように構成されています。 次にこれを行う方法について詳しく学習します。
