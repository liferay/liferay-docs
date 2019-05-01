# Setting up Your npm Environment [](id=setting-up-your-npm-environment)

If you're using npm for development in @product@, you should set up your npm 
environment to avoid potential permissions issues. Follow these steps to 
configure your npm environment:

1.  Create an `.npmrc` file in your user's home directory. This helps bypass npm 
    permission-related issues. 

2.  In the `.npmrc` file, specify a `prefix` property based on your user's home 
    directory, like the one shown below. This value specifies where to install 
    global npm packages:

    ```properties
    prefix=/Users/[username]/.npm-packages
    ```

3.  Set the `NPM_PACKAGES` system environment variable to the `prefix` value you 
    just specified:

    ```properties
    NPM_PACKAGES=/Users/[username]/.npm-packages (same as prefix value)
    ```

4.  Since npm installs Yeoman and gulp executables to `${NPM_PACKAGES}/bin` on 
    UNIX and to `%NPM_PACKAGES%` on Windows, make sure to add the appropriate 
    directory to your system path. For example, on UNIX you'd set this:

    ```properties
    PATH=${PATH}:${NPM_PACKAGES}/bin
    ```
