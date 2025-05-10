pipeline {

    agent any

    stages {
    
        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }
        
        stage('test connection to deploy env')
        steps {
            script {
                def result = sh(script: 'ssh -o StrictHostKeyChecking=no -o ConnectTimeout=5 -o BatchMode=yes -i ~/.ssh/id_rsa -p 22 user@host "echo hello"', returnStatus: true)
                if (result != 0) {
                    error("Failed to connect to the remote server")
                }
            }
        }steps {
            sh '''
                ansible -i workspace/ansible/hosts.yaml -m ping appserver-vm,dbserver-vm
            '''
        }
    }
}