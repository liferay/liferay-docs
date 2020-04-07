---
header-id: importing-clay-css-into-a-theme
---

# クレイCSSをテーマにインポートする

[TOC levels=1-4]

Clay CSSは、Bootstrapと@ \<product@\>特定のニーズとの間のギャップを埋め<product@\> 。 ブートストラップ機能が拡張され、より多くのユースケースに対応しました。 Clay CSSによって追加された新しいコンポーネントの一部を次に示します。

  - アスペクト比
  - カード
  - ドロップダウンワイドとドロップダウンフル
  - フィギュア
  - ネームプレート
  - サイドバー/サイドナブ
  - ステッカー
  - SVGアイコン
  - タイムライン
  - トグル

次のような時間のかかるタスクを達成するために、いくつかの再利用可能なCSSパターンも追加されました。

  - テキストの切り捨て
  - コンテナの残りの幅を埋めるコンテンツ
  - テーブルセル内のテキストの切り捨て
  - 残りのコンテナ幅を埋めるテーブルセルとコンテンツと同じ幅のテーブルセル
  - 折りたたみパネル内のアイコンの開閉
  - ネストされた垂直ナビゲーション
  - スライドアウトパネル
  - 通知アイコン/メッセージ
  - コンテンツの垂直方向の配置

次に、クレイの構造について詳しく学ぶことができます。

## 粘土CSS構造

[Clay CSS](https://claycss.com/) は、 [Clay Base](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay) と [Atlas](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/clay/atlas)

つのサブテーマがバンドルされています。 Clay Baseは@product@のBootstrap API拡張です。 必要なすべての機能とコンポーネントが追加され、Bootstrapのスタイルが継承されます。 その結果、Clay Baseは、BootstrapのSass変数APIを活用する [サードパーティテーマ](/docs/7-1/tutorials/-/knowledge_base/t/integrating-third-party-themes-with-clay) と完全に互換性があります。</p> 

Atlasは、クラシックテーマで使用される@product@のカスタムブートストラップテーマです。 その目的は、BootstrapとClay Baseを上書きおよび操作して、クラシックなルックアンドフィールを作成することです。 Atlasは、Bootstrapサードパーティテーマをインストールするのと同等です。

| **注：** サードパーティのテーマをAtlasに統合することはお勧めしません。 BootstrapのAPIの範囲外の変数とスタイルを追加します。

次に、Atlasテーマをカスタマイズする方法を学習できます。



## @product@でAtlasをカスタマイズする

すべてのクラシックテーマのファイルを含める場合は、これらの手順をスキップして次のセクションに進むことができます。

Atlasテーマをカスタマイズするには、次の手順に従います。

1.  テーマの `/ src / css` ディレクトリ（レガシーantテーマの場合、 `/ _diff / css`）に、以下のコードを含む `clay.scss` という名前のファイルを追加して保存します。
   
        @import "clay/atlas";
       

2.  以下のコードで `_imports.scss` という名前のファイルを追加して保存します。
   
        @import "bourbon";
        @import "mixins";
        @import "clay/atlas-variables";
       

3.  `font_awesome.scss` という名前のファイルを追加し、font-awesomeパスを追加してインポートします。
   
        // Icon paths
       
        $FontAwesomePath: "font-awesome/font";
       
        @import "font-awesome/scss/font-awesome";
       

4.  `_clay_variables.scss`という名前のファイルを追加します。 すべてのAtlas、Bootstrap、およびClay Base変数の変更は、このファイルに配置する必要があります。

5.  以下のコードで `_custom.scss` という名前のファイルを追加して保存します。
   
        /* Use these inject tags to dynamically create imports for 
        themelet styles. You can place them where ever you like in this file. */
       
        /* inject:imports */
        /* endinject */
       
        /* This file allows you to override default styles in one central 
        location for easier upgrade and maintenance. */
       

このファイルにカスタムCSSを配置します。 次に、Classicテーマを使用してAtlasを拡張する方法を学習できます。



### クラシックテーマを使用したAtlasの拡張

AtlasテーマをClassicテーマで拡張するには、これらのディレクトリにあるすべてのファイルをテーマにコピーします。

    frontend-theme-classic/src/css
    frontend-theme-classic/src/images
    frontend-theme-classic/src/js
    frontend-theme-classic/src/templates
    

また、自動的にできる [あなたのテーマにこれらのファイルをコピーする](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files) 使用して `一気のキックスタート` コマンドをし、画面の指示に従います。

次に、粘土ベースのカスタマイズ方法を学習できます。



## 粘土ベースのカスタマイズ

わずか数回のインポートでクレイベースをカスタマイズできます。

カスタムテーマの `/ src / css` フォルダー（レガシーantテーマ： `/ _diff / css`）に、 `font_awesome.scss` という名前のファイルを以下のコードとともに追加して保存します。

    // Icon paths
    
        $FontAwesomePath: "font-awesome/font";
    
        @import "font-awesome/scss/font-awesome";
    

次に、同じフォルダーに `_clay_variables.scss`という名前のファイルを追加します。 すべてのAtlas、Bootstrap、およびClay Base変数の変更は、このファイルに配置する必要があります。

前述のように、カスタムCSSは `_custom.scss`配置する必要があります。

これで、テーマでClay CSSを使用する方法がわかりました。



## 関連トピック

[クレイスタイルをアプリに適用する](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-styles-to-your-app)

[サードパーティのテーマとクレイの統合](/docs/7-1/tutorials/-/knowledge_base/t/integrating-third-party-themes-with-clay)
