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
    echo 'building the docker image...'
        withCredentials([usernamePassword(credentialsId: 'docker hub repository',passwordVariable: 'PASS', usernameVariable: 'USER')]){
        sh 'docker build -t cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME .' 
        sh 'echo $PASS | docker login -u $USER --password-stdin'       
        sh 'docker push cnwagba/jenkins-repo-dockerhub:$IMAGE_NAME'       
    }
}
        
def CommitToGit() {
    echo 'commit new version to git'
    withCredentials([usernamePassword(credentialsId: 'GitHub credentials',passwordVariable: 'PASS', usernameVariable: 'USER')]){
    sh 'git config --global user.email "chinenye.nw@gmail.com"                     
    sh 'git config --global user.name "nenye18" '
    sh 'git remote set-url origin https://$USER:$PASS@github.com/nenye18/Jenkins-project.git'
    sh 'git add .'
    sh 'git commit -m "commiting version update from jenkins CI/CD" '
    sh 'git push origin HEAD:main'
    }
}

return this
