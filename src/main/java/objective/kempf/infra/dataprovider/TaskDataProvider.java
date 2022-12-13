package objective.kempf.infra.dataprovider;

import io.smallrye.mutiny.Uni;
import objective.kempf.domain.entity.Task;
import objective.kempf.domain.gateway.ITaskGateway;
import objective.kempf.infra.db.model.PanacheTask;
import objective.kempf.infra.db.repository.ITaskRepository;
import objective.kempf.infra.db.repository.TaskRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ApplicationScoped
public final class TaskDataProvider implements ITaskGateway {
    private ITaskRepository repository;

    public TaskDataProvider() {
        repository = new TaskRepository();
    }

    @Override
    public Uni<Task> save(Task task) {
        return repository.save(Mapper.taskToPanacheTask(task))
                .onItem()
                .ifNotNull()
                .transform(jpa -> Mapper.panacheTaskToTask(jpa));
    }

    @Override
    public Uni<Task> getById(Long id) {
        return repository.getById(id)
                .onItem()
                .ifNotNull()
                .transform(jpa -> Mapper.panacheTaskToTask(jpa));
    }

    @Override
    public Uni<List<Task>> getAll() {
        return repository.getAll()
                .onItem()
                .ifNotNull()
                .transform(jpa -> Mapper.panacheTaskToTask(jpa));
    }

    static class Mapper {
        public static PanacheTask taskToPanacheTask(final Task task) {
            Objects.nonNull(task);

            PanacheTask panacheTask = new PanacheTask();
            panacheTask.setCompleted(task.isCompleted());
            panacheTask.setId(task.getId());
            panacheTask.setName(task.getName());
            panacheTask.setCreatedAt(task.getCreatedAt());
            panacheTask.setUpdatedAt(task.getCreatedAt());

            return panacheTask;
        }

        public static Task panacheTaskToTask(final PanacheTask panacheTask) {
            Objects.nonNull(panacheTask);

            Task task = new Task();
            task.setCompleted(panacheTask.isCompleted());
            task.setId(panacheTask.getId());
            task.setName(panacheTask.getName());
            task.setCreatedAt(panacheTask.getCreatedAt());
            task.setUpdatedAt(panacheTask.getCreatedAt());

            return task;
        }

        public static List<Task> panacheTaskToTask(final List<PanacheTask> tasks){
            Objects.nonNull(tasks);

            return tasks.stream()
                    .map(jpa -> panacheTaskToTask(jpa))
                    .collect(Collectors.toList());
        }
    }
}
