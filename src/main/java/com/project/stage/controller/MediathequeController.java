package com.project.stage.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.stage.dao.MediaRepository;
import com.project.stage.dao.TypeMediaRepository;
import com.project.stage.entities.Media;
import com.project.stage.entities.TypeMedia;
import com.project.stage.forms.FindMediaForm;
import com.project.stage.forms.MediaForm;

@Controller
public class MediathequeController {

	@Autowired
	private MediaRepository mediaRepository;
	
	@Autowired
	private TypeMediaRepository typeMediaRepository;

	@ModelAttribute("findMediaForm")
	public FindMediaForm populateFindMediaForm(){
		return new FindMediaForm();
	}

	@ModelAttribute("medias")
	public List<Media> populateMedias(){return mediaRepository.findAll();}
	
	@GetMapping(value = { "/", "/index" })
	public String index(Model model, HttpServletRequest request) {

		List<Media> medias = (List<Media>) request.getSession().getAttribute("medias");

		// recherche vide on ramène tous les médias
		if (medias == null || medias.isEmpty()) {
			// Appeler Dao pour remonter tous les média
			medias = (List<Media>) mediaRepository.findAll();
		}
		model.addAttribute("medias", medias);

		return "index";
	}

	@RequestMapping(value = { "/findMedia" }, method = POST)
	public String findMedia(@ModelAttribute("findMediaForm") FindMediaForm findMediaForm, Model model,
							HttpServletRequest request) {

		// appeler le dao pour rechercher un media.
		List<Media> medias = mediaRepository.findByTitle(findMediaForm.getTitle());

		request.getSession().setAttribute("medias", medias);

		return "redirect:index";
	}
	
	@RequestMapping(value = { "/consultmedia/{id}" }, method = GET)
	public String consultMedia(@PathVariable("id") String id , Model model) {

		// appeler le dao pour rechercher un media.
		Optional<Media> media = mediaRepository.findById(Long.parseLong(id));
		
		model.addAttribute("media", media.get());

		return "consultMedia";
	}

	@RequestMapping(value = {"/deletemedia/{id}"}, method =  GET)
	@Transactional
	public String deleteMedia(@PathVariable("id") Long id, Model model, HttpServletRequest request){
		mediaRepository.deleteMediaById(id);
		List<Media> medias = mediaRepository.findAll();
		
		//return "redirect:index";
		request.getSession().setAttribute("medias",medias);
		model.addAttribute("medias", medias);

		return "index";
	}

	@RequestMapping(value = {"/displayUpdateMedia/{id}"}, method = GET)
	public String displayUpdateMedia(@PathVariable("id") String id, Model model){
		// appeler le dao pour rechercher un media.
		Optional<Media> media = mediaRepository.findById(Long.parseLong(id));
		
		//alimenter le formulaire depuis l'entité
		MediaForm mediaForm = new MediaForm();
		mediaForm.setDescription(media.get().getDescription());
		mediaForm.setTitle(media.get().getTitle());
		mediaForm.setIdTypeMedia(media.get().getTypeMedia().getIdTypeMedia());
		mediaForm.setIdMedia(media.get().getId());
		
		//stocker l'information en memoire
		model.addAttribute("mediaForm", mediaForm);
		return "updateMedia";
	}
	
	@RequestMapping(value = {"/updateMedia"}, method = POST)
	@Transactional
	public String updateMedia(@ModelAttribute("mediaForm") MediaForm mediaForm, Model model){
		
		//controler les paramètres d'entrée
		//TODO 
		
		//Alimenter l'entité media depuis le formulaire mediaForm
		Media media = new Media();
		media.setDescription(mediaForm.getDescription());
		media.setId(mediaForm.getIdMedia());
		media.setTitle(mediaForm.getTitle());
		
		TypeMedia typeMedia = new TypeMedia();
		typeMedia.setIdTypeMedia(mediaForm.getIdTypeMedia());
		
		media.setTypeMedia(typeMedia);		
		
		// appeler le dao pour mettre à jour le media.		
		mediaRepository.saveAndFlush(media);
		
		return "redirect:index";
	}

	@RequestMapping(value = { "/displayAddMedia" }, method = GET)
	public String displayAddMedia(Model model) {
		

		List<TypeMedia> typesMedia = typeMediaRepository.findAll();
		model.addAttribute("typesMedia", typesMedia);
		
		
		MediaForm mediaForm = new MediaForm();
		model.addAttribute("mediaForm", mediaForm);

		return "addMedia";
	}
	
	@RequestMapping(value = { "/addMedia" }, method = POST)
	@Transactional
	public String addMedia( @ModelAttribute("mediaform") MediaForm mediaForm, Model model) {
		
		
		
		//alimenter le media depuis le formulaire
		Media media = new Media();
		media.setDescription(mediaForm.getDescription());
		media.setTitle(mediaForm.getTitle());
		
		//alimenter type media depuis le formulaire
		TypeMedia typeMedia = new TypeMedia();
		typeMedia.setIdTypeMedia(mediaForm.getIdTypeMedia());
		
		//Lier le media au type media
		media.setTypeMedia(typeMedia);
		
		
		//Appeler le dao mediaRepository pour sauvegarder le media dans la base de données
		mediaRepository.saveAndFlush(media);
		
		//rediriger vers la page index
		
		return "redirect:index";
	}
}
