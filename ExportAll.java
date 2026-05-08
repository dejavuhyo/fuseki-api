package com.example.fuseki.api;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.core.DatasetGraph;
import org.apache.jena.sparql.exec.http.DSP;

import java.io.FileOutputStream;

public class ExportAll {
    public static void main(String[] args) {
        // Dataset Store Protocol (DSP) 엔드포인트
        // Fuseki에서 데이터셋 전체를 다룰 때는 보통 /data 또는 /get 엔드포인트를 사용
        String dspEndpoint = "http://localhost:3030/my_dataset/data";

        try {
            // 전체 데이터셋(Named Graphs 포함) 가져오기
            // DatasetGraph 반환
            DatasetGraph datasetGraph = DSP.service(dspEndpoint).GET();

            // DatasetGraph를 Dataset으로 변환
            Dataset dataset = DatasetFactory.wrap(datasetGraph);

            // 로컬 파일로 저장 (Named Graphs를 유지하기 위해 TriG 형식 사용)
            // TriG나 N-Quads는 여러 그래프를 한 파일에 저장할 수 있음
            try (FileOutputStream out = new FileOutputStream("export_dataset.trig")) {
                RDFDataMgr.write(out, dataset, RDFFormat.TRIG);
                System.out.println("전체 데이터셋(Named Graphs 포함) 내보내기 성공!");
                System.out.println("결과 파일: export_dataset.trig");
            }
        } catch (Exception e) {
            System.err.println("데이터 내보내기 실패: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
