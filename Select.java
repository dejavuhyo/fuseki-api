package com.example.fuseki.api;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.RDFNode;

public class Select {
    public static void main(String[] args) {
        // Fuseki 서버 주소
        String serviceEndpoint = "http://localhost:3030/my_dataset/sparql";

        // SPARQL
        String queryString = """
                PREFIX ex: <http://example.org/>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                SELECT ?subject ?label
                WHERE {
                  ?subject rdfs:label ?label
                }
                LIMIT 20
                """;

        try (QueryExecution qexec = QueryExecution.service(serviceEndpoint)
                .query(queryString)
                // Fuseki 서버에 아이디/패스워드 설정한 경우
//                .httpClient(java.net.http.HttpClient.newBuilder()
//                        .authenticator(new java.net.Authenticator() {
//                            protected java.net.PasswordAuthentication getPasswordAuthentication() {
//                                return new java.net.PasswordAuthentication("admin", "password".toCharArray());
//                            }
//                        }).build())
                .build()) {

            // SELECT 결과 가져오기
            ResultSet results = qexec.execSelect();

            // 결과 루프
            while (results.hasNext()) {
                QuerySolution qs = results.nextSolution();

                // 리소스(URI)와 리터럴(값) 구분해서 가져오기
                RDFNode subject = qs.get("subject");
                RDFNode label = qs.get("label");

                System.out.println("subject: " + subject + " | label: " + label);
            }
        } catch (Exception e) {
            System.err.println("Fuseki 연결 실패: " + e.getMessage());
        }
    }
}
