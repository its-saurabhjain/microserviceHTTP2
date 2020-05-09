Enabling http/2 for Tomcat 9.0 and microservice.
https://stackoverflow.com/questions/49324700/enable-https-with-self-signed-certificate-in-spring-boot-2-0
http://gewton.nfshost.com/how-to-avoid-java-security-cert-certificateexception-no-name-matching-localhost-found/

https://howtodoinjava.com/spring-boot/spring-boot-ssl-https-example/


keygeneration
keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
Add the certificate to keystore JDK13
keytool -import -alias tomcat -keystore  "C:\Program Files\Java\jdk-13.0.1\lib\security\cacerts" -file keystore.cer 

keytool -delete -alias tomcat -keystore "C:\Program Files\Java\jre-9.0.4\lib\security\cacerts"
keytool -list -v -keystore "C:\Program Files\Java\jdk-9.0.4\lib\security\cacerts"  > java_cacerts_jdk.txt

http://magicmonster.com/kb/prg/java/ssl/pkix_path_building_failed.html
https://myshittycode.com/2015/12/17/java-https-unable-to-find-valid-certification-path-to-requested-target-2/
https://stackoverflow.com/questions/34655031/javax-net-ssl-sslpeerunverifiedexception-host-name-does-not-match-the-certifica

https://learnk8s.io/kubernetes-long-lived-connections
https://developers.redhat.com/blog/2017/11/22/dynamically-creating-java-keystores-openshift/

https://stackoverflow.com/questions/33887194/how-to-set-multiple-commands-in-one-yaml-file-with-kubernetes

docker image-frontendsvc:ocp43 is for only trust store
docker image-frontendsvc:ocp43 for keystore and trust store

**** Important - OCP route TLS re-encrypt whill alwyas http/1.1, change to passthrough and it will be h2 
