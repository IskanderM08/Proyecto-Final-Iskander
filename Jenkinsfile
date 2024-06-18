pipeline {
    agent any

    environment {
        // Define el nombre de la imagen Docker
        DOCKER_IMAGE = 'iskanderm08/api_iskander'
        // Define el nombre del contenedor Docker
        CONTAINER_NAME = 'api-iskander-container'
    }

    stages {
        stage('Checkout') {
            steps {
                // Clona el repositorio desde GitHub
                git branch: 'main', url: 'https://github.com/IskanderM08/Proyecto-Final-Iskander.git'
            }
        }

        stage('Build') {
            steps {
                // Compila la aplicación con Maven
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Ejecuta las pruebas unitarias con Maven
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                // Construye la imagen Docker
                script {
                    docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}")
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    // Realiza login en DockerHub utilizando las credenciales configuradas en Jenkins
                    docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
                        // Empuja la imagen a DockerHub
                        docker.image("${DOCKER_IMAGE}:${env.BUILD_ID}").push()
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // Para y elimina el contenedor anterior si está en ejecución
                    sh """
                    if [ \$(docker ps -q -f name=${CONTAINER_NAME}) ]; then
                        docker stop ${CONTAINER_NAME} && docker rm ${CONTAINER_NAME}
                    fi
                    """

                    // Inicia el nuevo contenedor con la imagen recién construida
                    sh """
                    docker run -d --name ${CONTAINER_NAME} -p 8080:8080 ${DOCKER_IMAGE}:${env.BUILD_ID}
                    """
                }
            }
        }
    }

    post {
        success {
            echo '¡Despliegue exitoso!'
        }
        failure {
            echo 'Falló el despliegue...'
        }
    }
}