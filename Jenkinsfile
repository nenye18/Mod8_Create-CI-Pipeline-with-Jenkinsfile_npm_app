#!/user/bin/env groovy

@Library('Jenkins-shared-library-nodejs')

def gv

pipeline {
    agent any
    tools {
        nodejs 'nodeJS-byme'
    }
    stages { 
        stage('init') {
            steps {
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage('increment app version') {
            steps {
                script{
                    VersionUpdate()
                }
            }
        }
        stage('test') {
            steps {
                script{
                  TestApp()
                }
            }
        }
        stage('build docker image and push to repo') {
            steps {
                script{
                    BuildImage() 
                }        
            }
        }
        stage('commit new version to github') {
            steps {
              script{
                  CommitToGit()
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
