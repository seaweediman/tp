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
  * What it does: Implements a functional model of job positions, containing information including the job position title and posting status, and store the list of job position in the HR Manager.
  * Justification: This feature is the foundation to allow user to manager job positions in their system.

* **New Feature: Adds the functionality to list all job positions in the display. `list_p`**
    * What it does: Display a full list of all existing job positions stored in the HR Manager, read from the storage.
    * Justification: This feature gives the users a comprehensive overview of all the existing job positions for further management.

* **New Feature: Adds interviews into the HR Manager system**
    * What it does: Implements a functional model of interviews, containing information including the job position interviewed, date, time, duration, candidates, and interview status, and store the list of interviews in the HR Manager.
    * Justification: This feature is the foundation to allow user to manager interviews in their system.

* **New Feature: Adds the functionality to list all job positions in the display. `list_i`**
    * What it does: Display a full list of all existing interview sessions stored in the HR Manager, read from the storage.
    * Justification: This feature gives the users a comprehensive overview of all the existing interviews for further management.

* **Enhancement: Improves the display to a 3-panel list**
  * What it does: Display 3 panels for the lists of candidates, positions, and interviews.
  * Justification: Compared to the old implementation of 1-panel display, this enables the users to view all lists without having to replace the previous ones.

* **Enhancement: Applies a new colour scheme for HR Manager**
  * What it does: Changes the graphical appearance of the HR Manager, including highlighting the panel lists, making HR Manager more distinct from other applications.

### Contribution to Documentations

* User Guide:
        * Added documentation for the features `list` and `view`
* Developer Guide:

    * Added the documentation for the following features:
      * `list_c`
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
  * Added an overview of the implementation of all the find features, such as `find_c`, `find_p` and `find_i`. The section also includes a UML diagram for the sequence diagram of a general find command `find_x`.

### Contributions to team-based tasks

* Set up team project repo

* Documenting user stories in table format in Developer Guide

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

* Non-exhausted list of bugs and suggestions reported for other teams
  * [Issue #5](https://github.com/JeffZincatz/ped/issues/5)
  * [Issue #7](https://github.com/JeffZincatz/ped/issues/7)
  * [Issue #8](https://github.com/JeffZincatz/ped/issues/8)
