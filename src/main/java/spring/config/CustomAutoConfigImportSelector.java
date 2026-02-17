package spring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class CustomAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "spring.config.autoconfig.DispatcherServletConfig",
                "spring.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
