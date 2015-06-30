package util.exception;

/**
 * Created by Wojciech on 2015-06-25.
 */
public class PortfolioWithoutParentCannotBeMemberOfCategoryException extends Throwable {
    public PortfolioWithoutParentCannotBeMemberOfCategoryException(String s) {
        super(s);
    }
}
