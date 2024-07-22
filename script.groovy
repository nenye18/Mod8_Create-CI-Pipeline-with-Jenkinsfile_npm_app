def VersionUpdate() {
    echo 'updating the version'
    dir("app") {
        sh "npm version patch"  
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
    //withCredentials([usernamePassword(credentialsId: 'docker hub repository',passwordVariable: 'PASS', usernameVariable: 'USER')]){
    //sh 'docker build -t cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME .' 
    //sh 'echo $PASS | docker login -u $USER --password-stdin'       
    //sh 'docker push cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME'       
    }
}
        
def CommitToGit() {
  echo 'committing to git'
}

return this
