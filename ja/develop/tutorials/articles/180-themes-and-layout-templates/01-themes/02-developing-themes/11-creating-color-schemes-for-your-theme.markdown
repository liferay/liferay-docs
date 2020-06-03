---
header-id: creating-color-schemes-for-your-theme
---

# テーマの配色の作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

カラースキームは、テーマに追加のカラーパレットを提供します。 テーマのCSSを少し変更するだけで済みます。 これは、同じデザインと雰囲気を維持しながら、テーマの外観を微妙に変更する簡単な方法です。

テーマの配色を作成するには、次の手順に従います。

1.  テーマの `css` フォルダーにカラースキーム（たとえば`color_schemes` ）を保持するフォルダーを作成し、テーマがサポートするカラースキームごとに `.scss` ファイルを追加します。

2.  CSSクラスを選択して各配色を識別し、配色のスタイルでこのクラスを指定します。 配色の名前は良い選択です。 このクラスは、サイトのに追加されます `<body>` カラースキームが適用されたとき、あなたがして、要素 *必見* 作品にそれらのためのカラースキームのスタイルでこのクラスを指定します。 たとえば、 `_day.scss`という名前の配色CSSファイルに `.day` を指定できます`</p>

<pre><code> body.day { background-color: #DDF; }
 .day a { color: #66A; }
`</pre>

    | **注：** デフォルトの配色では、テーマの `_custom.scss` を使用します。スタイルを設定するため、スタイルで名前を指定する必要はありません。</li>

3

カラースキーム `.scss` ファイルをテーマの `_custom.scss` ファイルにインポートします。 以下の例では、 `_day.scss` および `_night.scss` ファイルをインポートします。
  
       @import "color_schemes/day";
       @import "color_schemes/night";

4

テーマの `画像` フォルダーに各配色のフォルダーを作成し、各配色のサムネイルプレビュー</a> を追加します

。 フォルダ名 *は、手順2で指定した配色のCSSクラス名と* に一致する必要があります。</p></li> 
  
  5  テーマの `liferay-look-and-feel.xml` ファイルを開き、以下のパターンに従ってデフォルトのカラースキームを追加します。 ユーザーが別の配色を選択した後、テーマのデフォルトのルックアンドフィールに戻ることができるように、デフォルトの配色が必要です。 注配色のこと `名` 値は任意であるが、 `<css-class>` 要素の値 *一致しなければならない* 手順2で指定されたCSSクラス。 デフォルトの配色スタイルがテーマの `_custom.scss` ファイルにある場合、 `<css-class>``デフォルト` を使用します。 `<color-scheme-images-path>` 要素が含まれていることにも注意してください。 これは、テーマのサムネイル画像の場所を指定します。 この要素を最初の配色に配置して、すべての配色の画像パスを構成します。</ol> <theme id="my-theme-id" name="My Theme Name"> <color-scheme id="01" name="My Default Color Scheme Name"> <default-cs>本当</default-cs> <css-class>デフォルト</css-class> <color-scheme-images-path> ${images-path}/ my_color_schemes_folder_name /${css-class} </color-scheme-images-path> </color-scheme> ... </theme> 

    | **Note:** Color schemes are sorted alphabetically by `name` rather than `id`. | For example, a color scheme named `Clouds` and `id` `02` would be selected | by default over a color scheme named `Day` with `id` `01`. The | `<default-cs>` element overrides the alphabetical sorting and sets the color | scheme that is selected by default when the theme is chosen.
    

6.  次のパターンを使用して、デフォルトの配色の下に残りの配色を追加します。
   
        <color-scheme id="id-number" name="Color Scheme Name">
           <css-class>color-scheme-css-class</css-class>
        </color-scheme>
       

`liferay-look-and-feel.xml` ファイルの例を以下に示します。

    <look-and-feel>
        <compatibility>
            <version>7.1.0+</version>
        </compatibility>
        <theme id="my-liferay-theme" name="My Liferay Theme">
            <template-extension>ftl</template-extension>
            <color-scheme id="01" name="Default">
                <default-cs>true</default-cs>
                <css-class>default</css-class>
                <color-scheme-images-path>
                    ${images-path}/color_schemes/${css-class}
                </color-scheme-images-path>
            </color-scheme>
            <color-scheme id="02" name="Red">
                <css-class>red</css-class>
            </color-scheme>
            <color-scheme id="03" name="Green">
                <css-class>green</css-class>
            </color-scheme>
        </theme>
    </look-and-feel>
    

![図1：カラースキームにより、管理者はテーマの外観を選択できます。](../../../../images/theme-dev-color-schemes.png)

そこにあります。 今、あなたはあなたのテーマで狂気の配色をすることができます！



## 関連トピック

[レイアウトテンプレートの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-with-the-themes-generator)

[テーマのサムネイルプレビューを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-thumbnail-preview-for-your-theme)
