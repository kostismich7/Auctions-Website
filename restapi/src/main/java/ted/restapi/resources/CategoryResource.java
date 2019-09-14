package ted.restapi.resources;

import ted.restapi.beans.CategoryBean;
import ted.restapi.beans.Session;
import ted.restapi.dto.CategoryDTO;
import ted.restapi.dto.GeneralCategoryDTO;
import ted.restapi.dto.ItemDTO;
import ted.restapi.persistence.entities.Item;
import ted.restapi.util.Mapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("categories")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryResource {

    @Inject private Session session;
    @Inject private CategoryBean categoryBean;

    @GET
    public Response getAll(){
        List<GeneralCategoryDTO> list = categoryBean.getGeneralCategories().stream()
                .map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(list).build();
    }

    @GET
    @Path("{id}")
    public Response getCategoriesByGeneralCategoryId(@PathParam("id") int id){
        List<CategoryDTO> list = categoryBean.getCategoriesByGeneralCategoryId(id).stream()
                .map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(list).build();
    }

    @GET
    @Path("items/{id}")
    public Response getItemsByCategoryId(@PathParam("id") int id){
        List<ItemDTO> itemsDTO = new ArrayList<>();
        List<Item> items = categoryBean.getItemsByCategoryId(id);
        for (Item item : items) {
            itemsDTO.add(Mapper.toDTO(item));
        }
        return Response.ok(itemsDTO).build();
    }


}
