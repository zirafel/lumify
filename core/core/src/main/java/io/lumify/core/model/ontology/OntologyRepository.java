package io.lumify.core.model.ontology;

import io.lumify.core.security.LumifyVisibility;
import io.lumify.web.clientapi.model.ClientApiOntology;
import org.json.JSONArray;
import org.json.JSONException;
import org.securegraph.Authorizations;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import java.io.File;
import java.io.OutputStream;
import java.util.List;

public interface OntologyRepository {
    public static final String ENTITY_CONCEPT_IRI = "http://www.w3.org/2002/07/owl#Thing";
    public static final String ROOT_CONCEPT_IRI = "http://lumify.io#root";
    public static final String TYPE_RELATIONSHIP = "relationship";
    public static final String TYPE_CONCEPT = "concept";
    public static final String TYPE_PROPERTY = "property";
    public static final String VISIBILITY_STRING = "ontology";
    public static final LumifyVisibility VISIBILITY = new LumifyVisibility(VISIBILITY_STRING);

    void clearCache();

    Iterable<Relationship> getRelationships();

    Iterable<OntologyProperty> getProperties();

    String getDisplayNameForLabel(String relationshipIRI);

    OntologyProperty getPropertyByIRI(String propertyIRI);

    boolean hasRelationshipByIRI(String relationshipIRI);

    Iterable<Concept> getConceptsWithProperties();

    Concept getEntityConcept();

    Concept getParentConcept(Concept concept);

    Concept getConceptByIRI(String conceptIRI);

    List<Concept> getConceptAndChildrenByIRI(String conceptIRI);

    List<Concept> getAllLeafNodesByConcept(Concept concept);

    Concept getOrCreateConcept(Concept parent, String conceptIRI, String displayName, File inDir);

    Relationship getOrCreateRelationshipType(Iterable<Concept> domainConcepts, Iterable<Concept> rangeConcepts, String relationshipIRI, String displayName, String[] intents);

    OWLOntologyManager createOwlOntologyManager(OWLOntologyLoaderConfiguration config, IRI excludeDocumentIRI) throws Exception;

    void resolvePropertyIds(JSONArray filterJson) throws JSONException;

    void importFile(File inFile, IRI documentIRI, Authorizations authorizations) throws Exception;

    void exportOntology(OutputStream out, IRI documentIRI) throws Exception;

    void writePackage(File file, IRI documentIRI, Authorizations authorizations) throws Exception;

    ClientApiOntology getClientApiObject();

    String guessDocumentIRIFromPackage(File inFile) throws Exception;

    Concept getConceptByIntent(String intent);

    Relationship getRelationshipByIntent(String intent);

    OntologyProperty getPropertyByIntent(String intent);
}
