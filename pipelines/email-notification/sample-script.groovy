def notifyViaEmail(String buildStatus = 'SUCCESS', String MESSAGE = 'Report') {
    def decodedJobName = env.JOB_NAME.replaceAll("%2F", "/")

    emailext (
        subject: "${decodedJobName} - ${MESSAGE}",
        body: 'Test Email',
        to: "localjenkinstest@gmail.com",
        replyTo: 'localjenkinstest@gmail.com',
        attachLog: true
    )
}

pipeline {
    agent any
    tools {
        nodejs "node"
    }
    parameters{
      string defaultValue: 'master', description :'', name : 'branch_name', trim: false
      string defaultValue: 'https://github.com/VikashChoudahry/ng-sonar-app.git', description :'', name : 'ssh_url', trim: false
    }
    stages {
        stage('Pre Build') {
            steps {
              script {
                try {
                  dir("Users/Shared/Jenkins/Home/workspace/Angular App/ng-sonar-app") {
                    sh 'inode --version'
                    sh "rm -rf *"
                    git branch: "${branch_name}", credentialsId: "gitaccess", url: "${ssh_url}"
                  }
                } catch (error) {
                  currentBuild.result = "FAILURE"
                  notifyViaEmail(currentBuild.result, 'WARNING - tag invalid')
                }
              }
            }
        }
        stage('Build') {
            steps {
                dir("Users/Shared/Jenkins/Home/workspace/Angular App/ng-sonar-app") {
                    script {
                        if ("${branch_name}" == "master") {
                          echo "I am working for branch: ${branch_name}"
                          echo "Run npm install ..."
                          sh "pwd"
                          sh "npm install"
                        }
                    }
                }
            }
        }
        stage('Coverage') {
            steps {
                dir("Users/Shared/Jenkins/Home/workspace/Angular App/ng-sonar-app") {
                    script {
                        if ("${branch_name}" == "master") {
                            echo "I am working for branch: ${branch_name}"
                            echo "Generate the coverge in the lcov format ..."
                            sh "pwd"
                            echo "No coverage report as of now."
                        }
                    }
                }
            }
        }
        stage('Sonar Scanner') {
            steps {
                dir("Users/Shared/Jenkins/Home/workspace/Angular App/ng-sonar-app") {
                    script {
                        if ("${branch_name}" == "master") {
                          echo "I am working for branch: ${branch_name}"
                          sh "pwd"
                          echo "Sonar scanning started ..."
                          def scannerHome = tool 'sonar-scanner';
                          // sh "/Users/vikash.choudhary/.jenkins/tools/hudson.plugins.sonar.SonarRunnerInstallation/sonar-scanner/bin/sonar-scanner"
                          sh "${scannerHome}/bin/sonar-scanner"
                        }
                    }
                }
            }
        }
        stage('Deploy') {
            steps {
                dir("Users/Shared/Jenkins/Home/workspace/Angular App/ng-sonar-app") {
                    script {
                        if("${branch_name}" == "master"){
                            echo "Deployment command need to be added here ..."
                            sh "pwd"
                            sh "rm -rf *"
                        }
                    }
                }
            }
        }
    }
}
