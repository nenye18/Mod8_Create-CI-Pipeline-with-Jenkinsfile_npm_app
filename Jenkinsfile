pipeline {
    agent any
    tools {
        nodejs 'nodeJS-byme'
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
        stage('build image') {
            steps {
                    echo 'building the docker image..'   
            }
        }
        stage('deploy') {
            steps {
                echo 'Hello, deploying application'
            }
        }
    }
