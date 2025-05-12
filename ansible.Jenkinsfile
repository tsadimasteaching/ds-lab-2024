pipeline {

    agent any

    parameters {
        booleanParam(name: 'INSTALL_POSTGRES', defaultValue: true, description: 'Install PostgreSQL')
        booleanParam(name: 'INSTALL_SPRING', defaultValue: true, description: 'Install Spring Boot app')
    }

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
        
        stage('Install postgres') {
             when {
                expression { return params.INSTALL_POSTGRES }
            }
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l dbserver-vm ~/workspace/ansible/playbooks/postgres-16.yaml
                '''
            }
        }

        stage('install springboot') {
             when {
                expression { return params.INSTALL_SPRING }
            }
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l appserver-vm ~/workspace/ansible/playbooks/spring.yaml
                '''
            }
        }
}
}