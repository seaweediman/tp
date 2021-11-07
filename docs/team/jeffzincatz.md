---
layout: page
title: Xu Jiheng's Project Portfolio Page
---

## Project: HR Manager

HR Manager is a candidate and interview management application used for helping HR Managers of small organisations
or businesses in managing candidates, job positions and interviews all in one application.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java.

Given below are my contributions to the project.

### Code contributed

[RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=jeffzincatz)

### Enhancement Implemented

* **New Feature: Add job position into the HR Manager system**
  * What it does: Implements a functional model of job positions, containing information including the job position title and posting status.
  * Justification: This feature is the foundation to allow user to manager job positions in their system.
  * Relevant test cases of the position model are added.

* **New Feature: Add position list into the HR Manager system**
  * What it does: Implements a job position list in to the HR Manager model, to store a list of all job positions.
  * Justification: This feature is to enable the user to access all the stored position data in the system later with other related commands.
  * Relevant test cases of the position model are added.

* **New Feature: Adds the functionality to list all job positions in the display. `list_p`**
    * What it does: Display a full list of all existing job positions stored in the HR Manager, read from the storage.
    * Justification: This feature gives the users a comprehensive overview of all the existing job positions for further management.
    * Relevant test cases of the position list are added.

* **New Feature: Adds interviews into the HR Manager system**
    * What it does: Implements a functional model of interviews, containing information including the job position interviewed, date, time, duration, candidates, and interview status.
    * Justification: This feature is the foundation to allow user to manager interviews in their system.
    * Relevant test cases of the interview model are added.
    * Relevant test cases of the position model are added.

* **New Feature: Add interview list into the HR Manager system**
  * What it does: Implements an interview list in to the HR Manager model, to store a list of all interview sessions.
  * Justification: This feature is to enable the user to access all the stored interview data in the system later with other related commands.
  * Relevant test cases of the interview list are added.

* **New Feature: Adds the functionality to list all job positions in the display. `list_i`**
    * What it does: Display a full list of all existing interview sessions stored in the HR Manager, read from the storage.
    * Justification: This feature gives the users a comprehensive overview of all the existing interviews for further management.

* **Enhancement: Improves the display to a 3-panel list**
  * What it does: Display 3 panels for the lists of candidates, positions, and interviews, at the same time.
  * Justification: Compared to the old implementation of 1-panel display, this enables the users to easier view all lists without having to replace the previous ones.

* **Enhancement: Applies a new colour scheme for HR Manager**
  * What it does: Changes the graphical appearance of the HR Manager, including highlighting the panel lists, making HR Manager more distinct from other applications.
  * Justification: This makes the HR Manager more distinct as a product.

### Contribution to Documentations

* User Guide:
  * Added documentation for the features `position` and its storage
  * Added the documentation for the following features:
    * `list_c`
    * `add_p`
    * `delete_p`
    * `list_p`
    * `list_i`

* Developer Guide:
  * Added use cases for features including:
    * `list_c`
    * `delete_c`
    * `list_p`
    * `delete_p`
    * `list_i`
    * `delete_i`
  * Added various user stories for features including:
    * `add_p`
    * `delete_p`
    * `list_p`
  * Added an overview of the implementation of all the find features, such as `list_c`, `list_p` and `list_i`. The section also includes a UML diagram for the sequence diagram of a general find command `list_x`.

### Contributions to team-based tasks

* Set up team project repo
* Documenting user stories in table format in Developer Guide
* Refactor existing candidate command's format to include a "_c" suffix, after new models (positions and interviews) were added
* Fix various documentation bugs in UG and DG

### Review contributions
* Non-exhaustive list of some PRs reviewed:
  * [PR #18](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/18)
  * [PR #49](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/49)
  * [PR #71](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/71)
  * [PR #72](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/72)
  * [PR #74](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/74)
  * [PR #149](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/149)
  * [PR #150](https://github.com/AY2122S1-CS2103T-W13-1/tp/pull/150)

* [A full list](https://github.com/AY2122S1-CS2103T-W13-1/tp/pulls?q=is%3Apr+is%3Aclosed+reviewed-by%3Ajeffzincatz)
of PRs reviewed by me.

* Non-exhausted list of bugs and suggestions reported for other teams
  * [Issue #5](https://github.com/JeffZincatz/ped/issues/5)
  * [Issue #7](https://github.com/JeffZincatz/ped/issues/7)
  * [Issue #8](https://github.com/JeffZincatz/ped/issues/8)

* [A full list](https://github.com/JeffZincatz/ped/issues) of bugs reported
