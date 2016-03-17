# Sample Content Portlet

The Sample Content portlet allows you to create sample content for Liferay. This
sample content includes users, organizations, and user groups. The specific
entities that are created are specified in JSON files: `users.json`,
`organizations.json`, and `user-groups.json`. Here's some sample contents for these
files:

`users.json`:

	{"Users": [
		{
		"Screen Name": "jjeffries",
		"First Name": "James",
		"Last Name": "Jeffries",
		"Male": true,
		"Job Title": "Lunar Associate",
		"Email Address": "jjeffries@lunarresort.com",
		"User Groups": ["List Creators", "Message Board Administrators"],
		"Organizations": ["Recreation Department", "Lunar Golf Instructors"]
		},
		{
		"Screen Name": "msmart",
		"First Name": "Marvin",
		"Last Name": "Smart",
		"Male": true,
		"Job Title": "Lunar Associate",
		"Email Address": "msmart@lunarresort.com",
		"User Groups": ["List Creators", "Message Board Administrators"],
		"Organizations": ["Physical Plant Department", "Grounds Crew"]
		}
	]}

`organizations.json`:

	{"Organizations": [
		{
		"Name": "Lunar Resort",
		"Organizations": [
			{
			"Name": "Physical Plant Department",
			"Organizations": [
				{
				"Name": "Grounds Crew"
				},
				{
				"Name": "Janitorial Crew"
				},
				{
				"Name": "Mechanical Crew"
				}
			]
			},
			{
			"Name": "Recreation Department",
			"Organizations": [
				{
				"Name": "Lunar Golf Instructors"
				},
				{
				"Name": "Lunar Rover Instructors"
				},
				{
				"Name": "Lunar Sherpas"
				}
			]
			},
			{
			"Name": "Sales Department",
			"Organizations": [
				{
				"Name": "Up-sale Group"
				},
				{
				"Name": "Souvenir and Memorabilia Group"
				},
				{
				"Name": "Retail Group"
				}
			]
			}
		]
		}
	]}

`user-groups.json`:

	{"User Groups": [
		{
		"Name": "List Creators",
		"Description": "The List Creators user group can manage dynamic data lists in the Lunar Resort."
		},
		{
		"Name": "Message Board Administrators",
		"Description": "The Message Board Administrators user group can manage message boards in the Lunar Resort."
		}
	]}