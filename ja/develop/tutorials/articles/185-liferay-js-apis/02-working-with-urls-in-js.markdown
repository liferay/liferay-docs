---
header-id: working-with-urls-in-javascript
---

# JavaScriptでのURLの操作

[TOC levels=1-4]

`Liferay`グローバルJavaScriptオブジェクトは、ポータルコンテキストにアクセスするメソッド、オブジェクト、およびプロパティを公開しています。URLで使用する場合は、`authToken`、`currentURL`、`currentURLEncoded`、および`PortletURL`の4つが役立ちます。@product@でのJava開発の経験がある場合は、以前にこれらのいくつかを使用したことがあるかもしれません。`Liferay`グローバルオブジェクトは実行時に自動的に使用可能になるため、追加の依存関係は必要ありません。

このチュートリアルでは、`Liferay`グローバルJavaScriptオブジェクトを使用してURLを操作する方法について説明します。使用可能なメソッドとプロパティの完全なリストは、このチュートリアルの最後の表に記載されています。

## Liferay PortletURL

`Liferay.PortletURL`オブジェクトは、JavaScriptを介してポートレットAPI URL（`actionURL`、`renderURL`、および`resourceURL`）を作成するためのメソッドを提供します。以下に設定例を示します。

    var portletURL = Liferay.PortletURL.createURL(themeDisplay.getURLControlPanel());
    
    portletURL.setDoAsGroupId('true');
    portletURL.setLifecycle(Liferay.PortletURL.ACTION_PHASE);
    portletURL.setParameter('cmd', 'add_temp');
    portletURL.setParameter('javax.portlet.action', '/document_library/upload_file_entry');
    portletURL.setParameter('p_auth', Liferay.authToken);
    portletURL.setPortletId(Liferay.PortletKeys.DOCUMENT_LIBRARY);

上記の例で使用されているメソッドとプロパティの詳細については、[ポートレットURLのメソッドとプロパティ](#portlet-url-methods-and-properties)のセクションを参照してください。

## Liferay AuthToken

`Liferay.authToken`プロパティは、現在の認証トークン値をStringとして保持します。 `authToken`は、サービスを呼び出すときに権限を検証するために使用されます。URLで`authToken`を使用するには、以下の例に示すように、`Liferay.authToken`をURLの`p_auth`パラメーターとして渡します。

    portletURL.setParameter('p_auth', Liferay.authToken);

## Liferay CurrentURL

`Liferay.currentURL`プロパティは、サーバールートからの現在のURLのパスを保持します。

たとえば、以下に示すように、`my.domain.com/es/web/guest/home`からチェックした場合、値は`/es/web/guest/home`になります。

    // Inside my.domain.com/es/web/guest/home
    console.log(Liferay.currentURL); // "/es/web/guest/home"

## Liferay CurrentURLEncoded

`Liferay.currentURLEncoded`プロパティは、サーバールートからインターネット経由で安全に送信できるようにASCIIでエンコードされた現在のURLのパスを保持します。

たとえば、以下に示すように、`my.domain.com/es/web/guest/home`からチェックした場合、値は`%2Fes%2Fweb%2Fguest%2Fhome`になります。

    // Inside my.domain.com/es/web/guest/home
    console.log(Liferay.currentURLEncoded); // "%2Fes%2Fweb%2Fguest%2Fhome"

## ポートレットURLのメソッドとプロパティ

`Liferay.PortletURL`メソッド：

| メソッド | パラメーター | 戻り値 |
| --- | --- | --- |
| `createURL` | `basePortletURL`、`params` | 新しい`PortletURL(null, params, basePortletURL);` |
| `createActionURL` |  | 新しい`PortletURL(PortletURL.ACTION_PHASE`); |
| `createRenderURL` |  | 新しい`PortletURL(PortletURL.RENDER_PHASE`); |
| `createResourceURL` |  | 新しい`PortletURL(PortletURL.RESOURCE_PHASE`); |

`Liferay.PortletURL`プロパティ：

| プロパティ | 値 |
| --- | --- |
| `ACTION_PHASE` | "1" |
| `RENDER_PHASE` | "0" |
| `RESOURCE_PHASE` | "2" |

ポートレットURLが作成されると、URLをさらに操作するために使用できるいくつかのメソッドにアクセスできます。

| メソッド | 説明 | パラメーター | 戻り値 |
| --- | --- | --- | --- |
| `setDoAsGroupId` | URLのサイト、組織、またはユーザーグループのIDを設定します | `doAsGroupId` | 更新されたポートレットURLオブジェクト |
| `setDoAsUserId` | 偽装するユーザーのIDを設定します | `doAsUserId` | 更新されたポートレットURLオブジェクト |
| `setEscapeXML` | URLをXMLエスケープする必要があるかどうかを設定します | `true`または`false` | 更新されたポートレットURLオブジェクト |
| `setLifecycle` | このURLのターゲットポートレットのライフサイクルを設定します | `lifecycle` | 更新されたポートレットURLオブジェクト |
| `setName` | ポートレットURLの`javax.portlet.action`名を設定します | `name` | 更新されたポートレットURLオブジェクト |
| `setParameter` | 個々のパラメーターを作成するか、既存の予約済みパラメーターを置き換えます | key,value | 更新されたポートレットURLオブジェクト |
| `setParameters` | 複数のパラメーターを作成するか、既存の予約済みパラメーターを置き換えます | {key:value,...} | 更新されたポートレットURLオブジェクト |
| `setPlid` | ポートレットレイアウトIDを設定します | `plid` | 更新されたポートレットURLオブジェクト |
| `setPortletId` | ターゲットポートレットのIDを設定します | `portletId` | 更新されたポートレットURLオブジェクト |
| `setPortletMode` | URLが要求をトリガーする場合、ポートレットモードを設定します | `portletMode` | 更新されたポートレットURLオブジェクト |
| `setResourceId` | URLのターゲットリソースのIDを設定します | `ResourceId` | 更新されたポートレットURLオブジェクト |
| `setSecure` | URLをセキュアにする（HTTPS）かどうかを設定します。 | `true`または`false` | 更新されたポートレットURLオブジェクト |
| `setWindowState` | URLが要求をトリガーする場合、ポートレットのウィンドウ状態を設定します | `windowState` | 更新されたポートレットURLオブジェクト |
| `toString` | URLをStringとして返します |  | StringとしてのポートレットURL |
| `_isReservedParam` | パラメーターが予約されているかどうかを返します | `paramName` | パラメーターが予約されている場合は`true` |

これで、`Liferay`グローバルJavaScriptオブジェクト内のメソッドを使用してURLを操作する方法がわかりました。

## 関連トピック

[@product@ JavaScript Utilities](/docs/7-1/tutorials/-/knowledge_base/t/javascript-utilities)

[Liferay Theme Display](/docs/7-1/tutorials/-/knowledge_base/t/liferay-themedisplay)
