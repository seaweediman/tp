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

As mentioned below, I helped implement the features for `edit_p` and `edit_i` and helped in bugfixes for`edit_c`, 
`edit_p` and `edit_i` as well. The edit features were sometimes a challenge to implement due to the cascading effects
on the other objects when one object was edited.

For example, editing a position would mean that the candidates applied to the position, and the interviews scheduled for
that position would need to be updated as well. There were some instances in which I had missed out certain 
possibilities and this resulted in certain bugs that were eventually fixed.

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


* **Enhancement: bugfix for `edit_p` command where users were able to edit both title and status of the position.**
    * We decided against this behaviour as we did not want users to use edit_p as an alternative to adding new
      positions. Refer to [PR #265](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/265) or 
      [Issue #169](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/169) for more details.


* **Enhancement: change edit functionality for `edit_i` command**
    * Initially, the `edit_i` command allowed users to edit the position, set of candidates assigned, date, time,
      duration and status of the interview. However, this resulted in a few bugs that were caught by other teams in the
      PED. Hence, we decided to remove the functionality to edit the set of candidates assigned to an interview using the 
      `edit_i` command. Instead, we restricted editing of candidates assigned to `assign` and `unassign` commands.
    * My contribution to this enhancement was to remove the functionality to edit candidates using the `edit_i` command.
    * Please refer to [Issue ##220](https://github.com/AY2122S1-CS2103T-W13-1/tp/issues/220) or 
      [PR #254](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/254) for more details.


* **Enhancement: GUI for position labels for candidate list and status labels for position list.**
    * Before the positions a candidate had applied to was shown as just text. I edited the `DarkTheme.css` to add labels
      for the positions instead.
    * Similarly, for the position status, it was displayed as text in the list of positions. I also edited the 
      `DarkTheme.css` to add labels for the position statuses instead.
    * Please refer to [PR #78](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/78) for more details.
  
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
    * [PR #26](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/26)
    * [PR #72](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/72)
    * [PR #75](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/75)
    * [PR #76](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/76)
    * [PR #94](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/94)
    * [PR #99](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/99)
    * [PR #124](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/124)
    * [PR #146](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/146)
  
* [Complete list](https://github.com/AY2122S1-CS2103T-W13-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3A%40me)
  of PRs reviewed by me.

