package com.example.fuseki.api;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.riot.RDFDataMgr;

public class Indexer {
    public static void main(String[] args) {
        String datasetURL = "http://localhost:3030/myDataset";
        String ontologyPath = "path/to/your/ontology.owl";

        try {
            Model model = RDFDataMgr.loadModel(ontologyPath);
            System.out.println("파일 로드 성공: " + ontologyPath);

            try (RDFConnection conn = RDFConnectionRemote.service(datasetURL)
                    .destination(datasetURL)
                    .build()) {

                System.out.println("Fuseki 서버로 전송 중...");

                conn.load(model);

                // Model 객체 생성 없이 바로 전송 (Stream 방식)
                // conn.load(ontologyPath);

                System.out.println("색인 완료!");
            }
        } catch (Exception e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
