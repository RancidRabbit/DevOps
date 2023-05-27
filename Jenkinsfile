def gv
pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage("init"){
            steps {
                script {
                    gv = load "loader.groovy"
                }
            }
        }
        stage("test"){
            steps {
                script {
                   echo "testing app"
                   gv.testApp() 
                }
            }
        }
        stage("build") {
            when {
               expression {
                  BRANCH_NAME == "main"
               }
            }
            steps {
                script {
                    echo "building app"
                    gv.buildApp()
                }
            }
        }
        stage("build image") {
            when {
               expression {
                  BRANCH_NAME == "main"
               }
            }
            steps {
                script {
                    echo "building image"
                    gv.buildAndDeployImage()
                }
            }
        }
    }
}