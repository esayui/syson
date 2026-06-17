/*******************************************************************************
 * Copyright (c) 2024, 2026 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.syson;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * Configuration to force Simplified Chinese as the default locale.
 *
 * @author syson
 */
@Configuration
public class SysONLocaleConfiguration {

    @PostConstruct
    public void setDefaultLocale() {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
    }

    @Bean
    @Primary
    public Locale sysonDefaultLocale() {
        return Locale.SIMPLIFIED_CHINESE;
    }

    @Bean
    @Primary
    public LocaleResolver localeResolver() {
        return new AcceptHeaderLocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                return Locale.SIMPLIFIED_CHINESE;
            }

            @Override
            public Locale getDefaultLocale() {
                return Locale.SIMPLIFIED_CHINESE;
            }
        };
    }

    @Bean
    public FilterRegistrationBean<Filter> localeFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter((request, response, chain) -> {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            chain.doFilter(new HttpServletRequestWrapper(httpRequest) {
                @Override
                public String getHeader(String name) {
                    if ("Accept-Language".equalsIgnoreCase(name)) {
                        return "zh-CN,zh;q=0.9";
                    }
                    return super.getHeader(name);
                }

                @Override
                public Enumeration<String> getHeaders(String name) {
                    if ("Accept-Language".equalsIgnoreCase(name)) {
                        return Collections.enumeration(Collections.singletonList("zh-CN,zh;q=0.9"));
                    }
                    return super.getHeaders(name);
                }

                @Override
                public Locale getLocale() {
                    return Locale.SIMPLIFIED_CHINESE;
                }

                @Override
                public Enumeration<Locale> getLocales() {
                    return Collections.enumeration(Collections.singletonList(Locale.SIMPLIFIED_CHINESE));
                }
            }, response);
        });
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}
