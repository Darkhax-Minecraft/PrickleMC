#!/usr/bin/env groovy

pipeline {

    agent any

    tools {
        jdk "jdk-21"
    }
    stages {
        stage('Setup') {
            steps {
                echo 'Setup Project'
                sh 'chmod +x gradlew'
                sh './gradlew clean'
            }
        }
        stage('Build') {
            steps {
                withCredentials([file(credentialsId: 'build_secrets', variable: 'ORG_GRADLE_PROJECT_secretFile')]) {
                    echo 'Building project.'
                    sh './gradlew build publish publishCurseForge modrinth postDiscord updateVersionTracker --stacktrace --warn'
                }
            }
        }
    }
}