package com.example.fuseki.api;

import org.apache.jena.sparql.exec.http.GSP;

public class GspDelete {
    public static void main(String[] args) {
        // GSP 주소는 /data 엔드포인트를 사용
        String gspEndpoint = "http://localhost:3030/my_dataset/data";

        try {
            // 기본 그래프(Default Graph)의 모든 내용을 삭제
            GSP.service(gspEndpoint)
                    .defaultGraph()
                    .DELETE();

            // 특정 Named Graph를 삭제
//            GSP.service(gspEndpoint)
//               .graphName("http://example.org/myGraph")
//               .DELETE();

            System.out.println("전체 그래프 삭제 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
