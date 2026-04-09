package com.example.fuseki.api;

import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;

public class Delete {
    private static final String DATASET_URL = "http://localhost:3030/myDataset";

    public static void deleteSpecificTriple() {
        String deleteDataString =
                "DELETE DATA { <http://example/subject> <http://example/predicate> 'Target Value' }";

        try (RDFConnection conn = RDFConnectionRemote.service(DATASET_URL).build()) {
            conn.update(deleteDataString);
            System.out.println("삭제 완료");
        }
    }

    public static void deleteByCondition() {
        // Example 1) 특정 주어(<http://example/s1>)와 관련된 모든 속성(?p)과 값(?o) 삭제
        String deleteAllAboutSubject = "DELETE WHERE { <http://example/s1> ?p ?o }";

        // Example 2) 특정 카테고리에 속한 데이터만 골라서 삭제 (SPARQL 패턴 활용)
        String deleteCategory =
                "PREFIX ex: <http://example.org/> " +
                        "DELETE WHERE { ?s ex:category 'OldData' . ?s ?p ?o }";

        try (RDFConnection conn = RDFConnectionRemote.service(DATASET_URL).build()) {
            conn.update(deleteAllAboutSubject);
            System.out.println("조건 기반 삭제 완료");
        }
    }

    public static void main(String[] args) {
        deleteSpecificTriple();
        deleteByCondition();
    }
}
