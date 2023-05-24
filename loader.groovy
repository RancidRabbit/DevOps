def buildApp(){
    sh 'mvn package'
}

def buildAndDeployImage(){
    withCredentials([usernamePassword(credentialsId: 'jenkins-nexus', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t 209.38.249.127:8083/simple-java:1.1 .'
        sh "echo $PASSWORD | docker login -u $USERNAME --password-stdin 209.38.249.127:8083"
        sh 'docker push 209.38.249.127:8083/simple-java:1.1'
    }
}
return this