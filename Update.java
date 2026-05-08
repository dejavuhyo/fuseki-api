package com.example.fuseki.api;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.sparql.exec.http.GSP;

public class Update {
    public static void main(String[] args) {
        // GSP 엔드포인트는 /data를 사용
        String gspEndpoint = "http://localhost:3030/my_dataset/data";

        // 파일 읽기
        Model model = RDFDataMgr.loadModel("ontology.ttl");

        // GSP 빌더 사용 (Jena 6 정석)
        GSP.service(gspEndpoint)
                .defaultGraph()
                .PUT(model.getGraph()); // POST는 추가, PUT은 덮어쓰기

        System.out.println("색인 완료");
    }
}
