package objective.kempf.domain.usecase;

import io.smallrye.mutiny.Uni;
import objective.kempf.domain.entity.Task;

import java.util.List;

public interface IFindTaskUseCase {
    Uni<Task> getById(final Long id);

    Uni<List<Task>> getAll();
}
