# Using the LiferayFacesContext with Liferay Faces Portal [](id=using-the-liferayfacescontext-with-liferay-faces-portal)

[`LiferayFacesContext`](https://github.com/liferay/liferay-faces/blob/master/portal/src/main/java/com/liferay/faces/portal/context/LiferayFacesContext.java)
is an abstract class in Liferay Faces Portal that extends the JSF
[FacesContext](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html)
abstract class. Because of this, it supplies all the same method signatures. The
`LiferayFacesContext` implements the [delegation design
pattern](http://en.wikipedia.org/wiki/Delegation_pattern) for methods defined by
[FacesContext](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html)
by first calling
[FacesContext.getCurrentInstance()](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html#getCurrentInstance\(\))
and then delegating to corresponding methods. 

With the help of `LiferayFacesContext`, you can take advantage of the JSF
`FacesContext` class' functionality. Awesome! 

<!-- **Related Topics**

Add once JSF tutorials are finished. -Cody -->
