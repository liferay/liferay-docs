---
header-id: knowledge-base-zip-file-requirements
---

# ナレッジベースのZIPファイルの要件

[TOC levels=1-4]

ナレッジベースインポーターは記事の階層をサポートしているため、ZIPファイルのディレクトリ構造のどこにでもマークダウンファイルを指定できます。 それらは、任意の数のフォルダーにネストできます。 添付ファイルでサポートされているファイルは画像ファイルのみです。

| **注：** インポートされた記事は、ワークフロー設定から独立しています。 これ|は、 **インポートされた記事が自動的に承認されることを意味します。** | |ロールに割り当てられた *記事のインポート* 権限を持つユーザーのみが|記事をインポートできます。 この権限は、|を通じて手動で割り当てることができます。 *コントロールパネル* → *ユーザー* → *ロール*。 Liferayからアップグレードした場合|ポータル6.2。この役割を、すでに実行できたすべての役割に割り当てることもできます。 Gogoシェルからのコマンドで記事を追加します。 | | |を開く [Gogoシェル](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)。 | `knowledgeBase：addImportArticlePermissions` と入力し、Enterキーを押します。

ZIPファイルの記事は、ファイル順にインポートされます（英数字順）。 記事の優先度を指定するには、ファイル名に数値の接頭辞を追加します。 たとえば、 `01-file.markdown` および `02-file.markdown` という名前の記事の優先度は `1.0` および `2.0`ます。

記事を同じソースフォルダー内の他のすべての記事の親として指定するには、ファイル名の末尾に `-intro.markdown`ます。 これにより、親子階層が作成されます。 親記事にプレフィックス `00` を使用して、それらをフォルダーのファイル順序の一番上に配置できます。 インポーターは、記事の優先順位としてイントロファイルのフォルダーの数値プレフィックスを使用します。

`00` プレフィックスの基本的なロジックは次のとおりです。

  - 非イントロファイルのファイルプレフィックス `00` は、結果の記事の優先度を `1.0`割り当てます。
  - 最上位のイントロファイルのファイルプレフィックス `00` は、記事の優先順位を、 `1.0` 以上の最初に見つかったフォルダーの数値プレフィックスに設定します。

この規則により、階層内のトップレベル（子ではない）記事の優先順位を指定できます。

インポートするときに、チェックボックス *付けたままにします。記事ファイルの数値のプレフィックスを優先度* として適用します。 ファイルにプレフィックスがない場合、その記事は次に使用可能な優先度（現在の最高の優先度に1を加えたもの）を取得します。

以下は、これまでに述べた機能を示すZIPファイル構造の例です。

**ZIPファイル構造の例：**

  - `01-winter-events/`

      - `00-winter-excursions-intro.markdown`
      - `01-star-dust-snow-shoeing.markdown`
      - `02-lunar-alpine.markdown`

  - `02-summer-events/`

      - `00-summer-excursions-intro.markdown`
      - `01-lunar-rock-scrambling.markdown`
      - `02-extra-terrestrial-mountain-biking.markdown`
      - `03-lunar-olympics/`
          - `00-lunar-olympics-intro.markdown`
          - `01-zero-gravity-diving.markdown`

  - `images/`

      - `some-image.png`
      - `another-image.jpeg`

上記のZIPファイルは、隣接するMarkdownファイルの親として `00-winter-excursions-intro.markdown` しています： `01-star-dust-snow-shoeing.markdown` および `02-lunar-alpine.markdown`。 同様に、 `00-lunar-olympics-intro.markdown` は `01-zero-gravity-diving.markdown`の親です。 `00-lunar-olympics-intro.markdown` は、 `01-lunar-rock-scrambling.markdown` および `02-extra-terrestrial-mountain-biking.markdown`のピアでもあり、 `00-summerの子でもあります。 -excursions-intro.markdown`。

