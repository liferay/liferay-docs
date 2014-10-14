# Theme inheritance [](id=theme-inheritance)

By default, themes are based on the *_styled* theme, which provides only basic
styling of portlets. If you open the `build.xml` file in your theme's directory
using the Build Application Configuration Editor, you see the following code:

 ![Figure 5.8: Content of build.xml](../../images/05-themes-5.png)

The `theme.parent` property determines the theme your theme inherits its styling
from. In addition to the *_styled* theme, you can choose to inherit from the
*_unstyled* theme, which contains no styling. This makes more work for you, but
offers full flexibility to design your CSS files from scratch. 

You can also use the default Liferay theme **Classic** as a parent theme. You'll
start with a look and feel that's already smooth and works well. But since so
much is already done for you, there's less flexibility when building your
design. It's a compromise between creating a theme as quickly as possible versus
having full control of the result. It's your choice, and another example of the
flexibility Liferay offers.

Want to learn how to import resources with your theme? We'll discuss how you can
do this in the next section.
