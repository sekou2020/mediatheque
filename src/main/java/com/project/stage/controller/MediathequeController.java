package com.project.stage.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.stage.dao.MediathequeRepository;
import com.project.stage.entities.Media;
import com.project.stage.forms.FindMediaForm;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MediathequeController {

	@Autowired
	private MediathequeRepository mediathequeRepository;

	@ModelAttribute("findMediaForm")
	public FindMediaForm populateFindMediaForm(){
		return new FindMediaForm();
	}

	@ModelAttribute("medias")
	public List<Media> populateMedias(){return mediathequeRepository.findAll();}
	
	@GetMapping(value = { "/", "/index" })
	public String index(Model model, HttpServletRequest request) {

		List<Media> medias = (List<Media>) request.getSession().getAttribute("medias");
		// List<Media> medias = (List<Media>) model.getAttribute("medias");


		// recherche vide on ramène tous les médias
		if (medias == null || medias.isEmpty()) {
			// Appeler Dao pour remonter tous les média
			medias = mediathequeRepository.findAll();
		}
		model.addAttribute("medias", medias);

		return "index";
	}

	@RequestMapping(value = { "/findMedia" }, method = RequestMethod.POST)
	public String findMedia(@ModelAttribute("findMediaForm") FindMediaForm findMediaForm, Model model,
							HttpServletRequest request) {

		// appeler le dao pour rechercher un media.
		List<Media> medias = mediathequeRepository.findByTitle(findMediaForm.getTitle());
		// model.addAttribute("medias", medias);

		request.getSession().setAttribute("medias", medias);

		return "redirect:index";
	}
	
	@RequestMapping(value = { "/consultmedia/{id}" }, method = RequestMethod.GET)
	public String consultMedia(@PathVariable("id") String id , Model model,
			HttpServletRequest request) {
		

		// appeler le dao pour rechercher un media.
		Optional<Media> media = mediathequeRepository.findById(Long.parseLong(id));
		
		model.addAttribute("media", media.get());

		return "consultMedia";
	}

	@RequestMapping(value = { "/addMedia" }, method = RequestMethod.POST)
	public String addMedia(Model model) {

		// model.addAttribute("medias", medias);

		return "addMedia";
	}
}
