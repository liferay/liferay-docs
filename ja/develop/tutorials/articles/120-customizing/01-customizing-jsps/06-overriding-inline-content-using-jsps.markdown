---
header-id: overriding-inline-content-using-jsps
---

# JSPを使用したインラインコンテンツのオーバーライド

[TOC levels=1-4]

タグライブラリタグなどの一部の@product@コアコンテンツは、`.readme`で終わるJSPを使用しないとオーバーライドできません。サフィックス`.readme`はそれらを見つけやすくするものです。
これらのJSPからのコードはインライン化され（@product@ Javaソースファイルに組み込まれ）、パフォーマンスが向上しました。@product@は、サフィックス`.readme`が付いたJSPファイルを無視します。JSP `.readme`ファイルにコードを追加して、`.readme`サフィックスを削除すると、@product@はコアのインラインコンテンツの代わりにそのJSPを使用します。このチュートリアルでは、これらのカスタマイズを行う方法を示します。

 **重要**：このタイプのカスタマイズは最後の手段です。この実装の性質によりオーバーライドが壊れる可能性があり、コア機能がそれとともに機能しなくなる可能性があります。Liferayは、JSP `.readme`ファイルを使用してオーバーライドされたコンテンツのアップグレードを保証できません。

**警告**：@product@タグライブラリタグを変更すると、@product@のインストール時にそのタグのすべての使用が影響を受けます。

JSPを使用してインラインコンテンツをオーバーライドする方法は次のとおりです。

1. JSPをデプロイするための[カスタムJSPバッグ](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-custom-jsp-bag)を作成します。JSPを保存しているモジュールフォルダをメモします。デフォルトのフォルダは`[your
module]/src/main/resources/META-INF/jsps/`です。

   **注**：JSPはどこでも展開できますが、カスタムJSPバッグモジュールを使用すると、簡単にビルドおよびデプロイできます。

