---
layout: page
title: Liu Wanyu's Project Portfolio Page
---

### Project: HR Manager

HR Manager is a candidate and interview management application used for handling that enables recruiters of small organisations
to keep track of candidates, job positions and scheduled interviews easily and efficiently.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### Code contributed

[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=wanyu&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=wanyu-l&tabRepo=AY2122S1-CS2103T-W13-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Enhancements Implemented

* **Enhanced Feature**: Changed the Storage Component to store data in different files
    * Storage Component now reads from and saves to 3 different json files, corresponding to candidates, job positions and interviews.
    * File that stores job positions stores their respective title and status, whether it is still open for hiring or closed.
    * File that stores candidates also stores title(s) and respective status(es) of job position(s) which they applied to.
    * File that stores interviews store hashcode generated from particulars of respective candidates instead of all details of candidates.

* **New Feature**: Added functionality to delete a job position. `delete_p`
    * This feature allows the user to delete a job position from the current list of job positions in HR Manager, once deemed as not required by the user,
    perhaps when the company no longer require visibility of an already closed job position after a certain period of time.

* **New Feature**: Added functionality to add an interview. `add_i`
    * This feature allows the user to add details of a scheduled interview session for a particular job position in the company to HR Manager.

### Contributions to the UG
* Added Introduction
* Added FAQs
* Added Parameter Table for interview related commands
* Added the documentation for feature `add_i`
* Updated the documentation for storage component


### Contributions to the DG
* Added use case for adding an interview
* Updated StorageClassDiagram
* Renamed AddressBook to HrManager in the following diagrams:
    * ParserClasses diagram
    * LogicClass diagram

### Contributions to team-based tasks
* Renamed Address App to HR Manager as title of App

### Review/mentoring contributions
* Non-exhaustive list of some PRs reviewed:
    * [PR #43](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/43)
    * [PR #64](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/64)
    * [PR #67](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/67)
    * [PR #78](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/78)
    * [PR #234](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/234)
    * [PR #235](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/235)
