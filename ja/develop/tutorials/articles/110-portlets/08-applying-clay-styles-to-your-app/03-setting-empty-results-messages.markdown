---
header-id: setting-empty-results-messages
---

# 空の結果メッセージの設定

[TOC levels=1-4]

UIをツアーした場合、検索コンテナにメッセージが表示されたり、場合によってはアニメーションが表示されたりすることがあります。

![図1：これは、Webコンテンツポートレットの空の結果アニメーションからの静止フレームです。](../../../images/no-web-content-found.png)

`liferay-frontend：empty-results-message` タグのおかげで、空の結果メッセージとアニメーションも使用するようにアプリを構成できます。

次の手順を実行します：

1.  `liferay-frontend` taglib宣言をポートレットの `init.jsp`追加します。

    ``` markup
    <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
    ```

2.  [`empty-result-message` タグ](@app-ref@/frontend-taglib/latest/taglibdocs/liferay-frontend/empty-result-message.html) をポートレットのビューに追加します。

    ``` html
    <liferay-frontend:empty-result-message
    />
    ```

3.  タグの属性を設定して、アニメーションまたは画像の有無にかかわらず、検索コンテナの空の結果メッセージを定義します。 属性については、次の表で説明します。

    | 属性                    | 説明                                                                                                                                                                                                                                                                                                                                 |
    | --------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
    | `actionDropdownItems` | 使用可能なアクションの数に応じて、ドロップダウンメニュー、リンク、またはボタンで空の結果に対して表示するアクションを指定します。                                                                                                                                                                                                                                                                   |
    | `animationType`       | アニメーションのCSSクラス。 四つの値は、これらのCSSクラスでデフォルトで利用可能である： `EmptyResultMessageKeys.AnimationType.EMPTY` （`タグライブラリエンプティ状態`）、 `EmptyResultMessageKeys.AnimationType.SEARCH` （`タグライブラリ検索状態`）、 `EmptyResultMessageKeys.AnimationType。成功` （`taglib-success-state`）、および `EmptyResultMessageKeys.AnimationType.NONE`。 必要に応じて、カスタムCSSクラスを指定することもできます。 |
    | `componentId`         | `actionDropdownItems` コンポーネント（ドロップダウンメニュー、リンク、またはボタン）のIDを指定します                                                                                                                                                                                                                                                                     |
    | `description`         | メインメッセージの下に表示する説明テキスト。                                                                                                                                                                                                                                                                                                             |
    | `elementType`         | メインメッセージの言語キー `no-x-yet`の `x` パラメーターを置き換える要素のタイプ。                                                                                                                                                                                                                                                                                  |


    構成例を以下に示します。

    ``` html
    <liferay-frontend:empty-result-message
      actionDropdownItems="<%= FragmentPermission.contains(permissionChecker, scopeGroupId, 
      FragmentActionKeys.MANAGE_FRAGMENT_ENTRIES) ? fragmentDisplayContext.getActionDropdownItems() : null %>"
      animationType="<%= EmptyResultMessageKeys.AnimationType.NONE %>"
      componentId="actionsComponent"
      description='<%= LanguageUtil.get(request, "collections-are-needed-to-create-fragments") %>'
      elementType='<%= LanguageUtil.get(request, "collections") %>'
    />
    ```

    | **注：** 利用可能なデフォルトのアニメーションを独自のものに置き換えることができます|オーバーライドすることにより、 `のtaglib-空の状態`、 `のtaglib-状態-検索`、および| `taglib-success-state` 提供するCSSクラス| [\ _empty \ _result \ _message.scss](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-css/frontend-css-web/src/main/resources/META-INF/resources/taglib/_empty_result_message.scss)、|または `@ theme_image_path @ / states /` の既存のアニメーションを置き換えることにより|フォルダ。 または、定義する新しいCSSクラスを提供することもできます|アニメーションを作成し、 `animationType` 属性の値に使用します。

empty \ _state.gif：

![図2：空の状態のアニメーションを使用して、検索するエントリがないことを示します。](../../../images/empty_state.gif)

search \ _state.gif：

![図3：検索状態アニメーションを使用して、検索結果が見つからなかったことを示します。](../../../images/search_state.gif)

success \ _state.gif：

![図4：成功状態のアニメーションを使用して、検索結果が見つかったことを示します。](../../../images/success_state.gif)

| **注：** 空の結果メッセージには、必要に応じて静的画像を含めることもできます。 |代わりに有効な画像タイプを使用してください。 すべてのアニメーションは、タイプ `GIF` でなければなりません。しかし。

すばらしいです\！ これで、空の結果メッセージを表示するようにアプリを構成する方法がわかりました。

## 関連トピック

  - [LiferayフロントエンドTaglibの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-frontend-taglibs-in-your-portlet)
  - [ボタンの追加パターンを適用する](/docs/7-1/tutorials/-/knowledge_base/t/applying-the-add-button-pattern)
