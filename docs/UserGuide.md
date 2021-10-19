#User Guide

## Introduction
HR Manager will help you manage the candidates to be interviewed, 
making the scheduling process easier and faster for your company!<br>
Tired of losing track of scheduled interviews?<br>
HR manager's easy to use features will help you to arrange for upcoming interviews quickly in your desired manner.<br>
The data you have provided will also be stored safely and securely for subsequent uses,
transferable to other devices too!


## Features & Usage
### Feature: Candidate Management

Manage a list of people who are candidates for your company, with the simple instructions below!

<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `add_c name=<NAME>`, `NAME` is a parameter which can be used as `add_c name=John Doe`.

* Items in square brackets are optional.<br>
  e.g `name=<NAME> [tag=<TAG>] [status=<STATUS>]` can be used as `name=John Doe tag=friend status=scheduled` or as `name=John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[position=<POSITION>]...​` can be used as ` ` (i.e. 0 times), `position=Accountant`, `position=Accountant position=Bookkeeper` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `name=NAME phone=PHONE_NUMBER`, `phone=PHONE_NUMBER name=NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `phone=12341234 phone=56785678`, only `phone=56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>


<u>**Add a candidate: `add_c`**</u>

Adds a candidate to the list of candidates.

<u>Format:</u>

    add_c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER> address=<ADDRESS> [status=<STATUS>] [tag=<TAG>]... [position=<POSITION>]... 

<u>Example:</u>
 `add_c name=Bryan Seah email=bsah@gmail.com phone=12345678 address=311, Clementi Ave 2, #02-25 position=Project Manager status=Scheduled`
* status will default to APPLIED if field is left empty
* POSITION must be added to HR Manager before it can be used as a parameter
  * e.g if the position, `Project Manager` has not been added to HR Manager, `add_c position=Project Manager` will throw an error : `Position Project Manager not found in HR Manager`

<u>**Delete a candidate: `delete_c`**</u>

Deletes a candidate along with his/her details from the list of candidates.

<u>Format:</u>

    delete_c <INDEX>

<u>Example:</u>
 `delete_c 3`
* Deletes the 3rd candidate along with his/her details from the list of candidates.

<u>**List all candidates: `list_c`**</u>

Displays a list of all the candidates stored in the application.

<u>Format:</u>

    list_c

<u>**Remark a candidate: `remark_c`**</u>

Adds a remark to a candidate.

<u>Format:</u>

    remark_c <INDEX> remark=<REMARK>

<u>Example:</u>
`remark_c 1 remark=20 years of experience`
* Adds a remark to the 1st candidate that he/she has 20 years of experience.

<u>**Edit a candidate: `edit_c`**</u>

Edits a candidate's details. At least 1 edit field is needed.

<u>Format:</u>

    edit_c <INDEX> [name=<NAME>] [email=<EMAIL>] [phone=<PHONE_NUMBER>] [address=<ADDRESS>] [status=<STATUS>] [tag=<TAG>]... [position=<POSITION>]...

<u>Example:</u>
`edit_c 3 name=Ryan Koh`
* Edit the name of the 3rd candidate in the list to Ryan Koh.
* POSITION must be added to HR Manager before it can be used as a parameter
* e.g if the position, `Project Manager` has not been added to HR Manager, `edit_c 3 position=Project Manager` will throw an error : `Position Project Manager not found in HR Manager`


###Feature: Job Position Management

Manage a list of job positions posted by your company, with the simple instructions below!

<u>**Add a position: `add_p`**</u>

Adds a position to the list of positions.

<u>Format:</u>

    add_p title=<TITLE>

<u>Example:</u>
`add_p Assistant`
* Adds a position with the title of Assistant.

<u>**Delete a position: `delete_p`**</u>

Deletes a position from the list of positions.

<u>Format:</u>

    delete_p <INDEX>

<u>Example:</u>
`delete_p 3`
* Deletes the 3rd position from the list of positions.
* Also deletes this position from every candidate who applied for the position

<u>**List all positions: `list_p`**</u>

Displays a list of all the positions stored in the application.