2. @product@のソースコードをダウンロードするか、[GitHub (Liferay Portal CE)](https://github.com/liferay/liferay-portal/tree/7.1.x)でソースコードを参照します。

3. カスタマイズしているタグをオーバーライドする`.jsp.readme`ファイルのソースコードを検索します。

   **注**：`-ext.jsp.readme`で終わるファイルを使用すると、既存のコンテンツの先頭に新しいコンテンツを追加できます。 たとえば、@product@アプリケーションの`portal-web/docroot/html/common/themes`フォルダ内の`bottom-test.jsp.readme`、`bottom-ext.jsp.readme`、`body_top-ext.jsp.readme`、および`body_bottom-ext.jsp.readme`ファイルがあります。

4. `.jsp.readme`ファイルをプロジェクトにコピーし、`.readme`サフィックスをドロップします。
@product@が`.jsp.readme`ファイルに使用するのと同じ相対ファイルパスを使用します。
たとえば、@product@内のファイルが以下の場合：

       portal-web/docroot/html/taglib/aui/fieldset/start.jsp.readme
   
   以下のファイルパスを使用します。

       [your module]/src/main/resources/META-INF/jsps/html/taglib/aui/fieldset/start.jsp
   
5. 現在のUIコンテンツとロジックを把握して、適切にオーバーライドできるようにします。たとえば、タグライブラリのタグコンテンツロジックは、`util-taglib/src/com/liferay/taglib/[tag library]/`の下のそれぞれの`*Tag.java`ファイルにあります。

6. 置き換えようとしている現在のインラインロジックを念頭に置いて、新しいロジックを開発します。

7. JSPをデプロイします。

@product@は、現在のインラインロジックの代わりにJSPを使用します。オーバーライドの例を確認する場合は、引き続きこのチュートリアルを進めてください。これで、`.jsp.readme`ファイルを変更してコアのインラインコンテンツをオーバーライドすることができました。

## 例：フィールドセットTaglibタグのオーバーライド

この例は、`liferay:aui`タグライブラリの`fieldset`タグの変更を示しています。`portal-web/docroot/html/taglib/aui/fieldset`で@product@ Webアプリケーションまたはソースコードを参照すると、次のファイルが見つかります。

- `start.jsp.readme`
- `end.jsp.readme`

これらは、`fieldset`タグの開始と終了を作成するロジックをオーバーライドできます。 `FieldsetTag.java`クラスの`processStart`と`processEnd`メソッドは、現在のインラインコンテンツを実装しています。[`processStart`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/util-taglib/src/com/liferay/taglib/aui/FieldsetTag.java#L86-L141)メソッドは以下のとおりです。

    @Override
    protected int processStartTag() throws Exception {
    JspWriter jspWriter = pageContext.getOut();
    
    jspWriter.write("<fieldset class=\"fieldset ");
    jspWriter.write(GetterUtil.getString(getCssClass()));
    jspWriter.write("\" ");
    
    String id = getId();
    
    if (id != null) {
    jspWriter.write("id=\"");
    jspWriter.write(id);
    jspWriter.write("\" ");
    }
    
    jspWriter.write(
    InlineUtil.buildDynamicAttributes(getDynamicAttributes()));
    
    jspWriter.write(StringPool.GREATER_THAN);
    
    String lable = getLabel();
    
    if (lable != null) {
    jspWriter.write(
    "<legend class=\"fieldset-legend\"><span class=\"legend\">");
    
    MessageTag messageTag = new MessageTag();
    
    messageTag.setKey(lable);
    messageTag.setLocalizeKey(getLocalizeLabel());
    
    messageTag.doTag(pageContext);
    
    String helpMessage = getHelpMessage();
    
    if (helpMessage != null) {
    IconHelpTag iconHelpTag = new IconHelpTag();
    
    iconHelpTag.setMessage(helpMessage);
    
    iconHelpTag.doTag(pageContext);
    }
    
    jspWriter.write("</span></legend>");
    }
    
    if (getColumn()) {
    jspWriter.write("<div class=\"row\">");
    }
    else {
    jspWriter.write("<div class=\"\">");
    }
    
    return EVAL_BODY_INCLUDE;
    }

上記のコードは以下の動作を実行します：

1. `<fieldset class=\"fieldset `開始タグを書き込みます。

2. CSSクラス名属性を書き込みます。

3. タグにIDがある場合は、属性として`id`を追加します。

4. タグの動的属性（マップ）を書き込みます。

5. 開始`fieldset`タグを閉じます。

6. タグの`label`属性を取得します。

7. 開始`legend`要素を書き込みます。

8. `getLocalizeLabel()`を使用して、ローカライズされたラベルを`legend`に追加します。

9. （`getHelpMessage()`から取得した）ヘルプメッセージがある場合は、`icon-help-tag`に書き込みます。

10. 終了`legend`タグを書き込みます。

11. 列属性がある場合は、`<div class=\"row\">`と書き込みます。それ以外の場合は、`<div class=\"\">`と書き込みます。

カスタムJSPで現在のロジックを複製すると、カスタマイズ用にタグを適切に設定できます。`fieldset` の`init.jsp`は、開始タグの作成に必要なすべての変数を初期化します。`start.jsp`内で変数を使用できます。`start.jsp`のJSPコードに変換された（`start.jsp.readme`から名前が変更された）`FieldsetTag`の`processStart`メソッドのロジックは、以下のようになります。

    <%@ include file="/html/taglib/aui/fieldset/init.jsp" %>
    
    <fieldset class="fieldset <%= cssClass %>" <%= Validator.isNotNull(id) ?"id=\"" + id + "\"" : StringPool.BLANK %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
    <c:if test="<%= Validator.isNotNull(label) %>">
    <legend class="fieldset-legend">
    <span class="legend">
    <liferay-ui:message key="<%= label %>" localizeKey="<%= localizeLabel %>" />
    
    <c:if test="<%= Validator.isNotNull(helpMessage) %>">
    <liferay-ui:icon-help message="<%= helpMessage %>" />
    </c:if>
    </span>
    </legend>
    </c:if>
    
    <div class="<%= column ?"row" : StringPool.BLANK %>">

**ヒント**：`*Tag.java`ファイルの履歴により、インライン化された元のJSPコードが明らかになる可能性があります。たとえば、`fieldset`タグの[`start.jsp`](https://github.com/liferay/liferay-portal/blob/df22ba66eff49b76404cfda908d3cd024efbebd9/portal-web/docroot/html/taglib/aui/fieldset/start.jsp)からのロジックは[このコミット](https://github.com/liferay/liferay-portal/commit/7fba0775bcc1d1a0bc4d107cabfb41a90f15937c#diff-2ad802b4c0d8f7a2da45b895e89d6e46)でインライン化されました。

`start.jsp`をデプロイすると、`fieldset`タグは以前と同じようにレンダリングされます。`FieldsetTag`の`processStart`メソッドと同じロジックを使用しているため、これは予想どおりです。

![図1：@product@のホームページの検索およびサインインコンポーネントは`fieldset`にあります。](../../../images/jsp-readme-inline-fieldset.png)

`fieldset`開始ロジックはカスタマイズ可能です。これが機能するかどうかをテストするには、`fieldset`タグの開始ロジックの末尾の前にアスタリスクで囲まれた単語*test*を出力します。この行を`start.jsp`の最後の`div`タグの前に挿入します。

    <c:out value="**********test**********"/>

JSPを再度デプロイし、ページを更新して、`fieldset`のフィールドの上に出力されたテキストを確認します。

![図2：`fieldset`のネストされたフィールドの前に、アスタリスクで囲まれた*test* が出力されます。](../../../images/jsp-readme-override-inline-fieldset.png)

Liferayの`.jsp.readme`ファイルを使用して、特定の@product@コアのインラインコンテンツをオーバーライドする方法が分かりました。

## 関連トピック

[Customizing JSPs with Dynamic Includes](/docs/7-1/tutorials/-/knowledge_base/t/customizing-jsps-with-dynamic-includes)

[JSP Overrides Using Portlet Filters](/docs/7-1/tutorials/-/knowledge_base/t/jsp-overrides-using-portlet-filters)
