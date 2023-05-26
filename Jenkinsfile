#!/usr/bin/env groovy

@Library('jenkins-shared-library')
def gv
pipeline {
    agent any
    tools {
        maven 'maven-3.9.2'
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
//            when {
//               expression {
//                  BRANCH_NAME == "main"
//               }
//            }
            steps {
                script {
                    echo "building app"
                    buildApp()
                }
            }
        }
        stage("build image") {
//            when {
//               expression {
//                  BRANCH_NAME == "main"
//               }
//            }
            steps {
                script {
                    echo "building image"
                    buildAndDeployImage()
                }
            }
        }
    }
}