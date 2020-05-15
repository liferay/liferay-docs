---
header-id: associating-permissions-with-resources
---

# 権限とリソースの関連付け

[TOC levels=1-4]

権限を定義して登録したので、権限インターフェイスを公開して、ユーザーが権限を設定できるようにする必要があります。

モデルリソースに権限を構成するには、権限インターフェイスをUIに追加する必要があります。次の2つのLiferay UIタグをJSPに追加します。

1. `<liferay-security:permissionsURL>`：権限設定の構成ページへのURLを返します。

2. `<liferay-ui:icon>`：ユーザーにアイコンを表示します。これらはテーマで定義されており、そのうちの1つ（以下を参照）が権限に使用されます。

ブログアプリケーションは、次のようにこれらのタグを使用します。

    <liferay-security:permissionsURL
    modelResource="<%= BlogsEntry.class.getName() %>"
    modelResourceDescription="<%= BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) %>"
    resourceGroupId="<%= String.valueOf(entry.getGroupId()) %>"
    resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
    var="permissionsEntryURL"
    windowState="<%= LiferayWindowState.POP_UP.toString() %>"
    />
    
    <liferay-ui:icon
    label="<%= true %>"
    message="permissions"
    method="get"
    url="<%= permissionsEntryURL %>"
    useDialog="<%= true %>"
    />

`<liferay-security:permissionsURL />`タグに対し、次の属性を指定します。

`modelResource`：エンティティクラスの完全修飾クラス名。このクラス名は、`Language.properties`で指定されているより読みやすい名前に変換されます。

`Language.properties`：上記の例のエンティティクラスは、完全修飾クラス名が`com.liferay.blogs.model.BlogsEntry`のブログエントリクラスです。

`modelResourceDescription`：このモデルインスタンスの詳細な説明を入力できます。上記の例では、ブログのタイトルがモデルリソースの説明に使用されています。

`resourcePrimKey`：エンティティのプライマリキー。

`var`：結果のURL文字列が割り当てられている変数の名前。
変数はその後`<liferay-ui:icon>`タグに渡されるため、権限アイコンには適切なURLリンクが含まれます。

`redirect`というオプションの属性は、右上の矢印リンクのデフォルトの動作をオーバーライドする場合に使用できます。説明は以上です。これで、ユーザーはモデルリソースの権限設定を構成できます。

*DRAC*のステップ3を完了しました。次のステップは、アプリケーションの適切な領域で権限を確認することです。
