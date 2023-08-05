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
                    bat 'cd project-app && mvn sonar:sonar'
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
