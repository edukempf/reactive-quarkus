package objective.kempf.app.rest;

import io.smallrye.mutiny.Uni;
import objective.kempf.app.dto.TaskRequest;
import objective.kempf.app.service.TaskService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tasks")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {
    @Inject
    private TaskService taskService;

    @POST
    public Uni<Response> save(final TaskRequest request) {
        return taskService.save(request)
                .onItem().transform(response -> Response.ok(response))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getById(@PathParam("id") Long id) {
        return taskService.getById(id)
                .onItem().transform(response -> Response.ok(response))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @GET
    public Uni<Response> getAll() {
        return taskService.getAll()
                .onItem().transform(response -> Response.ok(response))
                .onItem().transform(Response.ResponseBuilder::build);
    }
}