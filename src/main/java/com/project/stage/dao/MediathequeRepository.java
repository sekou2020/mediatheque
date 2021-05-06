package com.project.stage.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.stage.entities.Media;

public interface MediathequeRepository extends JpaRepository<Media, Long>{	
	
	
	@Query("SELECT m FROM Media m  WHERE m.title like %:pTitle%")
	List<Media> findByTitle(@Param("pTitle") String title); 
	

}
