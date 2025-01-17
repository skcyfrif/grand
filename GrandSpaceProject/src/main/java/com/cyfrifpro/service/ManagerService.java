package com.cyfrifpro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.Project;
import com.cyfrifpro.repository.ManagerRepository;
import com.cyfrifpro.repository.ProjectRepository;
import com.cyfrifpro.request.ManagerRequest;

@Service
public class ManagerService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProjectRepository projectRepository;

	// add a manager
	public Manager register(ManagerRequest managerRequest) {
		Manager manager = modelMapper.map(managerRequest, Manager.class);
		manager.setPremium(false);
		manager.setRole("MANAGER");
		return managerRepository.save(manager);
	}

	// Add multiple clients
	public List<Manager> registerAll(List<ManagerRequest> managerRequests) {
		List<Manager> managers = managerRequests.stream().map(managerRequest -> {
			Manager manager = modelMapper.map(managerRequest, Manager.class);
			// Set the default role if not already set
			if (manager.getRole() == null || manager.getRole().isEmpty()) {
				manager.setRole("MANAGER");
			}
			return manager;
		}).collect(Collectors.toList());

		return managerRepository.saveAll(managers);
	}

	// Method to count total number of projects
	public long countTotalManagers() {
		return managerRepository.count();
	}

	public long getPremiumManagerCount() {
		return managerRepository.countPremiumManagers();
	}

	// Fetch a manager by ID
	public Optional<Manager> getManagerById(Long id) {
		return managerRepository.findById(id);
	}

	// Fetch a manager by Email
	public Optional<Manager> getManagerByEmail(String email) {
		return managerRepository.findByEmail(email);
	}

	// Fetch all manager at a time
	public List<Manager> findAlManagers() {
		return managerRepository.findAll();
	}

	// Fetch list of unsubscribed manager
	public List<Manager> getUnPremiumedManagers() {
		return managerRepository.findUnpremiumedManagers();
	}

	// Fetch all subscribed clients
	public List<Manager> getAllPremiumManagers() {
		return managerRepository.findByPremiumTrue();
	}

	/**
	 * Counts the number of active managers.
	 *
	 * An active manager is defined as a manager with at least one assigned project.
	 *
	 * @return the count of active managers
	 */
	public long countActiveManagers() {
		return projectRepository.countActiveManagers();
	}

	// Update the premium field of a client to true
	public Optional<Manager> updatePremiumStatus(Long managerId) {
		Optional<Manager> managerOptional = managerRepository.findById(managerId);
		if (managerOptional.isPresent()) {
			Manager manager = managerOptional.get();
			manager.setPremium(true); // Set the premium field to true
			return Optional.of(managerRepository.save(manager));
		}
		return Optional.empty(); // Return empty if the client does not exist
	}

	public Manager assignManagerToProject(Long projectId) {
		// Logic to find an available manager
		Manager manager = managerRepository.findAvailableManager()
				.orElseThrow(() -> new IllegalStateException("No managers available"));

		manager.setAssignedProjectId(projectId);
		managerRepository.save(manager);

		return manager;
	}

	public Manager updateAssignedProject(ManagerRequest managerRequest) {
		// Fetch the manager by ID
		Manager manager = managerRepository.findById(managerRequest.getManagerId()).orElseThrow(
				() -> new IllegalArgumentException("Manager not found with ID: " + managerRequest.getManagerId()));

		// Fetch the project by ID and ensure it is confirmed
		Project project = projectRepository.findById(managerRequest.getProjectId()).orElseThrow(
				() -> new IllegalArgumentException("Project not found with ID: " + managerRequest.getProjectId()));

		if (!project.isConfirmed()) {
			throw new IllegalStateException("Project must be confirmed before assigning to a manager.");
		}

		// Update the assigned project ID for the manager
		manager.setAssignedProjectId(project.getId());

		// Save the updated manager
		return managerRepository.save(manager);
	}

	public Manager acceptProjectAndUpdateStatus(Long managerId, Long projectId) {
		// Fetch the manager by ID
		Manager manager = managerRepository.findById(managerId)
				.orElseThrow(() -> new IllegalArgumentException("Manager not found with ID: " + managerId));

		// Fetch the project by ID and ensure it is in the "assigned" state
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new IllegalArgumentException("Project not found with ID: " + projectId));

		if (!project.isConfirmed()) {
			throw new IllegalStateException("Project must be confirmed before a manager can accept it.");
		}

		// Check if the project is in the "assigned" state, as it can only move to
		// "work_in_progress" from "assigned"
		if (project.getStatus() == null || !project.getStatus().equals("ASSIGNED")) {
			throw new IllegalStateException("Project is not in the 'ASSIGNED' state and cannot be accepted.");
		}

		// Assign the manager to the project
		manager.setAssignedProjectId(projectId);
		managerRepository.save(manager);

		// Set the project's status to "work_in_progress"
		project.setStatus("WORK_IN_PROGRESS");
		projectRepository.save(project);

		return manager;
	}

}
