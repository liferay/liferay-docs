---
header-id: creating-a-module-jar-using-maven
---

# Mavenを使用したモジュールJARの作成

[TOC levels=1-4]

Mavenを使って最初からビルドした既存のLiferayモジュールがある場合や、Mavenプロジェクトを@product@の以前のバージョンからアップグレードする場合に、プロジェクトが実行可能なOSGi JARを作成できない可能性があります。これを回避するには、モジュールのPOMにマイナー設定をいくつか作成します。

| **注：**LiferayのMavenアーキタイプを使用してモジュールを作成した場合、| プロジェクトにはOSGi| JARを作成するために必要なMavenプラグインが既に含まれています。

この方法について、以下で説明します。

1. プロジェクトの`pom.xml`ファイルに、以下の[BND Maven Plugin](http://njbartlett.name/2015/03/27/announcing-bnd-maven-plugin.html)宣言を追加します。

       <plugin>
       <groupId>biz.aQute.bnd</groupId>
       <artifactId>bnd-maven-plugin</artifactId>
       <version>3.3.0</version>
       <executions>
       <execution>
       <goals>
       <goal>bnd-process</goal>
       </goals>
       </execution>
       </executions>
       <dependencies>
       <dependency>
       <groupId>biz.aQute.bnd</groupId>
       <artifactId>biz.aQute.bndlib</artifactId>
       <version>3.2.0</version>
       </dependency>
       <dependency>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.ant.bnd</artifactId>
       <version>2.0.41</version>
       </dependency>
       </dependencies>
       </plugin>
   
   BND Mavenプラグインは、Mavenモジュールのすべてのリソース（例: `MANIFEST.MF`）を準備し、作成された`[Maven Project]/target/classes`フォルダに挿入します。このプラグインは、モジュールが@product@にデプロイ可能なOSGi JARとしてパッケージ化されるように準備します。

   | **注： **WABは`bnd-maven-plugin`を使用して作成できますが、これは| Liferayではサポートされていません。WABは標準のWARプロジェクトとして作成し、| WABを作成するために| [Liferay WAB Generator](/docs/7-1/tutorials/-/knowledge_base/t/using-the-wab-generator)にデプロイする必要があります。

2. プロジェクトの`pom.xml`ファイルに、以下の[Maven JAR Plugin](http://maven.apache.org/plugins/maven-jar-plugin/)宣言を追加します。

       <build>
       <plugins>
       <plugin>
       <groupId>org.apache.maven.plugins</groupId>
       <artifactId>maven-jar-plugin</artifactId>
       <version>2.6</version>
       <configuration>
       <archive>
       <manifestFile>${project.build.outputDirectory}/META-INF/MANIFEST.MF</manifestFile>
       </archive>
       </configuration>
       </plugin>
       </plugins>
       </build>
   
   Maven JARプラグインは、BND Mavenプラグインによって作成されたリソースを含んだJARファイルとして、Mavenプロジェクトをビルドします。上記の設定では、対象のプロジェクト用にデフォルトのプロジェクト`MANIFEST.MF`のファイルパスも設定します。これは、BND Mavenプラグインを使用してモジュールをパッケージ化する場合に不可欠です。デフォルトでは、Maven JAR PluginはBND Mavenプラグインによって作成された`target/classes/META-INF/MANIFEST.MF`を無視します。そのため、作成されたJARファイルに適切に含まれるように、マニフェストファイルとして明示的に設定する必要があります。

3. Liferay Mavenプロジェクトに[bnd.bndファイル](http://bnd.bndtools.org/)を追加し、プロジェクトの`pom.xml`ファイルと同じフォルダにあることを確認してください。

4. 以下を実行して、Maven OSGi JARをビルドします。

       mvn package
   
   これで、Maven JARがプロジェクトの`/target`フォルダに作成されます。@product@の`/deploy`フォルダに手動でデプロイすることもできます。また、[Deploying a Project Built with Maven to @product@](/docs/7-1/tutorials/-/knowledge_base/t/deploying-a-project-built-with-maven-to-product)のチュートリアルに従うと、プロジェクトを自動で@product@にデプロイするように設定できます。

 これで、Liferay Mavenプロジェクトをデプロイ可能なOSGiモジュールにパッケージ化するように設定できました。
