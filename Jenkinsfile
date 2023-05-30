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
             -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
             versions:commit'
             def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
             def version = matcher[0][1]
             env.IMAGE_NAME = "$version-${env.BUILD_NUMBER}"
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
            steps {
                script {
                    echo "IMAGE_NAME = ${env.IMAGE_NAME}"
                }
            }
        }
    }
}
