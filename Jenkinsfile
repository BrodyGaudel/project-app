pipeline {
    agent any
    
    environment {
        MAVEN_HOME = "C:\\Users\\brody\\Applications\\apache-maven-3.9.2"
        PATH = "${env.PATH};${env.MAVEN_HOME}\\bin"
    }
    
    stages {
        stage('Checkout') {
            steps {
                // Récupérer le code depuis GitHub
                bat 'git clone https://github.com/BrodyGaudel/project-app.git'
            }
        }
        
        stage('Build and Test') {
            steps {
                // Compiler le projet avec Maven
                bat 'cd project-app && mvn clean package'
                
                // Exécuter les tests unitaires
                bat 'cd project-app && mvn test'
            }
        }
        
        stage('SonarQube Analysis') {
            steps {
                // Analyser le code avec SonarQube
                withSonarQubeEnv('SonarQubeServer') {
                    bat "cd project-app && mvn sonar:sonar -Dsonar.projectKey=e-bank -Dsonar.projectName='e-bank'"
                }
            }
        }
        
        stage('Push to Nexus') {
            steps {
                // Pousser l'artefact JAR vers le référentiel Nexus
                withCredentials([usernamePassword(credentialsId: 'nexus-credentials', usernameVariable: 'NEXUS_USERNAME', passwordVariable: 'NEXUS_PASSWORD')]) {
                    bat 'cd project-app && mvn deploy -Dmaven.deploy.username=%NEXUS_USERNAME% -Dmaven.deploy.password=%NEXUS_PASSWORD%'
                }
            }
        }
        
        stage('Docker Build') {
            steps {
                // Construire une image Docker
                script {
                    def dockerImage = docker.build("mon-projet:%BUILD_NUMBER%")
                }
            }
        }
    }
    
    post {
        always {
            // Nettoyer et libérer les ressources
            cleanWs()
        }
    }
}
