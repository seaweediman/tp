#User Guide

## Introduction
HR manager will help you manage the candidates to be interviewed, 
making the scheduling process easier and faster for your company!<br>
Tired of losing track of scheduled interviews?<br>
HR manager's easy to use features will help you to arrange for upcoming interviews quickly in your desired manner.<br>
The data you have provided will also be stored safely and securely for subsequent uses, 
transferable to other devices too!


## Features & Usage
### Feature: Candidate Management

<u>**Add a candidate: `add c`**</u>

Adds a candidate to the list of candidates.

<u>Format:</u>

    add c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>

<u>Example:</u>
 `add c name=<Bryan Seah> email=<bsah@gmail.com> phone=<12345678>`
* Adds a candidate named Bryan Seah 
with email bsah@gmail.com and phone number 12345678.

<u>**Delete a candidate: `delete c`**</u>

Deletes a candidate to the list of candidates.

<u>Format:</u>

    delete c <INDEX>

<u>Example:</u>
 `delete c 3` 
* Deletes the 3rd candidate from the list of candidates.

<u>**List all candidates: `list c`**</u>

List out all the candidates in the app.

<u>Format:</u>

    list c

<u>**View a candidate: `view c`**</u>

View all details of a specific candidate, specified by its index in the `list`.

<u>Format:</u>

    view c <CANDIDATE INDEX>

### Feature: Storage
Save all of the candidates’ records into a data file locally.

When a candidate record is added, edited or deleted, the change will be done accordingly in the local save file in real time.

The data file will be saved at `/data/candidates.json` and `/data/interviews.json` (coming soon). By replacing it with an existing save file with the same name, the record data will be loaded accordingly in the app, if the data format is valid.

The candidate information will be saved using the JSON format below.
For candidates,
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
**A**: This is an easy and fast application to track candidates applying for job roles in your company.
You will interact with the application through typed commands.

**Q**: When will my data be saved? <br>
**A**: Your data will be automatically saved after any command.

**Q**: How can I export my data? <br>
**A**: You can copy the save file at ‘/data/save.json and transfer it to another system in the data folder.
The saved data will be loaded on the next system.

## Command summary
Action | Format, Examples | Expected result
--------|------------------|------------------|
**Add candidate** | `add c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>` <br> e.g., `add c name=<Bryan Seah> email=<bsah@gmail.com> phone=<12345678>` | Candidate Added: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**Delete candidate** | ` delete c <INDEX>`<br> e.g., `delete 3` | Candidate Deleted: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**List all candidates** | `list` | 1. James Doe <br> 2. John Doe
**See details for a specific candidate** | `view c <CANDIDATE INDEX>` <br> e.g., `view candidate 2` | Details of Candidate 2 as follows: Name: John Doe, E-mail: johndoe@gmail.com, Phone: 1234 5678

