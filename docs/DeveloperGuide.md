---
layout: page
title: Developer Guide
---
* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------
## **Acknowledgements**

* {list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

### Product scope

**Target user profile**:

* is HR professional
* has to manage multiple applicants, roles, and interviews
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps 

**Value proposition**: help them quickly enter the data into the system, reminders of interview timings and ranking of candidates for roles

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`
                               
| Priority | As a …​ | I want to …​                | So that I can…​                                                        |
| -------- | ---------- | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | user       | be able to add a candidate with all relevant information such as full name, age, contact information, scheduled interview time and date.|                  |
| `* * *`  | user       | be able to delete a candidate | remove the application if it was withdrawn entirely                                                                       |
| `* * *`  | user       | be able to see all active candidates | |
| `* * *`  | user       | be able to see the details of a specific candidate | |
| `* * *`  | user       | be able to edit the information related to a candidate | correct any wrongly filled information |
| `* * *`  | user       | be able to categorize the candidate, whether it was self-applied or referred | carry out administrative processes afterwards more easily |
| `* * *`  | user       | be able to add a remark/status to a candidate | make it more visible for the next course of administrative action|
| `* * *`  | user       | be able to edit comments of a candidate | update any further remarks for them|
| `* * *`  | user       | be able to delete comments of a candidate | remove mistakenly put remarks entirely |
| `* * *`  | user preparing interviews | be able to know the number of candidates on a certain day | |
| ` * * `  | user preparing interviews | be reminded what interviews I have the next day | not forget about them |
| ` * * `  | user preparing interviews | be able to reschedule the interview session for a candidate | |
| `* * *`  | user preparing interviews | be able to delete an interview session for a candidate if it no longer takes place | |
| ` * * `  | user preparing interviews | be able to schedule a new interview for a candidate | recruit more members |
| ` * * `  | user preparing interviews | be able to delete all other scheduled interview sessions for a particular candidate once I decide to assign him a particular post | |
| `* * *`  | user preparing interviews | be able to view all candidates who will be interviewed on a particular date | keep track of the interviews planned for that day |
| ` * * `  | user preparing interviews | be able to delete the interview sessions on a particular date | |
| `  *  `  | user preparing interviews | add a co-interviewer to an interview | I know who I will be interviewing the candidate with |
| ` * * `  | user after interviews | be able to mark a candidate as ‘Interviewed’ | |
| `* * *`  | user after interviews | be able to tag a candidate e.g. by the position they are applying for | group them according to the tags |
| `* * *`  | user       | want to be able to edit existing tags of a candidate | |
| `* * *`  | user       | be able to remove existing tags of a candidate | |
| ` * * `  | user searching for candidates | be able to filter the candidates for some specific requirements | find the right person for the post more efficiently |
| `* * *`  | user searching for candidates | be able to search for candidates who have a certain remark/description | find candidates by criteria |
| ` * * `  | user searching for candidates |  be able to search who applied for certain positions |  I know all the candidates for that specific position |
| ` * * `  | user preparing interviews | search what interviews I have for a particular date | better prepare for that day |
| ` * * `  | user       | see all the candidates scheduled for interview sessions for a particular job posting | |
| `* * *`  | user       | be able to search for a particular candidate to see his/her upcoming sessions | |
| `  *  `  | user       | be able to password lock the application to prevent unauthorised access | |
| `  *  `  | user       | encrypt the save file | prevent my data from being easily stolen |

### Use cases

(For all use cases below, the **System** is the `HR Manager` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a candidate**

**MSS**

1. User inputs the command to add a new candidate, with the initial details of the new candidate
2. User can see the added candidate

   Use case ends.

**Extensions**

**Use case: List all candidate**

**MSS**

1. User requests to list all candidates
2. HR manager shows a list of all candidates

   User case ends.

**Extensions**

**Use case: View a candidate**

**MSS**

1. User inputs view and an index for candidate
2. HR Manager displays the relevant information for that specific candidate

   User case ends.

**Extensions**

**Use case: Delete a candidate**

**MSS**

1. User inputs delete and an index for candidate
2. HR Manager deletes the corresponding candidate and displays result

   Use case ends

**Extensions**

[Sample use case] **Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to delete a specific person in the list
4.  AddressBook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.



### Non-Functional Requirements

1. Program needs to run on all operating systems with Java 11 installed.
2. Application needs to handle at least 500 candidates and 500 interviews without a noticeable sluggishness in performance for typical usage and no graphical errors.
3. A user with above average typing speed should be able to complete tasks faster using commands than they would have using a click-based interface.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
