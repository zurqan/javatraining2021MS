package com.example.sampleconfig.configuration;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

//@Order(Integer.MIN_VALUE)
public class ResourceSpringInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private ConfigurableApplicationContext applicationContext;
    private final String DEFAULT_CONFIG_FOLDERS= "config/**/*.yml,config/**/*.properties";


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        this.applicationContext = applicationContext;

        initializeConfigFiles();

    }

    private void initializeConfigFiles() {
        System.out.println("ResourceSpringInitializer.initializeConfigFiles");
        Optional<String> configFoldersOpt=getConfigFolders();
        String configFolders = configFoldersOpt
                .orElse(DEFAULT_CONFIG_FOLDERS);

        Stream.of(configFolders.split(","))
                .map(f->f.trim())
                .forEachOrdered(f-> {
                    try {
                        loadConfigFiles(f);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void loadConfigFiles(String folderName) throws IOException {

        Resource[] resources = applicationContext.getResources("classpath:"+folderName);


        if(resources==null) return;

        loadYamls(resources);

        loadPropertiesFiles(resources);

    }

    private void loadPropertiesFiles(Resource[] resources) {
        Stream.of(resources)
                .filter(rs->rs.getFilename().endsWith(".properties"))
                .peek(r-> System.out.println(r.getFilename()))
                .map(r-> {
                    try {
                        Properties properties = new Properties();
                         properties.load(new InputStreamReader(r.getInputStream(),"UTF-8"));
                        return new PropertiesPropertySource(r.getFilename(),properties);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    } })
                .filter(Objects::nonNull)
                .forEachOrdered(ps->applicationContext.getEnvironment().getPropertySources().addFirst(ps));
    }

    private void loadYamls(Resource[] resources) {
        YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

        Stream.of(resources)
                .filter(rs->rs.getFilename().endsWith("yml"))
                .peek(r-> System.out.println(r.getFilename()))
                .flatMap(r-> {
                    try {
                        return yamlPropertySourceLoader.load(r.getFilename(), r).stream();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Stream.empty();
                    } })
                .forEachOrdered(ps->applicationContext.getEnvironment().getPropertySources().addFirst(ps));
    }

    private Optional<String> getConfigFolders() {

        String configFolders = applicationContext.getEnvironment().getProperty("training.config-folders");
        return Optional.ofNullable(configFolders);
    }
}
