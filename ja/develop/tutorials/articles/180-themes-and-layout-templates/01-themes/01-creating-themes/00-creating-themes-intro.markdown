---
header-id: creating-themes
---

# テーマの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Theme Generatorを使用すると、テーマ、テーマレット、レイアウトテンプレートなどを作成できます。 Liferay JS Theme Toolkitの [ツール](https://github.com/liferay/liferay-themes-sdk/tree/master/packages)つにすぎません。 ジェネレータを実行するには、いくつかの依存関係が必要です。 NodeJSがインストールされている場合、すでに一歩進んでいます。

Liferay Theme Generatorをインストールしてテーマを生成するには、次の手順に従います。

1.  [Node.js](http://nodejs.org/)をインストールします。 Liferayポータル7.1がサポートするバージョンである [v8.10.0](https://nodejs.org/download/release/v8.10.0/)インストールすることをお勧めします。 Node Package Manager（npm）もこれと共にインストールされることに注意してください。 npmを使用して、残りの依存関係とジェネレーターをインストールします。 次のステップに進む前に、npm環境</a> を

設定してください。 これを行わないと、後で権限の問題が発生する可能性があります。</p></li> 
   
   2  npmを使用して [Yeoman](http://yeoman.io/) および [gulp](https://gulpjs.com/)をインストールし 。</p> 
  
       npm install -g yo
      
  
  | **注：** Gulpは、生成されたテーマのローカル依存関係として含まれているため、|インストールする必要はありません。 |を実行してアクセスできます。 `node_modules \ .bin \ gulp` 後に、生成されたテーマのGulpタスクが続きます|ルートフォルダ。</li> 
  
  3  Liferay Theme Generatorをインストールします。 Liferay Theme Generatorのいくつかのバージョンが利用可能です。 インストールする必要があるバージョンは、開発している@product@のバージョンによって異なります。 必要なバージョンは以下の表にリストされています。
  
  
    | Liferay バージョン | Liferayテーマジェネレーターバージョン | コマンド                                            |
    | ------------- | ---------------------- | ----------------------------------------------- |
    | 6.2           | 7.x.x                  | `npm install -g generator-liferay-theme@^7.x.x` |
    | 7.0           | 7.x.x または 8.x.x        | 上記または下記と同様                                      |
    | 7.1           | 8.x.x                  | `npm install -g generator-liferay-theme@^8.x.x` |

    
    Windowsを使用している場合は、ステップ4の指示に従ってSassをインストールします。それ以外の場合は、ステップ5にスキップできます。

4  ジェネレーターはノードサスを使用します。 Windowsを使用している場合は、 [node-gypおよびPython](https://github.com/nodejs/node-gyp#installation)もインストールする必要があります。

5  ジェネレーターを実行し、プロンプトに従ってテーマを作成します。
  
       yo liferay-theme
      
  
  ![図1：構成に関するいくつかの質問に答えるだけでテーマを生成できます。](../../../../images/theme-generator-theme-prompt.png)

6  テーマフォルダに移動し、 `gulp deploy` を実行して、新しいテーマをサーバーにデプロイします。</ol> 

これで、強力なテーマ開発ツールを自由に使用できます。 空は限界\！

![図2：想像できるあらゆるテーマを作成するためのツールが手元にあります。](../../../../images/theme-generator-theme-example.png)
