package MODEL; // Hoặc package HELPER

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class DuongdanFile {
    

    private static final String UPLOAD_DIR =  "src/Imagin/";


    public static String luuFileAnh(File sourceFile) {
        try {
            //  Xác định thư mục lưu trữ trong Project
        
            String projectPath = System.getProperty("user.dir");
            File directory = new File(projectPath + File.separator + UPLOAD_DIR);

            // Nếu thư mục chưa có thì tạo mới
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Tạo tên file mới (Duy nhất) để tránh trùng lặp
   
            String fileName = sourceFile.getName();
            String extension = "";
            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i);
            }
            
            // Tên mới = Chuỗi ngẫu nhiên + đuôi file
            String newFileName = UUID.randomUUID().toString() + extension;

            // Thực hiện Copy file
            Path sourcePath = sourceFile.toPath();
            Path destPath = Paths.get(directory.getAbsolutePath(), newFileName);
            
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);


            return UPLOAD_DIR  + newFileName;

        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
    }
}