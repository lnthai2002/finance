open module finance.data {
    requires spring.boot;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter;
    exports info.noip.darkportal.finance.model;
    exports info.noip.darkportal.finance.repository;
    exports info.noip.darkportal.finance.repository.map;
}
