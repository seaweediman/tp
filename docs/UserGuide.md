---
layout: page
title: User Guide
---

<aside markdown="1">
<h4>Table of Contents</h4>
* ToC
{:toc}
</aside>

## Introduction
HR Manager will help you manage the candidates to be interviewed,
making the scheduling process easier and faster for your company!<br>
Tired of losing track of scheduled interviews?<br>
HR manager's easy to use features will help you to arrange for upcoming interviews quickly in your desired manner.<br>
The data you have provided will also be stored safely and securely for subsequent uses,
transferable to other devices too!

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `HRManager.jar` from [here](https://github.com/AY2122S1-CS2103T-W13-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g., typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

* **`list_c`** : Lists all candidates.

* **`add_c`**`name=Bryan Seah email=bsah@gmail.com phone=12345678 address=311, Clementi Ave 2, #02-25 position=Project Manager` : Adds a contact named `Bryan Seah` to the Address Book.

* **`delete_c`**`3` : Deletes the 3rd candidate shown in the current candidate list.

* **`clear`** : Deletes all candidates, positions, interviews.

* **`exit`** : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features
<div markdown="block" class="alert alert-info">

**Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g., in `add_c name=<NAME>`, `NAME` is a parameter which can be used as `add_c name=John Doe`.

* Items in square brackets are optional.<br>
  e.g., `name=<NAME> [tag=<TAG>] [status=<STATUS>]` can be used as `name=John Doe tag=friend status=scheduled` or as `name=John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g., `[position=<POSITION>]...​` can be used as ` ` (i.e. 0 times), `position=Accountant`, `position=Accountant position=Bookkeeper` etc.

* Parameters can be in any order.<br>
  e.g., if the command specifies `name=NAME phone=PHONE_NUMBER`, `phone=PHONE_NUMBER name=NAME` is also acceptable.

* If a parameter is expected only once in the command, but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g., if you specify `phone=12341234 phone=56785678`, only `phone=56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g., if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Feature: Job Position Management

Manage a list of job positions posted by your company, with the simple instructions below!

#### <u>Add a position:</u> `add_p`

*Adds a position to the list of positions.*

<u>Format:</u>

    add_p title=<TITLE>

<u>Example:</u>

    add_p title=Assistant

* Adds a position with the title of Assistant.
  <br>
  <br>

#### <u>Delete a position:</u> `delete_p`

*Deletes a position from the list of positions.*

<u>Format:</u>

    delete_p <INDEX>

<u>Example:</u>

    delete_p 3

* Deletes the 3rd position from the list of positions.
* Also deletes this position from every candidate who applied for the position
  <br>
  <br>

#### <u>List all positions:</u> `list_p`

*Displays a list of all the positions stored in the application.*

<u>Format:</u>

    list_p

  <br>
  <br>

#### <u>Edit a position:</u> `edit_p`

*Edits a specific position's details. Only one edit field is needed. Users cannot edit both fields.*

<u>Format:</u>

    edit_p <INDEX> [title=<TITLE>]... [status=<STATUS>]...

<u>Example:</u>

    edit_p 3 status=closed

* Edits the status of the 3rd position in the list to closed.
* Setting position status to close will delete the position from every candidate who applied for the position.
* At least one field must be edited.
  <br>
  <br>

#### <u>Find a position:</u> `find_p`

*Filters the position list based on the parameters provided. Minimum of 1 field is needed. Searching is case-insensitive*

<u>Format:</u>

    find_p [title=<TITLE>]... [status=<STATUS>]...

<u>Example:</u>

    find_p status=closed title=Accountant Engineer

* Finds all positions that are closed and title contains "Accountant" or "Engineer"
  * (status contains word "closed") AND (title contains word "Accountant" OR "Engineer")
* Within 1 field, keywords are separated by a space
  * Command will find jobs that contains at least 1 of the keywords (OR)
* Across different fields
  * Command will return jobs that contain all the fields (AND)
  
  <br>
  <br>

### Feature: Candidate Management

Manage a list of people who are candidates for your company, with the simple instructions below!
Each candidate is uniquely identified by the same name, email and phone number. Different candidates can have the same name as along as the name, email and phone are not all the same!

#### <u>Add a candidate:</u> `add_c`

*Adds a candidate to the list of candidates.*

<u>Format:</u>

    add_c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER> address=<ADDRESS> position=<POSITION>... [status=<STATUS>] [tag=<TAG>]...

<u>Example:</u>

    add_c name=Bryan Seah email=bsah@gmail.com phone=12345678 address=311, Clementi Ave 2, #02-25 position=Project Manager status=Scheduled

* `STATUS` will default to `Applied` if field is left empty
  * `STATUS` field can take the following values
  * `None`
  * `Applied`
  * `Scheduled`
  * `Interviewed`
  * `Accepted`
  * `Rejected`
  * `Withdrawn`
* `POSITION` must be added to HR Manager before it can be used as a parameter
  * e.g., if the position, `Project Manager` has not been added to HR Manager, `add_c position=Project Manager` will throw an error : `Position Project Manager not found in HR Manager`
    <br>
    <br>

#### <u>Delete a candidate:</u> `delete_c`

*Deletes a candidate along with his/her details from the list of candidates.*

<u>Format:</u>

    delete_c <INDEX>

<u>Example:</u>

    delete_c 3

* Deletes the 3rd candidate along with his/her details from the list of candidates.
  <br>
  <br>

#### <u>List all candidates:</u> `list_c`

*Displays a list of all the candidates stored in the application.*

<u>Format:</u>

    list_c

  <br>
  <br>

#### <u>Remark a candidate:</u> `remark_c`

*Adds a remark to a candidate.*

<u>Format:</u>

    remark_c <INDEX> remark=<REMARK>

<u>Example:</u>

    remark_c 1 remark=20 years of experience

* Adds a remark to the 1st candidate that he/she has 20 years of experience.
  <br>
  <br>

#### <u>Edit a candidate:</u> `edit_c`

*Edits a candidate's details. At least 1 edit field is needed.*

<u>Format:</u>

    edit_c <INDEX> [name=<NAME>] [email=<EMAIL>] [phone=<PHONE_NUMBER>] [address=<ADDRESS>] [status=<STATUS>] [tag=<TAG>]... [position=<POSITION>]...

<u>Example:</u>

    edit_c 3 name=Ryan Koh

* Edit the name of the 3rd candidate in the list to Ryan Koh.
* `POSITION` must be added to HR Manager before it can be used as a parameter
* e.g., if the position, `Project Manager` has not been added to HR Manager, `edit_c 3 position=Project Manager` will throw an error : `Position Project Manager not found in HR Manager`
* `STATUS` field can take the following values
  * `None`
  * `Applied`
  * `Scheduled`
  * `Interviewed`
  * `Accepted`
  * `Rejected`
  * `Withdrawn`
    <br>
    <br>


#### <u>Find a Candidate:</u> `find_c`

*Filters the candidate list based on the parameters provided. Minimum of 1 field is needed. Searching is case-insensitive*

<u>Format:</u>

    find_c [name=<NAME>]... [email=<EMAIL>]... [phone=<PHONE_NUMBER>]... [address=<ADDRESS>]... [status=<STATUS>]... [tag=<TAG>]... [position=<POSITION>]...

<u>Example:</u>

    find_c name=Alex tag=recommended priority

* Finds all candidates that have the word "Alex" in their name and have position that contain the word "Accountant" or "Engineer"
  * (name contains "Alex") AND (title contains Accountant OR Engineer)
* Candidates that will be found
  * name="Alex Maslow", tags="recommended", ...
  * name="alex", tags="priority candidate",...
* Candidates that will not be found
  * name="AlexMaslow", tags="recommended", ... (Word "Alex" not in name)
  * name="Alex Maslow", tags="" (No tags containing word "recommended" or "priority")
* Within 1 field, keywords are separated by a space
  * Command will find candidates that contains at least 1 of the keywords (OR)
* Across different fields
  * Command will return candidates that contain all the fields (AND)

  <br>
  <br>


### Feature: Interview Management

Manage a list of interviews for you to select the desired candidates, with the simple instructions below!

#### <u>Add an interview:</u> `add_i`

*Adds an interview to the list of interviews.*

<u>Format:</u>

    add_i position=<POSITION> index=<INDEX>... date=DATE time=TIME duration=DURATION [interviewed=STATUS]

<u>Example:</u>

    add_i position=Accountant c=1 2 date=18/10/2021 time=1400 duration=120 interviewed=pending

* Adds an interview with the position of Accountant and the 1st and 2nd candidate in the list.
* `POSITION` must be added to HR Manager and must have been applied by corresponding candidates before it can be used as a parameter.
  * e.g., if the position, `Accountant` has not been added to HR Manager, the above command will result in an error : `Position Accountant not found in HR Manager`
* `DATE` must be in numbers in DD/MM/YYYY form and can tolerate single digit for day and month, but year must be 4 digits.
  * e.g., if the date, `2021/10/18` was used instead, HR Manager will show an error : `Date should be be valid and in DD/MM/YYYY format.`
  * e.g., if the date, `18 Oct 21` was used instead, HR Manager will show an error : `Date should be be valid and in DD/MM/YYYY format.`
* `TIME` must be in HHMM form, following 24-hour form, e.g., `1800` and `0600` for 6 P.M. and 6 A.M. respectively
  * e.g., if the time, `6pm` was used instead, HR Manager will show an error : `Time should be be valid and in HHMM format..`
* `DURATION` must be in numbers and is set to be in minutes
  * e.g., if the duration, `twenty` was used instead, HR Manager will show an error : `Duration should be in numbers.`
* `STATUS` must be either `pending` or `completed`
  * e.g., if the status, `tbc` was used instead, HR Manager will show an error :`Interview Status can ony take the values:pending completed`
    <br>
    <br>

#### <u>Delete an interview:</u> `delete_i`

*Deletes an interview from the list of interviews.*

<u>Format:</u>

`delete_i <INDEX>`

<u>Example:</u>

    delete_i 3

* Deletes the 3rd position from the list of interviews.
* Also deletes this interview from every candidate who were scheduled this interview
  <br>
  <br>


#### <u>Edit an interview:</u> `edit_i`

Edits a specific interview in the list of interviews.

<u>Format:</u>

    edit_i <INDEX> [position=POSITION]... [c=<CANDIDATE INDEX>]... [date=DATE]... [time=TIME]... [duration=DURATION]... [interviewed=STATUS]...

<u>Example:</u>
`edit_i 2 c=1 2 date=18/10/2021 time=1400`
* Edits the second interview in the interview list and updates the candidate set, date and time of the interview.
* Similar to `add_i` command, POSITION, DATE, TIME, DURATION AND STATUS must be valid inputs.
* At least one field must be edited.
  <br>
  <br>

#### <u>List all interviews:</u> `list_i`

*Displays a list of all the interviews stored in the application.*

<u>Format:</u>

    list_i

  <br>
  <br>

#### <u>Unassign candidates from interview:</u> `unassign`

*Unassigns candidates from interview*

<u>Format:</u>

    unassign i=<INTERVIEW_INDEX> c=<CANDIDATE_INDEX>...

<u>Example:</u>
`unassign i=1 c=2 4`

* You can only input any number of candidates but only 1 interview.
* Removes candidates with candidate index 2 and 4 from the first interview.
* Inputting `c=*` removes all candidates from an interview.
  <br>
  <br>

#### <u>Assign candidates to interview:</u> `assign`

*Assigns candidates to interview*

<u>Format:</u>

    assign i=<INTERVIEW_INDEX> c=<CANDIDATE_INDEX>...

<u>Example:</u>
`assign i=1 c=2 4`

* You can input any number of candidates but only 1 interview.
* Adds candidates with candidate index 2 and 4 to the first interview.
* NOTE: If the candidate has not applied to the position, attempting to assign the candidate to an interview
  for that position will result in an error message displayed.
  <br>
  <br>

#### <u>Find a Interview:</u> `find_i`

*Filters the candidate list based on the parameters provided. Minimum of 1 field is needed. Searching is case-insensitive*

<u>Format:</u>

    find_i [position=POSITION]... [c=<Candidate Name>]... [date=DATE]... [time=TIME]... [duration=DURATION]... [interviewed=STATUS]...

<u>Example:</u>

    find_i date=21/09/2021 time=1600

* Finds all interviews that are on 21/09/2021 and occur on 1600
* Interviews that will be found
  * date="21/09/2021", time="1500-1700"
  * date="21/09/2021", time="1600-1650"
* Interviews that will not be found
  * date="21/09/2020", time="1500-1700", ... (Not on 21/09/2021)
  * date="21/09/2021", time="1200-1300" (Interview not occurring on 1600)
* Within 1 field, keywords are separated by a space (except time)
  * Command will find candidates that contains at least 1 of the keywords (OR)
* Across different fields
  * Command will return candidates that contain all the fields (AND)

  <br>
  <br>

### Feature: Storage

Save all candidate, position and interview records into a data file locally, on your device itself.

When a candidate, position or interview is added, edited or deleted, the change will be done accordingly in the local save file in real time.

The data will be saved in separate files: `/data/candidates.json`, `/data/positions.json`, and `/data/interviews.json`.

By replacing it with another save file with the same name, the data will be loaded accordingly into the application, if the data format is valid.

If any data is invalid, HR Manager will launch without any data entries.

The candidate, position and interview information will be saved using the JSON format below.

*Note that interview does not save a candidate but its unique ID generated within the application.*

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

For an interview,

```json
[{
  "position" : "HR Manager",
  "candidateIDs" : [ "-550871537", "-2024498055" ],
  "date" : "18/10/2021",
  "startTime" : "1400",
  "duration" : "120",
  "status" : "PENDING"
}]
```

## FAQs

**Q**: What is this application? <br>
**A**: This is an easy and fast application to track candidates applying for job positions in your company.
You will interact with the application through typed commands.

**Q**: When will my data be saved? <br>
**A**: Your data will be automatically saved after any command.

**Q**: How can I export my data? <br>
**A**: You can copy the save files, `/data/candidates.json`, `/data/positions.json`, and `/data/interviews.json`
and transfer it to another system's 'data' folder. <br>
Or better yet, copy the entire `/data` folder and overwrite the data folder of the system you wish to transfer to.
The transferred save files can then be loaded readily when using this application.

## Command summary

| Action | Format, Examples | Expected result |
| -------- | ------------------ | ------------------ |
| **Add position** | `add_p title=<TITLE>` <br> e.g., `add_p title=Software engineer` | New position added: [Software engineer] |
| **Delete position** | `delete_p <INDEX>` <br> e.g., `delete_p 3` | Deleted Position: [Bookkeeper] |
| **List all positions** | `list_p` | Listed all positions <br> 1. Assistant <br> 2. Manager |
| **Edit a position** | `edit_p <INDEX> title=<TITLE>` or `edit_p <INDEX> status=<STATUS>` e.g., `edit_p 3 status=closed` | Edited Position's Status = CLOSED |
| **Find a position** | `find_p [title=<TITLE>]... [status=<STATUS>]...` <br> e.g., `find_p title=Accountant Engineer status=closed` | Candidates found
| **Add a candidate** | `add_c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER> address=<ADDRESS> position=<POSITION>...[status=<STATUS>] [tag=<TAG>]...`  <br> e.g., `add_c name=Bryan Seah email=bsah@gmail.com phone=12345678 address=311, Clementi Ave 2, #02-25 position=Project Manager status=Scheduled` | New candidate added: Bryan Seah; Phone: 12345678; Email: bsah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark:  Status: SCHEDULED; Positions: [Project Manager] |
| **Delete a candidate** | `delete_c <INDEX>`<br> e.g., `delete_c 3` | Deleted Candidate: Bryan Seah; Phone: 12345678; Email: bsah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark:  Status: SCHEDULED; Positions: [Project Manager] |
| **List all candidates** | `list_c` | Listed all candidates <br> 1. James Doe <br> 2. John Doe |
| **Add remark to a candidate** | `remark_c <INDEX> remark=<REMARK>`<br>eg.`remark_c 1 remark=20 years of experience` | Added remark to Person: Bryan Seah; Phone: 12345678; Email: bsah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark: 20 years of experience Status: SCHEDULED; Positions: [Project Manager] |
| **Edit a candidate** | `edit_c <INDEX> [name=<NAME>] [email=<EMAIL>] [phone=<PHONE_NUMBER>] [address=<ADDRESS>] [status=<STATUS>] [tag=<TAG>]... [position=<POSITION>]...` <br> e.g., `edit_c 3 phone=98602125 email=bryanseah@gmail.com` | Edited Candidate: Bryan Seah; Phone: 98602125; Email: bryanseah@gmail.com; Address: 311, Clementi Ave 2, #02-25 Remark: 20 years of experience Status: SCHEDULED; Positions: [Project Manager] |
| **Find candidates** | `find_c [name=<NAME>]... [email=<EMAIL>]... [phone=<PHONE_NUMBER>]... [address=<ADDRESS>]... [status=<STATUS>]... [tag=<TAG>]... [position=<POSITION>]...` <br> e.g., `find_c name=Alex tag=recommended priority` | Candidates Found
| **Add an interview** | `add_i position=<POSITION> [c=<INDEX>]... date=DATE time=TIME duration=DURATION [interviewed=STATUS]` <br> e.g., `add_i position=Accountant c=1 2 date=18/10/2021 time=1400 duration=120 interviewed=pending` | New interview added: [Accountant [Bernice Yu, David Li] 18 Oct 2021 14:00 - 16:00 PENDING] |
| **Delete an interview** | `delete_i <INDEX>` e.g., `delete_i 1` | Deleted Interview: [Accountant [Bernice Yu, David Li] 18 Oct 2021 14:00 - 16:00 PENDING] |
| **Edit an interview** | `edit_i <INDEX> [position=POSITION]... [c=<INDEX>]... [date=DATE]... [time=TIME]... [duration=DURATION]... [interviewed=STATUS]...` e.g., `edit_i 2 c=1 2 date=21/10/2021 time=1400` | Edited Interview: [Data Analyst [Jenny Lim, Max Tan] 21 Oct 2021 14:00 - 16:00 PENDING] |
| **List all interviews** | `list_i` | Listed all interviews <br> 1. [Accountant [Bernice Yu, David Li] 18 Oct 2021 14:00 - 16:00 PENDING] <br> 2. [Project Manager [Bernice Yu] 20 Oct 2021 15:00 - 16:00 PENDING] |
| **Unassign candidates** | `unassign i=<INTERVIEW_INDEX> c=<CANDIDATE_INDEX>...` e.g., `unassign i=1 c=4`| Candidates removed from interview: [Project Manager [Bernice Yu] 20 Oct 2021 15:00 - 16:00 PENDING]: <br> 1. David Li |
| **Assign candidates** | `assign i=<INTERVIEW_INDEX> c=<CANDIDATE_INDEX>...` e.g., `assign i=1 c=4`| Candidates added to interview: [Project Manager [Bernice Yu] 20 Oct 2021 15:00 - 16:00 PENDING]: <br> 1. David Li |
| **Find interview** | `find_i [position=POSITION]... [c=<Candidate Name>]... [date=DATE]... [time=TIME]... [duration=DURATION]... [interviewed=STATUS]...` <br> e.g., `find_i date=21/09/2021 time=1600` | Interviews found