**ZIP例の結果の関係と優先順位**

  - `01-winter-events/00-winter-excursions-intro.markdown`
      - **記事：** 冬の遠足
      - **関係： *夏の遠足の</strong> ピア*</li>
      - **優先度：** **1.0**</ul></li>
  - `01-winter-events/01-star-dust-snow-shoeing.markdown`
      - **記事：** スターダストスノーシューイング
      - **関係： *冬の遠足の子供</strong>*</li>
      - **優先度：** 1.0</ul></li>
  - `01-winter-events/02-lunar-alpine.markdown`
      - **記事：** 月の高山
      - **関係： *冬の遠足の子供</strong>*</li>
      - **優先度：** 2.0</ul></li>
  - `02-summer-events/00-summer-excursions-intro.markdown`
      - **記事：** 夏の遠足
      - **関係： *冬の遠足の</strong> ピア*</li>
      - **優先度：** **2.0**</ul></li>
  - `02-summer-events/01-lunar-rock-scrambling.markdown`
      - **記事：** ルナロックスクランブル
      - **関係： *夏の遠足の子供</strong>*</li>
      - **優先度：** 1.0</ul></li>
  - `02-summer-events/02-extra-terrestrial-mountain-biking.markdown`
      - **記事：** 地球外マウンテンバイク
      - **関係： *夏の遠足の子供</strong>*</li>
      - **優先度：** 2.0</ul></li>
  - `02-summer-events/03-summer-olympics/00-lunar-olympics-intro.markdown`
      - **記事：** 太陰五輪
      - **関係： *夏の遠足の子供</strong>*</li>
      - **優先度：** 3.0</ul></li>
  - `02-summer-events/03-summer-olympics/01-zero-gravity-diving.markdown`
      - **記事：** ゼロ重力ダイビング
      - **関係： *夏の遠足の</strong> 孫*</li>
      - **関係： *開会式の子供</strong>*</li>
      - **優先度：** 1.0</ul></li> </ul>

ZIPファイルは次の要件を満たしている必要があります。

  - 各ZIPファイルは、サフィックス `.zip`終わる必要があります。
  - 各ZIPファイルには、オプションでフォルダに整理された、少なくとも1つのMarkdownソースファイルが含まれている必要があります。
  - 参照されるすべての画像ファイルは、ZIPファイルのルートにある `images` という名前のフォルダーにある必要があります。
  - 画像ファイルはサポートされている形式である必要があり、適切なファイル拡張子を使用する必要があります。 サポートされている拡張子は `.BMP`、`.GIF`、`.JPEG`、`.JPG`、及び `.PNG`。 アプリのシステム設定で指定します。 詳細については、 [ナレッジベースシステム設定](/docs/7-1/user/-/knowledge_base/u/knowledge-base-system-settings)参照してください。

記事のZIPファイルを取得したら、それをインポートします。

次の手順に従って、ZIPファイルをインポートします。

1.  メニュー（![Menu](../../../../images/icon-menu.png)）で、 *サイト管理* （サイトのメニュー）→ *コンテンツ* → *ナレッジベース* → *記事*ます。

2.  *追加* （![Add](../../../../images/icon-add.png)）→ *インポート* をクリックして、インポーターページを表示します。

3.  ファイルの場所を参照し、ほとんどの場合、記事の優先度のチェックボックスをオンのままにして、 *[保存]*クリックします。

ファイルがアップロードされ、インポーターは各ソースファイルのMarkdownテキストをHTMLに変換し、HTMLを結果の記事に適用します。 記事で参照され、ZIPファイルに含まれている画像ファイルは、記事への添付ファイルとしてインポートされます。

![図1：ナレッジベースで*追加*→*インポート*を選択すると、ナレッジベースで記事を作成および更新するためのMarkdownソースファイルと画像のZIPファイルを選択するためのインターフェースが表示されます。](../../../../images/kb-admin-import.png)

ソースファイルと画像に加えて、ソースファイルのリポジトリの場所を指定するインポーターのベースソースURLシステム設定を構成できます。 各記事の *GitHubの上の編集* （有効な場合）ボタンを押して、元の場所にユーザーを取ります。 インポーターは、各ファイルのパスの先頭にベースソースURLを付けます。 これにより、記事のリポジトリソースの場所へのURLが作成されます。 `[ベースURL] / [記事のファイルパス]`ように見えます。 次に、ベースソースURLの例を示します。

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials

このベースURLと記事ソースファイル `folder-1 / some-article.markdown` から構成されるソースURLは次のようになります。

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/folder-1/some-article.markdown

ベースソースURLは、ZIPファイルのルートフォルダーにある `.METADATA` というファイルで指定します。 インポーターは `.METADATA` ファイルを標準のJavaプロパティファイルとして扱い、ベースソースURLを使用して、ZIPファイルのすべての結果の記事のソースURLを作成します。

ソースURL機能を使用するには、管理者が [ナレッジベースシステム設定](/docs/7-1/user/-/knowledge_base/u/knowledge-base-system-settings)有効にする必要があります。
