package ua.com.gfalcon.gesem.exeptions;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
public class HasChildrenEntityException extends Exception {
    public HasChildrenEntityException(String message) {
        super(message);
    }
}
