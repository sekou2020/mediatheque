package com.project.stage.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.stage.entities.Media;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface MediathequeRepository extends JpaRepository<Media, Long> {
	
	
	@Query("SELECT m FROM Media m  WHERE m.title like %:pTitle%")
	List<Media> findByTitle(@Param("pTitle") String title);

	Set<Media> findMediaByTitleLike(String title);

	Optional<Media> findMediaById(Long id);

	Set<Media> findMediaByDescriptionLike(String description);

	void deleteMediaById(Long id);
	

}
