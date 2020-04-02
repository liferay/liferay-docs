---
header-id: providing-the-user-personal-bar
---

# ユーザーパーソナルバーの提供

[TOC levels=1-4]

ユーザーパーソナルバーには、現在のユーザーに固有のオプションが表示されます。デフォルトでは、このメニューは製品メニューの[User Settings]サブメニューを展開するアバターボタンとして表示されます。カスタムテーマでは、ユーザーパーソナルバーはインターフェイスのどこにでも表示できます。

![図1：デフォルトでは、ユーザーパーソナルメニューにはサインインしたユーザーのアバターが含まれおり、選択すると製品メニューに移動します。](../../images/user-personal-bar.png)

Liferayのデフォルトのユーザーパーソナルバーは必要最小限の構成となっていますが、ニーズに合わせて機能を追加できます。他の製品ナビゲーションメニュー（製品メニューなど）とは異なり、ユーザーパーソナルバーは、パネルカテゴリとパネルアプリの拡張/作成を必要としません。

<!-- Add below reference once portlet providers tutorial is available.

It uses another common
Liferay framework for providing functionality:
[Portlet Providers](develop/tutorials/-/knowledge_base/7-1/portlet-providers).
-->

ユーザーパーソナルバーは、すべてのLiferayテーマでプレースホルダーとして表示できます。デフォルトでは、Liferayはそのプレースホルダーを埋める1つのサンプルの*ユーザーパーソナルバー*ポートレットを提供しますが、Liferayが提供するポートレットは他のポートレットに簡単に置き換えることができます。

**注：**次のスニペットを`portal_normal.ftl`に追加することにより、ユーザーパーソナルバーをテーマに追加できます。

<@liferay.user_personal_bar />

このチュートリアルでは、ユーザーパーソナルバーをカスタマイズする方法を学びます。Javaクラスを1つ作成し、既存のデフォルトのポートレットを置き換えるポートレットを指定します。

1. [OSGiモジュールを作成します](/docs/7-1/tutorials/-/knowledge_base/t/starting-module-development)。

2. モジュールの`src`ディレクトリに一意のパッケージ名を作成し、そのパッケージに新しいJavaクラスを作成します。

3. クラス宣言の上に、次のアノテーションを挿入します。

       @Component(
       immediate = true,
       property = {
       "model.class.name=" + PortalUserPersonalBarApplicationType.UserPersonalBar.CLASS_NAME,
       "service.ranking:Integer=10"
       },
       service = ViewPortletProvider.class
       )
   
   `model.class.name`プロパティは、ポートレットで処理するエンティティタイプのクラス名に設定する必要があります。この場合、ポートレットをユーザーパーソナルバーに表示できるかどうかに基づいて提供する必要があります。

   <!-- Add below reference once portlet providers tutorial is available.

     You may recall from the 
     [Portlet Providers](develop/tutorials/-/knowledge_base/7-1/portlet-providers)
     tutorial that you can request portlets in several different ways (e.g.,
     *Edit*, *Browse*, etc.).
     -->

   また、デフォルトをオーバーライドするように、新しいポートレットのサービスランクを指定する必要があります。`service.ranking:Integer`プロパティを、デフォルトで使用されているポートレットよりもランクが高い値に設定してください。

   ユーザーパーソナルバーにのみポートレットを表示するため、
`service`要素は、`ViewPortletProvider.class`でなければなりません。

4. クラスの宣言を更新して、[BasePortletProvider](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/BasePortletProvider.html)抽象クラスを拡張し、`ViewPortletProvider`を実装します。

       public class ExampleViewPortletProvider extends BasePortletProvider implements ViewPortletProvider {
   
5. クラスで次のメソッドを宣言して、ユーザーパーソナルバーに必要なポートレットを指定します。

       @Override
       public String getPortletName() {
       return PORTLET_NAME;
       }
   
   `PORTLET_NAME`テキストを、Liferayからユーザーパーソナルバーに表示するポートレットの要求があった場合に提供するポートレットに置き換えます。たとえば、Liferayは、デフォルトのユーザーパーソナルバーポートレットに対して`com_liferay_product_navigation_user_personal_bar_web_portlet_ProductNavigationPersonalBarPortlet`を宣言します。

ユーザーパーソナルバーに表示されるポートレットが正常に提供されました。
Liferayのデフォルトのユーザーパーソナルバーに使用されるモジュール全体を検査する場合は、[product-navigation-user-personal-bar-web](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/product-navigation/product-navigation-user-personal-bar-web)を参照してください。
このモジュールには、`*ViewPortletProvider`クラスの他に、定数を定義する2つのクラスと、提供するデフォルトのポートレットを定義する1つのポートレットクラスが含まれています。
これらの追加のクラスは必須ではありませんが、モジュールは提供するポートレットにアクセスできる必要があります。
