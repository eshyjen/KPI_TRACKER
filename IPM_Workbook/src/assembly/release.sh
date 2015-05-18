BASE_DIR=$PWD
RELEASE_VERSION=CC
BINARY_FILE_NAME=1_19089-CXP9023919Uen-$RELEASE_VERSION.tar.gz
BINARY_DIR_NAME=1_19089-CXP9023919Uen-$RELEASE_VERSION
TEMP_DIR=temp_${BINARY_FILE_NAME}
BINARY_DIR=$BASE_DIR/target/Release/Binary


rm -rf $BASE_DIR/target/Release

mkdir $BASE_DIR/target/Release
mkdir $BINARY_DIR
#mkdir $BINARY_DIR/conf
#mkdir $BINARY_DIR/lib
mkdir $BINARY_DIR/$BINARY_DIR_NAME

mkdir $BINARY_DIR/$BINARY_DIR_NAME/conf


#sleep 2
echo $BASE_DIR
cd $BASE_DIR/target
md5sum sdgui.war > ${BINARY_DIR}/$BINARY_DIR_NAME/checksum
cp sdgui.war ${BINARY_DIR}/$BINARY_DIR_NAME/

cd $BASE_DIR
cp src/main/resources/Resources.properties $BINARY_DIR/$BINARY_DIR_NAME/conf/

cd ${BINARY_DIR}

tar -zcvf ${BINARY_FILE_NAME}  $BINARY_DIR_NAME
rm -rf $BINARY_DIR_NAME

cd ${BASE_DIR}


echo ""
echo "Package created at: $BASE_DIR/target/Release"
echo "Thank You!"
echo "==================================================="




