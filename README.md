# API-test-suit
This contains automation test cases for  https://restful-api.dev

Prerequisites:
1. Install Java: https://www.oracle.com/java/technologies/javase-downloads.html
2. Download Maven: https://maven.apache.org/download/cgi
	After downloading this, extract the zip file.
	Then go to the bin folder and copy that path.
	After that, add that path to the system variable.
 
3. Download Allure for your operating system: https://allurereport.org/docs/gettingstarted-installation/.
	Download the Allure command-line zip file and extract it.
	Then go to the bin folder and copy that path.
	After that, add that path to the system variable.
 
4.We can check that the environment variables are correctly set using the following commands:
	run these command on command promt
	allure --version
	mvn --version
 
 
Report generation
1 method

	1.After running the test, we can see in the Inteliji IDEA there is a folder called Allure Results.
	2.Then copy that folder path (the absolute path).
	3.Open the command prompt.
	4.Run the following command: allure serve "copied path."

2 method 
	In the terminal run "mvn clean test allure:serve" command
 
 
