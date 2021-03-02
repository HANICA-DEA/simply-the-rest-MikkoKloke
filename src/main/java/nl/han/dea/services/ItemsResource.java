package nl.han.dea.services;

import nl.han.dea.services.dto.ItemDTO;

import javax.ejb.Singleton;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Parameter;
import java.util.List;

@Path("/items")
@Singleton
public class ItemsResource {

    private ItemService itemService;

    public ItemsResource() {
        this.itemService = new ItemService();
    }

    @DELETE
    @Path("/delete/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteItem(@PathParam("itemId") int id) {
        itemService.deleteItem(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }


    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addItem(ItemDTO item) {
        itemService.addItem(item);
        return Response
                .status(201)
                .build();
    }


    @GET
    @Path("/{itemId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemWithId(@PathParam("itemId") int id) {
        return Response
                .ok()
                .entity(itemService.getItem(id))
                .build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response itemsJson() {

        List<ItemDTO> listEntity = itemService.getAll();

        return Response
                .ok()
                .entity(listEntity)
                .build();
    }

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String itemsString() {
        return "bread, butter";
    }

}