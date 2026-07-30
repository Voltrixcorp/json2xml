# Evaulation app
Purpose of the application is to test OpenAPI implementation with
integration to a static xml service.
Translate the response from xml to json.

## Pre requisites
Java 21 and gradle 9

## Build and Run
```
.\gradlew clean build
.\gradlew bootRun
```

## Swagger
View the swagger doc http://localhost:8081/swagger-ui/index.html

Test the app via swagger or browser.

### Browser
Positive tests
http://localhost:8081/api/companies/1
```
{
 "id":"1",
 "name":"MWNZ",
 "description":"..is awesome"
}
```
http://localhost:8081/api/companies/2
```
{
 "id":"2",
 "name":"Other",
 "description":"....is not"
}
```

Negative test
http://localhost:8081/api/companies/9
```
{
    "id":null,
    "name":null,
    "description":"Range outside of allowed values"
}
```

### Runtime output

```
PS C:\WorkspaceJava21\jx> .\gradlew bootRun

> Task :bootRun

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v4.1.0)

2026-07-31T10:53:49.082+12:00  INFO 55532 --- [jx] [           main] com.middle.jx.JxApplication              : Starting JxApplication using Java 21.0.7 with PID 55532 (C:\WorkspaceJava21\jx\build\classes\java\main started by jonathan.diffenthal in C:\WorkspaceJava21\jx)
2026-07-31T10:53:49.087+12:00  INFO 55532 --- [jx] [           main] com.middle.jx.JxApplication              : No active profile set, falling back to 1 default profile: "default"
2026-07-31T10:53:49.789+12:00  INFO 55532 --- [jx] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat initialized with port 8081 (http)
2026-07-31T10:53:49.799+12:00  INFO 55532 --- [jx] [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2026-07-31T10:53:49.799+12:00  INFO 55532 --- [jx] [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/11.0.22]
2026-07-31T10:53:49.868+12:00  INFO 55532 --- [jx] [           main] b.w.c.s.WebApplicationContextInitializer : Root WebApplicationContext: initialization completed in 749 ms
2026-07-31T10:53:50.270+12:00  INFO 55532 --- [jx] [           main] o.s.v.b.OptionalValidatorFactoryBean     : Failed to set up a Bean Validation provider: jakarta.validation.NoProviderFoundException: Unable to create a Configuration, because no Jakarta Validation provider could be found. Add a provider like Hibernate Validator (RI) to your classpath.
2026-07-31T10:53:50.532+12:00  INFO 55532 --- [jx] [           main] .w.s.a.s.AnnotationActionEndpointMapping : Supporting [WS-Addressing August 2004, WS-Addressing 1.0]
2026-07-31T10:53:50.572+12:00  INFO 55532 --- [jx] [           main] o.s.boot.tomcat.TomcatWebServer          : Tomcat started on port 8081 (http) with context path '/'
2026-07-31T10:53:50.576+12:00  INFO 55532 --- [jx] [           main] com.middle.jx.JxApplication              : Started JxApplication in 1.793 seconds (process running for 2.275)
2026-07-31T10:54:02.529+12:00  INFO 55532 --- [jx] [nio-8081-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2026-07-31T10:54:02.529+12:00  INFO 55532 --- [jx] [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2026-07-31T10:54:02.532+12:00  INFO 55532 --- [jx] [nio-8081-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2026-07-31T10:54:02.586+12:00  INFO 55532 --- [jx] [nio-8081-exec-1] c.m.jx.controller.TranslateController    : Request company by 1
2026-07-31T10:54:03.426+12:00  INFO 55532 --- [jx] [nio-8081-exec-1] com.middle.jx.service.XmlStaticService   : XmlStaticService.callMiddle response callMiddle: <?xml version="1.0" encoding="UTF-8"?>
<Data>
        <id>1</id>
        <name>MWNZ</name>
        <description>..is awesome</description>
</Data>

2026-07-31T10:54:03.426+12:00  INFO 55532 --- [jx] [nio-8081-exec-1] c.m.jx.controller.TranslateController    : Response company by 1
2026-07-31T10:54:35.328+12:00  INFO 55532 --- [jx] [nio-8081-exec-2] c.m.jx.controller.TranslateController    : Request company by 2
2026-07-31T10:54:35.631+12:00  INFO 55532 --- [jx] [nio-8081-exec-2] com.middle.jx.service.XmlStaticService   : XmlStaticService.callMiddle response callMiddle: <?xml version="1.0" encoding="UTF-8"?>
<Data>
        <id>2</id>
        <name>Other</name>
        <description>....is not</description>
</Data>

2026-07-31T10:54:35.631+12:00  INFO 55532 --- [jx] [nio-8081-exec-2] c.m.jx.controller.TranslateController    : Response company by 2
```

