package org.tyaa.demo.java.spring.gae;

import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import org.tyaa.demo.java.spring.gae.models.Author;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class ObjectifyListener implements ServletContextListener {

    private static final Logger log =
            Logger.getLogger(ObjectifyListener.class.getName());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            // пытаемся считать из файла appengine-web.xml
            // пользовательской переменной окружения SPRING_PROFILES_ACTIVE
            if (System.getenv("SPRING_PROFILES_ACTIVE") == null) {
                // если переменная отсутствует - выполняем настройку подключения
                // к локальному эмулятору Google DataStore
                // через оболочку Objectify
                ObjectifyService.init(new ObjectifyFactory(
                        DatastoreOptions.newBuilder()
                                .setHost("http://localhost:8484")
                                .setProjectId("spring-pv021")
                                .build()
                                .getService()
                ));
            }
            else {
                // иначе - выполняем настройку подключения
                // к удаленному Google DataStore
                ObjectifyService.init(new ObjectifyFactory(
                        DatastoreOptions.getDefaultInstance().getService()
                ));
            }
        } catch (Exception ex) {
            log.log(Level.SEVERE, ex.getMessage());
        }
        // регистрация всех классов моделей (сущностей),
        // объекты которых разрешается читать/записывать в хранилище
        ObjectifyService.register(Author.class);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
