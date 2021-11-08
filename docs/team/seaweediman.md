---
layout: page
title: Mohamed Noriman's Project Portfolio Page
---

### Project: HR Manager

HR Manager is a candidate and interview management application used for helping HR Managers of small organisations
or businesses in managing candidates, job positions and interviews all in one application.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### 1. Code contributed
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=seaweed&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=seaweediman&tabRepo=AY2122S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### 2. Enhancements implemented
* **New Feature**: Added the ability to remark candidates. `remark_c`
  * This feature allows user to give remarks to a specific candidate. The remark is then displayed together with the 
  candidate's other information. This enables users to give comments to noteworthy candidates.
  * Refer to [PR#43](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/43) for more details.

* **New Feature**: Added the ability to add job positions. `add_p`
  * This feature allows user to add job positions that just opened in the company hence enabling them to keep track 
  of the positions in the company.
  * Highlights : The implementation of this feature required extensive knowledge on how the `add` feature in AB3 was implemented and to adapt it into `add_p`. 
    The implementation was difficult as it required adding more classes such as a new `Position` class and its respective components such as `Title`.
  * Refer to [PR#67](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/67) for more details.

* **New Feature**: Added the ability to delete interviews. `delete_i`
  * This feature allows user to delete interviews from HR Manager, enabling them delete unwanted interviews from 
  HR Manager to reduce clutter.
  * Highlights : The implementation of this feature required a good understanding on the relationship between candidates and interviews. For example, when deleting an interview, HR Manager needs to check which candidate was scheduled that interview, and delete the interview from the candidate object.
  * Refer to [PR#102](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/102) for more details.

* **New Feature**: Added the ability to 'unassign' candidates from a specific interview. `unassign`
  * Since the edit_i feature does not allow for users to edit the candidates assigned to the interview, this feature
    was added such that users might be able to 'unassign' candidates to a scheduled interview. This command was created
    to simplify the process of 'unassigning' a candidate to an interview.
  * Highlights : The implementation of this feature needed to take into account 2 cases: When the user inputs `*` and when the user inputs any candidate index. When the user inputs `unassign i=1 c=*`, HR Manager unassigns all candidates from the 1st interview in the list.
  * Refer to [PR #144](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/144) for more details.

* **Enhancements: Updated `add_c` and `edit_c` so that it throws an error when a closed `Position` is inputted.**
  * Before, you could use `add_c` to add a candidate who is applying for a closed position in the company. Similarly, you could use `edit_c` to edit a candidate to apply for a closed position in the company.
  * Refer to [Issue #79](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/79) for more details of the issue.
  * Refer to [PR #97](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/97) for more details of the fix.

* **Enhancements: Updated commands to allow multiple candidate indexes after a single `c=` prefix input**
  * Before, to add candidate indexes to add_i command, you had to do `c=1 c=2`. Now, you can put it under a single prefix input, 
   `c=1 2`.
  * Highlights : This enhancement required the use of implementing a new `parseCandidateIndexes` method to parse multiple candidate indexes at the same time.
  * Refer to [Issue #131](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/131) for more details of the issue.
  * Refer to [PR #144](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/144) for more details of the fix.

* **Enhancements: Updated commands to automatically update all relevant lists displayed on the GUI at the end of a command's execution.**
  * Before, after every command, the lists will not update automatically and only updates when you click on the person, position or interview card. Now, after each command, the lists will be updated automatically.
  * Highlights : This required changing the constructor of `CommandResult` to take in an enum `CommandType`. In `MainWindow`, new methods were created to handle updating specific lists for different types of commands. <br>
    e.g If the command is of `CommandType` `POSITION`, `MainWindow::handleP()` is called to update all 3 lists.
  * Refer to [Issue #219](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/219) for more details of the bug.
  * Refer to [PR #232](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/232) for details of the fix.

* **Enhancements: Updated commands that take in position such that regardless of what the user inputs, the position that is stored in the candidate or interview object has the same naming convention as the one in the position list.**
  * Before, after every command that takes in position, the position stored in the candidate or interview would have different casing from the actual position in the position list. Now, the position stored in the candidate or interview would have the same casing.
  * Refer to [Issue #206](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/206) for more details of the issue.
  * Refer to [PR #235](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/235) for details of the fix.

### 3. Contributions to the UG
* Added first iteration of command summary
* Added the documentation for the following features:
  * `remark_c`
  * `add_p`
  * `delete_i`
  * `unassign`
* Updated setup instructions

### 4. Contributions to the DG

* Added a section for add features which provides an overview of the implementation of all the add features, such as
  `add_c`, `add_p` and `add_i`. The section also includes a UML diagram for the sequence diagram of the add
  position command `add_p`. Refer to [PR #259](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/259)
* Updated class diagram for Model Component. Refer to [PR #281](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/281) for more details.
* Updated class diagram for Ui Component. Refer to [PR #281](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/281) for more details.
* Updated class diagram for Logic Component. Refer to [PR #301](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/301) for more details.

### 5. Contributions to team-based tasks
* Integrated Java CI into the team repository

* Separated candidate, position, interview classes into separate packages

* Renamed candidate commands to include Candidate in the name
  * e.g Changing `AddCommandParser` to `AddCandidateCommandParser`

* Helped setup github page for the team repository

* Updated the sample data in HR Manager. Refer to [PR #123](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/123) for details.

* Updated index.md to change AB3 references to HR Manager references.

* Often helped schedule team meetings

* Often helped teammates in resolving bugs

* Often helped in reviewing teammates' PR

### 6. Review contributions
* Reported bugs and suggestions for other teams in the class
  * Examples: [1](https://github.com/seaweediman/ped/issues/4), [2](https://github.com/seaweediman/ped/issues/3), [3](https://github.com/seaweediman/ped/issues/14)
  * [Full list of bugs and suggestions](https://github.com/seaweediman/ped/issues/)
* Non-exhaustive list of some PRs reviewed:
  * [PR #98](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/98)
  * [PR #103](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/103)
  * [PR #245](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/245)
  * [PR #252](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/252)
  * [PR #253](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/253)
* [Full list of PRs reviewed](https://github.com/AY2122S1-CS2103T-W13-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Aseaweediman)
