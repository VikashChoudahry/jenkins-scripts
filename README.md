# jenkins-scripts
Jenkins Pipeline Scripts

## Steps to enable the notificaiton feature
Description: It's always a good practice to send an exception whenever any required stages gets failed during the pipeline execution.

Pre-requisites:
1. LTS version of Jenkins must be setup and working as exepcted.

**Steps:**
1. Add these plugins from Jenkin's "Manage Jenkins -> Manage Plugins" section : Email-ext, Email Notification Plugin, and Email-ext Template plugin.
2. Configure the SMTP from Jenkin's Manage Jenkins -> Configure System".
3. Test the configuration. If configurations are proper it will show as "Email Test Successfull".
4. Apply and Save.

**Execution Steps:**
1. Use the sample script as a reference.
2. Replace the email id with the one where you want to get a notification.

**Challenges faced:**
1. Test Email Configuration was failing:
  Solution: After upgrading the Jenkins LTS version it has solved the issue.
2. Adding try-catch block was not working:
  Solution: As of now I know only two types of "Pipelines syantax" not sure if there are more way to write. Since as part of this samply script I am using "Declarative Pipeline fundamentals" so to handle try-catch is different from "Scripted Pipeline fundamentals" but overall fundmentail reamins same. In case of declarative we have use "script" with "stages -> stage -> steps" to handle try-catch else it may throw error.

  Reference: Jenkins pipleline syntax guidelines[https://www.jenkins.io/doc/book/pipeline/#:~:text=Declarative%20Pipeline%20syntax.-,Node,part%20of%20Scripted%20Pipeline%20syntax.]

  **Note:** "Scripted Pipeline fundamentals" pipeline sytanx looks straight forward so you may choose that for simplicity.