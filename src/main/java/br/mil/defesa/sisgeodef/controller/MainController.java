package br.mil.defesa.sisgeodef.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.mil.defesa.sisgeodef.services.PntService;

@RestController
@RequestMapping("/ptn")
public class MainController {

	@Autowired
	private PntService pntService;
	
	@RequestMapping( value = "/gen", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE ) 
	public @ResponseBody String getFiles( @RequestParam(value="tipo",required=true) String tipo ){
		pntService.doGen();
		return "ok";
	}

	
}

