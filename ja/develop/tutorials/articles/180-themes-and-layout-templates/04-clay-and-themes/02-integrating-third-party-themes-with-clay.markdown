---
header-id: integrating-third-party-themes-with-clay
---

# サードパーティのテーマとクレイの統合

[TOC levels=1-4]

[Clay Base](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay) は、テーマに必要なすべての機能とコンポーネントを提供し、Bootstrapのスタイルを継承します。 その結果、Clay Baseは、BootstrapのSass変数APIを活用するサードパーティのテーマと完全に互換性があります。

[スタイルテーマ](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled) は、Clay Baseを使用してスタイルとコンポーネントを提供します。 したがって、ベストプラクティスとして、スタイル設定されたベーステーマを使用してサードパーティのテーマを統合する必要があります。

| **注：** サードパーティのテーマを|から購入できます。 [Liferay Marketplace](https://web.liferay.com/marketplace)。 サードパーティのテーマ|互換性を保つには、Sassで構築する必要があります。 **** Sassファイルが含まれていることを確認してください|テーマを購入する前に。

次の手順に従って、サードパーティのテーマをClay Baseに統合します。

1.  スタイル付きテーマをベースとして新しいテーマを作成します。 これは、新しく作成されたテーマのデフォルトのベーステーマであるため、これ以上のアクションは不要です。 これにより、必要なClay Baseファイルが提供されます。

2.  テーマの `/ src / css` フォルダー（レガシーantテーマ： `/ _diff / css`）に、 `font_awesome.scss` という名前のファイルを以下のコードとともに追加して保存します。
   
        // Icon paths
       
        $FontAwesomePath: "font-awesome/font";
       
        @import "font-awesome/scss/font-awesome";

3.  同じフォルダーに、 `_clay_variables.scss`という名前のファイルを追加します。 すべてのAtlas、Bootstrap、およびClay Base変数の変更は、このファイルに配置する必要があります。

4.  サードパーティのテーマを含む `/ src / css` （レガシーantテーマの場合は `/ _diff / css`）内にフォルダーを作成します（例： `/ src / css / awesome-theme` または `/ _diff / css / awesome-テーマ`）

5.  テーマのCSSコンテンツを作成したフォルダーにコピーします。

6.  `_clay_variables.scss`で、テーマ変数を含むファイルをインポートします。 たとえば、 `@import "awesome-theme / variables.scss";`

    | **注：** Sassファイルをインポートするときに、先頭のアンダースコアを省略できます。

7.  `_custom.scss`で、CSSを含むファイルをインポートします。 たとえば、 `@import "awesome-theme / main.scss";`

8.  `gulp deploy` テーマをデプロイします（レガシーantテーマの場合、 `ant deploy`使用します）

これで、サードパーティのテーマをClay Base \に統合する方法がわかりました！

## 関連トピック

[クレイスタイルをアプリに適用する](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-styles-to-your-app)

[クレイCSSをテーマにインポートする](/docs/7-1/tutorials/-/knowledge_base/t/importing-clay-css-into-a-theme)
