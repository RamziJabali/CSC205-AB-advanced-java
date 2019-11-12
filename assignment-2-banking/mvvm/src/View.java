import java.util.Scanner;

/*
    View is the point of contact to the User for both input & output.
    It relays information to the user via information obtained from the ViewModel in the form of a ViewState object.
    It has no business logic what so ever.
 */
public class View {

    private final ViewListener listener;
    private Scanner inputReader = new Scanner(System.in);

    public View(ViewListener listener) {
        this.listener = listener;
    }

    public void setNewViewState(ViewState newViewState) {
        if (newViewState.displayOutput) {
            System.out.println(newViewState.output);
        }
        if (newViewState.askForInput) {
            listener.enteredInput(inputReader.next());
        }
    }
}
