# Creating Staged Model Data Handlers

A Staged Model Data Handler supplies information about a staged model (entity)
to the Staging and Export/Import framework. Data handlers replace the need to
manually access the database directly and run queries to export/import data.

You're required to create a staged model data handler for every entity you want
Staging to track. This means you must create a data handler for both your
guestbook and entry entities.

First, you'll create a staged model data handler for guestbook entries.
