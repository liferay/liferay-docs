---
header-id: developing-a-jsf-applications-behavior-and-ui
---

# JSFアプリケーションの動作とUIの開発

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

現在のJSFアプリケーションは、ポートレット記述子とWARスタイル構造の要件を満たしていますが、まだ何もしていません。 次に、JSFアプリケーションのバックエンドを開発し、シンプルなUIを提供する方法を学びます。

最初に行うことは、モジュールのJavaクラスを作成することです。 JSFポートレットの動作はここで定義されます。 Hello Userポートレットの場合、名前を取得/設定し、送信プロセスを容易にするJavaメソッドを提供する必要があります。

1.  モジュールの `src / main / java` フォルダーに一意のパッケージ名を作成し、そのパッケージに `ExampleBacking.java` という名前の新しいパブリックJavaクラスを作成します。 たとえば、クラスのフォルダー構造は `src / main / java / com / liferay / example / ExampleBacking.java`ます。 クラスに [@RequestScoped](http://docs.oracle.com/javaee/7/api/javax/faces/bean/RequestScoped.html) および [@ManagedBean](http://docs.oracle.com/javaee/7/api/javax/faces/bean/ManagedBean.html)注釈が付けられていることを確認し

 。</p> 
   
        @RequestScoped
        @ManagedBean
        public class ExampleBacking {
       
   
   管理対象Beanは、JSFフレームワークによって管理されるJava Beanです。 `@RequestScoped` アノテーションが付けられたマネージドBeanは通常、アクションとリスナーの処理を担当します。 JSF *管理* サーバからBeanオブジェクトを作成し、除去することにより、これらのBeanを。 詳細については、上記のリンクされた注釈をご覧ください。</li> 
   
   2  `ExampleBacking.java` クラスに次のメソッドとフィールドを追加します`</p>

<pre><code> public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public void submit(ActionEvent actionEvent) {
     FacesContextHelperUtil.addGlobalSuccessInfoMessage();
 }

 private String name;
`</pre> 
  
  プライベート `名前` フィールドに取得メソッドと設定メソッドを提供しました。 また、提供してきました `（...）を提出` 時に呼び出されるメソッド、 *提出* ボタンが選択されています。 メソッドが呼び出されると、成功情報メッセージが表示されます。
  
  Hello UserポートレットのJava動作を定義しました。 UIを作成してください！</li> 
  
  3  `webapp / WEB-INF / views` フォルダーに `view.xhtml` ファイルを作成します。 そのファイルに次のロジックを追加します。
  
       <?xml version="1.0"?>
      
       <f:view
           xmlns="http://www.w3.org/1999/xhtml"
           xmlns:f="http://java.sun.com/jsf/core"
           xmlns:h="http://java.sun.com/jsf/html"
       >
           <h:head>
               <h:outputStylesheet library="css" name="main.css" />
           </h:head>
           <h:form>
               <h:messages globalOnly="true" />
               <h:outputLabel value="#{i18n['enter-your-name']}" />
               <h:inputText value="#{exampleBacking.name}" />
               <h:commandButton actionListener="#{exampleBacking.submit}" value="#{i18n['submit']}">
                   <f:ajax execute="@form" render="@form" />
               </h:commandButton>
               <br />
               <h:outputText value="Hello #{exampleBacking.name}" />
           </h:form>
       </f:view>
      
  
  最初に気付くのは、作成した `main.css` ファイルがここで指定されていることです。これにより、ポートレットの見出し書体が太字になります。 次に、フォームは `<h:form>` タグ内で定義されます。 フォームはユーザーに名前を入力するように要求し、その値をJavaクラスの `name` フィールドに設定します。 フォームは `<h:commandButton>` タグを使用して[送信]ボタンを実行し、送信後にフォームをレンダリングします。
  
  `名前入力` と `submit` プロパティに対する `i18n` オブジェクト呼び出しに注目してください。 `Enter-your-name` プロパティは、指定した `i18n.properties` ファイルに設定されていますが、 `submit` プロパティはどうですか？ これは、ポートレットの `i18n.properties` ファイルで定義されていなかったので、ボタンに文字列 *Submit* を使用することをポートレットはどのように知っていますか？ 思い出すと、 `i18n` オブジェクトは@product@の `Language.properties` ファイルのメッセージにもアクセスできます。 これは、 `submit` 言語キーの由来です。
  
  最後に、 `<h:outputText>` タグは、ページに送信された名前に *Hello*プレフィックスを付けて印刷します。</ol> 

驚くばかり\！ Hello User JSFアプリケーションが完成しました！ Deploy your WAR to @<product@>. WARスタイルのポートレットがデプロイされると、WAB Generatorを介してWABに変換されることに注意してください。 訪問 [WABジェネレータ使用](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator) このプロセスの詳細や、ポートレットの結果のフォルダ構造のためのチュートリアルを。

![図1：ユーザーの名前を送信すると、挨拶とともに表示されます。](../../../../images/hello-user-jsf-portlet.png)

Hello User JSFアプリケーションの完成版を表示するには、 [ZIPファイル](https://portal.liferay.dev/documents/113763090/114000653/hello-user-jsf-portlet.zip)ダウンロードします。
