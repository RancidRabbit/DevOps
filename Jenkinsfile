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
                    dockerBuildImage "209.38.249.127:8083/jtest_${env.IMAGE_NAME}"
                    dockerLogin()
                    dockerPushImage "209.38.249.127:8083/jtest_${env.IMAGE_NAME}"
                }
            }
        }
        stage("push updated pom.xml") {
            steps {
                script {
                        withCredentials([usernamePassword(credentialsId: 'gh_token_for_jenkins', passwordVariable: 'PASS', usernameVariable: 'USER')]){

                        sh 'git config --global user.email "jenkins@gmail.com"'
                        sh 'git config --global user.name "jenkins"'

                        sh "git remote set-url origin https://${USER}:${PASS}@github.com/RancidRabbit/DevOps.git"
                        sh 'git add pom.xml'
                        sh 'git commit -m "updating version in pom.xml"'

                        sh 'git push origin HEAD:main'
                    }
                }
            }
        }
    }


}
