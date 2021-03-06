/*
* Copyright 2002 - 2017 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package eldorado.configuration;

import org.eldordoHomework.qa.common.gui.configuration.WebdriverConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({WebdriverConfig.class})
@ComponentScan("org.eldordoHomework.qa.common")
@PropertySources({
        @PropertySource("classpath:env/${NG_ENV:dev}.properties"),
        @PropertySource("classpath:core/webdrivermanager.properties")
})
public class AppConfig {
    @Autowired
    private Environment env;

    @Value("${rpEnabled:false}")
    private String reportPortalProviderEnabled;

    @Bean
    public Boolean reportPortalProviderEnabled() {
        return Boolean.valueOf(reportPortalProviderEnabled);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer PropertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
