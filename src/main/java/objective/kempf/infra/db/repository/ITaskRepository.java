package objective.kempf.infra.db.repository;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import objective.kempf.infra.db.model.PanacheTask;

import java.util.List;

public interface ITaskRepository extends PanacheRepository<PanacheTask> {
    Uni<PanacheTask> save(final PanacheTask panacheTask);

    Uni<List<PanacheTask>> getAll();

    Uni<PanacheTask> getById(final Long id);
}
