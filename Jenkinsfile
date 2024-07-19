pipeline {
    agent any
    tools {
        nodejs 'nodeJS-byme'
    }
    stages {    
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
    stage('build image') {
            steps {
                script{
                    echo 'building the docker image..'
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo',passwordVariable: 'PASS', usernameVariable: 'USER')]){
                        sh 'docker build -t cnwagba/jenkins-repo-dockerhub:node-1.0 .'  //Build Docker Image
                        sh 'echo $PASS | docker login -u $USER --password-stdin'       // Authentication
                        sh 'docker push cnwagba/jenkins-repo-dockerhub:node-1.0'       //Push to Docker repository
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
