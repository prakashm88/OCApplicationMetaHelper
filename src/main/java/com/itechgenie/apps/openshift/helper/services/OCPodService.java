package com.itechgenie.apps.openshift.helper.services;

import java.util.List;

import org.springframework.stereotype.Service;

import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientBuilder;

@Service
public class OCPodService {
	private final KubernetesClient kubernetesClient;

	public OCPodService() {
		this.kubernetesClient = new KubernetesClientBuilder().build();
	}

	public List<Pod> getPodsInNamespace() {
		String namespace = kubernetesClient.getNamespace();
		if (namespace == null) {
			namespace = "default"; // fallback to default namespace
		}
		return kubernetesClient.pods().inNamespace(namespace).list().getItems();
	}
}
