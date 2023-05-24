def gv
pipeline {
    agent any

    stages {
        stage("init"){
            steps {
                script {
                    gv = load "loader.groovy"
                }
            }
        }
        stage("testing") {
            steps {
                script {
                    echo "testing App"
                    gv.testApp()
                }
            }
        }
        stage("build") {
            steps {
                script {
                    echo "building app"
                    gv.buildApp()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "building image"
                    gv.buildAndDeployImage()
                }
            }
        }
    }
}