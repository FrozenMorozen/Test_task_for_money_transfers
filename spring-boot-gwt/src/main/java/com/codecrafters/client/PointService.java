package com.codecrafters.client;


import com.codecrafters.client.entities.Point;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("points")
interface PointService extends RestService {

    @GET
    @Path("?text={text}")
    void getPoints(@PathParam("text") final String text, MethodCallback<List<Point>> callback);

    @PUT
    void addPoint(final Point point, final MethodCallback<Void> callback);
}
