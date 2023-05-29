#!/usr/bin/env groovy

@Library('shared-lib')_

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage("test") {
            steps {
                script {
                    echo "testing app"
                    mavenTestApp()
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
                    mavenBuildApp()
                }
            }
        }
        stage("build and push image") {
            when {
               expression {
                  BRANCH_NAME == "main"
               }
            }
            steps {
                script {
                    echo "building image"
                    dockerBuildImage '209.38.249.127:8083/simple-java:1.2'
                    dockerLogin()
                    dockerPushImage '209.38.249.127:8083/simple-java:1.2'
                }
            }
        }
    }
}
