package spring.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import java.util.ArrayList;
import java.util.List;

public class CustomAutoConfigImportSelector implements DeferredImportSelector, BeanClassLoaderAware {
    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();

        // @CustomAutoConfiguration select
        ImportCandidates.load(CustomAutoConfiguration.class, classLoader)
                .forEach(candidate -> autoConfigs.add(candidate));

        // type safe
        return autoConfigs.toArray(new String[0]);
//        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
//        return autoConfigs.stream().toArray(String[]::new);
    }
}