<u>Format:</u>

    list_p

<u>**Edit a position: `edit_p`**</u>

Edits a specific position's details. Only one edit field is needed. Users cannot edit both fields.

<u>Format:</u>

    edit_p <INDEX> [title=<TITLE>] [status=<STATUS>]

<u>Example:</u>
`edit_P 3 status=closed`
* Edits the status of the 3rd position in the list to closed.
* Setting position status to close will delete the position from every candidate who applied for the position

### Feature: Storage
Save all candidate and position records into a data file locally, on your device itself.

When a candidate or position is added, edited or deleted, the change will be done accordingly in the local save file in real time.

The data will be saved as `/data/candidates.json`, `/data/positions.json`, and `/data/interviews.json` (coming soon).

By replacing it with another save file with the same name, the data will be loaded accordingly into the application, if the data format is valid.

The candidate and position information will be saved using the JSON format below.

For a candidate,
```json
 [{
  "name" : "Charlotte Oliveiro",
  "phone" : "93210283",
  "email" : "charlotte@example.com",
  "address" : "Blk 11 Ang Mo Kio Street 74, #11-04",
  "remark" : "",
  "tagged" : [ "rejected" ],
  "positions" : [{
    "title" : "Assistant",
    "positionStatus" : "OPEN"
  }]
 }]
```

For a position,

```json
[{
 "title" : "HR Manager",
   "positionStatus" : "OPEN"
}]
```

## FAQs

**Q**: What is this application? <br>
**A**: This is an easy and fast application to track candidates applying for job positions in your company.
You will interact with the application through typed commands.

**Q**: When will my data be saved? <br>
**A**: Your data will be automatically saved after any command.

**Q**: How can I export my data? <br>
**A**: You can copy the save file, `/data/save.json` and transfer it to another system's 'data' folder.
The transferred save file can then be loaded readily when using this application.

## Command summary
Action | Format, Examples | Expected result
--------|------------------|------------------|
**Add a candidate** | `add_c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>` <br> e.g.,`add_c name=Bryan Seah email=bsah@gmail.com phone=12345678 address=311, Clementi Ave 2, #02-25 position=Project Manager status=Scheduled ` | New candidate added: Bryan Seah; Phone: 12345678; Email: bsah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark:  Status: SCHEDULED; Positions: [Project Manager]
**Delete a candidate** | `delete_c <INDEX>`<br> e.g., `delete_c 3` | Deleted Candidate: Bryan Seah; Phone: 12345678; Email: bsah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark:  Status: SCHEDULED; Positions: [Project Manager]
**List all candidates** | `list_c` | Listed all candidates <br> 1. James Doe <br> 2. John Doe
**Add remark to a candidate** | `remark_c <INDEX> remark=<REMARK>`<br>eg.`remark_c 1 remark=20 years of experience` | Added remark to Person: Bryan Seah; Phone: 12345678; Email: bsah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark: 20 years of experience Status: SCHEDULED; Positions: [Project Manager]
**Edit a candidate** | `edit_c <INDEX> [name=<NAME>] [email=<EMAIL>] [phone=<PHONE_NUMBER>] [address=<ADDRESS>] [status=<STATUS>] [tag=<TAG>]... [position=<POSITION>]...` <br> e.g. `edit_c 3 phone=98602125 email=bryanseah@gmail.com` | Edited Candidate: Bryan Seah; Phone: 98602125; Email: bryanseah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark: 20 years of experience Status: SCHEDULED; Positions: [Project Manager]
**Add position** | `add_p title=<TITLE>` <br> e.g. `add_p title=Software engineer` | New position added: [Software engineer]
**Delete position** | `delete_p <INDEX>` <br> e.g. `delete_c 3` | Deleted Position: [Bookkeeper]
**List all positions** | `list_p` | Listed all positions <br> 1. Assistant <br> 2. Manager
**Edit a position** | `edit_p <INDEX> title=<TITLE>` or `edit_p <INDEX> status=<STATUS>` e.g. `edit_p 3 status=closed` | Edited Position's Status = CLOSED 
