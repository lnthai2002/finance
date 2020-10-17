module finance.web {
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter;
    requires spring.security.config;
    requires spring.web;

    requires finance.data;

    exports info.noip.darkportal.finance;
    exports info.noip.darkportal.finance.web.controller;
    opens info.noip.darkportal.finance;
}