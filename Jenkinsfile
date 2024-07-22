#!/user/bin/env groovy

//@library ('Jenkins-shared-library-nodejs')_

pipeline {
    agent any
    tools {
        nodejs 'nodeJS-byme'
    }
    stages { 
        stage('init'){
            steps {
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage('increment app version') {
            steps {
                script{
                    gv.VersionUpdate()
                }
            }
        }
        stage('test') {
            steps {
                script{
                  gv.TestApp()
                }
            }
        }
       stage('build docker image and push to repo') {
            steps {
                script{
                    gv.BuildImage() 
                }        
            }
        }
       stage('commit new version to github') {
            steps {
              script{
                  gv.CommitToGit()
              }
           } 
      }
      stage('deploy') {
            steps {
                echo 'Hello, deploying application'
            }
        }        
   }
}
