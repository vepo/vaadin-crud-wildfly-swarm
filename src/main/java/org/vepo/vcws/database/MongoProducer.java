package org.vepo.vcws.database;

import org.jnosql.artemis.ConfigurationUnit;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.mongodb.document.MongoDBDocumentCollectionManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * MongoProducer. Used for JNoSQL
 * 
 * @author Victor Osório
 */
@ApplicationScoped
public class MongoProducer {
    private static final String DATABASE = "database";

    @Inject
    @ConfigurationUnit(name = "document", fileName = "jnosql.yaml")
    private DocumentCollectionManagerFactory<MongoDBDocumentCollectionManager> entityManager;

    @Produces
    public DocumentCollectionManager getManager() {
        return entityManager.get(DATABASE);
    }
}