package MODEL; // Hoặc package HELPER

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.swing.JOptionPane;

public class DuongdanFile {
    

    private static final String UPLOAD_DIR =  "src/Imagin/";


    public static String luuFileAnh(File sourceFile) {
        try {
            //  Xác định thư mục lưu trữ 
        
            String projectPath = System.getProperty("user.dir");
            File directory = new File(projectPath + File.separator + UPLOAD_DIR);

            // Nếu thư mục chưa có thì tạo mới
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Tạo tên file mới để tránh trùng lặp
   
            String fileName = sourceFile.getName();
     
            String extension = "";
            
            // lấy dấu chấm ví dụ.png
            int i = fileName.lastIndexOf('.');
    
            
            if (i > 0) {
            	// xác địn đuôi file là gì , VD .png
                extension = fileName.substring(i);
            }

            // Tên mới = Chuỗi ngẫu nhiên + đuôi file
            String newFileName = UUID.randomUUID().toString() + extension;
            
  
            // đường dẫn file nằm trong hệ thống
            Path sourcePath = sourceFile.toPath();

            // xác định đường dẫn lưu vào thư mục imagin trong project
            Path destPath = Paths.get(directory.getAbsolutePath(), newFileName);
    
            
            // thực hiện copy từ file hệ thống vào trong thư mục imagin
            Files.copy(sourcePath, destPath, StandardCopyOption.REPLACE_EXISTING);


            return UPLOAD_DIR  + newFileName;

        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
    }
}