package com.example.demo.Service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Service {
    public void uploadFile(MultipartFile file) throws IOException, InterruptedException {
        Path path = Paths.get(System.getProperty("user.dir"),"temp.pdf");
        assert path != null;
        File tempFile = new File(path.toString());
        if(!tempFile.exists())
            throw new IOException("temp.pdf not found");
        Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
        File outputDir = new File("output");
        if(!outputDir.exists())
            outputDir.mkdir();
        List<String> conversionCommand = new ArrayList<>();
        conversionCommand.add("gs");
        conversionCommand.add("-q");
        conversionCommand.add("-r300");
        conversionCommand.add("-sDEVICE=tiffgray");
        conversionCommand.add("-o output/"+file.getOriginalFilename().substring(0,file.getOriginalFilename().length()-4)+"_300dpi.tif");
        conversionCommand.add("temp.pdf");
        ProcessBuilder pb = new ProcessBuilder();
        pb.command(conversionCommand);
        Process p = pb.start();
        p.waitFor();

    }
}
