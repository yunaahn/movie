package com.example.movieapi.entity;


import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; // 업로드한 파일명
    private String storeFileName;  // 서버 내부 관리 파일명

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    // storeFileName 필드에 대한 getter/setter 추가
    public String getStoreFileName() {
        return storeFileName;
    }

    public void setStoreFileName(String storeFileName) {
        this.storeFileName = storeFileName;
    }
}
