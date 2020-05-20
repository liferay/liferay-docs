---
header-id: packaging-ios-themes
---

# iOSテーマのパッケージ化

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

テーマ</a>

作成したら、 [インストール用にパッケージ化して、そのスクリーンレットで](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets) を使用できます。 テーマは、CocoaPodsを使用してパッケージ化できるコードライブラリです。</p> 

CocoaPodsで使用するテーマをパッケージ化するには、こちらの手順に従ってください。 これらの手順で説明されているものと同じ名前と識別子を使用することが重要です。

1.  空の *Cocoa Touch Framework* Xcodeプロジェクトを作成します。
   
   ![図1：テーマのプロジェクトを作成するときに* Cocoa Touch Framework *を選択します。](../../../../images/screens-ios-cocoa-touch-framework.png)

2.  プロジェクトに `LiferayScreensThemeName`という名前を付け、 `Name` をテーマの名前に置き換えます。 任意の名前を指定できますが、最初の文字を大文字にして、テーマのXcode名を使用することをお勧めします。 名前全体がテーマのCocoaPods名になります。

3.  [Liferayスクリーン用iOSプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens)説明されている手順を使用して、CocoaPods用のLiferayスクリーンを構成します。

4.  必ず、あなたのクラスはXcodeで、明示的にカスタムクラスのための有効なモジュールを指定することにより、正常にコンパイルすることによって、あなたのテーマのクラスとリソースを準備-グレーアウト *現在* デフォルト値はモジュールだけを示唆しています。
   
   ![図2：このXIBファイルのカスタムクラスのモジュールは指定されていません。](../../../../images/screens-ios-theme-custom-module-wrong.png)
   
   次のスクリーンショットでは、カスタムクラスの設定が正しいです。
   
   ![図3：XIBファイルは、指定されたモジュールを使用して、カスタムクラス名にバインドされます。](../../../../images/screens-ios-theme-custom-module-right.png)

5.  プロジェクトのルートフォルダーに、 `LiferayScreensTheme-Name.podspec` という名前のファイルを追加します（ `Name` をテーマのCocoaPods名に変更します。手順2で `Name` を置き換えるために使用した値）。 注： `.podspec` ファイルの名前とプロジェクトの名前を `LiferayScreens`開始する必要があります。
   
   次のコンテンツをファイルに追加します。
   
        Pod::Spec.new do |s|
            s.name = 'LiferayScreensThemeName'
            s.version = '1.0'
            s.summary = 'Your theme description'
            s.source = {
                :git => 'https://your_repository_url.git',
                :tag => 'v1.0'
            }
       
            s.platform = :ios, '8.0'
            s.requires_arc = true
       
            s.source_files = 'Your/Relative/Folder/**/*.{h,m,swift}'
            s.resources = 'Your/Relative/Folder/**/*.{xib,png,plist,lproj}'
       
            s.dependency 'LiferayScreens'
        end
       
   
   `.podspec` ファイルで次の置換を行います。
   
         - `LiferayScreensThemeName``Name` を、テーマのCocoaPods名（手順2で `Name` を置き換えるために使用した値）に置き換えます。
      - `your_repository_url` をリポジトリのURLに置き換えます。
      - `Your / Relative / Folder /` をソースファイルとリソースファイルへのパスに置き換えます。
6.  変更をコミットし、プロジェクトのブランチをGitリポジトリにプッシュします。

あなたのテーマは、他の開発者がGitリポジトリからプルできるようになりました。 または、テーマを公開ポッドとして公開できます。 手順については、 [CocoaPods公式ガイド](https://guides.cocoapods.org/making/getting-setup-with-trunk.html#deploying-a-library)の章 *ライブラリの展開* を参照してください。

開発者は、アプリの `Podfile`次の行を追加して、テーマを使用できるようになりました。もちろん、 `Name` をテーマのCocoaPods名に、 `your_repository_url` をリポジトリのURLに変更する必要があります。

    pod 'LiferayScreensThemeName', :git => 'https://your_repository_url.git'
    

よくやった\！ これで、ScreenaletテーマをCocoaPodsでパッケージ化して配布する方法がわかりました。

**関連トピック**

[iOSスクリーンレットでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)

[iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[iOSスクリーンレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-screenlets)

[Liferay画面用のAndroidプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens)
