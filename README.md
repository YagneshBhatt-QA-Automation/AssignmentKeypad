# Task
Task description

Attention: Please follow these instructions carefully. Develop the solution yourself as we are looking forward to review your approach and further 
interviews will be based on your response.

- The requirement for this code (com.test.dto.TelephoneDialPad) is to print possible combination of alphabets for the numeric that you see in a telephone 
dial pad. 
- The expectation from you is to 
a. Analyze and Develop an automation framework in Java for this requirement. Intentionally the code is written without any standards or comments.
b. Write test cases that covers possible combination to test the feature and also prioritize these test case as High / Medium / Low (Delimite text, .xls, .csv files are acceptable).
c. Automate the test cases that test the code and produce a result in any simple format.
d. Send the prepared test suite back with steps to run.
e. The test suite should not require any addtional "software purchase" (I, as a reviewer should be able to run without installing some custom applications. Junit, ant, maven are all ok.)

# APPROACH

# Data-Driven Test
A data-driven framework is where test input and output values are read from data files. Currently using xls file. All test cases data and expected value coming from Excel. 

# Tools (Not used any commercial tool)
-> Eclipse Java EE
-> Java 8+
-> JXL
-> TestNG
-> Extent Report
-> Maven Build

# Few Advantage of this framework

-> Redue maintanance 
-> Add new test case easily 

# Framework

-> src.java
* Com.TestAutomation
    * DataDrivenTest.Java (Driver Script)
* Com.utilDTO
    * Utility (All common functions)   
