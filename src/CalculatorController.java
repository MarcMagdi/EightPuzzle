import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * CS 221 : Object Oriented Programming.
 * Assignment 1 : Simple Calculator with GUI
 * Calculator Controller Class
 * @author Bishoy Nader
 * Friday 14 October 2016
 */
public class CalculatorController {

    /**
     * Result Output Label.
     */
    @FXML
    private Text output;

    /**
     * Expression Output Label.
     */
    @FXML
    private Text expressionOutput;

    /**
     * Instance of Model Class to perform operations.
     */
//    private CalculatorModel calculatorModel = new CalculatorModel();

    /**
     * Flag to indicate whether user has typed something or not.
     */
    private Boolean userIsInMiddleOfTyping = false;

    /**
     * Flag to indicate whether user has typed a decimal point or not.
     */
    private Boolean decimalPointWritten = false;

    /**
     * String holding the current expression.
     */
    private String expression = "";

    /**
     * Handling Clicking Numbers or decimal point.
     * @param event clicking a number or decimal point.
     */
    @FXML
    private void processNumpad(final ActionEvent event) {
    String operand = ((Button) event.getSource()).getText();
        if (decimalPointWritten && operand.equals(".")) {
            return;
        } else if (operand.equals(".") && !userIsInMiddleOfTyping) {
            return;
        } else if (operand.equals(".")) {
            decimalPointWritten = true;
        }
        if (!userIsInMiddleOfTyping) {
            output.setText("");
            userIsInMiddleOfTyping = true;
        }
        expression += operand;
        output.setText(output.getText() + operand);
        expressionOutput.setText(expression);
    }

    /**
     * Handling Clicking operations.
     * @param event clicking an operation.
     */
    @FXML
    private void processOperator(final ActionEvent event) {
        String operation = ((Button) event.getSource()).getText();
        switch (operation) {
        case "=":
            try {
//                calculatorModel.input(expression);
                } catch (Exception exception) {
                    expression = "";
                    expressionOutput.setText("Invalid Expression");
                    userIsInMiddleOfTyping = false;
                    return;
                }
            updateLabels();
            userIsInMiddleOfTyping = false;
            expression = "";
            break;
        default:
            if (!userIsInMiddleOfTyping) {
                return;
            } else {
                expression += operation;
                userIsInMiddleOfTyping = false;
            }
            expressionOutput.setText(expression);
            break;
        }
    }

    /**
     * Updates the text of output label.
     */
    private void updateLabels() {
        expressionOutput.setText(expression);
//        String res = calculatorModel.getResult();
//        if (res.equals("NaN")) {
//            output.setText("Undefined");
//        } else {
//            output.setText(calculatorModel.getResult());
//        }
    }

    /**
     * Handling Clicking save or load.
     * @param event clicking save or load.
     */
    @FXML
    private void processHistory(final ActionEvent event) {
        String operation = ((Button) event.getSource()).getText();
        String historyResult = "";
        switch (operation) {
        case "Previous":
//            historyResult = calculatorModel.prev();
            break;
        case "Next":
//            historyResult = calculatorModel.next();
            break;
        default:
            break;
        }
        if (historyResult != null) {
//            output.setText(calculatorModel.getResult());
            updateLabels();
            expressionOutput.setText(historyResult);
        }
    }

    /**
     * Handling Clicking previous or next.
     * @param event clicking previous or next.
     */
    @FXML
    private void processSaveAndLoad(final ActionEvent event) {
        String operation = ((Button) event.getSource()).getText();
        switch (operation) {
        case "Save":
//            calculatorModel.save();
            break;
        case "Load":
//            calculatorModel.load();
//            if (calculatorModel.current() != null) {
                updateLabels();
//                expressionOutput.setText(calculatorModel.current());
//            }
            break;
        default:
            break;
        }
    }
}
