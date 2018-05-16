# Weather Broker client

Клиентская часть приложения "Погодный брокер".

На стартовой JSP-странице в соответствующем поле вводится название города.
После нажатия кнопки `Submit` осуществляется поиск погоды по заданному городу
посредством сервиса `weather.yahoo.com`.

Полученный результат парсится и отправляется в JMS-topic, настроенный на Application Server.
 В качестве сервера приложений выступает WildFly 11.0.0

#
**Деплой и запуск тестов** 

N.B. для успешного выполнения тестов необходим запущенный локально ActiveMQ Message Broker.

Приложение собирается с помощью Apache Maven.

Для деплоя приложения на сервер используется плагин `wildfly-maven-plugin`.
  Деплой выполняется командой `mvn wildfly:deploy`; перед развертыванием
  приложения на сервере выполнятся тесты.

Для запуска тестов _отдельно_ необходимо заменить в `pom.xml` зависимость:
 
   	<dependency>
   			<groupId>org.springframework.boot</groupId>
   			<artifactId>spring-boot-starter-web</artifactId>
   	</dependency>
  
  на
  
      	<dependency>
      			<groupId>org.springframework.boot</groupId>
      			<artifactId>spring-boot-starter-web</artifactId>
      			<exclusions>
      				<exclusion>
      					<groupId>org.springframework.boot</groupId>
      					<artifactId>spring-boot-starter-tomcat</artifactId>
      				</exclusion>
      			</exclusions>
      	</dependency>
  
  и выполнить команду `mvn verify -Ptest`
  
  **Выход в браузер**
 
 После развертывания приложения на сервере, оно будет доступно по адресу
  `127.0.0.1:8080/weatherclient`.