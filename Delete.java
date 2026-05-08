package com.example.fuseki.api;

import org.apache.jena.update.UpdateExecution;
import org.apache.jena.update.UpdateFactory;

public class Delete {
    public static void main(String[] args) {
        // GSP 주소는 /update 엔드포인트를 사용
        String updateEndpoint = "http://localhost:3030/my_dataset/update";

        // 조건부 삭제 쿼리 (특정 URI와 관련된 모든 데이터 삭제 예시)
        String deleteQuery = """
                PREFIX ex: <http://example.org/ns#>
                DELETE WHERE {
                  ex:subject1 ?p ?o .
                }
                """;

        try {
            UpdateExecution.service(updateEndpoint)
                    .update(UpdateFactory.create(deleteQuery))
                    .build()
                    .execute();

            System.out.println("삭제 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
