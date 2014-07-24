# Understanding the Liferay Faces Version Scheme [](id=liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en)

[Liferay Faces](http://www.liferay.com/community/liferay-projects/liferay-faces/overview)
follows a `Major1.Major2.Minor-Type` versioning scheme:

`Major1`: 

- `1` = Portlet 1.0 Bridge for JSF 1.2 (JSR 301) *(Note that Liferay Faces
does not support Portlet 1.0)* 
- `2` = Portlet 2.0 Bridge for JSF 1.2 (JSR 329) 
- `3` = Portlet 2.0 Bridge for JSF 2.1 (JSR TBA) 
- `4` = Portlet 2.0 Bridge for JSF 2.2 (JSR TBA) 

`Major2`: 

- `0-legacy` = Liferay `5.2`
- `0` = Liferay `6.0` 
- `1` = Liferay `6.1` 
- `2` = Liferay `6.2` 

`Minor`: 

- May contain bug fixes, improvements, and new features. 

`Type`: 

- GA (General Availability) 
- RC (Release Candidate) 
- BETA (Beta Quality) 
- ALPHA (Alpha Quality)

*Examples*:

- `3.1.0-ga1`: First GA release for JSF 2.0/2.1 for use with Liferay 6.1.x
- `3.1.1-ga2`: Second GA release for JSF 2.0/2.1 for use with Liferay 6.1.x
 
---

 ![Note](../../images/tip.png) **Note:** Some permutations of this
 versioning scheme are not supported, see table below for specific information
 on supported versions of JSF and Liferay Portal. 

---

The following table displays the Liferay Faces version and its compatible
Liferay Portal version and its compatible JSF version:

| Liferay Faces Version | JSF Version (`Major1`) | Liferay Portal Version (`Major2`) |
|-----------------------|------------------------|-----------------------------------|
| `2.1.x`               | `1.2`                  | `6.1`                             |
| `3.0.x-legacy`        | `2.1`                  | `5.2`                             |
| `3.0.x`               | `2.1`                  | `6.0`                             |
| `3.1.x`               | `2.1`                  | `6.1`                             | 
| `3.2.x`               | `2.1`                  | `6.2`                             |
| `4.1.x`               | `2.2`                  | `6.1`                             | 
| `4.2.x` (master)      | `2.2`                  | `6.2`                             |

While Liferay Faces Bridge is theoretically compatible with any portal that
implements the Portlet 2.0 standard, it has been carefully tested for use with
Liferay Portal versions 5.2, 6.0, 6.1, and 6.2 and has several optimizations
that provide increased performance on Liferay. 

If you've developed portlets that use the PortletFaces Bridge, you'll need to
migrate them to Liferay Faces in order to deploy them using the Liferay Faces
Bridge--don't worry, it's very straightforward. In the next section, we'll show
you how easy it is to migrate to Liferay Faces. 
