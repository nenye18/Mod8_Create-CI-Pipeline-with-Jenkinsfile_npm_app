pipeline {
    agent any
    tools {
        nodejs 'nodeJS-byme'
    }
    stages {
        stage('build') {
            steps {
               echo 'building the application..'
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
}
