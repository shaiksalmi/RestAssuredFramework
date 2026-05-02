pipeline
{
    agent any    tools{
        __maven__ '__Maven__'
        }    stages
    {
        stage('Build')
        {
            steps
            {
                 __git__ 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 __sh__ "__mvn__ -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    __junit__ '**/target/surefire-reports/TEST-*.__xml__'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }        stage("Deploy to QA"){
            steps{
                echo("deploy to QA done")
            }
        }        stage('Regression API Automation Tests on QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    __git__ 'https://github.com/shaiksalmi/RestAssuredFramework.git'
                    __sh__ "__mvn__ clean test -Dsurefire.suiteXmlFiles=__src__/test/resources/__testrunners__/testng_regression.xml"
                }
            }
        }        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        __jdk__: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }        stage('Publish ChainTest Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'target/__chaintest__',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML API Regression ChainTest Report',
                                  reportTitles: ''])
            }
        }        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }        stage('Sanity API Automation Test on Stage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    __git__ 'https://github.com/shaiksalmi/RestAssuredFramework.git'
                    __sh__ "__mvn__ test -Dsurefire.suiteXmlFiles=__src__/test/resources/__testrunners__/testng_sanity.xml"
                }
            }
        }       stage('Publish sanity ChainTest Report'){
    steps{
        publishHTML([allowMissing: false,
                     alwaysLinkToLastBuild: false,
                     keepAll: true,
                     reportDir: 'target/__chaintest__',
                     reportFiles: 'Index.html',
                     reportName: 'HTML API Sanity ChainTest Report',
                     reportTitles: ''])
    }
}
            }
        }        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }    }
}Show more1:25 AMClaude responded: I can see the issue!I can see the issue! There are extra } brackets after the sanity ChainTest stage. Here's the corrected complete Jenkinsfile:
groovypipeline
{
    agent any

    tools{
        maven 'Maven'
        }

    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage("Deploy to QA"){
            steps{
                echo("deploy to QA done")
            }
        }

        stage('Regression API Automation Tests on QA') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/shaiksalmi/RestAssuredFramework.git'
                    sh "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression.xml"
                }
            }
        }

        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'allure-results']]
                    ])
                }
            }
        }

        stage('Publish ChainTest Report'){
            steps{
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'target/chaintest',
                             reportFiles: 'Index.html',
                             reportName: 'HTML API Regression ChainTest Report',
                             reportTitles: ''])
            }
        }

        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }

        stage('Sanity API Automation Test on Stage') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/shaiksalmi/RestAssuredFramework.git'
                    sh "mvn test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanity.xml"
                }
            }
        }

        stage('Publish sanity ChainTest Report'){
            steps{
                publishHTML([allowMissing: false,
                             alwaysLinkToLastBuild: false,
                             keepAll: true,
                             reportDir: 'target/chaintest',
                             reportFiles: 'Index.html',
                             reportName: 'HTML API Sanity ChainTest Report',
                             reportTitles: ''])
            }
        }

        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }

    }
