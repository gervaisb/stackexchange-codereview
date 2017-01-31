[![Code Quality](https://codeclimate.com/github/gervaisb/stackexchange-codereview/badges/gpa.svg)](https://codeclimate.com/github/gervaisb/stackexchange-codereview)

# stackexchange-codereview

A collection of code used to answer or just experiment with Java questions asked
on [codereview.stackexchange.com](http://codereview.stackexchange.com/questions/tagged/java)

## Getting started
This is a simple maven project compiled with Java 8. There is no branching model,
each questions live in a package with a main class having the question id.

### Prerequisites

+ [Apache Maven 3+](https://maven.apache.org/)
+ [Java 8 (SE)](http://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html)

### Installation

Clone this repository then move to the local copy and build it with maven :

    git clone https://github.com/gervaisb/stackexchange-codereview.git

    cd stackexchange-codereview
    mvn compile  


## License
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT),
see the [LICENSE](LICENSE.txt) file for details.
