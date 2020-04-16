---
header-id: accessing-the-content-targeting-api
---

# コンテンツターゲットAPIへのアクセス

[TOC levels=1-4]

コンテンツターゲティングAPIを使用すると、オーディエンスターゲティング機能をサードパーティアプリケーションと統合したり、Liferayのアプリケーションがオーディエンスターゲティングと対話する方法をカスタマイズしたりできます。 たとえば、独自のアプリケーションでユーザーセグメントを一覧表示したり、誰かがカレンダーイベントを作成したときにキャンペーンを更新したりできます。

このチュートリアルでは、アプリケーションにコンテンツターゲットAPIへのアクセスを許可する方法を学びます。 次に、利用可能なJavaおよびJSON APIの使用方法の例をいくつか示します。

## コンテンツターゲットAPIの公開

Content Targeting APIにアクセスできるようにアプリを構成するには、1行のコードしか必要ありません。 このコード行は、プロジェクトのビルドファイルに追加する必要がある依存関係です。 以下の手順に従って、GradleプロジェクトのコンテンツターゲットAPI依存関係を追加します。

1.  アプリのプロジェクトフォルダーにある `build.gradle` ファイルを開きます。

2.  `依存関係を見つける{... ` 宣言し、そのセクション内に次の行を追加します。

    ``` groovy
    compileOnly group: "com.liferay.content-targeting", name: "com.liferay.content.targeting.api", version: 5.0.0"
    ```

これで、アプリはコンテンツターゲティングAPIにアクセスできるようになり、オーディエンスターゲティングが提供するすべての機能を利用できます。 次に、いくつかの例を学習して、Content Targeting APIの使用方法を学習します。

## コンテンツターゲットJava APIの使用

JavaまたはJSONを介してコンテンツターゲットAPIを呼び出すことができます。

Javaを使用してポートレット内の既存のユーザーセグメントのリストを表示するには、

1.  次のコードをポートレットクラス（たとえば、 `MVCPortlet` クラスを拡張するクラス）に追加して、オーディエンスターゲティングによって提供される `UserSegmentLocalService` 実装を取得します。

    ``` java
    @Reference(unbind = "-")
    protected void setUserSegmentLocalService(
        UserSegmentLocalService userSegmentLocalService) {

        _userSegmentLocalService = userSegmentLocalService;
    }

    private UserSegmentLocalService _userSegmentLocalService;
    ```

    オーディエンスターゲティングアプリケーションがインストールされていて、 `UserSegmentLocalService` 実装が利用可能な場合、 `_userSegmentLocalService` フィールドが入力されます。 それ以外の場合、この依存関係が解決されるまで、ポートレットは使用できません。

2.  このサービスを使用して、既存のユーザーセグメントのリストを取得し、ビューレイヤーでリクエスト属性として使用できるようにします。 これを行うには、次のように、ユーザーセグメントを取得してリクエスト属性で公開するロジックをポートレットクラスに追加します。

    ``` java
    ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
        WebKeys.THEME_DISPLAY);

    List<UserSegment> userSegments = null;

    try {
        userSegments = _userSegmentLocalService.getUserSegments(
            themeDisplay.getScopeGroupId());
    }
    catch (Exception e) {
        _log.error(e, e);
    }

    renderRequest.setAttribute("userSegments", userSegments);

    private static final Log _log = LogFactoryUtil.getLog(MyPortlet.class)
    ```

    `userSegments` リストは、 `UserSegmentLocalService`の `getUserSegments` メソッドを呼び出すことで生成されます。 このサービスは、コンテンツターゲットAPIの一部です。

3.  このロジックをポートレットの `view.jsp`追加します。

    ``` markup
    <h2>User Segments</h2>

    <ul>

        <%
        List<UserSegment> userSegments = (List<UserSegment>)request.getAttribute("userSegments");

        for (UserSegment userSegment : userSegments) {
        %>
            <li><%= userSegment.getName(locale) %></li>
        <%
        }
        %>

    </ul>
    ```

    このロジックは、 `UserSegment` オブジェクトを使用して、既存のユーザーセグメントを一覧表示します。 それでおしまい\！ `UserSegment` および `UserSegmentLocalService` クラスをファイルにインポートすると、コンテンツターゲットJava APIを介してポータルのユーザーセグメントに直接アクセスできます。

## コンテンツターゲティングJSON APIの使用

JSON APIを使用して同じことを行うことができます。

1.  ポートレットの `view.jsp` ファイルを開き、次のコードを挿入します。

    ``` markup
    <h2>Campaigns</h2>

    <ul id="<portlet:namespace/>campaigns">
    </ul>

    <aui:script use="aui-base">
        var campaignsList = A.one('#<portlet:namespace/>campaigns');

        Liferay.Service(
            '/ct.campaign/get-campaigns',
            {
                groupId: '<%= scopeGroupId %>'
            },
            function(response) {
                if (response.length) {
                    A.Array.each(response, function(item) {
                        campaignsList.append('<li>' + item.name + '</li>');
                    });
                }
            }
        );
    </aui:script>
    ```

    Content Targeting APIが呼び出され、既存のキャンペーンを取得していることに注意してください。

    ``` markup
    Liferay.Service(
        '/ct.campaign/get-campaigns',
        {
    ```

    次に、各キャンペーンが `campaignList` リストされ、ユーザーが表示できるようにポートレットに表示されます。

オーディエンスターゲティングによってJSON APIで公開されている使用可能なすべてのメソッド（例を含む）を表示する場合は、 `/ api / jsonws` URL（例： `localhost：8080 / api / jsonws`）にアクセスできます。 ご覧のとおり、コンテンツターゲティングJSON APIへのアクセスは、関連するJava APIへのアクセスと同じくらい簡単です。

Content Targeting APIを公開してアプリケーションで使用する方法を学びました。 次に、カスタムルールタイプの作成方法を学びます。
