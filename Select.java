package com.example.fuseki.api;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;

public class Select {
    public static void main(String[] args) {
        String datasetURL = "http://localhost:3030/myDataset";

        String queryString =
                "SELECT ?s ?p ?o " +
                        "WHERE { ?s ?p ?o } " +
                        "LIMIT 10";

        try (RDFConnection conn = RDFConnectionRemote.service(datasetURL).build()) {

            System.out.println("--- 쿼리 실행 결과 ---");

            conn.querySelect(queryString, (QuerySolution row) -> {
                // 각 행(Row)을 처리하는 람다식
                System.out.println("Subject: " + row.get("s"));
                System.out.println("Predicate: " + row.get("p"));
                System.out.println("Object: " + row.get("o"));
                System.out.println("--------------------");
            });

            /* 표 형식으로 한꺼번에 출력
            ResultSet rs = conn.query(queryString).execSelect();
            ResultSetFormatter.out(System.out, rs);
            */

        } catch (Exception e) {
            System.err.println("쿼리 실행 중 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
