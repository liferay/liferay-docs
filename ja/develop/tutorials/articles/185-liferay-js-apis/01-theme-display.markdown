---
header-id: liferay-themedisplay
---

# ThemeDisplay情報へのアクセス

[TOC levels=1-4]

`Liferay`グローバルJavaScriptオブジェクトは、便利なメソッド、オブジェクト、およびプロパティを公開しています。それぞれには豊富な情報が含まれており、そのうちの1つが`ThemeDisplay`です。@product@でのJava開発の経験がある場合は、ThemeDisplayに精通しているかもしれません。JavaScriptオブジェクトは、ThemeDisplay Javaクラスと同じ情報を公開しています。ポータルインスタンス、現在のユーザー、ユーザーの言語、ユーザーがサインインしているかまたは偽装されているか、テーマのリソースへのファイルパスなど、アプリケーションで使用できる貴重な情報にアクセスできます。

`Liferay`グローバルオブジェクトは、実行時に@product@で自動的に使用可能になります。
`ThemeDisplay`オブジェクトにアクセスするには、アプリで次のドット表記を使用します。

    `Liferay.ThemeDisplay.method-name`

このチュートリアルでは、ID、ファイルパス、およびログイン情報を取得するために最も一般的に使用される`ThemeDisplay`メソッドの一部について説明します。使用可能なすべてのメソッドの完全なリストは、このチュートリアルの最後にある表に示しています。

## IDの取得

以下のメソッドは、現在のユーザーに関連するさまざまなポータル要素を取得します。

**getCompanyId**：[会社ID](/participate/liferaypedia/-/wiki/Main/Company+ID)を返します。

**getLanguageId**：ユーザーの言語IDを返します。

**getScopeGroupId**：現在のサイトの[グループID](/participate/liferaypedia/-/wiki/Main/Group+ID)を返します。

**getUserId**：[ユーザーのID](/participate/liferaypedia/-/wiki/Main/User+ID)を返します。

**getUserName**：ユーザーの名前を返します。

次に、デプロイされたさまざまなエンティティのファイルパスにアクセスする方法を学習します。

## ファイルパスの取得

以下のメソッドは、さまざまなテーマリソースのファイルパスを取得します。

**getPathImage**：ポートレットの画像ディレクトリの相対パスを返します。

**getPathJavaScript**：ポートレットのJavaScriptソースファイルを含むディレクトリの相対パスを返します。

**getPathMain**：ポータルインスタンスのメインディレクトリのパスを返します。

**getPathThemeImages**：現在のテーマの画像ディレクトリのパスを返します。

**getPathThemeRoot**：現在のテーマのルートディレクトリの相対パスを返します。

次に、現在のユーザーの情報を取得する方法を学習します。

## ログイン情報の取得

以下のメソッドは、現在のユーザーがサインインしているか、または偽装されているかを示すブール値を返します。

**isImpersonated**：現在のユーザーが偽装されている場合、`true`を返します。
承認された管理ユーザーは、別のユーザーとして[偽装](/docs/6-2/user/-/knowledge_base/u/the-users-section-of-the-control-panel#user-management)してそのユーザーのアカウントをテストできます。

**isSignedIn**：ユーザーがポータルにログインしている場合、`true`を返します。

以下の設定例は、ユーザーがゲストの場合は標準メッセージで、サインインしている場合はパーソナルグリーティングでユーザーに通知します。これは基本的な例であり、多少侵襲的かもしれませんが、`ThemeDisplay` APIを使用して各ユーザーに固有のエクスペリエンスを作成する方法を示しています。

    if(Liferay.ThemeDisplay.isSignedIn()){
    alert('Hello ' + Liferay.ThemeDisplay.getUserName() + '. Welcome Back.')
    }
    else {
    alert('Hello Guest.')
    }

## Liferay ThemeDisplayメソッド

使用可能な`Liferay.ThemeDisplay`メソッドの完全なリストを以下の表に示します。

| メソッド | タイプ | 説明 |
| --- | --- | --- |
| getLayoutId | 数値 |  |
| getLayoutRelativeURL | 文字列 | ページの相対URLを返します |
| getLayoutURL | 文字列 |  |
| getParentLayoutId | 数値 |  |
| isControlPanel | ブール値 |  |
| isPrivateLayout | ブール値 |  |
| isVirtualLayout | ブール値 |  |
| getBCP47LanguageId | 数値 |  |
| getCDNBaseURL | 文字列 | コンテンツ配信ネットワーク（CDN）のベースURLを返します。CDNのベースURLがnullの場合は、現在のポータルURLを返します |
| getCDNDynamicResourcesHost | 文字列 | コンテンツ配信ネットワーク（CDN）の動的リソースホストを返します。CDN動的リソースホストがnullの場合は、現在のポータルURLを返します |
| getCDNHost | 文字列 |  |
| getCompanyGroupId | 数値 |  |
| getCompanyId | 数値 | ポータルインスタンスIDを返します |
| getDefaultLanguageId | 数値 |  |
| getDoAsUserIdEncoded | 文字列 |  |
| getLanguageId | 数値 | ユーザーの言語IDを返します |
| getParentGroupId | 数値 |  |
| getPathContext | 文字列 |  |
| getPathImage | 文字列 | ポートレットの画像ディレクトリの相対パスを返します |
| getPathJavaScript | 文字列 | ポートレットのJavaScriptソースファイルを含むディレクトリの相対パスを返します |
| getPathMain | 文字列 | ポータルインスタンスのメインディレクトリのパスを返します |
| getPathThemeImages | 文字列 | 現在のテーマの画像ディレクトリのパスを返します |
| getPathThemeRoot | 文字列 | 現在のテーマのルートディレクトリの相対パスを返します |
| getPlid | 文字列 | ページの主キーを返します |
| getPortalURL | 文字列 | ポータルインスタンスのベースURLを返します |
| getScopeGroupId | 数値 | スコープ指定された、またはサブスコープ指定されたアクティブグループ（サイトなど）のIDを返します |
| getScopeGroupIdOrLiveGroupId | 数値 |  |
| getSessionId | 数値 | セッションIDを返します。セッションIDがアプリケーションで使用できない場合は空の文字列を返します |
| getSiteGroupId | 数値 |  |
| getURLControlPanel | 文字列 |  |
| getURLHome | 文字列 |  |
| getUserId | 数値 | 現在の要求が処理されているユーザーのIDを返します |
| getUserName | 文字列 | ユーザーの名前を返します |
| isAddSessionIdToURL | ブール値 |  |
| isFreeformLayout | ブール値 |  |
| isImpersonated | ブール値 | 現在のユーザーが偽装されている場合に`true`を返します。承認された管理ユーザーは、別のユーザーとして[偽装](/docs/7-1/user/-/knowledge_base/u/adding-editing-and-deleting-users#editing-users)してそのユーザーのアカウントをテストできます |
| isSignedIn | ブール値 | ユーザーがポータルにログインしている場合に`true`を返します |
| isStateExclusive | ブール値 |  |
| isStateMaximized | ブール値 |  |
| isStatePopUp | ブール値 |  |

## 関連トピック

[@product@ JavaScript Utilities](/docs/7-1/tutorials/-/knowledge_base/t/javascript-utilities)
