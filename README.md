# Génie Logiciel Project

## Introduction

As part of our third and last year in engineering school at ENSAI we had to carry out a project in groups of 4 to 5 for the ***Génie logiciel*** course.
Thereof consisted in extracting tables from **Wikipedia** pages into the **CSV** format. <br>
It is not new that Wikipedia tables are difficult to exploit and analyse by spreadsheets and statistical tools due to the syntaxt they are written in : **Wikitext**. Not only this language was not designed for table specification but the heterogeneity in the way to write the tables also complicates even more the task. <br>
That is why our motivation here is to develop the most robust and general procedure to extract Wikipedia tables and to translate them in a simpler and more suitable format : the CSV one. <br>

A Wikipedia page can be analysed using two different methods : <br>
- By picking up the Wikitext code corresponding
- By exploiting the HTML rendering of the Wikipedia page 

In our case, we'll be choosing the second point. 

## The Java extractor

Extracting Wikipedia tables into CSV files (basic skeleton for testing/benchmarking solutions). Once the git is cloned:
```
cd wikimatrix 
mvn test
``` 

We give 300+ Wikipedia URLs and the challenge is to:
 * integrate the extractors' code (HTML and Wikitext)
 * extract as many relevant tables as possible 
 * serialize the results into CSV files (within `output/html` and `output/wikitext`) 
 
More details can be found in `BenchTest.java`. We are expecting to launch `mvn test` and the results will be in `output` folder 
