BASE_DIR=$PWD
echo "==================================================="
echo "Welcome"
echo "You are about to clean and installing adapters Package"
cd ../../../../../adapters; mvn clean install

echo "Package compiled and installed successfully at $PWD" 

cd ${BASE_DIR}




