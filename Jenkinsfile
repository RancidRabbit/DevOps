#!/usr/bin/env groovy

@Library('shared-lib')_

pipeline {
    agent any
    environment {
        IMAGE_NAME = ''
    }
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
        stage("increment version") {
          when {
             expression {
                 BRANCH_NAME == "main"
             }
          }
           steps {
               script {
                  echo "incrementing version"
                  sh 'mvn build-helper:parse-version versions:set \
             -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.newIncrementalVersion} \
             versions:commit'
             def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
             def version = matcher[0][1]
             $IMAGE_NAME = "$version-$BUILD_NUMBER"
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
                    echo "building image ${IMAGE_NAME}"
                    dockerBuildImage "209.38.249.127:8083/${IMAGE_NAME}"
                    dockerLogin()
                    dockerPushImage "209.38.249.127:8083/${IMAGE_NAME}"
                }
            }
        }
    }
}
