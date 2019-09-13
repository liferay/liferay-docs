<footer id="footer" role="contentinfo">
  <div id="navbar-contact-wrapper" class="row mx-0">
  <nav id="navbar-footer" class="col-12 col-md-6 pt-5">
  <div id="social-media-wrapper" class="col-12 col-md-4 text-center mx-auto mb-4">
    <h2 class="nav-heading">
        Follow Us
    </h2>
    <div id="social-media-links">
      <ul class="nav flex-row mx-auto">
          <li class="mx-2">
              <div id="facebook"><a class="icon-facebook icon-3x text-white"
              href="http://www.facebook.com/pages/Liferay/45119213107" 
              target="_blank"><span class="hide">Facebook</span></a></div>
          </li>
          <li class="mx-2">
              <div id="twitter"><a class="icon-twitter icon-3x text-white" 
              href="http://www.twitter.com/liferay" 
              target="_blank"><span class="hide">Twitter</span></a></div>
          </li>
          <li class="mx-2">
              <div id="linked-in"><a class="icon-linkedin icon-3x text-white"
              href="http://www.linkedin.com/company/83609" 
              target="_blank"><span class="hide">LinkedIn</span></a></div>
          </li>
          <li class="mx-2">
              <div id="youtube"><a class="icon-youtube icon-3x text-white"
              href="http://www.youtube.com/user/liferayinc" 
              target="_blank"><span class="hide">YouTube</span></a></div>
          </li>
          <li class="mx-2">
              <div id="google-plus">
              <a class="icon-google-plus icon-3x text-white"
              href="https://plus.google.com/+liferay/posts" 
              target="_blank"><span class="hide">Google</span></a></div>
          </li>
      </ul>
    </div>
  </div>
    <div class="text-center mx-auto">
      <div class="nav text-uppercase" role="menubar">
        <#assign preferencesMap = {"displayDepth": "1", "portletSetupPortletDecoratorId": "barebone"} />

        <@liferay.navigation_menu
          default_preferences=freeMarkerPortletPreferences.getPreferences(preferencesMap)
          instance_id="footer_navigation_menu"
        />
      </div>
    </div>
  </nav>
    <div class="contact-info-container text-center pt-5 pb-2 col-12 col-md-4 mx-auto mb-4">
      <img alt="lunar-resort-logo" height="90" class="mb-2" src="${images_folder}/lunar-resort-logo-vertical.png" />
      <div id="contact-text-wrapper" class="row mx-0">
        <p class="col-12 col-md-6">
          123 Mare Nectaris Lane<br>
          Mare Nectaris, Moon Colony 10010<br>
        </p>
        <p class="col-12 col-md-6">
          Tel: 4-919-843-6666<br>
          Fax: 4-919-843-6667<br>
          <a href="mailto:info@lunarresort.com">info@thelunarresort.com</a>
        </p>
      </div>
    </div>
  </div>
  <p class="powered-by text-center text-white py-3 mb-0">
    The Lunar Resort-<@liferay.language key="powered-by" /> <a href="http://www.liferay.com" rel="external">Liferay</a>
  </p>
</footer>