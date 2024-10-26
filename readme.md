chmod +x create_project_structure.sh &&\
bash ./create_project_structure.sh

export TEST_DIR="src/test/java/com/company/automation" &&\
mkdir -p ${TEST_DIR}/service &&\
mkdir -p ${TEST_DIR}/controller &&\
mkdir -p ${TEST_DIR}/advice &&\
touch ${TEST_DIR}/advice/GlobalExceptionHandlerTest.java &&\
touch ${TEST_DIR}/controller/ArtifactoryControllerTest.java &&\
touch ${TEST_DIR}/service/ArtifactoryServiceTest.java