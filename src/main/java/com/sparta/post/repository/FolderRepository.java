package com.sparta.post.repository;

import com.sparta.post.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder,Long> {

    List<Folder> findByFolderNumber(Long folderNumber);
}
