# Auto-Save

Losing progress on a partially created form is bad. Make sure you save your work
frequently as you're creating forms. If you happen to forget to save your work,
Liferay Forms has your back.

By default, a Form is auto-saved every minute. The form builder won't notice
anything happening, but in the backend, the form is being saved. 

For unpublished forms, an auto-save works just like a manual save. 

Published forms are different. The information auto-saved isn't automatically
propagated to a published form. The form builder must click the *Save* button in
the form builder view to publish changes. 

The auto-save duration is configurable in Control Panel &rarr; Configuration
&rarr; System Settings &rarr; Forms (in the Content section). 

![Figure x: Configure the auto-save duration.](../../images/forms-autosave-interval.png)

To disable auto-save, set the interval to *0*.
