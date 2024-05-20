plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm")
	kotlin("kapt") version "1.8.21"
	id("org.jetbrains.kotlin.plugin.spring") version "1.8.22"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.8.22"
	idea
//	id("com.ewerk.gradle.plugins.querydsl") version "1.0.10" // QueryDSL 플러그인

	//kotlin("plugin.lombok") version "1.8.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

allOpen {
	// Spring Boot 3.0.0
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

dependencies {
	val querydslVersion = "5.0.0"


	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.apache.tomcat.embed:tomcat-embed-jasper")
	implementation("org.springframework.boot:spring-boot-starter-logging")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")

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
	implementation ("org.webjars.bower:axios:0.21.1")


	//롬복
	implementation("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	//타임리프
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")

	//레디스
	//implementation ("org.springframework.data:spring-data-redis:3.1.5")
	implementation ("org.springframework.boot:spring-boot-starter-cache:3.1.2")

	//내장 레디스
	implementation ("org.springframework.boot:spring-boot-starter-data-redis")

	//로그
	//implementation ("org.springframework.boot:spring-boot-starter-log4j2") // 필요시 Log4j2 사용

	//query dsl
//	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta") //jakarta 붙여야 config 파일 설정시 에러안남
//	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta") // 설정해야 Qclass가 생김......
//
//	annotationProcessor ("com.querydsl:querydsl-apt:5.0.0:jakarta")
//	annotationProcessor ("jakarta.annotation:jakarta.annotation-api")
//	annotationProcessor ("jakarta.persistence:jakarta.persistence-api")
//	api("com.querydsl:querydsl-jpa")
//	implementation ("com.querydsl:querydsl-jpa:5.0.0:jakarta")
//	kapt ("com.querydsl:querydsl-apt:5.0.0:jakarta")
//	kapt ("jakarta.annotation:jakarta.annotation-api")
//	kapt ("jakarta.persistence:jakarta.persistence-api")


	implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
	kapt("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")

	//MaridDB
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	// Swagger docs openapi
	//implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
	implementation("io.github.microutils:kotlin-logging:3.0.5")
	implementation("org.hamcrest:hamcrest-core:1.3")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

	//build.gradle dependency에 추가

	implementation ("org.springframework.boot:spring-boot-starter-security")

	//JWT
	implementation ("io.jsonwebtoken:jjwt-api:0.12.3")
	implementation ("io.jsonwebtoken:jjwt-impl:0.12.3")
	implementation ("io.jsonwebtoken:jjwt-jackson:0.12.3")

}

kapt {
	javacOptions {
		option("querydsl.entityAccessors", true)
	}
	arguments {
		arg("plugin", "com.querydsl.apt.jpa.JPAAnnotationProcessor")
	}
}




tasks.withType<Test> {
	useJUnitPlatform()
}

val generated = "src/main/generated"


sourceSets {
	main {
		java {
			srcDir(generated)
		}
	}
}

tasks.named("clean") {
	doFirst {
		delete(file(generated))
	}
}


