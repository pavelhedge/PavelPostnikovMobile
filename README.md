Native and web mobile tests for Android and iOS

For starting local test you should change URL in Surefire plugin settings in pom.xml  
For starting cloud test you should put your EPAM mobile cloud token in 'token' property in pom.xml  
If you want to run native test in cloud, you should install application to cloud device.

Maven has next local profiles:  
Android Native  (mvn test -Pnative)  
Android Web     (mvn test -Pweb)  

and cloud profiles:
Android Native  (mvn test -PnativeCloud)  
Android Web     (mvn test -PwebCloud)  
iOS Native      (mvn test -PiosNativeCloud)  
iOS Web         (mvn test -PiosWebCloud)  
