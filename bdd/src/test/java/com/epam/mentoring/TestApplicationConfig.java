package com.epam.mentoring;

import org.springframework.context.annotation.*;

/**
 * Created by Aleksandr_Ruzanov on 07.03.2017.
 */
@Configuration
@ComponentScan(value = {"com.epam.mentoring"})
@PropertySource(value = {"classpath:application.properties"}, ignoreResourceNotFound = true)
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class TestApplicationConfig {
}
