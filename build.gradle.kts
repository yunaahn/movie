plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm")
	kotlin("kapt") version "1.7.10"
	id("org.jetbrains.kotlin.plugin.spring") version "1.8.22"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.8.22"

	//kotlin("plugin.lombok") version "1.8.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
}

repositories {
	mavenCentral()
}

dependencies {



	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.apache.tomcat.embed:tomcat-embed-jasper")
	implementation("org.springframework.boot:spring-boot-starter-logging")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation(kotlin("stdlib-jdk8"))

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	// #. 스오플에 따르면 jdk8 쓰면 자바 11에서도 잘 동작한다고 함.
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.springframework.boot:spring-boot-starter-validation")


	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	//웹소켓
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("org.webjars:stomp-websocket:2.3.4")

	implementation ("org.webjars:sockjs-client:1.1.2")
	implementation ("org.webjars:stomp-websocket:2.3.3-1")
	implementation ("org.webjars.bower:axios:0.17.1")


	//롬복
	implementation("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

