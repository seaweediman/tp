## Features & Usage
### Feature: Candidate Management

<u>**Add a candidate: `add c`**</u>

Adds a candidate to the list of candidates.

<u>Format:</u>

    add c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>

<u>Example:</u><br/><br/>
`add c name=<Bryan Seah> email=<bsah@gmail.com> phone=<12345678>` adds a candidate named Bryan Seah 
with email bsah@gmail.com and phone number 12345678.

<u>**Delete a candidate: `delete c`**</u>

Deletes a candidate to the list of candidates.

<u>Format:</u>

    delete c <INDEX>

<u>Example:</u><br/><br/>
`delete c 3` deletes the 3rd candidate from the list of candidates.

<u>**List all candidates: `list c`**</u>

List out all the candidates in the app.

<u>Format:</u>

    list c

<u>**View a candidate: `view c`**</u>

View all details of a specific candidate, specified by its index in the `list`.

<u>Format:</u>

    view c <CANDIDATE INDEX>


## Command summary
Action | Format, Examples | Expected result
--------|------------------|------------------|
**Add candidate** | `add c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>` <br> e.g., `add c name=<Bryan Seah> email=<bsah@gmail.com> phone=<12345678>` | Candidate Added: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**Delete candidate** | ` delete c <INDEX>`<br> e.g., `delete 3` | Candidate Deleted: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**List all candidates** | `list` | 1. James Doe <br> 2. John Doe
**See details for a specific candidate** | `view c <CANDIDATE INDEX>` <br> e.g., `view candidate 2` | Details of Candidate 2 as follows: Name: John Doe, E-mail: johndoe@gmail.com, Phone: 1234 5678

