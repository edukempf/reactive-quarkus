package objective.kempf.infra.db.repository;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.panache.common.Sort;
import io.smallrye.mutiny.Uni;
import objective.kempf.infra.db.model.PanacheTask;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.List;

@ApplicationScoped
public final class TaskRepository implements ITaskRepository {
    @Override
    public Uni<PanacheTask> save(PanacheTask panacheTask) {
        return Panache
                .withTransaction(panacheTask::persist)
                .replaceWith(panacheTask)
                .ifNoItem()
                .after(Duration.ofMillis(10000))
                .fail()
                .onFailure()
                .transform(t -> new IllegalStateException(t));
    }

    @Override
    public Uni<List<PanacheTask>> getAll() {
        return listAll(Sort.by("createdAt"))
                .ifNoItem()
                .after(Duration.ofMillis(10000))
                .fail()
                .onFailure()
                .transform(t -> new IllegalStateException(t));
    }

    @Override
    public Uni<PanacheTask> getById(Long id) {
        return findById(id);
    }
}
