package ru.otus.todolist;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.PostgreSQL95Dialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.PostgresUUIDType;

public class PostgresUuidDialect extends PostgreSQL95Dialect {

    @Override
    public void contributeTypes(final TypeContributions typeContributions, final ServiceRegistry serviceRegistry) {
        super.contributeTypes(typeContributions, serviceRegistry);
        typeContributions.contributeType(new InternalPostgresUUIDType());
    }

    protected static class InternalPostgresUUIDType extends PostgresUUIDType {

        @Override
        protected boolean registerUnderJavaType() {
            return true;
        }
    }
}
