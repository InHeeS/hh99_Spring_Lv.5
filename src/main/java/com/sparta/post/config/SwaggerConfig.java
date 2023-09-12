//package com.sparta.post.config;
//
//import jdk.javadoc.doclet.Doclet;
//import jdk.javadoc.doclet.DocletEnvironment;
//import jdk.javadoc.doclet.Reporter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.lang.model.SourceVersion;
//import java.util.Locale;
//import java.util.Set;
//
//@Configuration
//public class SwaggerConfig {
//    @Bean
//    public Doclet api(){
//        return new Doclet(DocumentationType.OAS_30) {
//            @Override
//            public void init(Locale locale, Reporter reporter) {
//
//            }
//
//            @Override
//            public String getName() {
//                return null;
//            }
//
//            @Override
//            public Set<? extends Option> getSupportedOptions() {
//                return null;
//            }
//
//            @Override
//            public SourceVersion getSupportedSourceVersion() {
//                return null;
//            }
//
//            @Override
//            public boolean run(DocletEnvironment environment) {
//                return false;
//            }
//        }
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo apiInfo(){
//        return new ApiInfoBuilder()
//                .title("test")
//                .description("description")
//                .version("1.0.0")
//                .build();
//    }
//}