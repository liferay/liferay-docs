---
header-id: building-forms-with-aui-tags
---

# AUIタグを使用したフォームの構築

[TOC levels=1-4]

[AUIタグライブラリ](https://docs.liferay.com/portal/7.1-latest/taglibs/util-taglib/aui/tld-summary.html)は、アプリケーションのフォームを構築するために必要なすべてのコンポーネントを提供します。AUIタグは、カスタム 名前空間、ローカライズ、さらには検証まで、標準のフォーム要素に多くの利点を提供します。タグでは、ユーザーに必要なエクスペリエンスを作成できる複数の属性が提供されます。

このチュートリアルでは、AUIタグを使用してフォームを構築する方法を示します。フォームを作成するには、次の手順に従います。

1. まだ追加していない場合は、`aui` taglib宣言をポートレットの`view.jsp`に追加します。

       <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
   
2. 以下に示すタグを使用してフォームを作成します。各タグは、使用可能な属性がリストされている対応するtaglibdocにリンクされています。

   - [`<aui:input>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/input.html)
   - [`<aui:button>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/button.html)
   - [`<aui:button-row>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/button-row.html)
   - [`<aui:container>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/container.html)
   - [`<aui:col>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/col.html)
   - [`<aui:row>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/row.html)
   - [`<aui:field-wrapper>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/field-wrapper.html)
   - [`<aui:fieldset>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/fieldset.html)
   - [`<aui:fieldset-group>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/fieldset-group.html)
   - [`<aui:form>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/form.html)
   - [`<aui:select>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/select.html)
   - [`<aui:option>`](https://docs.liferay.com/ce/portal/7.1-latest/taglibs/util-taglib/aui/option.html)
   フォームの例を以下に示します。

       <aui:form name="fm">
       <aui:fieldset-group markupView="lexicon">
       <aui:fieldset label="Personal Information">
       <aui:row>
       <aui:col width="50">
       <aui:input label="First Name" name="firstName" type="text" />
       </aui:col>
       <aui:col width="50">
       <aui:input label="Last Name" name="lastName" type="text" />
       </aui:col>
       </aui:row>
       <aui:row>
       <aui:col width="50">
       <aui:input label="Username" name="username" type="text" />
       </aui:col>
       <aui:col width="50">
       <aui:input label="Email" name="email" type="email" />
       </aui:col>
       </aui:row>
       </aui:fieldset>
       </aui:fieldset-group>
       <aui:fieldset-group markupView="lexicon">
       <aui:fieldset label="Miscellaneous">
       <aui:input label="Hobbies" name="hobbies" type="textarea" />
       <aui:input label="Receive email updates" name="emailUpdates" type="checkbox" />
       </aui:fieldset>
       </aui:fieldset-group>
       <aui:button-row>
       <aui:button name="submitButton" type="submit" value="Submit" />
       </aui:button-row>
       </aui:form>
   
   ![図1：AUIタグでは、アプリケーションのフォームを構築するために必要なものがすべて提供されます。](../../../images/aui-taglib-basic-form.png)

3. 必要に応じて、フォームフィールドに検証を追加します。検証する各フォームフィールド内に`<aui:validator>`タグをネストします。`<aui:validator>`タグの`name`属性を使用して検証ルールを指定します（使用可能な検証ルールを次の表に示します）。フィールドのデフォルトの検証エラーメッセージは`errorMessage` 属性で上書きできます。構成例を以下に示します。

       <aui:form name="myForm">
       <aui:input name="password" id="password" label="Password"
       required="true" />
       <aui:input name="confirmPassword" id="password"
       label="Confirm Password" required="true">
       <aui:validator name="equalTo"
       errorMessage="The passwords much match. Please try again." >
       '#<portlet:namespace>password'
       </aui:validator>
       </aui:input>
       </aui:form>
   
   ![図2：AUIタグを使用して、フォームフィールドを検証することもできます。](../../../images/aui-taglib-form-validation.png)

   使用可能な検証ルールの全リストを以下の表に示します。

   | ルール | 説明 | デフォルトのエラーメッセージ |
   | --- | --- | --- |
   | `acceptFiles` | 指定されたファイルタイプのみをフィールドに含めることができることを指定します。各ファイル拡張子はコンマで区切る必要があります。例：</br> `<aui:validator name="acceptFiles">'jpg,png,tif,gif'</aui:validator>` | 'Please enter a file with a valid extension ([supported extensions]).' |
   | `alpha` | アルファベット文字を許可します | 'Please enter only alpha characters.' |
   | `alphanum` | 英数字を許可します | 'Please enter only alphanumeric characters.' |
   | `date` | 日付を許可します | 'Please enter a valid date.' |
   | `digits` | 数字を許可します | 'Please enter only digits.' |
   | `email` | メールアドレスを許可します | 'Please enter a valid email address.' |
   | `equalTo` | 指定されたフィールドIDを持つ別のフィールドと等しい内容を許可します。例：</br> `<aui:validator name="equalTo">'#<portlet:namespace/>password'</aui:validator>` | 'Please enter the same value again.' |
   | `max` | 指定された値より小さい整数値を許可します。たとえば、最大値20は次のように指定します</br> `<aui:validator name="max">20</aui:validator>` | 'Please enter a value less than or equal to [max value].' |
   | `maxLength` | 指定されたサイズの最大フィールド長を許可します（`max`と同じ構文に従います） | 'Please enter no more than [max] characters.' |
   | `min` | 指定された最小値より大きい整数値を許可します（`max`と同じ構文に従います） | 'Please enter a value greater than or equal to [min value].' |
   | `minLength` | 指定されたサイズより長いフィールド長を許可します（`max`と同じ構文に従います） | 'Please enter at least [min] characters.' |
   | `number` | 数値を許可します | 'Please enter a valid number.' |
   | `range` | 指定された範囲の間の数値を許可します。たとえば、1.23〜10の範囲は次のように指定します</br> `<aui:validator name="range">[1.23,10]</aui:validator>` | 'Please enter a value between [0] and [1].' |
   | `rangeLength` | 指定された範囲の間のフィールド長を許可します（`range`と同じ構文に従います） | 'Please enter a value between [0] and [1] characters long.' |
   | `required` | 空白フィールドを防ぎます | 'This field is required.' |
   | `url` | URL値を許可します | 'Please enter a valid URL.' |

これで、アプリケーション用の使いやすいフォームを作成する方法がわかりました。

## 関連トピック

[Using the Chart Taglib in Your Portlets](/docs/7-1/tutorials/-/knowledge_base/t/using-the-chart-taglib-in-your-portlets)

[Using Liferay Front-end Taglibs in Your Portlet](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-frontend-taglibs-in-your-portlet)

[Using the Clay Taglib in Your portlets](/docs/7-1/tutorials/-/knowledge_base/t/using-the-clay-taglib-in-your-portlets)
