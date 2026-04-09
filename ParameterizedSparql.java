package com.example.fuseki.api;

import org.apache.jena.query.ParameterizedSparqlString;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;

/**
 * ParameterizedSparqlString은 SQL의 PreparedStatement와 유사한 역할을 한다.
 * 쿼리문에 변수 자리를 만들어두고, 나중에 값을 채워 넣는 방식이다.
 * <p>
 * 이 방식을 쓰면 PREFIX 관리가 훨씬 편해지고,
 * 문자열 더하기(+)로 생기는 문법 오류나 SPARQL 주입(Injection) 공격을 방지할 수 있다.
 */
public class ParameterizedSparql {
    public static void main(String[] args) {
        String datasetURL = "http://localhost:3030/myDataset";

        ParameterizedSparqlString pss = new ParameterizedSparqlString();

        // 2. 공통 Prefix 설정 (일일이 쿼리에 쓸 필요가 없어짐)
        pss.setNsPrefix("ex", "http://example.org/");
        pss.setNsPrefix("rdf", "http://www.w3.org/1999/02/22-rdf-syntax-ns#");
        pss.setNsPrefix("rdfs", "http://www.w3.org/2000/01/rdf-schema#");

        pss.setCommandText(
                "SELECT ?subject ?label " +
                        "WHERE { " +
                        "  ?subject rdfs:label ?label . " +
                        "  ?subject ex:category ?type . " +
                        "  FILTER(CONTAINS(?label, ?searchName)) " +
                        "}"
        );

        pss.setIri("type", "http://example.org/Book");
        pss.setLiteral("searchName", "Java Programming");

        System.out.println("--- 실행될 쿼리 ---");
        System.out.println(pss);

        try (RDFConnection conn = RDFConnectionRemote.service(datasetURL).build()) {
            try (QueryExecution qexec = conn.query(pss.asQuery())) {
                ResultSet rs = qexec.execSelect();
                ResultSetFormatter.out(System.out, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
