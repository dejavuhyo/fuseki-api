package com.example.fuseki.api;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.exec.http.GSP;

public class Indexer {
    public static void main(String[] args) {
        // GSP(Graph Store Protocol) 엔드포인트는 /data를 사용
        String serviceEndpoint = "http://localhost:3030/my_dataset/data";

        // 모델 읽기
        Model model = RDFDataMgr.loadModel("ontology.ttl");

        // GSP 서비스를 사용하여 전송
        GSP.service(serviceEndpoint)
                .defaultGraph()
                .POST(model.getGraph()); // POST는 추가, PUT은 덮어쓰기

        System.out.println("색인 성공");
    }
}
