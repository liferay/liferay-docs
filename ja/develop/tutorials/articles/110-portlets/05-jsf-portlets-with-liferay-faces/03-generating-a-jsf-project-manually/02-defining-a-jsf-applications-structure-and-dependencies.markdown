---
header-id: defining-a-jsf-applications-structure-and-dependencies
---

# JSFアプリケーションの構造と依存関係の定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

JSFポートレットは、Liferayポータルで [Liferay Faces Bridge](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-faces-bridge)を使用してサポートされてい

 。 Liferay Faces Bridgeは、JSFポートレットの開発を、JSF Webアプリ開発に可能な限り似たものにします。</p> 

単純な *Hello User* アプリケーションを作成します。このアプリケーションは、ユーザーの名前を要求し、その名前で挨拶します。 まず、WARスタイルのフォルダー構造を作成してから、Liferay Faces Bridgeなどの依存関係を構成します。

1.  モジュールのWARスタイルのフォルダー構造を作成します。 [Mavenアーキタイプ](https://web.liferay.com/web/neil.griffin/blog/-/blogs/new-maven-archetypes-for-jsf-portlets) を使用すると、すぐに開始できます。 これらはデフォルト設定を設定し、定型コードを含んでいるので、ファイル作成手順をスキップしてすぐに開始できます。 JSFアプリケーションの場合、フォルダー構造を手動でセットアップします。 以下のフォルダー構造の概要に従ってください。
   
        - hello-user-jsf-portlet
            - src
                - main
                    - java
                    - resources
                    - webapp
                        - WEB-INF
                            - resources
                            - views
       

2.  Liferay JSFアプリケーションに必要な依存関係がモジュールで指定されていることを確認してください。 たとえば、Liferay互換JSFアプリケーションの依存関係として、Faces API、Faces Reference Implementation（Mojarra）、およびLiferay Faces Bridgeを常に指定する必要があります。 また、重要ではあるが必須ではない依存関係は、Log4jロギングユーティリティです。 これは、コンソールにDEBUGメッセージを記録するため、開発目的で強くお勧めします。 ロギングユーティリティは後で設定します。
   
   ビルドファイルの例については、MavenベースのHello User JSFアプリケーションで使用される `pom.xml` ファイルを以下に示します。 上記のすべての依存関係は、Hello User JSFアプリケーションの `pom.xml` ファイルで構成されます。
   
        <?xml version="1.0"?>
        <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
            <modelVersion>4.0.0</modelVersion>
            <groupId>com.liferay</groupId>
            <artifactId>com.liferay.hello.user.jsf.portlet</artifactId>
            <packaging>war</packaging>
            <name>hello-user-jsf-portlet</name>
            <version>1.0-スナップショット</version>
            <properties>
                <faces.api.version>2.2</faces.api.version>
                <liferay.faces.bridge.ext.version>5.0.3</liferay.faces.bridge.ext.version>
                <liferay.faces.bridge.version>4.1.2</liferay.faces.bridge.version>
                <mojarra.version>2.2.18</mojarra.version>
            </properties>
            <build>
                <plugins>
                    <plugin>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <version>3.3</version>
                        <configuration>
                            <encoding>UTF-8</encoding>
                            <source>1.8</source>
                            <target>1.8</target>
                        </configuration>
                    </plugin>
                    <plugin>
                        <artifactId>maven-war-plugin</artifactId>
                        <version>2.3</version>
                        <configuration>
                            <filteringDeploymentDescriptors>本当</filteringDeploymentDescriptors>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
            <dependencies>
                <dependency>
                    <groupId>javax.faces</groupId>
                    <artifactId>javax.faces-api</artifactId>
                    <version>${faces.api.version}</version>
                    <scope>提供された</scope>
                </dependency>
                <dependency>
                    <groupId>org.glassfish</groupId>
                    <artifactId>javax.faces</artifactId>
                    <version>${mojarra.version}</version>
                    <scope>ランタイム</scope>
                </dependency>
                <dependency>
                    <groupId>com.liferay.faces</groupId>
                    <artifactId>com.liferay.faces.bridge.ext</artifactId>
                    <version>${liferay.faces.bridge.ext.version}</version>
                </dependency>
                <dependency>
                    <groupId>com.liferay.faces</groupId>
                    <artifactId>com.liferay.faces.bridge.impl</artifactId>
                    <version>${liferay.faces.bridge.version}</version>
                </dependency>
                <dependency>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                    <version>1.2.14</version>
                </dependency>
            </dependencies>
        </project>

    There are also two plugins the Hello User JSF application defined in its `pom.xml`: [maven-compiler-plugin](https://maven.apache.org/plugins/maven-compiler-plugin/) and [maven-war-plugin](https://maven.apache.org/plugins/maven-war-plugin/). These two plugins are responsible for building and compiling the JSF application using Maven.
    
    There are several UI component suites that a JSF application can use, which include [*Liferay Faces Alloy*](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-faces-alloy), [*PrimeFaces*](http://primefaces.org/), [*ICEfaces*](http://www.icesoft.org/java/projects/ICEfaces/overview.jsf), and [*RichFaces*](http://richfaces.jboss.org/). Furthermore, you can take advantage of [*Liferay Faces Portal*](/docs/7-1/reference/-/knowledge_base/r/understanding-liferay-faces-portal) in order to use Liferay-specific utilities and UI components. These components can be used by specifying them as dependencies in your build file, as well.
    

ビルドファイルが構成されたので、アプリケーションのJSF固有の構成を定義する必要があります。 これらは、一般的な記述子とLiferay記述子の2つの便利なカテゴリに分類されます。 次に、必要な一般的な記述子を作成することから始めます。
