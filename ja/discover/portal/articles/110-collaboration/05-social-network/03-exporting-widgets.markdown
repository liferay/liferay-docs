---
header-id: exporting-widgets-to-other-websites
---

# ウィジェットを他のWebサイトにエクスポートする

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

そのウィジェットをOpenSocial経由で他のWebサイトに公開できます。 これにより、別のWebサイトのコンテキストでウィジェットまたはコンテンツを提供できます。 これがどのように行われるかを知るために読んでください。

## OpenSocialガジェットの共有

OpenSocialは、ソーシャルネットワーキング用の一連のAPIで構成されています。 サーバーのウィジェットを [ighome.com](http://ighome.com) や [igoogleportal.com](http://igoogleportal.com)などの他のサイトと共有すると便利な場合があります。 これらのサイトでは、ユーザーはガジェットを使用して自分のページをカスタマイズできます。 ユーザーは、任意のOpenSocial互換サイトで@product@ウィジェットを共有できます。 @product@は、ウィジェットのURLをOpenSocialプラットフォームと共有することでこれを行います。 このURLは、ユーザーの特定のウィジェットインスタンスに固有です。 したがって、ユーザーは同じウィジェットの複数のインスタンスを異なるOpenSocialガジェットとして共有できます。

OpenSocialでウィジェットを共有するには、次の手順に従います。

1.  共有するウィジェットをサイトページに追加します。

2.  ウィジェットの *Options* アイコン（![Options](../../../images/icon-app-options.png)）をクリックし、 *Configuration*を選択します。

3.  *共有* タブで、 *OpenSocialガジェット* セクションを開きます。

4.  セレクターを *設定し* YES *for* ユーザーがOpenSocialプラットフォームに\ [ウィジェット名\]を追加できるようにします*。 *OpenSocial Gadget URL* フィールドで、 `localhost：8080` をパブリックドメインとポートの名前に置き換えます。</p></li>

5

[ *保存*クリックします。

6

ダイアログを閉じ、ウィジェットの *オプション* アイコン（![Options](../../../images/icon-app-options.png)）をクリックします。 *OpenSocialプラットフォームに追加*という名前の新しいオプションが利用可能です。 OpenSocialプラットフォームのページにウィジェットを追加するには、このオプションを選択します。</ol>

![図1：OpenSocialを介してウィジェットを共有できます。](../../../images/open-social-sharing.png)
