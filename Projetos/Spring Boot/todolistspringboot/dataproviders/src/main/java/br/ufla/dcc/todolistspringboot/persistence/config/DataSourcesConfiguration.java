package br.ufla.dcc.todolistspringboot.persistence.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@PropertySource("classpath:/application.dataproviders.properties")
@EnableJpaRepositories(
        basePackages={
                "br.ufla.dcc.todolistspringboot",
        }
)
@EntityScan("br.ufla.dcc.todolistspringboot.persistence.entities")
public class DataSourcesConfiguration {
}