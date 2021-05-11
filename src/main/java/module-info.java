module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.bank to javafx.fxml;
    exports org.bank;
}