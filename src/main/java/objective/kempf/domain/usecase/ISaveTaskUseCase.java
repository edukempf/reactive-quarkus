package objective.kempf.domain.usecase;

import io.smallrye.mutiny.Uni;
import objective.kempf.domain.entity.Task;

public interface ISaveTaskUseCase {
    Uni<Task> save(Task task);
}
