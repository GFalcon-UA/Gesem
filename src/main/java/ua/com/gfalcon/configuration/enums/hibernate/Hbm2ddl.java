package ua.com.gfalcon.configuration.enums.hibernate;

/**
 * Enum for set the hibernate.hbm2ddl.auto
 * {@link Hbm2ddl#VALIDATE} validate the schema, makes no changes to the database.
 * {@link Hbm2ddl#UPDADE} update the schema.
 * {@link Hbm2ddl#CREATE} creates the schema, destroying previous data.
 * {@link Hbm2ddl#CREATE_DROP} (create-drop) drop the schema at the end of the session.
 *
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Hbm2ddl {
    VALIDATE("validate"), UPDADE("update"), CREATE("create"), CREATE_DROP("create-drop");

    private String strategy;

    Hbm2ddl(String strategy) {
        this.strategy = strategy;
    }

    public String getValue() {
        return this.strategy;
    }
}
