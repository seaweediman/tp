---
layout: page
title: Chua Sue-Ann's Project Portfolio Page
---

## Project: HR Manager

HR Manager is a candidate and interview management application used for helping HR Managers of small organisations
or businesses in managing candidates, job positions and interviews all in one application.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### 1. Code contributed
[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=sueann-chua&tabRepo=AY2122S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### 2. Enhancements implemented
* **New feature: Added the functionality to edit positions** `edit_p`
    * The feature was added such that users might be able to edit the job positions added to the HR Manager app.
    The command allows users to edit either the title of the position or the status of the position.


* **New feature: Added the functionality to edit interviews** `edit_i`
    * The feature was added such that users might be able to edit the interviews scheduled using the HR Manager app.
    The command allows users to edit any number of fields of the interview, including the position, date, time, duration and status.


* **New feature: Added the functionality to assign candidates to a specified interview** `assign`
    * Since the edit_i feature does not allow for users to edit the candidates assigned to the interview, this feature
      was added such that users might be able to assign candidates to a scheduled interview. This command was created
      to simplify the process of assigning a candidate to an interview.
      

* **Enhancement: bugfix for `edit_c` command where editing of candidate's applied position was not reflected in the
list of interviews**
    * Before, there was a bug where changes to the positions a candidate had applied to was not reflected in the list of
      interviews. Refer to [Issue #194](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/194) for more details on the
      bug.
    * With this bugfix, `EditCandidateCommand#Execute` now checks if the edited candidate's positions match the 
      positions of their scheduled interviews and updates the interview list accordingly.

### 3. Contributions to the UG
* Added the documentation for the following features:
    * `add c`
    * `delete c`
    * `edit_p`
    * `edit_i`
    * `assign`
    
### 4. Contributions to the DG
* Added an extension for use case: Deleting a candidate.

* Added a section for edit features which provides an overview of the implementation of all the edit features, such as
`edit_c`, `edit_p` and `edit_i`. The section also includes a UML diagram for the sequence diagram of the edit
  position command `edit_p`.

### 5. Contributions to team-based tasks
* Completed the necessary general code enhancements regarding changing all references of AB3 to HR Manager.

* Helped make the release for v1.3.

* Created UI Mock-up for intended final product.

### 6. Review contributions
* Non-exhaustive list of some PRs reviewed:
    * [PR #17](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/17)
    * [PR #22](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/22)
    * [PR #25](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/25)
    * [PR #26](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/26)
    * [PR #72](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/72)
    * [PR #75](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/75)
    * [PR #76](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/76)
    * [PR #94](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/94)
    * [PR #99](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/99)
    * [PR #124](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/124)
    * [PR #146](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/146)

