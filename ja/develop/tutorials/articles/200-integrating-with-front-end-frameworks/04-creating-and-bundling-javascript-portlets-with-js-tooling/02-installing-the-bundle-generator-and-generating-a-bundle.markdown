---
header-id: installing-the-bundle-generator-and-generating-a-bundle
---

# Bundle Generatorのインストールとバンドルの生成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

このチュートリアルでは、 [Liferay JS Generator](https://www.npmjs.com/package/generator-liferay-js) をインストールする方法と、それを使用してJavaScriptウィジェットを作成する方法を示します。

| **注：** Liferay Bundle Generatorは、|のv2.7.1で非推奨になりました。 [Liferay JSツールキット](https://github.com/liferay/liferay-js-toolkit)。 されている| Liferay JS Generatorに名前を変更しました。 Liferay Bundleをまだ実行している場合| Generator、代わりにLiferay JS Generatorをインストールすることをお勧めします| Liferay Bundle Generatorは|将来のバージョン。

| **重要：** Liferay JS Generatorを使用するには、Liferay JSが必要です。 @product@インスタンスにインストールされたPortlet Extender。 JSポートレットエクステンダー| Liferay Marketplaceから入手できるLabsアプリケーションです| [Liferay Digital Enterprise 7.1](https://web.liferay.com/marketplace/-/mp/application/115543020) |と| [Liferay Portal CE 7.1](https://web.liferay.com/marketplace/-/mp/application/115542926)。 | Labsとして指定されたアプリは実験的なものであり、Liferayではサポートされていません。 彼らは便利で最先端の機能の利用を促進するためにリリースされました。 |このステータスは予告なく変更される場合があります。 Labsアプリをダウンロードして使用してください|自分の裁量。

JavaScriptウィジェットを作成するには、次の手順に従います。

1.  [Node.js](http://nodejs.org/)をインストールします。 Node Package Manager（npm）もこれと共にインストールされることに注意してください。 npmを使用して残りの依存関係とジェネレーターをインストールし、 [npm環境を構成します](/docs/7-1/reference/-/knowledge_base/r/setting-up-your-npm-environment)。

2.  ジェネレーターに [Yeoman](http://yeoman.io/) をインストールします

</p> 
   
        npm install -g yeoman
       </li> 
   
   3  Liferay JS Generatorをインストールします。
  
       npm install -g generator-liferay-js
      

4  以下のコマンドでジェネレーターを実行し、作成するJavaScriptウィジェットを選択して、続くプロンプトに答えます。
  
       yo liferay-js
      
  
  ![図1：liferay-bundleジェネレーターは、ウィジェットオプションの入力を求めます。](../../../images/liferay-js-generator-prompts.png)

5  ウィジェットの生成時にアプリサーバー情報を指定した場合、以下のコマンドを実行してウィジェットをデプロイできます。 これを確認するには、ウィジェットの `.npmbuildrc`の `liferayDir` エントリの値を確認します。
  
       npm run deploy
      </ol> 

すばらしいです\！ これで、Liferay JS Generatorをインストールして実行する方法がわかりました。



## 関連トピック

[liferay-npm-bundler](/docs/7-1/reference/-/knowledge_base/r/liferay-npm-bundler)

[ポートレットでnpmを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-npm-in-your-portlets)

[クレイスタイルをアプリに適用する](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-styles-to-your-app)
