Native and web mobile tests for Android and iOS
 
For starting cloud test you should put your EPAM mobile cloud token in token.txt file in project root library  
If you want to run native test in cloud, you should install application to cloud device.

Maven has next local profiles:  
Android Native  (mvn test -Pnative)  
Android Web     (mvn test -Pweb)  

and cloud profiles:
Android Native  (mvn test -PnativeCloud)  
Android Web     (mvn test -PwebCloud)  
iOS Native      (mvn test -PiosNativeCloud)  
iOS Web         (mvn test -PiosWebCloud)  
