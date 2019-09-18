package ted.restapi.resources;

import ted.restapi.beans.CategoryBean;
import ted.restapi.dto.CategoryDTO;
import ted.restapi.dto.GeneralCategoryDTO;
import ted.restapi.util.Mapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Path("images")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ImageResource {

    @Inject private CategoryBean categoryBean;

    @GET
    @Path("categories")
    public Response categories(){
        List<GeneralCategoryDTO> list = categoryBean.getGeneralCategories().stream()
                .map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(list).build();
    }

    @GET
    @Path("subcategories/{id}")
    public Response subcategories(@PathParam("id") int id){
        List<CategoryDTO> list = categoryBean.getCategoriesByGeneralCategoryId(id).stream()
                .map(Mapper::toDTO).collect(Collectors.toList());
        return Response.ok(list).build();
    }

}
