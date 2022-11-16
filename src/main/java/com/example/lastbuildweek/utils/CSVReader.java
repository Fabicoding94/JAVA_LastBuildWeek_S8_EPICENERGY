package com.example.lastbuildweek.utils;

import com.example.lastbuildweek.entities.Provincia;
import com.example.lastbuildweek.services.ProvinciaService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class CSVReader {
    private static final String CSC_FILE_PATH_PROVINCE = "CSVcomuni&provicie/province-italiane.csv";
    private static final String CSC_FILE_PATH_COMUNI = "CSVcomuni&provicie/comuni-italiani.csv";

    @Autowired
    ProvinciaService ps;

    public  String[] listProvince() throws IOException {

        File file = new File(CSC_FILE_PATH_PROVINCE);
        String readString = FileUtils.readFileToString(file, "UTF-8");

        return readString.split("\r?\n");
    }

    public  String[] listComuni() throws IOException {

        File file = new File(CSC_FILE_PATH_COMUNI);
        String readString = FileUtils.readFileToString(file, "UTF-8");

        return readString.split("\r?\n");
    }

    public void registraProvince() throws IOException {
        for (String prov : listProvince()) {
            String[] line = prov.split(";");
            String sigla = line[0];
            String provincia = line[1];
            String regione = line[2];
            Provincia newProv = Provincia.builder()
                    .build();
           ps.save(newProv);
        }
    }

}
