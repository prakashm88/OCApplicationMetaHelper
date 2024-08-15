package com.itechgenie.apps.openshift.helper.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itechgenie.apps.openshift.helper.services.OCPodService;

import io.fabric8.kubernetes.api.model.Pod;

@RestController
@RequestMapping("/api")
public class OCMetaHelperController {

	@Autowired
	private OCPodService podService;

	public OCMetaHelperController(OCPodService podService) {
		this.podService = podService;
	}

	@GetMapping("/pods")
	public List<Pod> getPods() {
		return podService.getPodsInNamespace();
	}
}
