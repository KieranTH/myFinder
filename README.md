<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/github_username/repo_name">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">NHS Facility Finder</h3>

  <p align="center">
    Locates NHS facility surrounding given Postcode.
    <br />
    <a href="https://github.com/KieranTH/NHSFinder"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/KieranTH/NHSFinder">View Demo</a>
    ·
    <a href="https://github.com/KieranTH/NHSFinder/issues">Report Bug</a>
    ·
    <a href="https://github.com/KieranTH/NHSFinder/issues">Request Feature</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project


### Built With

* [Java](https://www.java.com/en/)
* [Apache Maven](https://maven.apache.org/)
* [Apache POI-HSSF](http://poi.apache.org/components/spreadsheet/)
* [JXL Excel API](https://mvnrepository.com/artifact/net.sourceforge.jexcelapi/jxl)



<!-- GETTING STARTED -->
## Getting Started


### Prerequisites

* Java
* Build through compatible Maven IDE

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/KieranTH/NHSFinder.git
   ```



<!-- USAGE EXAMPLES -->
## Usage

This system is used to return relevant NHS Facilities surrounding given Postcode.
* Main Structure
  All java files are stored within the `src/` directory. The main constructor is found within `finderMain.java`.
  
* Data
  Postcode data and facility data is stored within given Excel Spreadsheet - `epraccur.xls`.
  
* Libraries
  All needed Maven dependancies and repos are stated within the `pom.xml` file as expected.

* Implementation
  * Maven
    Build from contained git copy within Maven compatible builder.
    Once initiated JFrame window will open.
    Input necessary Postcode.
    

<!-- CONTACT -->
## Contact

Kieran Hughes - kieran.hughes2@live.co.uk

Project Link: [https://github.com/KieranTH/NHSFinder](https://github.com/KieranTH/NHSFinder)






<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/github_username
