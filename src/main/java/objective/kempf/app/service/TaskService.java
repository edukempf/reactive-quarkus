package objective.kempf.app.service;

import io.smallrye.mutiny.Uni;
import objective.kempf.app.dto.TaskRequest;
import objective.kempf.app.dto.TaskResponse;

import java.util.List;

public interface TaskService {
    Uni<TaskResponse> save(final TaskRequest request);

    Uni<TaskResponse> getById(final Long id);

    Uni<List<TaskResponse>> getAll();
}
