---
layout: page
title: Mohamed Noriman's Project Portfolio Page
---

### Project: HR Manager

HR Manager is a candidate and interview management application used for handling that enables recruiters of small organisations to keep track of candidates, job positions and scheduled interviews easily and efficiently. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Added the ability to remark candidates. `remark_c`
    * What it does: allows user to give remarks to a specific candidate. Remark is then displayed together with the candidate's other information.
    * Justification: This feature allows users to comment something about a particular candidate.

* **New Feature**: Added the ability to add job positions. `add_p`
    * What it does: allows user to add job positions that just opened in the company.
    * Justification: This feature allows users to keep track of the positions in the company.

* **New Feature**: Added the ability to delete interviews. `delete_i`
    * What it does: allows user to delete interviews from HR Manager
    * Justification: This feature allows users delete unwanted interviews from HR Manager to reduce clutter.

* **New Feature**: Added the ability to 'unassign' candidates from an interview. `unassign`
    * What it does: allows users to remove specific candidates from a specific interview
    * Justification: Before, the only way to remove candidates from an interview is to use the `edit_i` feature. This required users to enter all the candidate indexes of the candidates that remain scheduled for that interview, which can be hassle. But with `unassign`, removing candidates from an interview is done much quicker.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=seaweed&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=seaweediman&tabRepo=AY2122S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
    * Updated commands to allow multiple candidate indexes after a single `c=` prefix input.
    * Updated commands to automatically update all relevant lists displayed on the GUI at the end of a command's execution.

* **Documentation**: //TODO
    * User Guide:
        * Added command summary
    * Developer Guide:
        * Added use case for viewing a specific candidate

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]() //TODO
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/seaweediman/ped/issues/4), [2](https://github.com/seaweediman/ped/issues/3), [3](https://github.com/seaweediman/ped/issues/14))