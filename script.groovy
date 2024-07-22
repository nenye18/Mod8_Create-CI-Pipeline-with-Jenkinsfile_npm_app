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
}

def buildImage() {
    echo 'building the image'
}

def CommitToGit() {
  echo 'committing to git'
}

return this
