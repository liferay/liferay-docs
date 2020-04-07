---
header-id: creating-layouts-inside-custom-portlets
---

# カスタムポートレット内でのレイアウトの作成

[TOC levels=1-4]

レイアウトテンプレートは、サイトページでのポートレットとコンテンツの整理方法を指定します。 代わりに、ポートレットのコンテンツを整理したい場合はどうしますか？ `<aui>` タグを使用すると、ポートレット内でBootstrapを使用してレイアウトを作成できます。 このチュートリアルでは、このプロセスについて説明します。

次の手順を実行します：

1.  ポートレットのJSPを開き、まだ含まれていない場合はAUI taglib宣言を含めます。
   
        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

2.  ポートレットのコンテンツを [`<aui:container>` タグ](#aui-container)ラップします。 ポートレットのコンテンツの一部のみをレイアウトに含める場合は、その部分を `<aui:container>` タグでラップします。

3.  `<aui:container>...</aui:container>` タグの間に、ポートレットのレイアウトで必要な行ごとに [`<aui:row>` タグ](#aui-row) セットを追加します。

4.  行に含める列ごとに [`<aui:col>` タグ](#aui-col) セットを追加します。 レイアウトの各行に対してこの手順を繰り返します。

完全な例を以下に示します。

    <aui:container>
      <aui:row>
        <aui:col md="12">
          <h1>My Custom Layout Portlet</h1>
        </aui:col>
      </aui:row>
      <aui:row>
        <aui:col md="4" sm="6">
          <h2>Column One</h2>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
            Integer eget pulvinar quam. Vivamus ornare leo libero, sed 
            mollis urna aliquam ac. Duis porta sapien non felis varius, in 
            iaculis orci fermentum. Etiam quis molestie elit, in tempor 
            risus. Morbi varius elementum lectus at feugiat. Quisque 
            dapibus orci ac dui eleifend, ut ullamcorper nulla sagittis. 
            Ut ac scelerisque sem.
          </p>
        </aui:col>
        <aui:col md="8" sm="6">
        <h2>Column Two</h2>
          <p>
            Aliquam hendrerit augue sed nisl ullamcorper pulvinar. Donec 
            tristique congue erat ac condimentum. Suspendisse vehicula 
            nunc vel velit imperdiet dapibus. In hac habitasse platea 
            dictumst. Morbi eleifend arcu sit amet magna faucibus, vitae 
            posuere erat finibus. Sed hendrerit convallis ipsum id luctus. 
            Aliquam aliquam consequat turpis eu vulputate. Nulla vitae 
            libero lorem. Proin nec lacus et nunc laoreet posuere. 
            Vestibulum euismod vestibulum faucibus. Vivamus dolor justo, 
            malesuada ac libero ac, feugiat varius leo. Integer viverra 
            nisi vel fringilla aliquam.
          </p>
          <p>
            Suspendisse potenti. Mauris neque nisl, hendrerit a sem at, 
            rutrum dictum arcu. Ut aliquet tortor vel tortor interdum 
            dictum. Sed non sapien quam. Nunc aliquet in massa elementum 
            aliquam. Cras convallis tristique ante ut ultrices. Aenean 
            quis congue nulla. Integer in lacus lectus. Mauris maximus, 
            nibh sit amet pharetra laoreet, sem dolor eleifend metus, non 
            semper sem justo vel mauris. Praesent tristique quis risus 
            vulputate faucibus. Nullam feugiat diam vel elit pharetra, id 
            porta velit fringilla. Pellentesque metus justo, dictum et 
            dolor venenatis, pretium egestas massa. Donec risus nisi, 
            elementum in lectus id, imperdiet blandit mauris.
          </p>
        </aui:col>
      </aui:row>
    </aui:container>

![図1：ポートレットのカスタムレイアウトを使用すると、ユーザーを念頭に置いてポートレットのコンテンツを整理できます。](../../images/portlets-layout.png)

2行目の列は、Bootstrapのグリッドクラスを利用してレスポンシブレイアウトを作成します。 中サイズのビューポートでは、 `列-1` は幅33.33％、 `列-2` は幅66.66％ですが、小サイズのビューポートでは `列-1` と `列-2` 両方が50％幅です。

![図2：Bootstrapのグリッドクラスを利用して、カスタムポートレット内にレスポンシブレイアウトを作成できます。](../../images/portlets-layout-responsive.png)

## AUIレイアウトタグの属性

このセクションには、各タグで使用可能な属性のリストとその目的の簡単な説明が含まれています。

### AUIコンテナー

`<aui:container>` タグは、 `<aui:row>` コンポーネントをラップして追加のスタイリングを提供するコンテナ `<div>` タグを作成します。

次の属性をサポートしています。

| 属性                | タイプ                    | 説明                                        |
| ----------------- | ---------------------- | ----------------------------------------- |
| cssClass          | ひも                     | コンポーネントをスタイリングするためのCSSクラス                 |
| dynamicAttributes | 地図\<String, Object\> | コンテナの `データ` 属性のマップ                        |
| 流体                | boolean                | コンテナがビューポートの幅全体に広がるようにするかどうか。 デフォルト値はtrue |
| id                | ひも                     | コンポーネントインスタンスのID                          |

### AUI行

`<aui:row>` タグは、 `<aui:col>` コンポーネントを保持する行を作成します。

次の属性をサポートしています。

| 属性       | タイプ | 説明                        |
| -------- | --- | ------------------------- |
| cssClass | ひも  | コンポーネントをスタイリングするためのCSSクラス |
| id       | ひも  | コンポーネントインスタンスのID          |

### AUI Col

`<aui:col>` タグは、 `<aui:row>` コンポーネントのコンテンツを表示する列を作成します。

次の属性をサポートしています。

| 属性       | タイプ | 説明                                                                                                                            |
| -------- | --- | ----------------------------------------------------------------------------------------------------------------------------- |
| cssClass | ひも  | コンポーネントのスタイルを設定するためのCSSクラス。                                                                                                   |
| id       | ひも  | コンポーネントインスタンスのID。                                                                                                             |
| lg       | ひも  | Boostrapグリッド `col-lg-`使用される1〜12の数字のカンマ区切り文字列                                                                                  |
| md       | ひも  | Boostrapグリッド `col-md-`使用されるコンマ区切りの数字1〜12の文字列                                                                                  |
| sm       | ひも  | Boostrapグリッド `col-sm-`使用されるカンマ区切りの数字1〜12の文字列                                                                                  |
| xs       | ひも  | Boostrapグリッド `col-xs-`使用されるコンマ区切りの数字1〜12の文字列                                                                                  |
| スパン      | int | 含まれる行の列の幅（小数12）。 たとえば、スパンが4の場合、列の幅は含まれる行の合計幅の4/12（または1/3）になります。                                                               |
| 幅        | int | 含まれる行の列の幅（パーセンテージ）。span属性をオーバーライドします。 次に、幅は（（width / 100）x 12）で表されるスパンに変換され、最も近い整数に丸められます。 たとえば、幅33は3.96に変換され、スパン値4に切り上げられます。 |

これで、ポートレット内にレイアウトを作成する方法がわかりました。

## 関連トピック

[Liferayテーマジェネレーターを使用したレイアウトテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-with-the-themes-generator)
