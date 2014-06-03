<%
/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
%>

 <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
 <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
 
 <div id="myCarousel">
  <div id="image1"></div>
  <div id="image2"></div>
  <div id="image3"></div>
  <div id="image4"></div>
 </div>  
 
 <aui:script>
 AUI().use(
   'aui-carousel',
   function(Y) {
    new Y.Carousel(
      {
        contentBox: '#myCarousel',
        height: 250,
        width: 700,
        intervalTime: 2,
        animationTime: 1,
        activeIndex: 0,
        boundingBox: '#myCarousel'
      }
    ).render();
   }
 );
 </aui:script>               
