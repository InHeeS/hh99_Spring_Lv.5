package com.sparta.post.repository;

import com.sparta.post.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends JpaRepository<Folder,Long> {
    List<Folder> findByFolderNumber(Long folderNumber);
}
