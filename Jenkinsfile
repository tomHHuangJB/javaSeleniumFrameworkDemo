pipeline {
    agent any
    options {
        timestamps()
    }
    parameters {
        string(name: "BASE_URL", defaultValue: "http://host.docker.internal:5173", description: "LocalAutomationApp base URL.")
        string(name: "BROWSER", defaultValue: "chrome", description: "Browser name (chrome recommended).")
        string(name: "HEADLESS", defaultValue: "true", description: "Run browser in headless mode (true/false).")
        string(name: "REMOTE_URL", defaultValue: "http://selenium:4444/wd/hub", description: "Remote Selenium URL.")
        booleanParam(name: "USE_APP_PROFILE", defaultValue: false, description: "Use LocalAutomationApp from docker compose --profile app.")
    }
    environment {
        BASE_URL = "${params.BASE_URL}"
        BROWSER = "${params.BROWSER}"
        HEADLESS = "${params.HEADLESS}"
        REMOTE_URL = "${params.REMOTE_URL}"
    }
    stages {
        stage("Resolve Base URL") {
            steps {
                script {
                    if (params.USE_APP_PROFILE) {
                        env.BASE_URL = "http://local-frontend:5173"
                    }
                }
            }
        }
        stage("Tests") {
            steps {
                sh "mvn -q test -Dbase.url=${BASE_URL} -Dbrowser=${BROWSER} -Dheadless=${HEADLESS} -Dremote.url=${REMOTE_URL}"
            }
        }
    }
    post {
        always {
            junit "target/surefire-reports/*.xml"
            archiveArtifacts artifacts: "target/surefire-reports/**", fingerprint: true, allowEmptyArchive: true
        }
    }
}
