def VersionUpdate() {
    echo 'updating the version'
    dir("app") {
        sh "npm version patch"  //update the package.json version
        def Version = readJSON(file: 'package.json').version
        env.IMAGE_NAME ="$Version-$BUILD_NUMBER"                                    
    }
  }


def TestApp() {
    echo 'building the image'
    dir("app") {
    sh "npm install"
    sh "npm run test"                
    }
}

def BuildImage(){
    echo 'building the docker image...
    withCredentials([usernamePassword(credentialsId: 'docker hub repository',passwordVariable: 'PASS', usernameVariable: 'USER')]){
    sh 'docker build -t cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME .'  //Build Docker Image
    sh 'echo $PASS | docker login -u $USER --password-stdin'       // Authentication
    sh 'docker push cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME'       //Push to Docker repository
    }
}
        
def CommitToGit() {
  echo 'committing to git'
}

return this
