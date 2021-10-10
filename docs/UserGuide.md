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


<u>**Add a candidate: `add_c`**</u>

Adds a candidate to the list of candidates.

<u>Format:</u>

    add_c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>

<u>Example:</u>
 `add_c name=Bryan Seah email=bsah@gmail.com phone=12345678`
* Adds a candidate named Bryan Seah
whose email is bsah@gmail.com and has a phone number of 12345678.

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

Edits a candidate's details.

<u>Format:</u>

    edit_c <INDEX> name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>

<u>Example:</u>
`edit_c 3 name=Ryan Koh`
* Edit the name of the 3rd candidate in the list to Ryan Koh.

### Feature: Storage
Save all candidates’ records into a data file locally, on your device itself.

When a candidate is added, edited or deleted, the change will be done accordingly in the local save file in real time.

The data will be saved as `/data/candidates.json` and `/data/interviews.json` (coming soon).

By replacing it with another save file with the same name, 
the data will be loaded accordingly into the application, if the data format is valid.

The candidates' information will be saved using the JSON format below.
For example,
```
 [{
  "name" : "Charlotte Oliveiro",
  "phone" : "93210283",
  "email" : "charlotte@example.com",
  "address" : "Blk 11 Ang Mo Kio Street 74, #11-04",
  "remark" : "",
  "tagged" : [ "rejected" ]
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
**Add a candidate** | `add_c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>` <br> e.g., `add_c name=Bryan Seah email=bsah@gmail.com phone=12345678` | Candidate Added: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**Delete a candidate** | `delete_c <INDEX>`<br> e.g., `delete_c 3` | Candidate Deleted: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**List all candidates** | `list_c` | 1. James Doe <br> 2. John Doe
**Add remark to a candidate** | `remark_c <INDEX> remark=<REMARK>`<br>eg.`remark_c 1 remark=20 years of experience` | Added remark to Person: Bernice Yu; Phone: 99272758; Email: berniceyu@example.com; Address: Blk 30 Lorong 3 Serangoon Gardens, #07-18 Remark: 20 years of experience; Tags: [colleagues][friends]
**Edit a candidate** | `edit_c <INDEX> name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>` <br> e.g. `edit_c 3 name=Ryan Koh` | Edited Candidate: Ryan Koh; Phone: 93210283; Email: charlotte@example.com; Address: Blk 11 Ang Mo Kio Street 74, #11-04