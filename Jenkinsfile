pipeline {

agent any

environment {
        DOCKER_TOKEN=credentials('docker-push-secret')
        DOCKER_USER='tsadimas'
        DOCKER_SERVER='ghcr.io'
        DOCKER_PREFIX='ghcr.io/tsadimas/ds-lab-2024'
    }


stages {

    stage('Test') {
        steps {
           sh '''
                 echo "Start testing"
                ./mvnw test
           '''
        }
    }

      stage('Docker build and push') {
            steps {
                sh '''
                   cd services/backend
                   HEAD_COMMIT=$(git rev-parse --short HEAD)
                   TAG=$HEAD_COMMIT-$BUILD_ID
                   docker build --rm -t $DOCKER_PREFIX:$TAG -t $DOCKER_PREFIX:latest -f nonroot-multistage.Dockerfile .
                '''

                sh '''
                    echo $DOCKER_TOKEN | docker login $DOCKER_SERVER -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_PREFIX --all-tags
                '''
            }
        }
        
    }

}
}