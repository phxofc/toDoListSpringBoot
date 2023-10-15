package br.com.pedrobarbosa.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedrobarbosa.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    // recuperando o id do user
    var idUser = request.getAttribute("idUser");

    // VALIDAR DATA
    var currentDate = LocalDateTime.now();
    if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A DATA DE INICIO DEVE SER MAIOR QUE A DATA ATUAL");
    }

    if (currentDate.isAfter(taskModel.getEndAt())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("A DATA DE INICIO DEVE SER MENOR QUE A DATA DE TERMINO");
    }

    taskModel.setIdUser((UUID) idUser);
    var task = this.taskRepository.save(taskModel);

    return ResponseEntity.status(HttpStatus.OK).body(task);
  }

  // listar todos os taks
  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var idUser = request.getAttribute("idUser");
    var tasks = this.taskRepository.findByIdUser((UUID) idUser);
    return tasks;
  }

  // update

  @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){
     

      var task = this.taskRepository.findById(id).orElse(null);
      if(task == null){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("tarefa nao existe");
      }

      var idUser = request.getAttribute("idUser");
      if(!task.getIdUser().equals(idUser)){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USUARIO NAO TEM PERMISSAO PARA ALTERAR ESSA TAREFA");
      }

      Utils.copyNonNullProperties(taskModel, task);


      var taskUpdated = this.taskRepository.save(task);
      
      return ResponseEntity.ok().body(taskUpdated);

    }

  // update parcial

}
