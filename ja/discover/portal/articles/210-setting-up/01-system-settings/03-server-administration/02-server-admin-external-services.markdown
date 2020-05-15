---
header-id: server-administration-external-services
---

# サーバー管理：外部サービス

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ユーザーは、Documents and Mediaライブラリを介して、あらゆるタイプのファイルをアップロードおよび共有できます。これは、ファイルのカスタマイズ可能で権限が有効なオンラインリポジトリです（詳細については、 [公開ファイル ](/docs/7-1/user/-/knowledge_base/u/publishing-files) を参照）。 [PDFBox](https://pdfbox.apache.org/) は@product@に含まれており、特定のファイルタイプ（主にPDF）の自動プレビューを生成します。 2つの追加ツールをインストールして、他のファイルタイプのプレビューを生成できます。

  - [**OpenOffice：**](https://www.openoffice.org/) または [**LibreOffice：**](https://www.libreoffice.org/) さまざまなタイプのドキュメントのプレビューを変換および生成します。

  - [**ImageMagick：**](https://www.imagemagick.org/script/index.php) 多くの種類の画像の高品質な画像プレビューを生成します。

  - [**Xuggler：**](http://www.xuggle.com/xuggler/) オーディオおよびビデオファイルのプレビューを変換および生成します。

Liferay 7.1以降、OpenOffice / LibreOfficeはポータルプロパティではなく、OSGi構成管理を介して構成されます。 したがって、これらの設定を調整するには、 *コントロールパネル* \&arr; *構成*\&arr; *システム設定* \&arr; *その他* → *OpenOffice統合*ます。 `[Liferay Home] / osgi / configs` フォルダーの `com.liferay.document.library.document.conversion.internal.configuration.OpenOfficeConfiguration.config` ファイルを使用して、これらの設定を調整することもできます。 詳細については、 [Breaking Changes](https://portal.liferay.dev/docs/7-1/reference/-/knowledge_base/r/breaking-changes#moved-openoffice-properties-to-osgi-configuration) ドキュメントを参照してください。

ImageMagickとXugglerをインストールしたら、サーバー管理アプリの[外部サービス]タブを使用して、それらを使用するように@product@を構成できます。 ご使用のオペレーティングシステムに合ったこれらのツールの正しいバージョンを選択してください。 古いバージョンは@product@で正しく動作しない可能性があるため、最新の安定バージョンをインストールすることをお勧めします。 ImageMagickは手動でインストールする必要がありますが、コントロールパネルからXugglerをインストールできます。

| **ヒント：** Linuxサーバーで@product@を実行していて問題が発生した場合| Xugglerを有効にするには、サーバーのglibcバージョンを確認します。 Xugglerが機能するには、 glibcをバージョン2.6以降に更新する必要がある場合があります。

## ImageMagick設定

ImageMagickを設定して画像とPDFプレビューを生成する前に、それとその依存関係であるGhostscriptをインストールします。 これはオペレーティングシステムによって異なります。Linuxでは、両方が既にインストールされている可能性があります。 Windowsにはインストールされない可能性がありますが、macOSにインストールされる可能性があります。

1.  [ImageMagick](https://www.imagemagick.org/script/index.php)ダウンロードしてインストールし

 。</p></li> 
   
   2  [Ghostscript](https://www.ghostscript.com/)ダウンロードしてインストールし 。</p></li> </ol> 
  
  インストールしたら、サーバー管理アプリの[外部サービス]タブまたは `portal-ext.properties` ファイルでImageMagickを有効にする必要があります。 `portal-ext.properties`を使用する場合は、次の行を追加し、検索パスがImageMagickおよびGhostscript実行可能ファイルを含むディレクトリを指していることを確認します。 macOSまたはUnix環境でGhostscriptが使用するフォントのパスを構成する必要がある場合もあります。
  
      imagemagick.enabled=true
      imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
      imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
      imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick
      
  
  代わりに次の手順に従って、サーバー管理アプリの[外部サービス]タブからImageMagickを有効にします。
  
  1.  *コントロールパネル*、 *構成* → *サーバー管理*に移動し、 *外部サービス* タブをクリックします。

2.  ImageMagickおよびGhostscriptセクションを展開し、 *Enabled*を選択します。

3.  ImageMagickおよびGhostscript実行可能ファイルへのパスが正しいことを確認します。

![図1：ImageMagickとGhostscriptを有効にし、パスが正しいことを確認します。](../../../../images/imagemagick-ghostscript.png)



## Xugglerの設定

以下の手順に従って、Xugglerをインストールして構成します。

1.  *コントロールパネル*、 *構成* → *サーバー管理*に移動し、 *外部サービス* タブをクリックします。

2.  Xugglerセクションで、オペレーティングシステムに一致するXuggler `.jar` ファイルを選択します。 次に、[ *インストール*]をクリックします。

3.  アプリケーションサーバーを再起動します。

4.  ポータルでXugglerを有効にします。 これを行うには2つの方法があります。
   
         - コントロールパネルで、[ *Server Administration* →[ *External Services* ]タブに移動し、[ *Enabled*]を選択して、[ *Save*]をクリックします。

      - 次の行を `portal-ext.properties` ファイルに追加し、アプリケーションサーバーを再起動します。
        
            xuggler.enabled=true
            

![図2：Xugglerをインストールします。](../../../../images/xuggler-install.png)
