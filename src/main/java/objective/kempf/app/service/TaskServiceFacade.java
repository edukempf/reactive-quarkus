package objective.kempf.app.service;

import io.smallrye.mutiny.Uni;
import objective.kempf.app.dto.TaskRequest;
import objective.kempf.app.dto.TaskResponse;
import objective.kempf.domain.entity.Task;
import objective.kempf.domain.usecase.IFindTaskUseCase;
import objective.kempf.domain.usecase.ISaveTaskUseCase;
import objective.kempf.domain.usecase.impl.FindTaseUseCase;
import objective.kempf.domain.usecase.impl.SaveTaskUseCase;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public final class TaskServiceFacade implements TaskService {
    private ISaveTaskUseCase saveTaskUseCase;
    private IFindTaskUseCase findTaskUseCase;

    public TaskServiceFacade() {
        this.saveTaskUseCase = new SaveTaskUseCase();
        this.findTaskUseCase = new FindTaseUseCase();
    }

    @Override
    public Uni<TaskResponse> save(TaskRequest request) {
        return saveTaskUseCase.save(Mapper.requestToTask(request))
                .onItem()
                .ifNotNull()
                .transform(entity -> Mapper.taskToResponse(entity));
    }

    @Override
    public Uni<TaskResponse> getById(Long id) {
        return findTaskUseCase.getById(id)
                .onItem()
                .ifNotNull()
                .transform(entity -> Mapper.taskToResponse(entity));
    }

    @Override
    public Uni<List<TaskResponse>> getAll() {
        return findTaskUseCase.getAll()
                .onItem()
                .ifNotNull()
                .transform(entity -> Mapper.taskToResponse(entity));
    }

    static class Mapper {
        public static Task requestToTask(final TaskRequest request) {
            Objects.nonNull(request);

            var task = new Task();
            task.setCompleted(request.isCompleted());
            task.setName(request.getName());

            return task;
        }

        public static TaskResponse taskToResponse(final Task task) {
            var response = new TaskResponse();
            response.setCompleted(task.isCompleted());
            response.setId(task.getId());
            response.setName(task.getName());
            response.setCreatedAt(task.getCreatedAt());
            response.setUpdatedAt(task.getUpdatedAt());

            return response;
        }

        public static List<TaskResponse> taskToResponse(final List<Task> tasks){
            Objects.nonNull(tasks);

            return tasks.stream()
                    .map(entity -> taskToResponse(entity))
                    .collect(Collectors.toList());
        }
    }
}
