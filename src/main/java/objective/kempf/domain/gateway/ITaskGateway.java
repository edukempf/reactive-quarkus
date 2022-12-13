package objective.kempf.domain.gateway;

import io.smallrye.mutiny.Uni;
import objective.kempf.domain.entity.Task;

import java.util.List;

public interface ITaskGateway {
    Uni<Task> save(final Task task);

    Uni<Task> getById(final Long id);

    Uni<List<Task>> getAll();
}
