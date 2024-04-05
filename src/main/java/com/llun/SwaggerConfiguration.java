//package com.llun;
//
//import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//
//import jakarta.ws.rs.ApplicationPath;
//import jakarta.ws.rs.core.Application;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@ApplicationPath("/api")
//public class SwaggerConfiguration extends Application {
//
//    @Override
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new HashSet<>();
//        resources.add(OpenApiResource.class);
//        return resources;
//    }
//
//
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("RestHrPortal API")
//                        .version("1.0")
//                        .description("This is a sample RestHrPortal server.")
//                        .termsOfService("http://swagger.io/terms/")
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
//    }
//
//}