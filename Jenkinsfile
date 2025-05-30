pipeline {

agent any

environment {
        DOCKER_TOKEN=credentials('docker-push-secret')
        DOCKER_USER='tsadimas'
        DOCKER_SERVER='ghcr.io'
        DOCKER_PREFIX='ghcr.io/tsadimas/ds-lab-2024'
    }


stages {


    stage('run ansible pipeline') {
        steps {
            build job: 'ansible'
        }
    }

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

    

    stage('deploy to kubernetes') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -e new_image=$DOCKER_PREFIX:$TAG ~/workspace/ansible/playbooks/k8s-update-spring-deployment.yaml
                '''
            }
        }

//    post {
//        always {
//            mail  to: "tsadimas@gmail.com", from: "tsadimas@gmail.com", body: "Project ${env.JOB_NAME} <br>, Build status ${currentBuild.currentResult} <br> Build Number: ${env.BUILD_NUMBER} <br> Build URL: ${env.BUILD_URL}", subject: "JENKINS: Project name -> ${env.JOB_NAME}, Build -> ${currentBuild.currentResult}"
//        }
//    }




}