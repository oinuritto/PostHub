package ru.itis.kpfu.listener;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.kpfu.repositories.*;
import ru.itis.kpfu.repositories.impl.ImgInfoRepositoryJdbcTemplateImpl;
import ru.itis.kpfu.repositories.impl.LikesRepositoryJdbcTemplateImpl;
import ru.itis.kpfu.repositories.impl.PostsRepositoryJdbcTemplateImpl;
import ru.itis.kpfu.repositories.impl.UsersRepositoryJdbcTemplateImpl;
import ru.itis.kpfu.services.*;
import ru.itis.kpfu.services.impl.FilesServiceImpl;
import ru.itis.kpfu.services.impl.LikesServiceImpl;
import ru.itis.kpfu.services.impl.PostsServiceImpl;
import ru.itis.kpfu.services.impl.UsersServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener
public class AppContextListener implements ServletContextListener {
    private HikariDataSource hikariDataSource;
    private final String storagePath = "C:\\Users\\oinuritto\\IdeaProjects\\PostHub\\files\\";

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

        // users
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(hikariDataSource);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        sce.getServletContext().setAttribute("usersService", usersService);

        // files
        ImgInfoRepository imgInfoRepository = new ImgInfoRepositoryJdbcTemplateImpl(hikariDataSource);
        FilesService filesService = new FilesServiceImpl(storagePath, imgInfoRepository);
        sce.getServletContext().setAttribute("filesService", filesService);

        // posts
        PostsRepository postsRepository = new PostsRepositoryJdbcTemplateImpl(hikariDataSource);
        PostsService postsService = new PostsServiceImpl(postsRepository, filesService);
        sce.getServletContext().setAttribute("postsService", postsService);

        // likes
        LikesRepository likesRepository = new LikesRepositoryJdbcTemplateImpl(hikariDataSource);
        LikesService likesService = new LikesServiceImpl(likesRepository);
        sce.getServletContext().setAttribute("likesService", likesService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        hikariDataSource.close();
    }
}
