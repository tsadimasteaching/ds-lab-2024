pipeline {

agent any
stages {

    stage('Test') {
        steps {
           sh '''
                 echo "Start testing"
                ./mvn test
           '''
        }
    }

}