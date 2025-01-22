package aegis.server.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.metamodel.EntityType;
import org.hibernate.Session;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DataBaseCleaner implements InitializingBean {

    @PersistenceContext
    private EntityManager em;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableNames = em.getMetamodel().getEntities().stream()
                .map(EntityType::getName)
                .map(name -> name.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase())
                .collect(Collectors.toList());
    }

    public void clean() {
        em.unwrap(Session.class).doWork(this::doClean);
    }

    private void doClean(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("SET REFERENTIAL_INTEGRITY FALSE");
            for (String tableName : tableNames) {
                statement.executeUpdate("TRUNCATE TABLE " + tableName);
                statement.executeUpdate("ALTER TABLE " + tableName + " ALTER COLUMN " + tableName + "_ID RESTART WITH 1");
            }
            statement.executeUpdate("SET REFERENTIAL_INTEGRITY TRUE");
        }
    }
}
