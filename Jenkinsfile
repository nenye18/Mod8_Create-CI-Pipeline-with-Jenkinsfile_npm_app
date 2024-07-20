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
                         def Version = readJSON(file: 'package.json').version
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
      stage('commit new version to github') {
            steps {
              script{
                    echo 'commit new version to git'
                    withCredentials([usernamePassword(credentialsId: 'GitHub credentials',passwordVariable: 'PASS', usernameVariable: 'USER')]){
                        sh 'git config --global user.email "chinenye.nw@gmail.com" '
                        sh 'git config --global user.name "nenye18" '
                        sh 'git remote set-url origin https://$USER:$PASS@github.com/nenye18/Jenkins-project.git'
                        sh 'git add .'
                        sh 'git commit -m "commiting version update from jenkins CI/CD" '
                        sh 'git push origin HEAD:main'
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
