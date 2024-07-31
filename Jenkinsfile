#!/user/bin/env groovy

@Library('Jenkins-shared-library-nodejs')_

//def gv

pipeline {
    agent any
    tools {
        nodejs 'nodeJS-byme'
    }
    stages { 
        //stage('init') {
            //steps {
                //script{
                    //gv = load "script.groovy"
                //}
            //}
        //}
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
                    buildImage() 
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
                script{
                    echo 'Hello, deploying application....'
                    def dockerCmd = 'docker run -p 3050:3000 -d cnwagba/jenkins-repo-dockerhub:1.0.4-2'
                    sshagent(['d_ec2']) {
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@44.193.200.99 ${dockerCmd}"
                }
            }
       }        
     }
   }
 }
