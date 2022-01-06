pipeline {
    agent any
    tools {
        maven 'maven3'
    }
    stages{
        stage('Build'){
            steps{
                sh script: 'mvn clean pachage'
            }

        }
        stage('Upload War To Nexus'){
            steps{
                nexusArtifactUploader artifacts: [
                    [artifactId: 'demo', classifier: '', 
                    file: 'target/demo-1.0.war', type: 'war'
                    ]
                    ], credentialsId: 'ea101652-db7d-419c-a5ee-d086ad0d70a1',
                     groupId: 'com.example', nexusUrl: '172.19.9.28:8081',
                      nexusVersion: 'nexus3', protocol: 'http', repository: 'simpleapp-release', version: '1.0'
            }
        }
    }
}