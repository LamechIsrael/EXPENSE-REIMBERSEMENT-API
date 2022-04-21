package dev.israel.exceptions;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(int id){
        super("The resource with the id " + id + " was not found");
    }
}
