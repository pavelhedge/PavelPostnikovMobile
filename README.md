Repo contains two groups of mobile tests.

For starting local test you should change URL in Surefire plugin settings in pom.xml.
For starting cloud test you should put your token in 'token' property in pom.xml.

Maven has next local profiles:
Android Native  (mvn test -Pnative)
Android Web     (mvn test -Pweb)

and cloud profiles:
Android Native  (mvn test -PnativeCloud)
Android Web     (mvn test -PwebCloud)
iOS Native      (mvn test -PiosNativeCloud)
iOS Web         (mvn test -PiosWebCloud)
