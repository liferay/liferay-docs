---
header-id: compiling-sass-files-in-a-maven-project
---

# MavenプロジェクトでのSassファイルのコンパイル

[TOC levels=1-4]

Liferay MavenプロジェクトでSassファイルを使用してUIのスタイルを設定する場合、SassファイルをCSSファイルに変換するようにプロジェクトを設定して、Mavenビルドライフサイクルで認識できるようにする必要があります。Mavenプロジェクトをビルドする前に、SassファイルをCSSファイルに手動で変換するのは骨の折れる作業です。

そこで、Liferayでは`com.liferay.css.builder`プラグインを提供しています。[CSS Builder](/docs/7-1/reference/-/knowledge_base/r/css-builder-plugin)はSassファイルをCSSファイルに変換し、Mavenビルドがスタイルシートを解析できるようにします。

LiferayのCSS BuilderをMavenプロジェクトに適用する方法は、以下のとおりです。

1. プロジェクトの`pom.xml`ファイルを開き、LiferayのCSSビルダーを適用します。

       <plugin>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.css.builder</artifactId>
       <version>2.1.0</version>
       <executions>
       <execution>
       <id>default-build</id>
       <phase>compile</phase>
       <goals>
       <goal>build</goal>
       </goals>
       </execution>
       </executions>
       <configuration>
       <docrootDirName>${com.liferay.portal.tools.theme.builder.outputDir}</docrootDirName>
       <outputDirName>/</outputDirName>
       <portalCommonPath>target/deps/com.liferay.frontend.css.common.jar</portalCommonPath>
       </configuration>
       </plugin>
   
   上記の設定は、`groupId`、`artifactId`、および`version`を指定することで、CSS Builderを適用します。次に、CSS Builderの実行と設定を定義します。

   - [executions](https://maven.apache.org/guides/mini/guide-configuring-plugins.html#Using_the_executions_Tag)タグは、CSS BuilderがMavenプロジェクトのビルドライフサイクルの`compile`フェーズ中に実行するように設定します。 `build`
      [goal](http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#A_Build_Phase_is_Made_Up_of_Plugin_Goals)は、ライフサイクルフェーズ用に定義されています。
   - [configuration](https://maven.apache.org/pom.html#Plugins)タグは、2つの重要なプロパティを定義します。
      - `docrootDirName`: コンパイルするSassファイルを含むベースの`リソース`フォルダです。
      - `outputDirName`: SCSSファイルがコンパイルされるサブディレクトリの名前です。
      - `portalCommonPath`: Liferay Frontend Common CSS JAR ファイルのファイルパスです。

2. Sassファイルで[Bourbon](http://bourbon.io/)を使用している場合は 、プロジェクトのPOMに[プラグインの依存関係を追加する](/docs/7-1/tutorials/-/knowledge_base/t/configuring-dependencies)必要があります。Bourbonを使用していない場合は、この手順をスキップしてください。以下のプラグインの依存関係を追加します。

       <plugin>
       <artifactId>maven-dependency-plugin</artifactId>
       <executions>
       <execution>
       <phase>generate-sources</phase>
       <goals>
       <goal>copy</goal>
       </goals>
       <configuration>
       <artifactItems>
       <artifactItem>
       <groupId>com.liferay</groupId>
       <artifactId>com.liferay.frontend.css.common</artifactId>
       <version>2.0.4</version>
       </artifactItem>
       </artifactItems>
       <outputDirectory>${project.build.directory}/deps</outputDirectory>
       <stripVersion>true</stripVersion>
       </configuration>
       </execution>
       </executions>
       </plugin>
   
   [maven-dependency-plugin](http://maven.apache.org/plugins/maven-dependency-plugin/)は`com.liferay.frontend.css.common`依存をMaven Centralからプロジェクトのビルドフォルダにコピーし、CSS Builderで活用できるようにします。

3. 以下のコマンドを使用して、MavenプロジェクトのSassファイルをコンパイルします。

       mvn compile
   
| **注：**LiferayのCSS BuilderはOracleのJDKでサポートされており、速度を上げるためにネイティブ| コンパイラを使用します。IBM JDKを使用している場合、| Sassファイルをビルドするとき（例: テーマをビルドするとき）に| 問題が生じるかもしれません。| Oracle JDKの使用に切り替えることをお勧めしますが、IBM| JDKを使用する方を選ぶ場合は、フォールバックのRubyコンパイラを使用する必要があります。これを行うには、以下の| タグをPOMのCSS Builder設定に追加します。
|
|     <sassCompilerClassName>ruby</sasscompilerClassName>
|
| Rubyベースのコンパイラは、ネイティブ| コンパイラと同様に機能するわけではないことに注意してください。コンパイル時間が長くなる可能性があります。

 これで、Liferay MavenプロジェクトでSassファイルをコンパイルできるようになりました。
