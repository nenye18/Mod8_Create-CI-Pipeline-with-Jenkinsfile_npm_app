pipeline {
    agent any
    tools {
        nodejs 'nodeJS-byme'
    }
    stages {   
        stage('increment app version') {
            steps {
                script{
                    dir("app") {
                        sh "npm version patch"  //update the package.json version
                        //def matcher = readJSON file: 'package.json'
                        //def version = matcher.version
                        //env.IMAGE_NAME ="$version-$BUILD_NUMBER" 
                        // def version = matcher[0][1]
                       // env.IMAGE_NAME ="$version-$BUILD_NUMBER
                        //def matcher =readJSON(file: 'package.json').version
                         def Version=readJSON(file: 'package.json').version
                        env.IMAGE_NAME ="$Version-$BUILD_NUMBER" 
                                      
                    }
                }
            }
        }
        stage('test') {
            steps {
                script{
                    dir("app") {
                        sh "npm install"
                        sh "npm run test"
                    }
                }
            }
        }
    stage('build docker image and push to repo') {
            steps {
                script{
                    echo 'building the docker image..'
                    withCredentials([usernamePassword(credentialsId: 'docker hub repository',passwordVariable: 'PASS', usernameVariable: 'USER')]){
                        sh 'docker build -t cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME .'  //Build Docker Image
                        sh 'echo $PASS | docker login -u $USER --password-stdin'       // Authentication
                        sh 'docker push cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME'       //Push to Docker repository
                    }
                        
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
