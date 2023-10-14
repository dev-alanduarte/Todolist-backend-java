package br.com.alanduarte.todolist.task;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alanduarte.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    
        UUID idUser = (UUID) request.getAttribute("idUser");
        taskModel.setIdUser(idUser);
        
        var currentDateTime = LocalDateTime.now();

        if (currentDateTime.isAfter(taskModel.getStartAt()) || currentDateTime.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(400).body("A data de inicio / data término => deve ser maior do que a data atual");
        }
        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(400)
            .body("A data de inicio deve ser menor que a data de término");
        }

        TaskModel task = this.taskRepository.saveAndFlush(taskModel);
        
        return ResponseEntity.status(200).body(task) ;

    }

    @GetMapping("/")
    public java.util.List<TaskModel> list(HttpServletRequest request) {
   
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        Optional<TaskModel> taskOptional = this.taskRepository.findById(id);
    
        if (!taskOptional.isPresent()) {
            return ResponseEntity.status(404).body("Tarefa não encontrada");
        }
    
        TaskModel task = taskOptional.get();
    
        var idUser = (UUID) request.getAttribute("idUser");
    
        if (!task.getIdUser().equals(idUser)) {
            return ResponseEntity.status(401).body("Você não tem permissão para alterar essa tarefa");
        }
    
        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdate = this.taskRepository.saveAndFlush(task);
        return ResponseEntity.status(200).body(taskUpdate);
    }
    

}
