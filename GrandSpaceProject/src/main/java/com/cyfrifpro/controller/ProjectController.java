package com.cyfrifpro.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cyfrifpro.model.Project;
import com.cyfrifpro.repository.ProjectRepository;
import com.cyfrifpro.request.ProjectRequest;
import com.cyfrifpro.response.ProjectResponse;
import com.cyfrifpro.service.AdminService;
//import com.cyfrifpro.service.PaymentService;
import com.cyfrifpro.service.ProjectService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/project")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Tag(name = "ProjectController", description = "By using this class we can map all kind of project requests.")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private AdminService adminService;

	@Autowired
	private ModelMapper modelMapper;

	// @Autowired
//	private PaymentService paymentService;

	// Submit a project
	@PostMapping("/submit")
	@Operation(summary = "Post Api", description = "This is a method for project submission")
	public ResponseEntity<ProjectResponse> submitProject(@RequestBody ProjectRequest projectRequest) {
		ProjectResponse projectResponse = projectService.submitProject(projectRequest);
		return ResponseEntity.ok(projectResponse);
	}

	// Endpoint to get all projects
	@GetMapping("/allProjects")
	public List<ProjectResponse> getAllProjects() {
		List<Project> projects = projectService.getAllProjects();
		// Map each project to a response DTO (ProjectResponse)
		return projects.stream().map(project -> modelMapper.map(project, ProjectResponse.class))
				.collect(Collectors.toList());
	}
	
	// Endpoint to get the budget of a project by ID
    @GetMapping("/projects/{id}/budget")
    public ResponseEntity<BigDecimal> getProjectBudget(@PathVariable Long id) {
        try {
            BigDecimal projectBudget = projectService.getProjectBudget(id);
            return ResponseEntity.ok(projectBudget);  // Returns the budget as a response
        } catch (Exception ex) {
            return ResponseEntity.status(404).body(null);  // Returns a 404 with no content if project not found
        }
    }

	// Endpoint to assign a manager to a project
	@PutMapping("assignManager/{projectId}")
	@Operation(summary = "Put Api", description = "This is a method for manager assigning")
	public ResponseEntity<Project> assignManager(@PathVariable Long projectId,
			@RequestBody ProjectRequest projectRequest) {
		// Set the projectId in the request object
		projectRequest.setId(projectId);

		try {
			// Call the service to assign the client and manager to the project
			Project project = projectService.assignedManager(projectRequest);

			// Return the updated project
			return ResponseEntity.ok(project);
		} catch (RuntimeException ex) {
			// If the client or manager is not premium, handle the error
			return ResponseEntity.badRequest().build();
		}
	}

	// Endpoint to assign a client to a project
	@PutMapping("assignClient/{projectId}")
	@Operation(summary = "Put Api", description = "This is a method for client assigning")
	public ResponseEntity<Project> assignClient(@PathVariable Long projectId,
			@RequestBody ProjectRequest projectRequest) {
		// Set the projectId in the request object
		projectRequest.setId(projectId);

		try {
			// Call the service to assign the client and manager to the project
			Project project = projectService.assignedClient(projectRequest);

			// Return the updated project
			return ResponseEntity.ok(project);
		} catch (RuntimeException ex) {
			// If the client or manager is not premium, handle the error
			return ResponseEntity.badRequest().build();
		}
	}

	// Endpoint to get a project by ID
	@GetMapping("/{projectId}")
	@Operation(summary = "Get Api", description = "This is a method for geting a project by its id")
	public Optional<Project> getProjectById(@PathVariable Long projectId) {
		return projectService.findProjectById(projectId);
	}

	// Endpoint to get projects by client ID
	@GetMapping("/client/{clientId}")
	@Operation(summary = "Get Api", description = "This is a method for geting a project by client id")
	public List<Project> getProjectsByClientId(@PathVariable Long clientId) {
		return projectService.findProjectsByClientId(clientId);
	}

	// Endpoint to get projects by manager ID
	@GetMapping("/manager/{managerId}")
	@Operation(summary = "Get Api", description = "This is a method for geting a project by assigned manager id")
	public List<Project> getProjectsByManagerId(@PathVariable Long managerId) {
		return projectService.findProjectsByManagerId(managerId);
	}

	// Endpoint for client to confirm the project
	@PutMapping("/updateConfirm/{projectId}")
	@Operation(summary = "Put Api", description = "This is a method for client to confirm the project")
	public ResponseEntity<Project> updatePremium(@PathVariable Long projectId) {
		Optional<Project> updatedConfirm = projectService.updateConfirmationStatus(projectId);
		return updatedConfirm.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/{projectId}/assign-to-managers")
	@Operation(summary = "Post Api", description = "This is a method to notify all the managers about the project")
	public ResponseEntity<String> assignProjectToManagers(@PathVariable Long projectId) {
		Project project = projectRepository.findById(projectId)
				.orElseThrow(() -> new RuntimeException("Project not found"));

		// Notify managers and update the project status
		adminService.assignProjectToManagers(project);

		return ResponseEntity.status(HttpStatus.OK).body("Project assigned to all managers and status updated.");
	}

	@PutMapping("/update-status/completed/{projectId}")
	public String updateStatusToCompleted(@PathVariable Long projectId) {
		Optional<Project> updatedProject = projectService.updateStatusToCompleted(projectId);
		if (updatedProject.isPresent()) {
			return "Project status updated to 'COMPLETED' for project ID: " + projectId;
		} else {
			return "Project not found with ID: " + projectId;
		}
	}

	@GetMapping("/projects/count")
	public long getTotalProjectsCountLastYear() {
		return projectService.countLastYearProjects();
	}

	// Endpoint to count "ASSIGNED_PROJECTS" projects registered in last year
	@GetMapping("/count-assigned-projects-last-year")
	public long countAssignedProjectsLastYear() {
		return projectService.countAssignedProjectsLastYear();
	}

	// Endpoint to count "ASSIGNED_PROJECTS" projects registered in last year
	@GetMapping("/count-not-assigned-projects-last-year")
	public long countNotAssignedProjectsLastYear() {
		return projectService.countNotAssignedProjectsLastYear();
	}

	// Endpoint to count "ASSIGNED_PROJECTS" projects registered in last year
	@GetMapping("/count-completed-projects-last-year")
	public long countCompletedProjectsLastYear() {
		return projectService.countCompletedProjectsLastYear();
	}

	// Endpoint to get the count of NOT_ASSIGNED projects
	@GetMapping("/projects/un-publish/count")
	public long getUnpublishProjectsCount() {
		return projectService.countUnpublishProjects();
	}

	// Endpoint to get the count of projects with "AWAITING_ESTIMATES" status from
	// last year
	@GetMapping("/count-awaiting-estimates-last-year")
	public long getCountAwaitingEstimatesProjectsLastYear() {
		return projectService.countAwaitingEstimatesProjectsLastYear();
	}

	// Endpoint to get the count of projects with "AWAITING_ESTIMATES" status from
	// last year
	@GetMapping("/count-wip-last-year")
	public long getWIPProjectsLastYear() {
		return projectService.countWIPProjectsLastYear();
	}

	// Endpoint to get the count of projects with "AWAITING_ESTIMATES" status from
	// last year
	@GetMapping("/count-dispute-last-year")
	public long getDisputeProjectsLastYear() {
		return projectService.countDISPUTEProjectsLastYear();
	}

	/// GET ALL////
	
	
	@GetMapping("/projects/last-year")
    public List<Project> getLastYearProjects() {
        return projectService.getLastYearProjects();
    }
	
	@GetMapping("/un-publish-projects")
	public List<Project> getUnpublishProjects() {
		return projectService.getUnpublishProjects();
	}

	// Endpoint to get all "AWAITING_ESTIMATES" projects from last year
	@GetMapping("/awaiting-estimates-last-year")
	public List<Project> getAwaitingEstimatesProjectsLastYear() {
		return projectService.getAwaitingEstimatesProjectsLastYear();
	}

	// Endpoint to get all "ASSIGNED_PROJECTS" with registration date in the last
	// year
	@GetMapping("/assigned-projects-registered-last-year")
	public List<Project> getAssignedProjectsRegisteredLastYear() {
		return projectService.getAssignedProjectsRegisteredLastYear();
	}

	// Endpoint to get all "ASSIGNED_PROJECTS" with registration date in the last
	// year
	@GetMapping("/not-assigned-projects-registered-last-year")
	public List<Project> getNotAssignedProjectsRegisteredLastYear() {
		return projectService.getNotAssignedProjectsRegisteredLastYear();
	}

	// Endpoint to get all "ASSIGNED_PROJECTS" with registration date in the last
	// year
	@GetMapping("/completed-projects-registered-last-year")
	public List<Project> getCompletedProjectsRegisteredLastYear() {
		return projectService.getCompletedProjectsRegisteredLastYear();
	}

	// Endpoint to get all "ASSIGNED_PROJECTS" with registration date in the last
	// year
	@GetMapping("/wip-projects-registered-last-year")
	public List<Project> getWIPRegisteredLastYear() {
		return projectService.getWIPProjectsRegisteredLastYear();
	}

	// Endpoint to get all "ASSIGNED_PROJECTS" with registration date in the last
	// year
	@GetMapping("/dispute-projects-registered-last-year")
	public List<Project> getDisputeRegisteredLastYear() {
		return projectService.getDISPUTEProjectsRegisteredLastYear();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProject(@PathVariable Long id) {
		boolean isDeleted = projectService.deleteProjectById(id);

		if (isDeleted) {
			return ResponseEntity.ok("Project deleted successfully.");
		} else {
			return ResponseEntity.status(404).body("Project not found.");
		}
	}

}
