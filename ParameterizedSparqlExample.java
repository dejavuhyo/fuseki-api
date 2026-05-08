package com.example.fuseki.api;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class ParameterizedSparqlExample {
    public static void main(String[] args) {
        // Fuseki 서버 주소
        String fusekiService = "http://localhost:3030/my_dataset/sparql";

        // 쿼리 템플릿
        ParameterizedSparqlString pss = new ParameterizedSparqlString();
        pss.setCommandText("""
                PREFIX ex: <http://example.org/>
                SELECT ?name ?age
                WHERE {
                  ?person ex:id ?id ;
                          ex:name ?name ;
                          ex:age ?age .
                }
                """);

        // 파라미터 바인딩
        pss.setLiteral("id", 12345);

        // 특정 타입의 리터럴이나 URI를 주입할 때
//         pss.setIri("person", "http://example.org/user/john");

        // 실행
        try (QueryExecution qexec = QueryExecution.service(fusekiService)
                .query(pss.asQuery()) // ParameterizedSparqlString을 Query 객체로 변환
                .build()) {

            ResultSet results = qexec.execSelect();
            ResultSetFormatter.out(System.out, results);
        }
    }
}
