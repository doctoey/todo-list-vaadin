package org.vaadin.example.admin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
public class LoginView extends VerticalLayout {

    private TextField usernameField;
    private PasswordField passwordField;
    private Span errorMessage;

    public LoginView() {
        // Page title
        H1 title = new H1("Login");
        title.addClassName("login-title");

        // Username and Password fields
        usernameField = new TextField("Username");
        usernameField.setPlaceholder("Enter your username");
        passwordField = new PasswordField("Password");
        passwordField.setPlaceholder("Enter your password");

        // Error message (initially hidden)
        errorMessage = new Span("Invalid credentials");
        errorMessage.getStyle().set("color", "red").set("display", "none");

        // Login button
        Button loginButton = new Button("Login", e -> login());

        // Layout
        FormLayout formLayout = new FormLayout(usernameField, passwordField, errorMessage, loginButton);
        formLayout.setMaxWidth("300px");

        addClassName("login-view");
        addClassName(Lumo.DARK);

        add(title, formLayout);
    }

    private void login() {
        String username = usernameField.getValue();
        String password = passwordField.getValue();

        // Mock authentication (you can expand this to check with a real authentication system)
        if ("admin".equals(username) && "password".equals(password)) {
            VaadinSession.getCurrent().setAttribute("authenticatedUser", username);
            getUI().ifPresent(ui -> ui.navigate("admin/dashboard"));
        } else {
            errorMessage.setText("Invalid credentials");
            errorMessage.getStyle().set("display", "block");
            passwordField.clear();
        }
    }
}
