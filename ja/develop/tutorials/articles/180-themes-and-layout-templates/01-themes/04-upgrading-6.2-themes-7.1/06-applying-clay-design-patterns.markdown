---
header-id: applying-clay-design-patterns-to-6-2-themes
---

# 粘土のデザインパターンの適用

[TOC levels=1-4]

@product-ver@は、Liferayの [Lexicon Experience Language](https://lexicondesign.io/)のWeb実装である [Clay](https://liferay.github.io/clay/)使用し

 。 Lexicon Experience Languageは、アプリケーションUIのスタイルガイドラインとベストプラクティスを提供します。 ClayのCSS、HTML、およびJavaScriptコンポーネントにより、開発者は完全に実現されたUIを迅速かつ効果的に構築できます。 @product@の [互換性レイヤー](/docs/7-1/tutorials/-/knowledge_base/t/using-the-bootstrap-3-lexicon-css-compatibility-layer) 、 [Clay CSS](https://clayui.com/)とともに [Lexicon CSS](https://lexiconcss.wedeploy.io/) マークアップを使用できます。</p> 

| **注：** 互換性レイヤーは、以下を確実にするための短期的なソリューションとして意図されています。 Bootstrap 3とLexicon CSSコンポーネントが壊れていないことを確認してください。 [Bootstrap 4](https://getbootstrap.com/docs/4.3/migration/) を使用するようにテーマを更新します|および [Clay CSS](https://clayui.com/docs/css-framework/scss.html)。 なります|将来のリリースでは無効になります。 Bootstrap 4とClayを使用するようにテーマを移行します|できるようになり次第、CSS。

このセクションでは、ClayをHTMLマークアップに適用する方法を示します。 たとえば、これはLiferay Portal 6.2 Lunar Resortの予約フォームです。

    <p>
    Thanks for choosing to stay at the Liferay Lunar Resort! Please fill out the 
    form below to book your stay. We know you have a choice in where to stay on 
    the Moon... 
    oh wait no you don't. Thanks for picking us anyways. We'll see you soon on 
    the Moon!
    </p>
    
    <form class="form-horizontal">
            <fieldset>
              <legend>Reservation Form</legend>
              <div class="control-group">
                  <label class="control-label" for="inputName">Name</label>
                  <div class="controls">
                          <input type="text" id="inputName"
                          placeholder="Enter your Name here" required="required">
                      </div>
              </div>
              <div class="control-group">
                  <label class="control-label" for="inputEmail">Email</label>
                  <div class="controls">
                      <input type="email" id="inputEmail"
                      placeholder="Enter your E-Mail here" required="required">
                  </div>
              </div>
              <div class="control-group">
                  <div class="controls">
                      <button type="submit" class="btn">Submit</button>
                  </div>
              </div>
            </fieldset>
    </form>
    
    <p style="padding-bottom:25px;">
    Thanks again for booking with Liferay. When you book with Liferay, you 
    remember your stay. Please take a moment to fill out our guestbook below. 
    </p>
    

上記のHTMLコードは、Bootstrap 2のマークアップとCSSクラスを使用しています。 Lunar Resortフォームの更新されたClayマークアップは次のとおりです。

    <p>
    Thanks for choosing to stay at the Liferay Lunar Resort! Please fill out the 
    form below to book your stay. We know you have a choice in where to stay on 
    the Moon... 
    oh wait no you don't. Thanks for picking us anyways. We'll see you soon on 
    the Moon!
    </p>
    
    <form role="form-horizontal">
            <fieldset>
              <legend>Reservation Form</legend>
              <div class="form-group">
                  <label for="inputName">Name</label>
                  <input type="text" id="inputName" class="form-control"
                  placeholder="Enter your Name here" required="required">
              </div>
              <div class="form-group">
                  <label for="inputEmail">Email</label>
                  <input type="email" id="inputEmail" class="form-control"
                  placeholder="Enter your E-Mail here" required="required">
              </div>
              <div class="form-group">
                      <button type="submit" class="btn btn-primary">Submit
                      </button>
              </div>
            </fieldset>
    </form>
    
    <p style="padding-bottom:25px;">Thanks again for booking with Liferay. When 
    you book with Liferay, you remember your stay. Please take a moment to fill 
    out our guestbook below.</p>
    

フォームに適用されるクレイの更新は次のとおりです。

  - `コントロールグループ` クラスは、 `フォームグループ` クラスに更新されました。
  - `コントロールラベル` クラスは、 `ラベル` 要素から削除されました。
  - `<div class=""controls>` 要素が削除されました。
  - `フォームコントロール` クラスは、各 `入力` 要素に追加されました。
  - フォームの送信ボタンを強調するために、 `btn-primary` クラスが追加されました。

テーマのHTMLファイルに同様のClayパターンを適用できます。

テーマを@product-ver@ \に更新しました！ テーマプロジェクトから展開できます。 ユーザーは、アップグレードしたテーマで作成した視覚スタイルを引き続き楽しむことができます。



## 関連トピック

[Liferayテーマジェネレーター](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[@product-ver@へのアップグレード](/docs/7-1/deploy/-/knowledge_base/d/upgrading-to-liferay-71)
