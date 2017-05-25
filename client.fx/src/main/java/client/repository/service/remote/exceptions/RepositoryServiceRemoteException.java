package client.repository.service.remote.exceptions;

/**
 * Top exception level for repository service layer
 * Created by Bohumil Brázda on 25.5.2017.
 */
public class RepositoryServiceRemoteException extends RuntimeException {

    public RepositoryServiceRemoteException(String message, Throwable cause) {
        super(message, cause);
    }
}
