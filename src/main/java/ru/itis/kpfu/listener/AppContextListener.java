package ru.itis.kpfu.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.kpfu.repositories.UsersRepository;
import ru.itis.kpfu.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.itis.kpfu.services.UsersService;
import ru.itis.kpfu.services.UsersServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class AppContextListener implements ServletContextListener {
    private HikariDataSource hikariDataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // initialize usersRepository and usersService
        Properties properties = new Properties();
        try {
            properties.load(AppContextListener.class.getResourceAsStream("/db.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }


        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.maxPoolSize")));
        hikariConfig.setUsername(properties.getProperty("db.username"));
        hikariConfig.setPassword(properties.getProperty("db.password"));
        hikariConfig.setDriverClassName(properties.getProperty("db.driverClassName"));
        hikariConfig.setJdbcUrl(properties.getProperty("db.url"));

        hikariDataSource = new HikariDataSource(hikariConfig);

        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(hikariDataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);

        sce.getServletContext().setAttribute("usersService", usersService);
//        sce.getServletContext().setAttribute("usersRepository", usersRepository);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        hikariDataSource.close();
    }
}
