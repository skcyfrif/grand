package com.cyfrifpro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyfrifpro.model.Manager;
import com.cyfrifpro.request.ManagerRequest;
import com.cyfrifpro.service.ManagerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/manager")
@Tag(name = "ManagerController", description = "By using this class we can map all kind of manager requests.")
@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@PostMapping("/register")
	@Operation(summary = "Post Api", description = "This is a method for manager registration")
	public ResponseEntity<Manager> register(@RequestBody ManagerRequest managerRequest) {
		Manager registeredManager = managerService.register(managerRequest);
		return new ResponseEntity<>(registeredManager, HttpStatus.OK);
	}

	@PostMapping("/registerAll")
	@Operation(summary = "Post Api", description = "This is a method for multiple manager registration")
	public ResponseEntity<List<Manager>> registerAll(@RequestBody List<ManagerRequest> managerRequest) {
		List<Manager> savedManager = managerService.registerAll(managerRequest);
		return new ResponseEntity<>(savedManager, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(summary = "Get Api", description = "This is a method for finding manager by there id")
	public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
		return managerService.getManagerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/email/{email}")
	@Operation(summary = "Get Api", description = "This is a method for finding manager by there email")
	public ResponseEntity<Manager> getManagerByEmail(@PathVariable String email) {
		return managerService.getManagerByEmail(email).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	// Endpoint to get total number of managers
	@GetMapping("/managers/count")
	public long getTotalManagersCount() {
		return managerService.countTotalManagers();
	}

	@GetMapping("/findAll")
	@Operation(summary = "Get Api", description = "This is a method for finding all managers")
	public ResponseEntity<List<Manager>> findAllManagers() {
		List<Manager> manager = managerService.findAlManagers();
		return ResponseEntity.ok(manager);
	}

	@GetMapping("/unpremium")
	@Operation(summary = "Get Api", description = "This is a method for finding managers who dont purches premium plan")
	public ResponseEntity<List<Manager>> getUnsubscribedManagers() {
		List<Manager> unsubscribedManagers = managerService.getUnPremiumedManagers();
		return ResponseEntity.ok(unsubscribedManagers);
	}

	@GetMapping("/premium")
	@Operation(summary = "Get Api", description = "This is a method for finding managers who purches premium plan")
	public ResponseEntity<List<Manager>> getSubscribedManagers() {
		List<Manager> subscribedManagers = managerService.getAllPremiumManagers();
		return ResponseEntity.ok(subscribedManagers);
	}

	@GetMapping("/premium/count")
	public ResponseEntity<Long> getPremiumManagerCount() {
		long count = managerService.getPremiumManagerCount();
		return ResponseEntity.ok(count);
	}

	@GetMapping("/active/count")
	public ResponseEntity<Long> getActiveManagerCount() {
		long count = managerService.countActiveManagers();
		return ResponseEntity.ok(count);
	}

	@PutMapping("/updatePremium/{managerId}")
	@Operation(summary = "Put Api", description = "This is a method for update a managers unpremium plan to premium plan")
	public ResponseEntity<Manager> updatePremium(@PathVariable Long managerId) {
		Optional<Manager> updatedManager = managerService.updatePremiumStatus(managerId);
		return updatedManager.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/assign/{projectId}")
	@Operation(summary = "Post Api", description = "This is a method for assigning a project to all the managers")
	public ResponseEntity<Manager> assignManagerToProject(@PathVariable Long projectId) {
		Manager assignedManager = managerService.assignManagerToProject(projectId);

		// Return the Manager entity directly in the response
		return ResponseEntity.ok(assignedManager);
	}

	@PutMapping("/assign-project")
	@Operation(summary = "Put Api", description = "This is a method for assigning a project to manager after client confirm the project")
	public ResponseEntity<Manager> assignProjectToManager(@RequestBody ManagerRequest managerRequest) {
		Manager updatedManager = managerService.updateAssignedProject(managerRequest);
		return ResponseEntity.ok(updatedManager);
	}

	@PostMapping("/{managerId}/accept-project/{projectId}")
	public ResponseEntity<String> acceptProject(@PathVariable Long managerId, @PathVariable Long projectId) {
		try {
			Manager manager = managerService.acceptProjectAndUpdateStatus(managerId, projectId);
			return ResponseEntity.ok("Project successfully accepted and status updated to 'work_in_progress'.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
		}
	}

}
