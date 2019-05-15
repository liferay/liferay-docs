# Installing the Liferay Theme Generator to Import a 6.2 Theme

<div class="learn-path-step">
    <p>Setting up the Development Environment<br>Step 1 of 2</p>
</div>

Follow these steps: 

1.  Install [NodeJS](http://nodejs.org/) (along with Node Package Manager(npm)) 
    if it's not already installed. We recommend installing the Long Term Support 
    (LTS) version. Once NodeJS is installed, [set up your npm environment](/docs/7-2/reference/-/knowledge_base/r/setting-up-your-npm-environment).

2.  Use npm to install the [Yeoman](http://yeoman.io/) dependency:

```bash
npm install -g yo
```

3.  Install the Liferay Theme Generator with the command below:

```bash
npm install -g generator-liferay-theme
```

    If you're on Windows, follow the instructions in step 4 to install Sass, 
    otherwise you can skip that step.

4.  The generator uses node-sass. If you're on Windows, you must also install 
    [node-gyp and Python](https://github.com/nodejs/node-gyp#installation).

Nice job! Your npm environment is set up and the Liferay Theme Generator and 
dependencies are installed. Next, you can import your @product@ 6.2 theme to use 
the Liferay JS Theme Toolkit. 