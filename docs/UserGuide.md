## Command summary
Action | Format, Examples | Expected result
--------|------------------|------------------|
**Add candidate** | `add c name=<NAME> email=<EMAIL> phone=<PHONE_NUMBER>` <br> e.g., `add c name=<Bryan Seah> email=<bsah@gmail.com> phone=<12345678>` | Candidate Added: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**Delete candidate** | ` delete c <INDEX>`<br> e.g., `delete 3` | Candidate Deleted: Name: Bryan Seah, E-mail: bsah@gmail.com, Phone: 1234 5678
**List all candidates** | `list` | 1. James Doe <br> 2. John Doe
**See details for a specific candidate** | `view c <CANDIDATE INDEX>` <br> e.g., `view candidate 2` | Details of Candidate 2 as follows: Name: John Doe, E-mail: johndoe@gmail.com, Phone: 1234 5678

