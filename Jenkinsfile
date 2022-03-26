node('master') {
    def workspace = env.WORKSPACE
    def build_tag = env.BUILD_TAG

    def image = "${build_tag}-image"
    def container = "${build_tag}-container"
    def network = "hub_network"

    stage("Checkout Repository") {
        checkout scm
    }

    stage("Build Docker Image") {
        sh "docker build --tag ${image} --file '${workspace}/Dockerfile' '${workspace}' "
    }

    stage("Run Selenium Hub & Node") {
        sh "docker-compose -f docker-compose.yaml -p ${network} up -d --remove-orphans"
    }

    stage("Run Selenium Grid Hub and Scaling Node") {
        sh "docker-compose -p ${network} up --scale chrome=5 --remove-orphans -d"
    }
// --network=${JOB_NAME}_default
    stage("Execute Automation Tests") {
        def exitCode = sh script: "docker run -t --network=${network}_default --name ${container} ${image} mvn clean test", returnStatus: true
        if (exitCode == 1)
            currentBuild.result = "UNSTABLE"
    }

    stage("Remove Docker Image & Container") {
        sh "docker rm  ${container}"
        sh "docker rmi ${image} -f"
    }

    stage("Remove Selenium Hub & Node") {
        sh "docker-compose -p ${network} down --remove-orphans"
    }
}