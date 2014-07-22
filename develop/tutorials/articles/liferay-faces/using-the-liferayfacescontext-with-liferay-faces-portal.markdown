# Using the LiferayFacesContext [](id=liferay-faces-portal-liferayfacescontext-liferay-portal-6-2-dev-guide-en)

`LiferayFacesContext` is an abstract class that extends the JSF
[FacesContext](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html)
abstract class. Because of this, it supplies all the same method signatures. The
`LiferayFacesContext` implements the [delegation design
pattern](http://en.wikipedia.org/wiki/Delegation_pattern) for methods defined by
[FacesContext](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html)
by first calling 
[FacesContext.getCurrentInstance()](http://docs.oracle.com/cd/E17802_01/j2ee/javaee/javaserverfaces/2.0/docs/api/javax/faces/context/FacesContext.html#getCurrentInstance())
and then delegating to corresponding methods. 

<!-- We need to get Liferay Faces HTML Javadoc generated and posted on
docs.liferay.com so that we can provide links to it for classes like
LiferayFacesContext. - Jim --> 
