package org.zof.cqrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.zof.cqrs.event.CommandListener;
import org.zof.cqrs.utility.GeneralConstants;
import reactor.Environment;
import reactor.bus.EventBus;

import static reactor.bus.selector.Selectors.$;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ZofCqrsApplication implements CommandLineRunner {

    @Autowired
    private EventBus eventBus;

    @Autowired
    private CommandListener commandListener;

    @Bean
    Environment env() {
        return Environment.initializeIfEmpty().assignErrorJournal();
    }

    @Bean
    EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.THREAD_POOL);
    }

    @Override
    public void run(String... args) throws Exception {
        eventBus.on($(GeneralConstants.TRANSACTION_CONSUMER), commandListener);
    }

    public static void main(String[] args) {
        SpringApplication.run(ZofCqrsApplication.class, args);
    }
}
