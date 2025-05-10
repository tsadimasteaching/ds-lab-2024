pipeline {

    agent any

    stages {
    
        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }

        stage('test connection to deploy env') {
        steps {
            sh '''
                ansible -i ~/workspace/ansible/hosts.yaml -m ping appserver-vm,dbserver-vm
            '''
        }
    }
}
}