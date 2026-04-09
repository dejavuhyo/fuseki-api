package com.example.fuseki.api;

import org.apache.jena.query.Dataset;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.tdb2.TDB2Factory;

import java.io.FileOutputStream;

public class ExportData {
    public static void main(String[] args) {
        String directory = "path/to/your/tdb2_folder";
        Dataset dataset = TDB2Factory.connectDataset(directory);

        try (FileOutputStream out = new FileOutputStream("backup_data.nq")) {
            RDFDataMgr.write(out, dataset, RDFFormat.NQUADS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        dataset.close();
    }
}
