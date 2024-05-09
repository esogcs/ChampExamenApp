module champqcsoft.org.champexamen_by_jose {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens champqcsoft.org.champexamen_by_jose to javafx.fxml;
    exports champqcsoft.org.champexamen_by_jose;
}