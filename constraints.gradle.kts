dependencies {
    constraints {
        // 🔐 Spring Framework / Security (DoS, Auth Bypass, Password length)
        implementation("org.springframework:spring-web:6.1.6")
        implementation("org.springframework:spring-context:6.1.6")
        implementation("org.springframework.security:spring-security-web:6.2.3")
        implementation("org.springframework.security:spring-security-core:6.2.3")
        implementation("org.springframework.security:spring-security-crypto:6.2.3")

        // 🛡️ Jetty (SpringDoc, Swagger, etc.)
        implementation("org.eclipse.jetty:jetty-server:11.0.20")
        implementation("org.eclipse.jetty:jetty-http:11.0.20")
        implementation("org.eclipse.jetty:jetty-util:11.0.20")

        // ⚡ Netty (WebFlux internals, DoS vuln)
        implementation("io.netty:netty-common:4.1.110.Final")
        implementation("io.netty:netty-handler:4.1.110.Final")

        // 🔐 Nimbus JOSE JWT (DoS via parsing)
        implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")

        // ⚠️ Logback-core (EL Injection + CSRF via config)
        implementation("ch.qos.logback:logback-core:1.4.14")
        implementation("ch.qos.logback:logback-classic:1.4.14")

        // 🔒 Bouncy Castle (Marvin attack, DNS poisoning, Infinite loop, CPU usage)
        implementation("org.bouncycastle:bcprov-jdk18on:1.78.1")

        // ⚠️ Apache Commons
        implementation("commons-beanutils:commons-beanutils:1.9.4")
        implementation("commons-io:commons-io:2.15.0")

        // 🌐 Apache HTTP Client 5
        implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")

        // 📨 ActiveMQ Artemis
        implementation("org.apache.activemq:artemis-core-client:2.31.2")

        // 🧪 XMLUnit (Insecure defaults for XSLT)
        implementation("org.xmlunit:xmlunit-core:2.9.1") // última versión estable
    }
}
