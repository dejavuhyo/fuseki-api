package com.example.fuseki.api;

import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;

public class Update {
    public static void main(String[] args) {
        String datasetURL = "http://localhost:3030/myDataset";

        String updateString = "INSERT DATA { <http://example/s> <http://example/p> 'Hello Jena 6' }";

        try (RDFConnection conn = RDFConnectionRemote.service(datasetURL).build()) {
            conn.update(updateString);
            System.out.println("데이터 업데이트 성공!");

        } catch (Exception e) {
            System.err.println("쿼리 실행 중 오류: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
