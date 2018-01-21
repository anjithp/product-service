# bulletproof
Instructions for building the  Java modules:

1)Download the project to your computer
2)Install Java 1.8 and Maven 3/.5.2
3)Goto the root folder of the project and run the command "mvn clean install"

Instructions for building the  Angular UI module:

1)Download and install node.js on your computer.
2)Download and install Angular CLI(1.6.4).
3)Goto the UI module and run the command "npm install"

Installing Database:
1) Download Apache Solr from "http://apache.mirror.amaze.com.au/lucene/solr/7.2.1/"
2) Copy the products and stores schema to your computer.
3) Start solr server (Example: C:\Users\Anjith\Downloads\solr-7.2.1\bin>./solr start)
4) Create products core using the command "./solr create_core -c stores -d e:/worksoft/ew_practice/onlineshop/schema/products" (point to relevant directory).
5) Create stores core using the command "./solr create_core -c stores -d e:/worksoft/ew_practice/onlineshop/schema/stores" (point to relevant directory).


Starting Java Services:
1) Goto coreservices\target directory and run the command "java -jar coreservices-1.0.0.jar"

Starting UI:

1) UI can be started using Angular development server. Go to UI directory and run the command "ng serve" and access the UI on "http://localhost:4200"
2) Alternatively You can build the UI module using the command "ng build --prod --aot" and deploy resources to any web server.




